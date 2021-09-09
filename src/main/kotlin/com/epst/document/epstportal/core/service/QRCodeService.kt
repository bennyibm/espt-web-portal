package com.epst.document.epstportal.core.service

interface QRCodeService {

    fun generateQRCode(innerInfo : String) : String
}
