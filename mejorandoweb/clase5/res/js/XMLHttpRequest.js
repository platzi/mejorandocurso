var req = new XMLHttpRequest();
req.onload = function(){
	var json = JSON.parse(this.responseText)
	document.getElementById('response')
		.innerHTML = json.data;
};
req.open('get', 'res/data/hola.json', true);
req.send();

