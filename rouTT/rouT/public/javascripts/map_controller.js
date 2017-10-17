function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(initMap);
    } else {
        body.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function initMap(position) {
        var uluru = {lat: position.coords.latitude, lng: position.coords.longitude};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 18,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });

        // var infowindow = new google.maps.InfoWindow({
        //   content: "Wily's village"
        // });
        // marker2.addListener("click", function(){
        //     infowindow.open(map, marker2);
        // });

        // console.log(localStorage.getItem("range"));

        var directionsService = new google.maps.DirectionsService;
        var placesService = new google.maps.places.PlacesService(map);

        // var flightPath = new google.maps.Polyline({
        //   path: flightPlanCoordinates,
        //   geodesic: true,
        //   strokeColor: '#FF0000',
        //   strokeOpacity: 1.0,
        //   strokeWeight: 2
        // });

        directionsService.route({
            origin: uluru,
            destination: localStorage.getItem('destination'),
            // waypoints: waypts,
            optimizeWaypoints: false,
            travelMode: 'DRIVING'
        }, function(response, status) {
            if (status === 'OK') {
                var waypnts = [];

                // directionsDisplay.setDirections(response);
                var route = response.routes[0];
                var path = response.routes[0].overview_path;

                for(var i=0;i<path.length;i++){
                    // console.log(path[i].lat() + " - " + path[i].lng());
                    waypnts.push({lat: path[i].lat(),lng: path[i].lng()});

                    placesService.nearbySearch({
                        location: {lat: path[i].lat(),lng: path[i].lng()},
                        radius: 500,//localStorage.getItem("radius"),
                        type: localStorage.getItem("place-type")
                    }, placeMarker);

                    function placeMarker(results, status) {
                        if (status === google.maps.places.PlacesServiceStatus.OK) {
                            for (var i = 0; i < results.length; i++) {
                                var markerInterestPoint = new google.maps.Marker({
                                    position: {lat: results[i].geometry.location.lat(),lng: results[i].geometry.location.lng()},
                                    map: map
                                });
                                console.log(results[i]);
                            }
                        }
                    }

                    
                }
                
                var flightPath = new google.maps.Polyline({
                  path: waypnts,
                  geodesic: true,
                  strokeColor: '#FF0000',
                  strokeOpacity: 1.0,
                  strokeWeight: 2
                });
                
                var marker2 = new google.maps.Marker({
                    position: waypnts[waypnts.length-1],
                    map: map
                });

                flightPath.setMap(map);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });

        // directionsDisplay.setMap(map);
}