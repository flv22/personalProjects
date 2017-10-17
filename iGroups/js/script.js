var jsonData = '[{"name":"group 1","description":"group 1 description","cover":"http://www.opel.ro/content/dam/Opel/Europe/master/hq/en/01_Vehicles/01_PassengerCars/Mokka/01_ModelOverview/MY_15/768x432/Opel_Mokka_Exterior_View_768x432_mok15_e03_056.jpg"},{"name":"group 2","description":"group 2 description","cover":"http://www.opel.ro/content/dam/Opel/Europe/master/hq/en/01_Vehicles/01_PassengerCars/Mokka/01_ModelOverview/MY_15/768x432/Opel_Mokka_Exterior_View_768x432_mok15_e03_055.jpg"},{"name":"group 3","description":"group 3 description","cover":"http://www.bmwusa.com/bmw/api/assets/images/BMWi/BMWi8/BMWi_i8_module4_B4.jpg?v=5fc09bfa5efbb09cf3cd0e73e2d9aad7"},{"name":"group 4","description":"group 4 description","cover":"https://i.guim.co.uk/img/static/sys-images/Guardian/Pix/pictures/2015/7/9/1436448264593/cbf63e0c-dec4-41d1-9ef0-7d3002fc5230-2060x1166.jpeg?w=1125&q=55&auto=format&usm=12&fit=max&s=dbfdb8ee84706005a51b9f6f35d31eda"},{"name":"group 5","description":"group 5 description","cover":"https://www.callofduty.com/content/dam/atvi/callofduty/hub/main-hub/iw-hub/hero/iw-key-art.jpg"}]';

var jsonObj = $.parseJSON(jsonData);
var jsonSessionObj = jsonObj;

var current_page = 1;
var records_per_page = 3;
var resultIndex = records_per_page;

$(document).ready(function(){

    $("#search-box").on("keydown", function(e){
        if(e.which == 13 || e.keyCode == 13){
            //$("#search-box").fadeOut("slow");
                callback();
        }
    });

    $("#my-groups,#groups").on("click",function(){
        $(this).addClass("active");
    });

    $(window).resize(function(){
        overflowText();
    });

});

function callback(){

    var resultsRow = $("#results-row");

    changePage(1);

}

function loadResults(){
    
}

function overflowText(){
    console.log("inside overflow text");

    var p=$('.group-description p');
    var divHeight=$('.group-description').height();
    
    while ($(p).outerHeight()>divHeight) {
        $(p).text(function (index, text) {
            return text.replace(/\W*\s(\S)*$/, '...');
        });
    }
}

function showMessage(event){
    var a = event.target;
    alert(a);
}

function getDataJson(){

    var jsonData = '[{"name":"group 1","description":"group 1 description","cover":"http://www.opel.ro/content/dam/Opel/Europe/master/hq/en/01_Vehicles/01_PassengerCars/Mokka/01_ModelOverview/MY_15/768x432/Opel_Mokka_Exterior_View_768x432_mok15_e03_056.jpg"},{"name":"group 2","description":"group 2 description","cover":"http://www.opel.ro/content/dam/Opel/Europe/master/hq/en/01_Vehicles/01_PassengerCars/Mokka/01_ModelOverview/MY_15/768x432/Opel_Mokka_Exterior_View_768x432_mok15_e03_055.jpg"},{"name":"group 3","description":"group 3 description","cover":"http://www.bmwusa.com/bmw/api/assets/images/BMWi/BMWi8/BMWi_i8_module4_B4.jpg?v=5fc09bfa5efbb09cf3cd0e73e2d9aad7"},{"name":"group 4","description":"group 4 description","cover":"https://i.guim.co.uk/img/static/sys-images/Guardian/Pix/pictures/2015/7/9/1436448264593/cbf63e0c-dec4-41d1-9ef0-7d3002fc5230-2060x1166.jpeg?w=1125&q=55&auto=format&usm=12&fit=max&s=dbfdb8ee84706005a51b9f6f35d31eda"}]';

    return jsonData;

}

//


function prevPage()
{
    if (current_page > 1) {
        current_page--;
        changePage(current_page);
    }
}

function nextPage()
{
    if (current_page < numPages()) {
        current_page++;
        changePage(current_page);
    }
}

function changePage(page){
    
    var resultsRow = $("#results-row");

    var btnNext = $("#btn_next");
    var btnPrevious = $("#btn_prev");

    if(page < 1)
        page = 1;
    if(page > numPages())
        page = numPages();

}

function changePage(page)
{	
    
	var elem = document.getElementById('results-row');

    var btn_next = document.getElementById("btn_next");
    var btn_prev = document.getElementById("btn_prev");
    var listing_table = document.getElementById("results-row");
    var page_span = document.getElementById("page");
 
    // Validate page
    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    listing_table.innerHTML = "";

    console.log(jsonSessionObj);

    for (var i = (page-1) * records_per_page; i < (page * records_per_page); i++) {
        if(i >= jsonSessionObj.length)
            break;

        var resultDiv = $('<div class="col-sm-4 results"></div>');

        $(resultDiv).append('<div class="group-pic"><img src="' + jsonSessionObj[i].cover + '" class="img-rounded"/></div>');
        $(resultDiv).append('<div class="group-title"><h3>' + jsonSessionObj[i].name + '</h3></div>');
        $(resultDiv).append('<div class="group-description"><p>' + jsonSessionObj[i].description + '</p></div>');
        $(resultDiv).append('<div class="group-button-bar"><img src="img/add-button.png" class="button-responsive"/></div>');

        $("#results-row").append(resultDiv);

        $(".results").animate({
            opacity: 0.7,
        }, 1500);

    	
    }
    overflowText();

    $(".button-responsive").bind('click',function(e){
        var resultDiv = $($(e.target).parent()).parent();
        // resultDiv.animate({
        //     opacity: 0.0
        // }, 500, function(){
        //     resultDiv.remove();
        // });
        resultDiv.addClass("fade-out-animation");
        resultDiv.on("webkitAnimationEnd",function(){
            
            if(resultIndex<jsonSessionObj.length){

                var newResultDiv = $('<div class="col-sm-4 results"></div>');

                $(newResultDiv).append('<div class="group-pic"><img src="' + jsonSessionObj[resultIndex].cover + '" class="img-rounded"/></div>');
                $(newResultDiv).append('<div class="group-title"><h3>' + jsonSessionObj[resultIndex].name + '</h3></div>');
                $(newResultDiv).append('<div class="group-description"><p>' + jsonSessionObj[resultIndex].description + '</p></div>');
                $(newResultDiv).append('<div class="group-button-bar"><img src="img/add-button.png" class="button-responsive"/></div>');

                overflowText();

                $("#results-row").append(newResultDiv);

                newResultDiv.animate({
                    opacity: 0.7,
                }, 1500, function(){
                    
                });
                
                resultIndex++;
                
                getElement(resultDiv);

                resultDiv.remove();
            }
        });
        
    });

    page_span.innerHTML = page;

    if (page == 1) {
        btn_prev.style.visibility = "hidden";
    } else {
        btn_prev.style.visibility = "visible";
    }

    if (page == numPages()) {
        btn_next.style.visibility = "hidden";
    } else {
        btn_next.style.visibility = "visible";
    }
}

function numPages() {
    return Math.ceil(jsonSessionObj.length / records_per_page);
}

function goToWebsite(){
	console.log('ciao!');
}

function getElement(elem){

    var title = $(elem.children()[1]).children()[0];
    var description = $(elem.children()[2]).children()[0];

    //console.log($(title).text());
    //console.log($(description).text());

    console.log("Before: " + jsonSessionObj.length);

    for(var i=0;i<jsonSessionObj.length;i++){
        console.log(jsonSessionObj[i].name + " cu " + $(title).text());
        if(jsonSessionObj[i].name === $(title).text()){
            jsonSessionObj.splice(i, 1);
            break;
        }
    }

    console.log("After: " + jsonSessionObj.length);

}