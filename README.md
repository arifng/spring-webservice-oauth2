1. Run as web service in Jetty server.
2. Request access_token to access resources using following URL from any REST client:
	- http://localhost:8080/spring2/oauth/token?grant_type=password&username={username}&password={password}
		Note that: username and password will be any username and corresponding password according to database.
	- To get access token you need another authentication i.e client username and password which hard coded. Client username: my-trusted-client, password: secret
	- Request type: POST
3. Using the access_token, you can now operate CRUD operation by following URL:
	- GET: Employee Details -> http://localhost:8080/spring2/employee/{username}?access_token=368d5b8e-1736-4250-98ba-f4cb79ac36e1
	- GET: List -> http://localhost:8080/spring2/employee/list?access_token=368d5b8e-1736-4250-98ba-f4cb79ac36e1
	- POST: Create Employee -> http://localhost:8080/spring2/employee?access_token=368d5b8e-1736-4250-98ba-f4cb79ac36e1 and request body with employee data in xml format.
	- PUT: Update -> http://localhost:8080/spring2/employee?access_token=368d5b8e-1736-4250-98ba-f4cb79ac36e1 and request body with employee data in xml format.
	- DELETE: Delete -> http://localhost:8080/spring2/employee/{username}?access_token=368d5b8e-1736-4250-98ba-f4cb79ac36e1