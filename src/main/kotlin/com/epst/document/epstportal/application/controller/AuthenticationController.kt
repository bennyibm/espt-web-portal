package com.epst.document.epstportal.application.controller

import com.epst.document.epstportal.application.form.SignupForm
import com.epst.document.epstportal.common.util.Constant
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_SLUG
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_SLUG_LOGIN
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_SLUG_REGISTER
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_TITLE
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_TITLE_LOGIN
import com.epst.document.epstportal.common.util.Constant.Companion.PAGE_TITLE_REGISTER
import com.epst.document.epstportal.common.util.Constant.Companion.SIGNUP_FORM
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

        model.addAttribute(PAGE_TITLE, PAGE_TITLE_LOGIN)
        model.addAttribute(PAGE_SLUG, PAGE_SLUG_LOGIN)
        return "auth/login"
    }

    @GetMapping("/register")
    fun signup(model : Model) : String{
        logger.info("requesting signup page ...")

        model.addAttribute(PAGE_TITLE, PAGE_TITLE_REGISTER)
        model.addAttribute(PAGE_SLUG, PAGE_SLUG_REGISTER)
        //inject empty signup object in the model
        model.addAttribute(SIGNUP_FORM, SignupForm())

        return "auth/signup"
    }

    @PostMapping("/register")
    fun signup(@ModelAttribute(SIGNUP_FORM) signupForm : SignupForm) : String {

        logger.info("signup a new user, catching post data ...")

        return "redirect:/login"
    }
}
