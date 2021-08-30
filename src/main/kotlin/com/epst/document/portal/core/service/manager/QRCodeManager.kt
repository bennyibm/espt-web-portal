package com.epst.document.portal.core.service.manager

import com.epst.document.portal.core.service.QRCodeService
import com.epst.document.portal.infra.QRCodeRequestDTO
import com.epst.document.portal.infra.externalService.QRCodeExternalService
import org.springframework.stereotype.Service
import java.util.*

@Service
class QRCodeManager(private val qrCodeExternalService: QRCodeExternalService) : QRCodeService {


    override fun generateQRCode(innerInfo: String): String {
        //TODO("Not yet implemented")

        val qrCodeRequestDTO = QRCodeRequestDTO("no-frame", innerInfo, "PNG" , "scan-me-square")

        val qrCodeByteArray = qrCodeExternalService.generateQRCode(qrCodeRequestDTO)


        return Base64.getEncoder().encodeToString(qrCodeByteArray)
    }
}
