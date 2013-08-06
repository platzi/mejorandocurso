var a = 'Hola'
String.prototype.suspensivos = function(){
	return this + '...'
}
alert(a.suspensivos()) // 'Hola...'
