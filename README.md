# code-with-quarkus Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Testing the Mailer

You can launch this in dev.  If you want to use the scripts provided, then

chmod 775 start*
chmod 775 build*
chmod 775 run*

When you hit this REST URL with your browser or any client, you should get back an OK:

http://localhost:9090/test/emailer/simple

Initially, it runs in dev with the mock mailer and outputs to the log.

You'll then need to test your SMTP server with dev by adding configuration to application.yml in src/main/resources.  You can use the application-template.yml to copy the settings you need and change PATH_TO_TRUSTSTORE, YOURDOMAIN and YOURDOMAIN_SMTP_HOST.

This also has "changeit" for your truststore password.  Change it if necessary.  

Your truststore should include the TLS cert of the SMTP server you'll be talking to.  

Also copy start2.template to start2 and update it with the path to your truststore.  Run that to launch dev using the truststore.  

Once this is configured, you should get an OK when you hit the REST service.  

If you also change the ```receiver``` String in EmailerTestREST.java to your email address, you should receive the test email in your inbox.  

### Testing in container

So far so good hopefully.  Now that you have it working in dev, just have to build and run the container since you already configured it in application.yml.  This presumes you are already setup for and comfortable with docker.

Run the build-docker script.  

./build-docker

Copy run-docker.template to run-docker and chmod 775 on it.  edit it and change USER.  You can verify the image name with 

```
docker images|grep quarkus-mailer-reproducer
```

Now when you ```./run-docker``` and hit the REST TEST URL, you should see "Internal Server Error" in your browser, and in the logs

Caused by: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related Guides

- YAML Configuration ([guide](https://quarkus.io/guides/config#yaml)): Use YAML to configure your Quarkus application
- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
