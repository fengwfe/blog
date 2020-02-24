基于jwt的认证和授权,实现以下效果



1.server processing logic for login:

	     -->success-->return jwt token
login-->|
	     -->failed-->401


2.server processing logic for logout:

logout-->mark jwt token as invalid by comparing the IssuedAt date of jwt token to the logout date,all tokens with IssuedAt date before logout date will be treated as invalid


3.server processing logic when user access secured resource:
												 								  -->need refresh token-->add new token into response header
											  -->has privilege-->allow to access  |
  jwt token from http header--> token is valid|									  --> no need refresh token--> add original token into response header 
							|	              -->no privilege-->403
							|							
							|							
							|							
							--> not token found or token is invalid-->401



4. for API caller, need to get token from response header and use it during the next call



Notes:
1. AuthenticationFailureHandler vs AuthenticationEntryPoint

AuthenticationEntryPoint tells unauthenticated users where to authenticate, for example, by redirecting them to a login form
Authentication fail掉的时候不throw exception AuthenticationEntryPoint就会被call到

https://stackoverflow.com/questions/39512849/spring-boot-spring-security-authenticationentrypoint-not-getting-executed