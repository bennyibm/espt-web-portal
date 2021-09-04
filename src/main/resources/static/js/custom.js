//
// window.onload( () => {
//
//
// })
//

const checkResultForm = document.getElementById("checkResultForm")
checkResultForm.addEventListener("submit", e => processCheckResultForm(e))

const processCheckResultForm = (e) =>{
    e.preventDefault();
    console.log("initiate checking result");


    const url = "/find-out-certificate";
    const isError = validateFormCheckResultForm();
    console.log("form is Valid : ", !isError);
    if(!isError){
        //continue and process the checking-flow

        const authenticateDocumentRequestDto = {
            code14 : document.getElementById("code14").value,
            docType : document.getElementById("docType").value
        }
        console.log("code14 [", authenticateDocumentRequestDto.code14, "] et docType [", authenticateDocumentRequestDto.docType, "]");


        console.log("processing the checking result")
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

const validateFormCheckResultForm = () =>{

    const code14 = document.getElementById("code14");
    const docType = document.getElementById("docType");

    let error;
    let errorMessage;

    //the code14 value error
    if(code14.value == "" || code14.value.length < 13 ){
        error = true;
        document.getElementById("code14Error").classList.add("invisible");
    }else{
        error = false;
        document.getElementById("code14Error").classList.remove("invisible");
    }
    // //the document type value error
    // if(docType.value = ""){
    //     error = true;
    //     document.getElementById("docTypeError").classList.add("invisible");
    // }else{
    //     error = error || true;
    //     document.getElementById("docTypeError").classList.remove("invisible");
    // }

    return error;
}
