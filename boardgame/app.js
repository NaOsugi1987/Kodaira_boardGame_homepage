var express = require('express');
var app = express();
var path = require('path');
var http = require('http').Server(app);

http.listen(80);

app.use(express.static(__dirname + '/public'));
app.get('/', function (req, res) {
  res.sendFile(__dirname + '/public/index.html');
});

app.get('/mail', function (req, res) {
	send_email();
  res.sendFile(__dirname + '/public/index.html');
});

app.get('/job/GitHubPushReceiver/buildWithParameters', function (req, res) {
	console.log(req);
});
