# SpringBootSecurityLoginTutorial

db setup
1. install mysql
2. create scheme named site  
3. run sql located in static/db
	1.db_structure.sql
	2.db_insert_roles.sql

app setup  
1. configure connection info located in application.properties  

build application  
1. mvn clean
2. mvn clean install
3. Go to the target folder
4. java -jar demo-0.0.1-SNAPSHOT.jar

- http://localhost:8080/registration
- http://localhost:8080/login

