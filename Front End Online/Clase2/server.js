var servidor = require("connect");
servidor.createServer(
	servidor.static(__dirname)
).listen(6969);