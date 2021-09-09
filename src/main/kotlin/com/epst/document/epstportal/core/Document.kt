package com.epst.document.epstportal.core

import java.util.*
import kotlin.collections.LinkedHashMap

class Document {
    var id: String? = null
    var ref: String? = null
    var type: String? = null
    var generated: Date? = null
    var results: ResultExetat? = null

    companion object{
        fun mapToRealDocument(linkedHashMap: LinkedHashMap<String, String>) : Document{
            val document = Document()
            document.id = linkedHashMap["id"]
            document.ref = linkedHashMap["ref"]
            document.type = linkedHashMap["type"]
//            document.generated = linkedHashMap["generated"]
            val linkedHashMapResult = linkedHashMap["results"] as LinkedHashMap<String, String>
            document.results = ResultExetat.mapToRealResultExetat(linkedHashMapResult)

            return document
        }
    }
}
