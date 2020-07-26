$(document).ready(function() {

    if($("#showModal") && $("#showModal").text() === "showModal"){
        console.log("Entrou");
        $('#simularLoanApp').modal("show");
    }
})