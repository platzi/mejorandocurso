(function(){
	var	X =  150,
		Y = X,
		R = 100,
		STROKE_STYLE = 'rgb(200,0,0)',
		FILL = 'red'

	function drawCircleInCanvas(x, y, r, fill, strokeStyle){
		var canvas = document.getElementsByTagName('canvas')[0],
			ctx = canvas.getContext('2d')

		ctx.beginPath()
		ctx.arc(x, y, r, 0, 2 * Math.PI, false)
		ctx.fillStyle = 'red'
		ctx.fill()
		ctx.strokeStyle = strokeStyle
		ctx.lineWidth = 4
		ctx.stroke()
	}
	
	function drawCircleInSVG(x, y, r, fill, strokeStyle){
		var paper = Raphael('svg-container')
		paper.circle(x, y, r)
			.attr({
				fill: fill,
				stroke: strokeStyle,
				'stroke-width': 4
			})
	}

	drawCircleInCanvas( X, Y, R, FILL, STROKE_STYLE )
	drawCircleInSVG( X, Y, R, FILL, STROKE_STYLE )

}())