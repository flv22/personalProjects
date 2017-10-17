
var places;
var range;
var step;

$(document).ready(function() {

  step = 1;

  $("#button1").click(function() {
        localStorage.setItem("destination",$("#dest").val());
        window.location.href = "/customize";
  });

  $(".col-xs-6").keypress(function(e) {
    if(e.which == 13) {
      if(step == 1) {
        generate_dropdown();
      }
      else {
        //redirect to customize
      }
    }
  });

  $("#set_range_button").click(function() {
    if(step == 1) {
      generate_dropdown();
      step = 2;
    }
    else {
      //alert("DAA");
      $("#arrow").slideUp();
      $("#places").slideUp();
      var input = "<input id='range' type='number' placeholder='Enter range'>";
      $("#set_range").html(input);
      var button = "<button id='set'> Set range </button>";
      $("#set_range_button").html(button);
      step = 1;
    }
  });

  // $("#set_range").click(function() {
  //   if(step == 2) {
  //     alert("DAA");
  //     $("#arrow").slideUp();
  //     $("#places").slideUp();
  //     var input = "<input id='range' type='number' placeholder='Enter range'> <div id='set_range_button'/>"
  //     $("#set_range").html(input);
  //     var button = "<button id='set'> Set range </button>";
  //     $("#set_range_button").html(button);
  //     step = 1;
  //   }
  // });
  
});

function generate_dropdown() {
  range = $("#range").val();
  localStorage.setItem("range",range);
  $("#range").slideUp();
  $("#set").slideUp();
  var dropdown = "<select id='places'> <option> ---Select--- </option> </select>";
  $("#set_range").html(dropdown);
  var button = "<button id='arrow'> Back </button>";
  $("#set_range_button").html(button);

  $.getJSON( "/resources/type_of_places.json", function( data ) {
    places = [];
    $.each( data, function( number, place ) {
      //alert(place.name + " " + place.value);
      var op = new Option(place.name, place.value);
      $(op).html(place.name);
      $("#places").append(op);
    });
  });

  $("#places").change(function(){
    place_type = $(this).val();
    localStorage.setItem("place-type",place_type);
  });

}

function callRadar(range,place_type){
  $.get("http://localhost:3001/route",{"range":range, "place_type":place_type}).
    done(function(data){
      console.log(data)
    });
}

function displayPredictions() {
        var displaySuggestions = function(predictions, status) {
          if (status != google.maps.places.PlacesServiceStatus.OK) {
            alert(status);
            return;
          }

          predictions.forEach(function(prediction) {
            console.log(prediction.description);
            $("#destinations").append('<option value="' + prediction.description + '"');
          });
        };

        var service = new google.maps.places.AutocompleteService();
        service.getQueryPredictions({ input: 'pizza near Syd' }, displaySuggestions);
}