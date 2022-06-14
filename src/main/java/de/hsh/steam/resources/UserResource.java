/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsh.steam.resources;

import de.hsh.steam.entities.User;
import de.hsh.steam.repositories.SerializedSeriesRepository;
import de.hsh.steam.services.SteamService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import com.sun.jersey.core.header.FormDataContentDisposition;



/**
 *
 * @author luhangfang
 */
@Path("/user")
public class UserResource {
   
    SteamService steamService = SteamService.getInstance();
    /*
        1.Log in
        @POST
    */
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserData user)
    {
        if (steamService.login(user.getUsername(), user.getPassword())){
            Message mg =new Message("login success!");
            return Response.status(Response.Status.OK)
                    .entity(mg)
                    .build();
        }else{
            Message mg =new Message("login false!");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(mg)
                    .build();
        }
        }
    
    /*
        2.New User
        @POST
    */
    @Path("/newUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(UserData user)
    {
        boolean ok=steamService.newUser(user.getUsername(), user.getPassword());
        if (ok){
            Message mg=new Message("User "+user.getUsername()+" created!");
            System.out.print("New User: "+user.getUsername()+" created!");
            return Response.status(Response.Status.CREATED)
                    .entity(mg)
                    .build();
        }else{
            Message mg=new Message("create failed!");
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(mg)
                    .build();
        }
    }
    

    
    
}
