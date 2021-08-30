package com.epst.document.portal.application.controller

import com.epst.document.portal.application.form.SignupForm
import com.epst.document.portal.common.util.Constant
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class AuthenticationController {

    private val logger = LoggerFactory.getLogger(AuthenticationController::class.java)

    @GetMapping("/login")
    fun login(model : Model) : String{
        logger.info("requesting signup page ...")

        return "auth/login"
    }

    @GetMapping("/register")
    fun signup(model : Model) : String{
        logger.info("requesting signup page ...")

        //inject empty signup object in the model
        model.addAttribute(Constant.SIGNUP_FORM, SignupForm())

        return "auth/register"
    }

    @PostMapping("/register")
    fun signup(@ModelAttribute(Constant.SIGNUP_FORM) signupForm : SignupForm) : String {

        logger.info("signup a new user, catching post data ...")

        return "redirect:/login"
    }
}
