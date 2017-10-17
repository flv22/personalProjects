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