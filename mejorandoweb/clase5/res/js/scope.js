function n1(){
	var a = 1
	function n2(){
		var b = 2
		function n3(){
			var c = 3
			function n4(){
				var d = 4
				function n5(){
					console.log(a, b, c, d)
				}
				n5()
			}
			n4()
		}
		n3()
		console.log(a, b)
		console.log(c, d)
	}
	n2()
}
n1()