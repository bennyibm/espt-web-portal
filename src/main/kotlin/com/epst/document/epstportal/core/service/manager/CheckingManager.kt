package com.epst.document.epstportal.core.service.manager

import com.epst.document.epstportal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.epstportal.application.dto.AuthenticateDocumentResponse
import com.epst.document.epstportal.application.form.FindOutCertificateForm
import com.epst.document.epstportal.core.Document
import com.epst.document.epstportal.core.ResponseModel
import com.epst.document.epstportal.core.ResultExetat
import com.epst.document.epstportal.core.service.CheckingService
import com.epst.document.epstportal.infra.externalService.CheckingExternalService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CheckingManager(private val checkingExternalService : CheckingExternalService) : CheckingService {

    private val logger = LoggerFactory.getLogger(CheckingManager::class.java)
    override fun checkDocument(findOutCertificateForm: FindOutCertificateForm): ResponseModel<Document> {
        val response =  checkingExternalService.checkDocument(findOutCertificateForm)

        if(response.content != null) {
            val linkedHashMap = response.content as LinkedHashMap<String, String>
            response.content = Document.mapToRealDocument(linkedHashMap)
        }

        return response
    }

    override fun checkCertificate(findOutCertificateForm: FindOutCertificateForm): ResponseModel<ResultExetat> {
        val response = checkingExternalService.checkCertificate(findOutCertificateForm)



        if(response.content != null) {
            val linkedHashMap = response.content as LinkedHashMap<String, String>
            response.content = ResultExetat.mapToRealResultExetat(linkedHashMap)
        }


        return response
    }

}
