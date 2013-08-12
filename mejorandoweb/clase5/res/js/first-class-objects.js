//------------------------
// Función como retorno
//------------------------

// Función que devuelve el resutado de
// un número multiplicado por tres
function por3(a){
	return a * 3
}

// Función que devuelve una función
// que calcula la x potencia de otro
// número
function porX(x){
	return function(a){
		return a * x
	}
}

var porX3 = porX(3)

por3(3) == porX3(3) // true

var porX5 = porX(5)

porX5(5) // 25


//------------------------
// Función como parámetro
//------------------------

function sumatoria(num, callback){
	var sum = 0
	for(var i=1; i<=num; i++){
		sum += i
	}
	// Ejecución del parámetro callback
	callback(sum)
}

sumatoria(99, function(suma){
	console.log('La sumatoria de 99 es '+suma)
})

//------------------------
// Función como parámetro
// (vida real)
//------------------------
function imprimirContenido(){
	alert(this.innerHTML)
}
$('a').on('click', imprimirContenido)