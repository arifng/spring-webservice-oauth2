1. At first, run _mvn clean install_ to build repository. Then, run as web service using this command - _mvn jetty:run_.
2. To get access token, request the following URL from any REST client (Example - Advanced Rest Client):
	- http://localhost:8080/spring2/oauth/token?grant_type=password&username={username}&password={password}
		Note that: username and password will be any username and corresponding password according to database.
	- To get access token you need another authentication i.e client username and password which is hard coded in project. Client username: example-client, password: secret
	- Request type: POST
	- Response would be like:
	`<DefaultOAuth2AccessToken>
        <access_token>aa430eec-04ba-4e28-9c54-747e9a72b60e</access_token>
        <token_type>bearer</token_type>
        <refresh_token>4b83093a-08b4-4589-b19b-a59e9d25adf9</refresh_token>
        <expires_in>415</expires_in>
        <scope>read write trust</scope>
    </DefaultOAuth2AccessToken>`
3. Using the access_token, now operate CRUD operation by following URL:
	- GET: Employee Details -> http://localhost:8080/spring2/employee/{username}?access_token={access_token}
	- GET: List -> http://localhost:8080/spring2/employee/list?access_token={access_token}
	- POST: Create Employee -> http://localhost:8080/spring2/employee?access_token={access_token}
	    - Content-Type: application/xml
	    - Request body xml format (example)
	    `<Employee>
	        <username>test_user</username>
	        <password>test_password</password>
	        <firstname>First Name</firstname>
	    </Employee>`
	    
	- PUT: Update -> http://localhost:8080/spring2/employee?access_token={access_token}
	    - Content-Type: application/xml
     	- Request body xml format (example)
	    <Employee>
            <username>test_user</username>
            <password>updated_password</password>
            <firstname>Updated First Name</firstname>
        </Employee>
	- DELETE: Delete -> http://localhost:8080/spring2/employee/{username}?access_token={access_token}