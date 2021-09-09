package com.epst.document.epstportal.core.service

import com.epst.document.epstportal.core.Document
import com.epst.document.epstportal.core.ResponseModel
import com.epst.document.epstportal.core.ResultExetat

interface DocumentService {
    fun save(result : ResultExetat, type : String) : ResponseModel<Document?>
    fun check(code : String) : ResponseModel<Document?>
}
