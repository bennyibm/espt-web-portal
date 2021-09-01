package com.epst.document.portal.core.service

import com.epst.document.portal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.portal.application.dto.AuthenticateDocumentResponse
import com.epst.document.portal.application.form.FindOutCertificateForm
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat

interface CheckingService {

    fun checkDocument(authenticateDocumentRequestDTO : AuthenticateDocumentRequestDTO) : AuthenticateDocumentResponse
    fun checkCertificate(findOutCertificateForm: FindOutCertificateForm) : ResponseModel<ResultExetat>
}
