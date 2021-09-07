package com.epst.document.portal.core.service.manager

import com.epst.document.portal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.portal.application.dto.AuthenticateDocumentResponse
import com.epst.document.portal.application.form.FindOutCertificateForm
import com.epst.document.portal.core.Document
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat
import com.epst.document.portal.core.service.CheckingService
import com.epst.document.portal.infra.externalService.CheckingExternalService
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
