package com.epst.document.portal.infra.externalService

import com.epst.document.portal.application.controller.DefaultController
import com.epst.document.portal.application.dto.AuthenticateDocumentRequestDTO
import com.epst.document.portal.application.dto.AuthenticateDocumentResponse
import com.epst.document.portal.application.form.FindOutCertificateForm
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat
import com.epst.document.portal.infra.QRCodeRequestDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.retry.RetryCallback
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CheckingExternalService(private val retryTemplate: RetryTemplate,
                              private val restTemplate: RestTemplate,
                              @Value("\${service.brevets.documents.url}") private val documentBaseUrl : String,
                              @Value("\${service.brevets.results.url}") private val resultsBaseUrl : String) {

    private val logger = LoggerFactory.getLogger(CheckingExternalService::class.java)

    fun checkDocument(authenticateDocumentRequestDTO : AuthenticateDocumentRequestDTO) : AuthenticateDocumentResponse {
        var response = AuthenticateDocumentResponse()

        try {
            logger.info("requesting the backend-module to checkout the document : $authenticateDocumentRequestDTO")
            response = retryTemplate.execute(RetryCallback<AuthenticateDocumentResponse, java.lang.Exception> {
                restTemplate.postForObject(documentBaseUrl, authenticateDocumentRequestDTO, AuthenticateDocumentResponse::class.java)
            })

            logger.info("request success with the response : $response")

        }
        catch(ex : Exception){
            logger.error("error occurred : " + ex.message)
        }

        return response
    }

    fun checkCertificate(findOutCertificateForm: FindOutCertificateForm) : ResponseModel<ResultExetat> {

        var response = ResponseModel<ResultExetat>();

        try {
            logger.info("requesting the backend-module to checkout the document : $findOutCertificateForm")
            response = retryTemplate.execute(RetryCallback<ResponseModel<ResultExetat>, java.lang.Exception> {
                restTemplate.postForObject(resultsBaseUrl.plus("/checkout"), findOutCertificateForm, ResponseModel<ResultExetat>()::class.java)
            })

            logger.info("request success with the response : $response")

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
