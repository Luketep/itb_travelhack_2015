/*global require*/
/*global __dirname*/

var express = require('express'),
    app = express(),
    server = require('http').createServer(app)
;

server.listen(8080);

app.use(express.static(__dirname + '/build'));

app.get('/', function AppRouteRoot(req, res) {
	'use strict';
    res.sendFile(__dirname + 'index.html');
});