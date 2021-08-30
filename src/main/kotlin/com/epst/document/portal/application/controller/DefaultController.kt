package com.epst.document.portal.application.controller

import com.epst.document.portal.core.service.QRCodeService
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

        return "home"
    }


    @GetMapping("/faq")
    fun faq() : String{
        return "info/faq"
    }

    @GetMapping("/contact")
    fun contact(model : Model) : String{
        return "info/contact"
    }
}
