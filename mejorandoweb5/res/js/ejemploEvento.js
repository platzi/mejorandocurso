// JQUERY TRAVERSING
var lis = $('.summary-list li')
lis.on('click', agrandarFuente)
function agrandarFuente(){
	$(this).css('font-size', '2em')
}