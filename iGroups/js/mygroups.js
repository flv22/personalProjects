$(document).ready(function(){
    
});

function addGroup(){

    var newGroup = $("<div class='group-thumbnail col-sm-4'></div>");
    $(newGroup).append("<div class='group-pic'></div>");
    $(newGroup).append("<div class='group-title'><h3>" + $("#group-name").val() + "</h3></div>");

    $("#group-name").text("");
    $("#group-description").text("");
    //$(newGroup).append("<div class='group-description'><p>" + $("#group-description").val() + "</p></div>");

    $("#groups-content").append(newGroup);



}