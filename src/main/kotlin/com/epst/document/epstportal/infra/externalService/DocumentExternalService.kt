package com.epst.document.epstportal.infra.externalService

import com.epst.document.epstportal.application.controller.DefaultController
import com.epst.document.epstportal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.epstportal.application.dto.AuthenticateDocumentResponse
import com.epst.document.epstportal.application.form.FindOutCertificateForm
import com.epst.document.epstportal.core.Document
import com.epst.document.epstportal.core.ResponseModel
import com.epst.document.epstportal.core.ResultExetat
import com.epst.document.epstportal.infra.QRCodeRequestDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.retry.RetryCallback
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class DocumentExternalService(private val retryTemplate: RetryTemplate,
                              private val restTemplate: RestTemplate,
                              @Value("\${service.brevets.documents.url}") private val documentBaseUrl : String) {

    private val logger = LoggerFactory.getLogger(DocumentExternalService::class.java)

    fun save(document : Document) :  ResponseModel<Document?> {

        var response = ResponseModel<Document?>();

        try {
            logger.info("saving a document for the student code  : ${document.type}")
            response = retryTemplate.execute(RetryCallback<ResponseModel<Document?>, java.lang.Exception> {
                restTemplate.postForObject(documentBaseUrl, document, ResponseModel<Document?>()::class.java)
            })
        }
        catch(ex : Exception){
            logger.error("error occurred : " + ex.message)
            response.content = null
            response.error = true
            response.errorMessage = ex.message
            response.status = 500
        }

        return response
    }

}
