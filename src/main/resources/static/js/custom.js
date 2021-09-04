//
// window.onload( () => {
//
//
// })
//

document.getElementById("checkResultForm").addEventListener("submit", e => checkResultForm(e))
const checkResultForm = (e) =>{
    e.preventDefault();
    console.log("initiate checking result");

    const code14 = document.getElementById("code14").value;
    const docType = document.getElementById("docType").value;
    console.log("code14 [", code14, "] et docType [", docType, "]");
}
