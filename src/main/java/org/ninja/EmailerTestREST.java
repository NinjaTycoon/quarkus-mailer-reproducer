package org.ninja;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@Path("test/emailer")
public class EmailerTestREST {
	String receiver = "you@whereever.com";
	@Inject
	Mailer mailer;

	@GET
	@Path("simple")
	public Response sendASimpleEmail() {
	    mailer.send(Mail.withText(receiver, "A simple email from quarkus", "This is my body"));
	    return Response.accepted("OK").build();
	}	
}
