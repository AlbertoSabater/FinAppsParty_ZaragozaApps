var http = require("http");
var PythonShell = require('python-shell');
var url = require("url");

var defaultResponseHeaders = {"Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "accept, content-type, bc"};

var jugadores = [];
var won = -1;
var pass = false;
var idMax = 0;
var previousPosition = null;
var positions = [[2,2],[3,2],[4,2],[5,2],[2,3],[3,3],[4,3],[5,3],
                    [2,4],[3,4],[4,4],[5,4],[2,5],[3,5],[4,5],[5,5]];
var colorResults = [];
var color = [[0,0,255],[255, 0, 0],[255,255,0],
    [0,255,0],[0,128,0],[255,0,255],[0,255,255],[255,69,0]];
var done = false;


var httpServer = http.createServer( function (request, response) {
	var body = '';

    request.on('data', function(chunk) { 
        body += chunk;
    });
    request.on('end', function() {
        handlePost();
    });

    function handlePost() {
    	console.log("Received POST with URL: " + request.url);
        if (request.url == "/won") {
            if (won >= 0) {
                simpleResponse(201, "", won+"");
                console.log(won);
            } else {
                simpleResponse(200, "", "wait");
            }
        } else if (request.url == "/Init") {
            var data = JSON.parse(body);
            var id = parseInt(data["id"]);
            if (jugadores.length == 2) {
                //jugadores = [];
                won = -1;

            }
            console.log(id);
            jugadores[id] = -1;
            pass = false;
            
            var exec = require('child_process').exec;
            exec('python screenreset.py ', function callback(error, stdout, stderr){
                // result
            });

            console.log("Player added " + id);
            simpleResponse(200, "", "OK");
            //Use different names
        } else if (request.url == "/touchgameWaitPlayers") {
            if (jugadores[0] && jugadores[1]) {
                simpleResponse(200, "", "start");
            } else {
               simpleResponse(200, "", "wait");
            }
        } else if (request.url == "/MathWaitPlayers") {
            console.log(JSON.stringify(jugadores));
            if (jugadores[0] && jugadores[1]) {
                console.log("blabla");
                simpleResponse(200, "", "start");
                console.log("blabla");
                /*var exec = require('child_process').exec;
                exec('python CountDown.py', function callback(error, stdout, stderr){
                });*/
            } else {
               simpleResponse(200, "", "wait");
            }
        } else if (request.url == "/PairWaitPlayers") {
            console.log(JSON.stringify(jugadores));
            if (jugadores[0] && jugadores[1]) {
                if (pass) {
                    simpleResponse(200, "", "start");
                } else {
                    pass = true;
                    var numColors = [0,0,0,0,0,0,0,0];
                    for (var i = 0; i < positions.length; i++) {
                        console.log("aaa");
                        r = randomInt(0,8);
                        console.log("bbb");
                        while (numColors[r] > 1) {
                            r = randomInt(0,8);
                            console.log(r);
                        }
                        console.log("1");
                        numColors[r] = numColors[r] + 1;
                        console.log("2");
                        colorResults[i] = r;
                        console.log(JSON.stringify(colorResults));
                    }
                    var string = "python match_pairs.py";
                    
                    for (var j = 0; j < colorResults.length; j++) {
                        string = string + ' '+ color[colorResults[j]][0] + ' '+ color[colorResults[j]][1] + ' ' + color[colorResults[j]][2];
                    }
                    console.log(string);
                    var exec = require('child_process').exec;
                    exec(string, function callback(error, stdout, stderr){
                    });
                    console.log("Send response");
                    simpleResponse(200, "", "start");
                }
            } else {
               simpleResponse(200, "", "wait");
            }
        } else if (request.url == "/touchgame") {
            console.log(body);
            var data = JSON.parse(body);
            console.log("success!!");
            var id = parseInt(data["id"]);
            console.log(id + " - " + data["height"]);
            //if (jugadores.length == 0;) {
                jugadores[id] = parseInt(data["height"]);
                if (data["height"] == 7) {
                    won = id;
                    console.log("Ganador " + id);
                    jugadores = [];
                }
            /*} else {
                jugadores[id] = jugadores[id] + parseInt(data["height"]);
            }*/
            /*var options = {
              args: [id, height]
            };*/
            console.log("afeiif");
            var exec = require('child_process').exec;
            exec('python race.py ' + id + ' ' + data["height"], function callback(error, stdout, stderr){
                // result
            });
            console.log(JSON.stringify(jugadores));
            simpleResponse(200, defaultResponseHeaders, "Eres el mejor!");

        } else if (request.url == "/plusplus") {
            var data = JSON.parse(body);
            console.log(data["id"]);
            console.log("success!!");
            var id = parseInt(data["id"]);
            jugadores[id] = parseInt(data["result"]);
            console.log("jugadores: " + JSON.stringify(jugadores));
            console.log("jugadores length: " + jugadores.length);
            if (!done) {
                done = true;
            } else {
                done = false;
                var max = Math.max.apply(null, jugadores);
                for (var i = 0; i < jugadores.length; i++) {
                    if (jugadores[i] == max) {
                        idMax = i;
                    }
                }
                if (jugadores[0] == jugadores[1]) {
                    idMax = 2;
                }
                console.log("idMax: " + idMax + " max: " + max);
                var exec = require('child_process').exec;
                exec('python plusplus.py ' + idMax, function callback(error, stdout, stderr){
                    // result
                });
                jugadores = [];
            }
            simpleResponse(200, defaultResponseHeaders, "Eres el mejor!");
        } else if (request.url == "/pushButton") {
            var data = JSON.parse(body);
            var id = data["id"];
            var x = data["x"];
            var y = data["y"];
            console.log(id + " " + x + " " + y);
            var exec = require('child_process').exec;
            var i = 0;
            var pos = 0;
            while (!(x==positions[i][0] && y==positions[i][1])){
                i++;
            }
            pos = colorResults[i];
            console.log(JSON.stringify(colorResults));
            var script = 'python turnLight.py ' + x + ' ' + y + ' '+ color[pos][0] + ' '+ color[pos][1] + ' ' + color[pos][2];
            if (previousPosition == null) {
                previousPosition = [x, y, color[pos][0], color[pos][0], color[pos][0]];
            } else {
                if (!(color[pos][0]==previousPosition[2] && color[pos][1]==previousPosition[3] && color[pos][2]==previousPosition[4])) {
                    setTimeout(function (prevPos) {
                      var script = 'python turnLight.py ' + x + ' ' + y + ' '+ 0 + ' '+ 0 + ' ' + 0;
                      var script = 'python turnLight.py ' + prevPos[0] + ' ' + prevPos[1] + ' '+ 0 + ' '+ 0 + ' ' + 0;
                    }, 3000, x, y/*, r, b, g ,*/, previousPosition);
                }
                previousPosition=null;
            }
            console.log(script);
            exec(script, function callback(error, stdout, stderr){
            });
            simpleResponse(200, defaultResponseHeaders, "Eres el mejor!");
        }
    }

    function randomInt (low, high) {
        return Math.floor(Math.random() * (high - low) + low);
    }
    function simpleResponse(code, headers, message) {
        //response.writeHead(code);
        response.statusCode = 200;//(200, {'Content-Type': 'text/plain'});
        response.end(message);
    }

});



httpServer.listen(8081);
console.log("Listening on port " + 8081);
