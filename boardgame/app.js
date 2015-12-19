var express = require('express');
var app = express();
var path = require('path');
var http = require('http').Server(app);
var io = require('socket.io')(http);

http.listen(80);

app.use('/', express.static(path.resolve('public')));
app.get('/test', function(req, res){
  res.sendFile(__dirname + '/public/index.html');
});