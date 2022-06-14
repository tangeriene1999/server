/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsh.steam.resources;

import de.hsh.steam.entities.Genre;
import de.hsh.steam.entities.Score;
import de.hsh.steam.entities.Series;
import de.hsh.steam.entities.Streamingprovider;
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
import org.json.JSONObject;

/**
 *
 * @author luhangfang
 */



@Path("/series")
public class SeriesResource {
    SteamService steamService = SteamService.getInstance();
    
    /*
        1.Get All Series
        @GET
    */
    @Path("/allSeries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSeries(){
        ArrayList<Series> seriesList = steamService.getAllSeries();
        ArrayList<JSONObject> jsonList = new ArrayList();
        for (Series serie : seriesList){
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("title", serie.getTitle());
            jsonobject.put("season", serie.getNumberOfSeasons());
            jsonobject.put("genre", serie.getGenre());
            jsonobject.put("platform", serie.getStreamedBy());
            jsonList.add(jsonobject);
        }
        
        return Response.status(200)
                       .entity(jsonList.toString())
                       .build();
    }
    
    
    /*
        2.Search and return Series
    Attribute:
    username (pflicht)
    genre
    streamingProvider
    score
        @POST
    */
    @Path("/search")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@FormParam("username") String username,
                         @FormParam("genre") String genre,
                         @FormParam("platform") String platform,
                         @FormParam("score") String score){
        try{
            Genre _genre;
            Streamingprovider _platform;
            Score _score;
            
            if (genre.equalsIgnoreCase("thriller")) _genre = Genre.Thriller;
            else if (genre.equalsIgnoreCase("sciencefiction")) _genre = Genre.ScienceFiction;
            else if (genre.equalsIgnoreCase("drama")) _genre = Genre.Drama;
            else if (genre.equalsIgnoreCase("action")) _genre = Genre.Action;
            else if (genre.equalsIgnoreCase("comedy")) _genre = Genre.Comedy;
            else  _genre = Genre.Documentary;
            
            if (platform.equalsIgnoreCase("netflix")) _platform = Streamingprovider.Netflix;
            else if (platform.contains("amazon")) _platform = Streamingprovider.AmazonPrime;
            else  _platform = Streamingprovider.Sky;
            
            if (score.contains("very")) _score = Score.very_good;
            else if(score.contains("bad")) _score = Score.bad;
            else if(score.contains("mediocre")) _score = Score.mediocre;
            else  _score = Score.good;
            
            ArrayList<Series> seriesList = steamService.search(username, _genre, _platform, _score);
            ArrayList<JSONObject> jsonList = new ArrayList();
            for (Series serie : seriesList){
                JSONObject jsonobject = new JSONObject();
                jsonobject.put("title", serie.getTitle());
                jsonobject.put("season", serie.getNumberOfSeasons());
                jsonobject.put("genre", serie.getGenre());
                jsonobject.put("platform", serie.getStreamedBy());
                jsonList.add(jsonobject);
            }
            if (username.equals(""))
                return Response.status(200)
                            .entity("username can not be null!")
                            .build();
            else
                return Response.status(200)
                               .entity(jsonList.toString())
                               .build();
            
            } catch (Exception e){}
        
        return Response.status(200)
                        .entity("search successful")
                        .build();

    }

    
    /*
        3.Add or Modify Series
        @POST
    Caution!
    while testing, input season must be number, otherwise will have a Error 400
    Egal was man eintippt, kÃ¶nnte es eigentlich ein Serie update
    */
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSeries(@FormParam("username") String username,
                               @FormParam("title") String title,
                               @FormParam("season") int season,
                               @FormParam("genre") String genre,
                               @FormParam("platform") String platform,
                               @FormParam("score") String score,
                               @FormParam("remark") String remark){
        try{
            Genre _genre;
            Streamingprovider _platform;
            Score _score;
            
            if (genre.equalsIgnoreCase("thriller")) _genre = Genre.Thriller;
            else if (genre.equalsIgnoreCase("sciencefiction")) _genre = Genre.ScienceFiction;
            else if (genre.equalsIgnoreCase("drama")) _genre = Genre.Drama;
            else if (genre.equalsIgnoreCase("action")) _genre = Genre.Action;
            else if (genre.equalsIgnoreCase("comedy")) _genre = Genre.Comedy;
            else  _genre = Genre.Documentary;
            
            if (platform.equalsIgnoreCase("netflix")) _platform = Streamingprovider.Netflix;
            else if (platform.contains("amazon")) _platform = Streamingprovider.AmazonPrime;
            else _platform = Streamingprovider.Sky;
            
            if (score.contains("very")) _score = Score.very_good;
            else if(score.contains("bad")) _score = Score.bad;
            else if(score.contains("mediocre")) _score = Score.mediocre;
            else _score = Score.good;
            
            steamService.addOrModifySeries(title, season, Genre.Drama, Streamingprovider.Sky, username, Score.good, remark);

            
        } catch (Exception e){
            return "input error";
        } finally { 
            return "update success";
        }
        
       
    }
  
    /*
        4.All Series of a Title
    */
    @Path("/allSeriesWT/{title}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSeriesWithTitle(@PathParam("title") String title){
        ArrayList<Series> seriesList = steamService.getAllSeriesWithTitle(title);
        ArrayList<JSONObject> jsonList = new ArrayList();
        for (Series serie : seriesList){
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("title", serie.getTitle());
            jsonobject.put("season", serie.getNumberOfSeasons());
            jsonobject.put("genre", serie.getGenre());
            jsonobject.put("platform", serie.getStreamedBy());
            jsonList.add(jsonobject);
        }
        
        return Response.status(200)
                       .entity(jsonList.toString())
                       .build();
    }
    
    /*
        5.All Series of a User
    */
    @Path("/allSeriesOU/{username}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSeriesOfUser(@PathParam("username") String username){
        ArrayList<Series> seriesList = steamService.getAllSeriesOfUser(username);
        ArrayList<JSONObject> jsonList = new ArrayList();
        for (Series serie : seriesList){
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("username",username);
            jsonobject.put("title", serie.getTitle());
            jsonobject.put("season", serie.getNumberOfSeasons());
            jsonobject.put("genre", serie.getGenre());
            jsonobject.put("platform", serie.getStreamedBy());
            jsonList.add(jsonobject);
        }
        
        return Response.status(200)
                       .entity(jsonList.toString())
                       .build();
    }
    
    /*
        6.Get a Series Rating
    */
    @Path("/rating/{seriesname}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRating(@FormParam("seriesname") String seriesname,
                            @FormParam("username") String username){
        
        return steamService.getRating(seriesname, username).toString();
    }
    
    /*
        7.Delete a Serie
        @POST
    */


    
    
}