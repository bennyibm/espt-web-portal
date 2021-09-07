package com.epst.document.portal.application.controller

import com.epst.document.portal.application.form.FindOutCertificateForm
import com.epst.document.portal.common.util.Constant
import com.epst.document.portal.common.util.Constant.Companion.CURRENT_DATE
import com.epst.document.portal.common.util.Constant.Companion.PAGE_SLUG
import com.epst.document.portal.common.util.Constant.Companion.PAGE_SLUG_AUTHENTICATE_DOCUMENT
import com.epst.document.portal.common.util.Constant.Companion.PAGE_SLUG_DOWNLOAD_CERTIFICATE
import com.epst.document.portal.common.util.Constant.Companion.PAGE_SLUG_VERIFY_RESULT
import com.epst.document.portal.common.util.Constant.Companion.PAGE_TITLE
import com.epst.document.portal.common.util.Constant.Companion.PAGE_TITLE_AUTHENTICATE_DOCUMENT
import com.epst.document.portal.common.util.Constant.Companion.PAGE_TITLE_DOWNLOAD_CERTIFICATE
import com.epst.document.portal.common.util.Constant.Companion.PAGE_TITLE_VERIFY_RESULT
import com.epst.document.portal.common.util.Constant.Companion.QR_CODE
import com.epst.document.portal.common.util.Constant.Companion.RESULT_EXETAT
import com.epst.document.portal.core.Document
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat
import com.epst.document.portal.core.service.CheckingService
import com.epst.document.portal.core.service.DocumentService
import com.epst.document.portal.core.service.QRCodeService
import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.WebContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.time.LocalDate
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class CheckingController(private val checkingService: CheckingService,
                         private val qrCodeService: QRCodeService,
                         private val documentService : DocumentService,
                         private val servletContext: ServletContext, private val templateEngine : TemplateEngine
) {

    @GetMapping("/download-document-pdf")
     fun getPDF(@SessionAttribute(RESULT_EXETAT) result : ResultExetat, request: HttpServletRequest?, response: HttpServletResponse?): ResponseEntity<*>? {


        val imgQrCode = qrCodeService.generateQRCode("icashbs.com")

        val context = WebContext(request, response, servletContext)

        context.setVariable(RESULT_EXETAT, result)
        context.setVariable(QR_CODE, imgQrCode)
        context.setVariable(CURRENT_DATE, LocalDate.now())

        val document : String = templateEngine.process("features/certificate", context)

        val target = ByteArrayOutputStream()
        val converterProperties = ConverterProperties()
        converterProperties.baseUri = "http://localhost:8080"

        HtmlConverter.convertToPdf(document, target, converterProperties)

        val bytes: ByteArray = target.toByteArray()

        val filename = result.hashCode()

        documentService.save(result, Constant.DEFAULT_DOCUMENT_TYPE)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$filename.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(bytes)
    }


    @GetMapping("/authenticate-document")
    fun authenticateDocument(model : Model) : String{


        model.addAttribute(PAGE_TITLE, PAGE_TITLE_AUTHENTICATE_DOCUMENT)
        model.addAttribute(PAGE_SLUG, PAGE_SLUG_AUTHENTICATE_DOCUMENT)

        return "features/auth"
    }

    @GetMapping("/download-certificate")
    fun downloadCertificate(model : Model, @SessionAttribute(RESULT_EXETAT) resultExetat: ResultExetat) : String{

        val imgQrCode = qrCodeService.generateQRCode("http://192.168.43.171:8085/authenticate-document?code=${resultExetat.id}")



        model.addAttribute(PAGE_TITLE, PAGE_TITLE_DOWNLOAD_CERTIFICATE)
        model.addAttribute(PAGE_SLUG, PAGE_SLUG_DOWNLOAD_CERTIFICATE)

        model.addAttribute(RESULT_EXETAT, resultExetat)
        model.addAttribute(CURRENT_DATE, LocalDate.now())
        model.addAttribute(QR_CODE, imgQrCode)
        return "features/certificate"
    }

    @GetMapping("/find-out-certificate")
    fun findOutDocument(model : Model) : String{


        model.addAttribute(PAGE_TITLE, PAGE_TITLE_VERIFY_RESULT)
        model.addAttribute(PAGE_SLUG, PAGE_SLUG_VERIFY_RESULT)

        return "features/verification2"
    }

    @PostMapping("/authenticate-document")
    @ResponseBody
    fun authenticateDocument(request : HttpServletRequest, @RequestBody findOutCertificateForm: FindOutCertificateForm) : ResponseModel<Document>{
        val response =  checkingService.checkDocument(findOutCertificateForm)

        if(!response.error){
            request.session.setAttribute(Constant.RESULT_EXETAT, response.content)
        }

        return response
    }

    @PostMapping("/find-out-certificate")
    @ResponseBody
    fun findOutCertificate(request : HttpServletRequest, @RequestBody findOutCertificateForm: FindOutCertificateForm): ResponseModel<ResultExetat> {

        val response = checkingService.checkCertificate(findOutCertificateForm)

        if(!response.error){
            request.session.setAttribute(Constant.RESULT_EXETAT, response.content)
        }

        return response
    }

//
//    @ModelAttribute(Constant.PAGE_TITLE)
//    fun getPageTitle(request: HttpServletRequest) : String{
//        return ""
//    }
}
