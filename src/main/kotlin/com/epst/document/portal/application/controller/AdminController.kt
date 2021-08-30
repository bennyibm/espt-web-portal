package com.epst.document.portal.application.controller

import com.epst.document.portal.common.util.Constant.Companion.CREDENTIAL
import com.epst.document.portal.common.util.Constant.Companion.USER
import com.epst.document.portal.common.util.Credential
import com.epst.document.portal.common.util.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttribute
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@RequestMapping("/admin")
@SessionAttributes(CREDENTIAL, USER)
class AdminController {

    private val logger = LoggerFactory.getLogger(AdminController::class.java)

    @GetMapping("/dashboard")
//    fun dashboard(model : Model, @SessionAttribute(CREDENTIAL) credential : Credential, @SessionAttribute(USER) user : User) : String{
    fun dashboard(model : Model) : String{

        logger.info("requesting admin-dashboard page ...")

        return "admin/dashboard"
    }

    @GetMapping("/transactions")
//    fun transactions(model : Model, @SessionAttribute(CREDENTIAL) credential : Credential, @SessionAttribute(USER) user : User) : String{
    fun transactions(model : Model) : String{
        logger.info("requesting admin-transactions history page ...")

        return "admin/transactions"
    }
    @GetMapping("/downloads")
//    fun downloads(model : Model, @SessionAttribute(CREDENTIAL) credential : Credential, @SessionAttribute(USER) user : User) : String{
    fun downloads(model : Model) : String{
        logger.info("requesting admin-downloads history page ...")

        return "admin/downloads"
    }
}
