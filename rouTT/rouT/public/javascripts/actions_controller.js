var loginFB;

$(document).ready(function(){

    window.fbAsyncInit = function() {
		    FB.init({
		      appId      : '811223915597612',
		      xfbml      : true,
		      version    : 'v2.3'
		    });
		    FB.getLoginStatus(function(response) {
		    	if (response.status === 'connected') {
		    		// document.getElementById('status').innerHTML = 'We are connected.';
                    var access_tkn = FB.getAuthResponse();
					console.log(access_tkn);
					$.get("http://localhost:3001/places", {"access_token":access_tkn}).done(function(data){
                        console.log(data);
                    });
		    		// document.getElementById('login').style.visibility = 'hidden';
		    	} else if (response.status === 'not_authorized') {
		    		// document.getElementById('status').innerHTML = 'We are not logged in.'
		    	} else {
		    		// document.getElementById('status').innerHTML = 'You are not logged into Facebook.';
		    	}
		    });
			
		};
		(function(d, s, id){
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) {return;}
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js";
		    fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		
		// login with facebook with extra permissions
		loginFB = function login() {
			FB.login(function(response) {
				if (response.status === 'connected') {
		    		// document.getElementById('status').innerHTML = 'We are connected.';
		    		// document.getElementById('login').style.visibility = 'hidden';
		    	} else if (response.status === 'not_authorized') {
		    		// document.getElementById('status').innerHTML = 'We are not logged in.'
		    	} else {
		    		// document.getElementById('status').innerHTML = 'You are not logged into Facebook.';
		    	}
			}, {scope: 'email'});
		}
		
		// getting basic user info
		/*function getInfo() {
			FB.api('/me', 'GET', {fields: 'first_name,last_name,name,id,picture.width(150).height(150)'}, function(response) {
				document.getElementById('status').innerHTML = "<img src='" + response.picture.data.url + "'>";
			});
		}
		function getInfo(){
			FB.api('/1225931954101588/photos', function(response) {
				console.log(response);
			});
		}*/
		function getInfo(){
			FB.api('/me/albums?fields=id,name', function(response) {
                for (var i=0; i<response.data.length; i++) {
                        var album = response.data[i];

                        FB.api('/'+album.id+'/photos', function(photos){
                            if (photos && photos.data && photos.data.length){
                                for (var j=0; j<photos.data.length; j++){
                                        var photo = photos.data[j];
                                        // photo.picture contain the link to picture
                                        var image = document.createElement('img');
                                        image.src = photo.picture;
                                        document.body.appendChild(image);
                                }
                            }
                        });
                }
            });
		}
});