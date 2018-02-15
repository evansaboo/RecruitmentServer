/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Applications;
import model.Competence;

/**
 *
 * @author Evan
 */
@Stateless
@Path("applications")
public class ApplicationsREST {

    @Inject
    private Controller contr;

    public ApplicationsREST() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("initAppListing")
    public JsonArray initFromClient(JsonObject entity) {
        JsonArray list = null;
        switch (entity.getString("type")) {
            case "getAllCompetences":
                List<Competence> comps = contr.getAllCompetences();
                list = compListToJsonArray(comps);
                break;
            case "getAllJobApplications":
                List<Applications> applications = contr.getAllApplications();
                list = appListToJsonArray(applications);

        }
        return list;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("searchApplications")
    public JsonArray searcApplications(JsonObject entity) {
        JsonArray list = null;
        switch (entity.getString("type")) {
            case "searchApplications":
                Date regDate = null;
                try{
                 regDate = new SimpleDateFormat("d-MM-yyyy").parse(entity.getString("subDate"));
                } catch(ParseException e){
                    try{
                        System.out.println("hello");
                    regDate = new SimpleDateFormat("d-MM-yyyy").parse("1-11-0000");
                    } catch(ParseException ex){
                        System.out.println("hello1");
                    }
                }
                
                List<Applications> applications
                        = contr.getApplications(
                                regDate,
                                entity.getString("periodFrom"),
                                entity.getString("periodTo"),
                                Long.parseLong(entity.getString("competence")),
                                entity.getString("name"));
                list = appListToJsonArray(applications);

        }
        return list;
    }

    public JsonArray compListToJsonArray(List<Competence> list) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Competence l : list) {
            builder.add(l.toJson().build());
        }
        return builder.build();
    }

    public JsonArray appListToJsonArray(List<Applications> list) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Applications l : list) {
            builder.add(l.toJson());
        }
        return builder.build();
    }
}
