//
// const form = document.getElementById("authenticate-document-form");
//
// form.addEventListener("submit", (e) =>  onAuthenticateDocumentSubmit(e));
//
// const validateForm = () => {
//     const fields = form.getElementsByTagName("input");
//     let isValid = true;
//
//     for(let i = 0; i < fields.length; i++){
//         console.log("field : ", fields[i])
//         isValid = isValid && validateField(fields[i])
//     }
//
//     return isValid;
// }
//
// const validateField = (field) => {
//
//     let isValid;
//
//     if(field.value === ""){
//         isValid = true;
//         field.style.border = "1px solid red";
//     }
//     else{
//         isValid = false;
//         field.style.border = "1px solid dodgerblue"
//     }
//
//     return isValid;
// }
// const onAuthenticateDocumentSubmit = (e) =>{
//     e.preventDefault();
//
//     const isValid = true; // validateForm();
//
//     if(isValid){
//         const authenticateDocumentRequestDto = {
//             docRef : document.getElementById("refDocument").value,
//             studentName : document.getElementById("studentName").value
//         }
//
//         const authenticateDocumentUrl = "/authenticate-document";
//
//         fetch(authenticateDocumentUrl, {
//             method: "POST",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify(authenticateDocumentRequestDto)}
//         )
//         .then(response => {
//             return response.json()}
//         )
//         .then(response => {
//             console.log("response from portal : ", response);
//             if(response.error){
//                 //error on in the response retrieved from portal
//                 displayErrorMessage(response.message)
//             }
//         })
//         .catch( (error) => displayErrorMessage())
//     }
//
// }
//
//
// const displayErrorMessage = (errorMsg = "") =>{
//     document.getElementById("error-message-zone").classList.remove("invisible");
// }
