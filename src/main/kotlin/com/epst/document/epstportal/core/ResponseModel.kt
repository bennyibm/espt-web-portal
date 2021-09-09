package com.epst.document.epstportal.core

class ResponseModel<T> {
    var error = false
    var errorMessage: String? = null
    var status = 0
    var content: T? = null
}
