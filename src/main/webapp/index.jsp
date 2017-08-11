
<html>
<title>Web service using Spring web mvc + Oauth2</title>
<body>
<h2 align="middle">Spring webservice with Oauth2 login!</h2>
<p>This provides you  to login using oauth2 and get xml data against employee table.</p>
<ol>
    <li>Access token URL - <a href="http://localhost:8080/spring2/oauth/token?grant_type=password&username=admin&password=admin123">http://localhost:8080/spring2/oauth/token?grant_type=password&username={username}&password={password}</a>, Request type: POST, username and password is that one which store in employee table.</li>
        * Need client id and password, Client ID: example-client, password: secret
    <li>Others request URL -
         <li>Employee list url - <a href = "http://localhost:8080/spring2/employee/list?access_token={access_token}">http://localhost:8080/spring2/employee/list?access_token={access_token}</a>, Request type: GET</li>
         <li>Employee details url - <a href = "http://localhost:8080/spring2/employee/{username}?access_token={access_token}">http://localhost:8080/spring2/employee/{username}?access_token={access_token}</a>, Request type: GET</li>
         <li>Employee delete url - <a href = "http://localhost:8080/spring2/employee/{username}?access_token={access_token}">http://localhost:8080/spring2/employee/{username}?access_token={access_token}</a>, Request type: DELETE</li>
         <li>Employee add url - <a href = "http://localhost:8080/spring2/employee/?access_token={access_token}">http://localhost:8080/spring2/employee/?access_token={access_token}</a>, Request type: POST, Content-Type: application/xml; charset="utf-8", Request body: xml of employee, minimal would be
            <xmp>
                <Employee>
                    <username>test_user</username>
                    <password>test_password</password>
                    <phone>454656</phone>
                    <firstName>test</firstName>
                </Employee>
            </xmp>
         </li>
         <li>Employee update url - <a href = "http://localhost:8080/spring2/employee/?access_token={access_token}">http://localhost:8080/spring2/employee/?access_token={access_token}</a>, Request type: PUT, Content-Type: application/xml; charset="utf-8", Request body: xml of employee like, minimal would be
            <xmp>
                <Employee>
                 <username>test_user</username>
                 <phone>3434343434</phone>
                </Employee>
            </xmp>
         </li>
     </li>
</ol>
</body>
</html>
