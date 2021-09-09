package com.epst.document.epstportal.application.dto

import com.epst.document.epstportal.core.AuthenticateDocumentResponseContent

class AuthenticateDocumentResponse {
    var error : Boolean? = null
    var errorMessage : String? = null
    var status : String? = null
    var content : AuthenticateDocumentResponseContent? = null

    constructor(){
        error = false
        errorMessage = "nothing oh!"
        status = "found"
        content = AuthenticateDocumentResponseContent()
    }
}
