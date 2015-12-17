## Sring Rest + HATEOAS (Hypermedia as the Engine of Application State)

This is a simple example of how to use the framework spring-hateoas in your rest project in order to 
make your services complain with HATEAOS constraint. HATEOAS is a principle in the rest resources
that allows the client interacts with the api entirely through dynamic links provided by the server.

The links generated for the api resources could contain variables, paths, different domain names, etc
which makes a little difficult to  build this urls manually. Spring Hateoas framework provides an easy
api to generate these links. 

If you want to know more about [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS) or more about 
[spring-hateoas](http://spring.io/guides/gs/rest-hateoas/).


#### Explanation
This is a simple gradle - spring-boot application with rest and hateoas capabilities enabled. To run it
just execute the command `gradle bootRun` in your project and visit the url 
[http://localhosst:8080/accounts](http://localhosst:8080/accounts)



#### Swagger
Swagger configuration has been added to this project in order to document the restful api using swagger-spring-mvc.
Also swagger-spring-mvc-ui has been added in order to display the swagger ui application for this application.
In order to access the swagger ui application visit the url [http://localhost:8080/sdoc.jsp](http://localhost:8080/sdoc.jsp).
The api-docs will be served from [http://localhost:8080/api-docs](http://localhost:8080/api-docs).


