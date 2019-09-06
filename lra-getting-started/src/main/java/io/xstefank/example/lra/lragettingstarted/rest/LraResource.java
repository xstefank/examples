package io.xstefank.example.lra.lragettingstarted.rest;

import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.lra.annotation.ParticipantStatus;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/lra")
public class LraResource {

    @GET
    @Path("/perform")
    @LRA(LRA.Type.REQUIRES_NEW)
    public Response performOperation(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) URI lraId) {
        System.out.println("Performing operation inside the LRA with LRA id = " + lraId);

        return Response.ok().build();
    }

    @PUT
    @Path("/compensate")
    @Compensate
    public Response compensate(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) URI lraId) {
        System.out.println("Compensation called for LRA id = " + lraId);

        return Response.ok(ParticipantStatus.Compensated).build();
    }
    
    @PUT
    @Path("/complete")
    @Complete
    public Response complete(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) URI lraId) {
        System.out.println("Complete called for LRA id = " + lraId);

        return Response.ok(ParticipantStatus.Completed).build();
    }
}
