//
// window.onload( () => {
//
//
// })
//

const checkResultForm = document.getElementById("authDocumentForm")
checkResultForm.addEventListener("submit", e => processAuthDocumentForm(e))

const processAuthDocumentForm= (e) =>{
    e.preventDefault();
    console.log("initiate auth-document resulprocesst");


    const url = "/authenticate-document";
    const isError = validateAuthDocumentForm();
    console.log("form is Valid : ", !isError);
    if(!isError){
        //continue and process the checking-flow

        const authenticateDocumentRequestDto = {
            code14 : document.getElementById("code").value,
            docType : "exetat" // document.getElementById("docType").value
        }
        console.log("code [", authenticateDocumentRequestDto.code, "] et docType [", authenticateDocumentRequestDto.docType, "]");


        console.log("processing the authentication of document")
        fetch(url, {
                method : "post",
                headers : { "Content-Type" : "application/json" },
                body : JSON.stringify(authenticateDocumentRequestDto)
        })
        .then(result => { return result.json();})
        .then( result => {
            console.log("result : ", result);
            if(result.content != null && result.content != undefined && result.content.id != null){
                document.getElementById("payment-zone").classList.remove("invisible");
                document.getElementById("not-valid").classList.add("invisible");

                const fullName = result.content.nmsCdt.split(" ");

                document.getElementById("resultName").innerText = fullName[0];
                document.getElementById("resultFirst").innerText = fullName[1];
                document.getElementById("resultLastName").innerText = fullName[2]
                document.getElementById("resultSchool").innerText = result.content.nmEts
            }
            else if(result.error){
                document.getElementById("not-valid").classList.remove("invisible");
                document.getElementById("payment-zone").classList.add("invisible");
            }
        })
        .catch((error) =>{
            console.log("error occurred : ", error);
        })


    }

}

const validateAuthDocumentForm = () =>{

    const code = document.getElementById("code");

    let error;
    let errorMessage;

    //the code14 value error
    if(code.value == "" || code.value.length < 13 ){
        error = true;
        // document.getElementById("code").classList.add("invisible");
    }else{
        error = false;
        // document.getElementById("code").classList.remove("invisible");
    }

    return error;
}
