var express = require('express');
var app = express();
var path = require('path');
var http = require('http').Server(app);

http.listen(80);

app.use(express.static(__dirname + '/public'));
app.get('/', function (req, res) {
  res.sendFile(__dirname + '/public/index.html');
});
