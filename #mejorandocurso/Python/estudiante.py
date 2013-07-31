class Estudiante(object):
	def __init__(self, nombre, edad):
		self.nombre = nombre
		self.edad = edad 
        
	def hola(self):
		return 'Hola soy: %s' % self.nombre
        
e = Estudiante("Miguel",22)
print e.hola()