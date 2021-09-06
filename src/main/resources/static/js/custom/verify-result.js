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

        document.getElementById("error-msg").classList.add("d-none");


        fetch(url, {
            method : "post",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify(authenticateDocumentRequestDto)
        })
            .then(result => { return result.json();})
            .then( result => {
                console.log("result : ", result);
                if(result.content != null && result.content != undefined && result.content.id != null){

                    const fullName = result.content.nmsCdt.split(" ");

                    document.getElementById("resultName").innerText = fullName[0];
                    document.getElementById("resultFirst").innerText = fullName[1];
                    document.getElementById("resultLastName").innerText = fullName[2]
                    document.getElementById("resultSchool").innerText = result.content.nmEts
                    document.getElementById("resultPrcnt").innerText = result.content.prcnt
                    document.getElementById("resultYear").innerText = "2021"

                    //the toggle ON/OFF zones
                    document.getElementById("checking-form").classList.add("d-none");
                    hideShowPayZones(false);
                }
                else if(result.error){
                    document.getElementById("error-msg").classList.remove("d-none");
                    hideShowPayZones(true);
                }
            })
            .catch((error) =>{
                console.log("error occurred : ", error);
                document.getElementById("error-msg").classList.remove("d-none");
            })


    }

}

const showForm = () => {
    document.getElementById("checking-form").classList.remove("d-none");
    document.getElementById("code14").value = "";
    hideShowPayZones(true);
}

const hideShowPayZones = (hide = true) =>{
    const payZones = document.getElementsByClassName("payment-zone");
    for(let i = 0; i < payZones.length; i++) {
        if(hide == true)
            payZones[i].classList.add("d-none");
        else
            payZones[i].classList.remove("d-none");
    }
}

const validateFormCheckResultForm = () =>{

    const code14 = document.getElementById("code14");
    const docType = document.getElementById("docType");
    document.getElementById("code14Error").classList.add("d-none");

    let error;
    let errorMessage;

    //the code14 value error
    const code14Error = document.getElementById("code14Error");
    error = code14.value == "" || code14.value.length < 14;

    if(code14.value == ""){
        code14Error.innerText = "Champ obligatoire";
    }
    else if(code14.value.length < 14){
        code14Error.innerText = " *14 caractères au minimum";
    }

    if(error){
        code14Error.classList.remove("d-none");
    }else{
        code14Error.classList.add("d-none");
    }

    return error;
}
