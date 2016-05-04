var exec = require('child_process').exec;

var net = require('net');

var host = 'localhost';
var port = '8080';

var child = exec('java -cp bin Server', function (error, stdout, stderr) {
    console.log('stdout: ' + stdout);
    console.log('stderr: ' + stderr);
    if (error !== null) {
        console.log('exec error: ' + error);
    }
});

setTimeout(run, 1000);

function run() {
    
    var client = new net.Socket();

    client.connect(port, host, function() {
        console.log('Connected to ' + host + ":" + port);
        client.write('Test message\n');
        console.log('=====');
    });

    client.on('data', function(data) {
        console.log(data.toString());
    });

    client.on('close', function(err) {
        if (!err) {
            console.log('=====');
            console.log('Connection closed');
        }
        client.destroy();
    });

    client.on('error', function(err) {
        console.log(err.message);
    });
    
}