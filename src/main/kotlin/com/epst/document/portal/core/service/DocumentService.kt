package com.epst.document.portal.core.service

import com.epst.document.portal.core.Document
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat

interface DocumentService {
    fun save(result : ResultExetat, type : String) : ResponseModel<Document?>
    fun check(code : String) : ResponseModel<Document?>
}
