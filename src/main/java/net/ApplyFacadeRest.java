/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import controller.Controller;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import model.Availability;
import model.CompetenceDTO;
import model.CompetenceProfile;
import model.RoleEnum;
import model.Secured;

/**
 *
 * @author Oscar
 */
@Stateless
@Secured({RoleEnum.Applicant})
@Path("apply")
public class ApplyFacadeRest {
    @Inject private Controller controller;
    
    @Context SecurityContext securityContext;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCompetence() {
        List<CompetenceDTO> competences = controller.listCompetence();
        
        GenericEntity<List<CompetenceDTO>> entity= new GenericEntity<List<CompetenceDTO>>(competences){};
        
        if(competences.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(entity).build();
        }
    }

    @POST
    @Path("/competence")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCompetence(List<CompetenceProfile> profiles) {
        controller.addCompetenceProfiles(getUserFromPrincipal(), profiles);
        return Response.noContent().build();
    }
    
    @POST
    @Path("/availability")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerAvailability(List<Availability> availabilities) {
        controller.addAvailabilities(getUserFromPrincipal(), availabilities);
        return Response.noContent().build();
    }
    
    private String getUserFromPrincipal() {
        String user = securityContext.getUserPrincipal().getName();
        return user;
    }
    
}
