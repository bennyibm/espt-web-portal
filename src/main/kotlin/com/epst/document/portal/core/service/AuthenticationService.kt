package com.epst.document.portal.core.service

import com.epst.document.portal.common.util.User
import org.springframework.security.core.Authentication

interface AuthenticationService {

    fun createUser(user: User)

    fun authenticate(authentication: Authentication?): String?

    fun getUserInfo(token: String): User

}
