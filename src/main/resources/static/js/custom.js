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

    const code14 = document.getElementById("code14").value;
    const docType = document.getElementById("docType").value;

    console.log("code14 [", code14, "] et docType [", docType, "]");

    const url = "/find-out-certificate";
    const isError = validateFormCheckResultForm();
    console.log("form is Valid : ", !isError);
    if(!isError){
        //continue and process the checking-flow

        console.log("processing the checking result")
        fetch(url, {
                method : "post",
                headers : { "Content-Type" : "application/json" },
                body : JSON.stringify(
                    {code : code14, type : docType}
                )
        }).then(result => {
            const resultTransformed = result.json();
            console.log("result in json : ", result);
        }).catch((error) =>{
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
    if(code14.value == "" || code14.value.length < 14 ){
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
