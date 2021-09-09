package com.epst.document.epstportal.core

import com.sun.javafx.scene.layout.region.Margins
import org.springframework.cglib.core.Converter
import java.util.*
import kotlin.collections.LinkedHashMap

class ResultExetat {
    var id: String? = null

    var nmPr: String? = null
    var cdPr: String? = null
    var nmCtr: String? = null
    var cdCtr: String? = null
    var nmOp: String? = null
    var cdOp : String? = null
    var nmEts: String? = null
    var cdEts: String? = null
    var ordEts : String? = null
    var cdGst : String? = null
    var nID : String? = null
    var cdCdt: String? = null
    var nmsCdt: String? = null
    var prcnt: String? = null
    var section: String? = null
    var ln: String? = null
    var dateN: String? = null
    var sx: String? = null
    var ecoleCode: String? = null
    var litiges: String? = null

    companion object {
        fun mapToRealResultExetat(linkedHashMap: LinkedHashMap<String, String>): ResultExetat {

            val realResultExetat = ResultExetat()
            realResultExetat.id   = linkedHashMap["id"]
            realResultExetat.nmPr = linkedHashMap["nmPr"]
            realResultExetat.nmOp = linkedHashMap["nmOp"]
            realResultExetat.cdOp = linkedHashMap["cdOp"]
            realResultExetat.nmEts = linkedHashMap["nmEts"]
            realResultExetat.cdEts = linkedHashMap["cdEts"]
            realResultExetat.ordEts = linkedHashMap["ordEts"]
            realResultExetat.cdGst = linkedHashMap["cdGst"]
            realResultExetat.nID = linkedHashMap["nID"]
            realResultExetat.cdCdt = linkedHashMap["cdCdt"]
            realResultExetat.nmsCdt = linkedHashMap["nmsCdt"]
            realResultExetat.prcnt = linkedHashMap["prcnt"]
            realResultExetat.section = linkedHashMap["section"]
            realResultExetat.ln = linkedHashMap["ln"]
            realResultExetat.dateN = linkedHashMap["dateN"]
            realResultExetat.sx = linkedHashMap["sx"]
            realResultExetat.ecoleCode = linkedHashMap["ecoleCode"]
            realResultExetat.litiges = linkedHashMap["litiges"]


            return realResultExetat;

        }
    }
}
