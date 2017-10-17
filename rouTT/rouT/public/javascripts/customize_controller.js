$(document).ready(function(){
    console.log(localStorage.getItem("destination"));
});

function moveToMap(){
    window.location.href = "/map";
}