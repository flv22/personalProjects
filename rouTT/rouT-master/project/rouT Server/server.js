var options = {
  host: 'www.google.com',
  port: 80,
  path: '/upload',
  method: 'POST'
};

var http = require('http');

var options = {
  host: 'www.google.com',
  port: 80,
  path: '/index.html'
};

http.get(options, function(res) {
  console.log("Got response: " + res.statusCode);
}).on('error', function(e) {
  console.log("Got error: " + e.message);
});