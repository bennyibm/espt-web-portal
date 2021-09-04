package com.epst.document.portal.application.controller

import com.epst.document.portal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.portal.application.dto.AuthenticateDocumentResponse
import com.epst.document.portal.application.form.FindOutCertificateForm
import com.epst.document.portal.common.util.Constant
import com.epst.document.portal.core.service.CheckingService
import com.epst.document.portal.core.service.QRCodeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class CheckingController(private val checkingService: CheckingService, private val qrCodeService: QRCodeService) {

    @GetMapping("/authenticate-document")
    fun authenticateDocument() : String{

        return "/features/auth"
    }

    @GetMapping("/find-out-certificate")
    fun findOutDocument() : String{

        return "features/verification"
    }

    @PostMapping("/authenticate-document")
    @ResponseBody
    fun authenticateDocument(@RequestBody authenticateDocumentRequestDTO : AuthenticateDocumentRequestDTO) : AuthenticateDocumentResponse{

        return AuthenticateDocumentResponse()
    }

    @PostMapping("/find-out-certificate")
    @ResponseBody
    fun findOutCertificate(model : Model, @ModelAttribute(Constant.FIND_OUT_CERTIFICATE_FORM) findOutCertificateForm: FindOutCertificateForm) : String{
        val response = checkingService.checkCertificate(findOutCertificateForm)

        if(!response.error){
            val imgString = qrCodeService.generateQRCode("192.168.1.103:8085/authenticate-document?ref=${response.content?.id}" )
            model.addAttribute(Constant.QR_CODE, imgString)
        }

        model.addAttribute(Constant.RESPONSE_MODEL, response)
        return "certificate"
    }

}
