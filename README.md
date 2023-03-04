# feature-switch
## Setup 
Run the following command to generate docker image

```shell
docker build -t feature-switch . 
```


The start the application on 8080 port of your local machine, run the following command

```shell 
docker run -p 8080:8080 -t feature-switch
```

The application should start and localhost port 8080. Swagger API documentation should be accessible from the following url

http://localhost:8080/swagger-ui/index.html

