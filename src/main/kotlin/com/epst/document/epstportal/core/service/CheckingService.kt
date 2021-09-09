package com.epst.document.epstportal.core.service

import com.epst.document.epstportal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.epstportal.application.dto.AuthenticateDocumentResponse
import com.epst.document.epstportal.application.form.FindOutCertificateForm
import com.epst.document.epstportal.core.Document
import com.epst.document.epstportal.core.ResponseModel
import com.epst.document.epstportal.core.ResultExetat

interface CheckingService {

    fun checkDocument(findOutCertificateForm: FindOutCertificateForm) : ResponseModel<Document>
    fun checkCertificate(findOutCertificateForm: FindOutCertificateForm) : ResponseModel<ResultExetat>
}
