from django.contrib.auth.models import User, check_password

class EmailBackend(object):
	def authenticate(self,email=None,password=None):
		try:
			user=User.objects.get(email=email)

			if user.check_password(password):
				return user
		except User.DoesNotExist:
			return None

	def get_user(self,user_id):
		try:
			return User.objects.get(id=user_id)
		except User.DoesNotExists:
			return None