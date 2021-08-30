package com.epst.document.portal.infra.externalService

import com.epst.document.portal.common.util.User
import com.epst.document.portal.core.domain.user.UserAuthenticate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.RetryCallback
import org.springframework.retry.support.RetryTemplate
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.lang.Exception
import java.net.URI

@Component
class AuthenticationExternalService(
        private val restTemplate: RestTemplate,
        private val retryTemplate: RetryTemplate,
        @Value("\${service.authentication.base.url}") private val authenticationUrl: String,
        @Value("\${service.authentication.info.url}") private val userInfoUrl: String,
        @Value("\${service.authentication.reset.url}") private val resetPasswordUrl: String,
        @Value("\${service.authentication.update.password.url}") private val updatePasswordUrl: String
) {

    val logger: Logger = LoggerFactory.getLogger(AuthenticationExternalService::class.java)

    fun create(user: User) {
        logger.info("out create user account ${user.email}")
        restTemplate.postForObject(authenticationUrl,user, Void::class.java)
    }

    fun authenticate(authentication: Authentication?): String?{
        logger.info("out authenticate user")
        val userAuth = UserAuthenticate(authentication!!.name, authentication.credentials.toString())
        return retryTemplate.execute(RetryCallback<String, Exception>{
            restTemplate.postForObject(authenticationUrl.plus("/authenticate"),userAuth,String::class.java)
        })
    }

    fun getUserInfo(token: String): User?{
        val url: URI? = URI(String.format(userInfoUrl,token))
        logger.info("out get user infos")
        return retryTemplate.execute(RetryCallback<User, Exception>{restTemplate.getForObject(url!!, User::class.java)})
    }


}
