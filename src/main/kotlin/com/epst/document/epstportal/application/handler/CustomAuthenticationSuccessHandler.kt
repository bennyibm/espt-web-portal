package com.epst.document.epstportal.application.handler

import com.epst.document.epstportal.common.util.Constant.Companion.CREDENTIAL
import com.epst.document.epstportal.core.service.AuthenticationService
import com.epst.document.epstportal.common.util.Credential
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler(private val authenticationService: AuthenticationService): SimpleUrlAuthenticationSuccessHandler() {

    private val requestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         authentication: Authentication){
        setUseReferer(true)
        val savedRequest = requestCache.getRequest(request,response)

        val token = (authentication.principal as com.epst.document.epstportal.common.util.User).email
        val userInfo = authenticationService.getUserInfo(token!!)
        val credential = Credential(token, userInfo.access!!)


        request.session.setAttribute(CREDENTIAL, credential)

        logger.info("user ${userInfo.email} authenticated successfully")

        try {

            if(savedRequest == null){
                response.sendRedirect("/my-profile")
                super.onAuthenticationSuccess(request, response, authentication)
                return
            }

            if(isAlwaysUseDefaultTargetUrl || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))){
                requestCache.removeRequest(request,response)
                super.onAuthenticationSuccess(request, response, authentication)
                return
            }

            clearAuthenticationAttributes(request)
            val targetUrl: String = if (savedRequest.redirectUrl.contains("sw.js"))  "/my-profile" else savedRequest.redirectUrl

            val url: String =   if(targetUrl.endsWith("/error"))
                                    targetUrl.replace("/error", "/my-profile")
                                else targetUrl

            redirectStrategy.sendRedirect(request,response,url)

        }
        catch (e: Exception) {
            logger.error(e.message)
            redirectStrategy.sendRedirect(request,response, "/?error=true")
        }
    }
}
