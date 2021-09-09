package com.epst.document.epstportal.infra.externalService

import com.epst.document.epstportal.application.form.FindOutCertificateForm
import com.epst.document.epstportal.core.Document
import com.epst.document.epstportal.core.ResponseModel
import com.epst.document.epstportal.core.ResultExetat
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
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

    fun checkDocument(findOutCertificateForm: FindOutCertificateForm) :  ResponseModel<Document> {

        var response = ResponseModel<Document>();

        try {
            logger.info("requesting the backend-module to checkout the document : $findOutCertificateForm")
            response = retryTemplate.execute(RetryCallback<ResponseModel<Document>, java.lang.Exception> {
                restTemplate.postForObject(documentBaseUrl.plus("/checkout"), findOutCertificateForm, ResponseModel<Document>()::class.java)
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
