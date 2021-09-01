package com.epst.document.portal.core

class AuthenticateDocumentResponseContent {
    var firstName : String? = null
    var lastName : String? = null
    var percentage : Double? = null
    var year : Int? = null
    var schoolInstitute :String? = null

    constructor(){
        firstName = "Benny"
        lastName = "Nkonga"
        percentage = 55.7
        year = 2021
        schoolInstitute = "Bord Ezanga kombo"
    }
}
