/*global require*/
/*global __dirname*/

var express = require('express'),
    app = express(),
    server = require('http').createServer(app)
;

server.listen(1337);
app.use(express.static(__dirname + '/build'));

app.get('/', function AppRouteRoot(req, res) {
	'use strict';
    res.sendFile(__dirname + 'index.html');
});

// FAKE BACKEND

app.get('/getTravelData', function(req, res) {
	var data = {
		location: 0,
		date: 0
	};
	res.writeHead(200, {'Content-Type': 'text/json'});
	res.end(JSON.stringify(data));
});