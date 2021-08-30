package com.epst.document.portal.core.service

interface QRCodeService {

    fun generateQRCode(innerInfo : String) : String
}
