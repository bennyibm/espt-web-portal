package com.epst.document.epstportal.core.service.manager


import com.epst.document.epstportal.common.util.User
import com.epst.document.epstportal.core.service.AuthenticationService
import com.epst.document.epstportal.infra.externalService.AuthenticationExternalService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthenticationManager(private val authenticationExternalService: AuthenticationExternalService) : AuthenticationService {
    private val logger: Logger = LoggerFactory.getLogger(AuthenticationManager::class.java)
    companion object {
        const val PROFILE = "opcc"
    }

    override fun getUserInfo(token: String): User {
        return authenticationExternalService.getUserInfo(token)?:User()
    }

    override fun createUser(user: User) {

        try {
            logger.info("auth manager : out authenticate...")
            authenticationExternalService.create(user)
        }
        catch (exception: Exception) {
            logger.warn(exception.message)
        }
    }

    override fun authenticate(authentication: Authentication?): String? {
        return try {
            authenticationExternalService.authenticate(authentication)
        } catch (ex : Exception) {

            null
        }
    }



}
