var express = require('express');
var app = express();
var fs = require("fs");
// var unirest = require("unirest");
// var googlePlaces = require("googleplaces");
// var googlePlacesConfig = require("./config.js");
var GooglePlaces = require('google-places');
const places = new GooglePlaces('AIzaSyA19pkGacEYctC_xCWTBsWU7-JGPRifzr8');

var SparqlClient = require('sparql-client');
var util = require('util');
var endpoint = 'http://dbpedia.org/sparql';

var FB = require('fb');
var options = FB.options();
fb = new FB.Facebook(options);

app.get('/route', function (req, res) {
	
//     console.log(req.query.range);
//     console.log(req.query.place_type);

//     var query = "SELECT * FROM <http://dbpedia.org> WHERE {?city <http://dbpedia.org/property/leaderName> ?leaderName} LIMIT 10";
// var client = new SparqlClient(endpoint);
// console.log("Query to " + endpoint);
// console.log("Query: " + query);
// client.query(query)
//   //.bind('city', 'db:Chicago')
//   //.bind('city', 'db:Tokyo')
//   //.bind('city', 'db:Casablanca')
//   .bind('city', '<http://dbpedia.org/resource/Vienna>')
//   .execute(function(error, results) {
//   process.stdout.write(util.inspect(arguments, null, 20, true)+"\n");
//   console.log(error);
//   console.log(results);
// });

    // var nearbySearch = new NearBySearch(config.apiKey, config.outputFormat);
    
    // var parameters = {
    //     location: [40.7127, -74.0059],
    //     keyword: "doctor"
    // };

    // nearBySearch(parameters, function (error, response) {
    //     if (error) throw error;
    //     console.log(response.results.length + "Place search must not return 0 results");
    // });
    // unirest.get('http://httpbin.org/get').query({'foo': 'bar'}).query({'stack': 'overflow'}).end(function(res) {
    //     if (res.error) {
    //         console.log('GET error', res.error)
    //     } else {
    //         console.log('GET response', res.body)
    //     }
    // });



	// res.writeHead(200, {
    //             'Content-Type':'text/plain',
    //             'Access-Control-Allow-Origin':'*',
    //             'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE'
    //         });
			// res.end("you're on /carFinder - lat=");
            var redURL = "http://localhost:3000/map";
            res.redirect(redURL);
	
	
	
    // res.end(JSON.stringify(xmlHttp.responseText));
})

app.get('/places', function ( req, res) {
    // var radius = req.params.radius;
    // var type = req.params.type;
    var access_token = req.params.access_token;
    console.log(access_token + " - token");
    FB.setAccessToken(access_token);

    FB.api('/me', function (res) {
        if(!res || res.error) {
            console.log(!res ? 'error occurred' : res.error);
            return;
        }
        console.log(res.id);
        console.log(res.name);
    });

    var body = 'My first post using facebook-node-sdk';
    FB.api('me/feed', 'post', { message: body }, function (res) {
        if(!res || res.error) {
            console.log(!res ? 'error occurred' : res.error);
            return;
        }
        console.log('Post Id: ' + res.id);
    });

	res.end("you're on /places");
})

app.get('/actions/reviews', function ( req, res) {
    res.end("you're on /reviews");
})

app.get('/actions/photos', function ( req, res) {
    res.end("you're on /photos");
})

app.get('/actions/videos', function ( req, res) {
    res.end("you're on /videos");
})

app.get('/actions/advices', function ( req, res) {
    res.end("you're on /advices");
})

app.get('/actions/sync', function ( req, res) {
    res.end("you're on /sync");
})

app.get('/carFinder/save', function( req, res){
	
	pg.connect(conString, function (err, client, done){
      if(err) {
        return console.error();
      }

      client.query('INSERT INTO public.carposition(lat, lng) VALUES($1, $2)', [req.query.lat, req.query.lng], function (err, result) {
        done();

        if(err || result.rowCount == 0){
			res.writeHead(403, {
                'Content-Type':'text/plain',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE'
            });
        }else{
			res.writeHead(200, {
                'Content-Type':'text/plain',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE'
            });
			res.end("you're on /carFinder - lat=" + req.query.lat + " - lng=" + req.query.lng + " end");
		}
      });
    });	
})

app.get('/carFinder/find', function( req, res){
	
	pg.connect(conString, function (err, client, done){
      if(err) {
        return console.error();
      }

      client.query('SELECT * FROM public.carposition', function (err, result) {
        done();

        if(err || result.rowCount == 0){
			res.writeHead(403, {
                'Content-Type':'text/plain',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE'
            });
        }else{
			res.writeHead(200, {
                'Content-Type':'text/plain',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE'
            });
			res.end(JSON.stringify(result.rows));
		}
      });
    });	
})

var server = app.listen(3001, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})