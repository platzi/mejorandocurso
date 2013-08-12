(function(global){
	var a = 5, k = 22
	function functionPrivada(c){
		return c + a + b + k
	}
	global.funcionExpuesta = functionPrivada
}(this))

//jQuery...