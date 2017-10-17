var currentEditedDot;

$(document).ready(function(){
        
    
    $(".add-button").click(generateLifeDotCaption);

    

});



function generateLifeDotCaption(){

    var dotCaption = "<div class='life-dot-caption'>" +
            "<div class='life-dot-bar'>" +
            "<a data-toggle='modal' href='#view-modal'>view</a><a data-toggle='modal' href='#edit-modal' class='edit-button'>edit</a><a class='delete-button'>delete</a></div>" +
            "<div class='life-dot-content'><h3 class='title'></h3>" +
            "</div></div>"
    
    $("#main-page-row").append(dotCaption);

    $(".delete-button").click(function(event){
        deleteLifeDot($(event.currentTarget.parentNode.parentNode));
    });

    $(".edit-button").click(function(event){
        currentEditedDot = $(event.currentTarget.parentNode.parentNode);
    });

    $(".btn-save").click(function(event){
        var currentElem = $(currentEditedDot).children()[1];
        $($(currentElem).children()[0]).text($("#content").value);
        console.log($("#content").value);
    });
}

function deleteLifeDot(dot){

    $(dot).remove();

}