package com.epst.document.portal.core.service.manager

import com.epst.document.portal.core.Document
import com.epst.document.portal.core.ResponseModel
import com.epst.document.portal.core.ResultExetat
import com.epst.document.portal.core.service.DocumentService
import com.epst.document.portal.infra.externalService.DocumentExternalService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.LocalDate

@Service
class DocumentManager(private val documentExternalService : DocumentExternalService) : DocumentService {

    private val logger = LoggerFactory.getLogger(DocumentManager::class.java)

    override fun save(result: ResultExetat, type : String): ResponseModel<Document?> {
        val document = Document()
        document.type = type
        document.results =  result
        document.generated = Date.valueOf(LocalDate.now())

        return documentExternalService.save(document)
    }

    override fun check(code: String): ResponseModel<Document?> {
        logger.warn("check method not yet implement...")
        return ResponseModel()
    }
}
