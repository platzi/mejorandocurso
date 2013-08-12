$.ajax({
	url: 'res/data/hola.json'
}).done(function(res){
	$('#response').html(res.data)
})

