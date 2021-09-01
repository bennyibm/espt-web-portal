package com.epst.document.portal.core

class ResponseModel<T> {
    var error = false
    var errorMessage: String? = null
    var status = 0
    var content: T? = null
//
//    constructor(error: Boolean, errorMessage: String?, status: Int, content: T?) {
//        this.error = error
//        this.errorMessage = errorMessage
//        this.status = status
//        this.content = content
//    }
//
//    fun constructor() {
//        error = false
//        errorMessage = ""
//        status = 0
//        content = null
//    }
}
