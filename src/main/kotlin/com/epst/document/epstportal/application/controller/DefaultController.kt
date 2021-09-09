package com.epst.document.epstportal.application.controller

import com.epst.document.epstportal.application.form.FindOutCertificateForm
import com.epst.document.epstportal.common.util.Constant
import com.epst.document.epstportal.core.service.QRCodeService
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import java.util.*

@Controller
class DefaultController(private val qrCodeService: QRCodeService) {

    @GetMapping("")
    fun home(model : Model) :String{
//
//        val imgB64 = qrCodeService.generateQRCode("http://gtc.osigrouprdc.com/contact-us")
//        model.addAttribute("imgData", imgB64)

        model.addAttribute(Constant.FIND_OUT_CERTIFICATE_FORM, FindOutCertificateForm())
        return "home"
    }

    @GetMapping("/faq")
    fun faq(model : Model) : String{
        model.addAttribute(Constant.PAGE_TITLE, Constant.PAGE_TITLE_FAQ)
        return "info/faq"
    }

    @GetMapping("/contact")
    fun contact(model : Model) : String{
        model.addAttribute(Constant.PAGE_TITLE, Constant.PAGE_TITLE_CONTACT)
        return "info/contact"
    }
}
