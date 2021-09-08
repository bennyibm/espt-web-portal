package com.epst.document.portal.infra.externalService

import com.epst.document.portal.application.controller.DefaultController
import com.epst.document.portal.infra.QRCodeRequestDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.retry.RetryCallback
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class QRCodeExternalService(private val retryTemplate: RetryTemplate,
                            private val restTemplate: RestTemplate,
                            @Value("\${service.qrCodeApi.url}") private val baseUrl : String) {

    private val logger = LoggerFactory.getLogger(QRCodeExternalService::class.java)

    fun generateQRCode(qrCodeRequestDto : QRCodeRequestDTO) : ByteArray?{

        try{
            logger.info("in generateQRCode method, try to contact the remote api...")

            val response = retryTemplate.execute(RetryCallback<ByteArray, java.lang.Exception>{
                restTemplate.postForObject("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${qrCodeRequestDto.qr_code_text}",null,ByteArray::class.java)
            })

            logger.info("response from remote service : $response")

            return response
        }
        catch(ex : Exception){
            logger.error("error occurred : " + ex.message)
            return null
        }
    }
}
