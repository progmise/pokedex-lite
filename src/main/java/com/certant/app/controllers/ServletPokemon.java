package com.certant.app.controllers;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.service.ServiceEvolution;
import com.certant.app.service.ServicePokemon;
import com.certant.app.utils.UtilParser;
import com.certant.app.utils.WrapperEvolution;
import com.certant.app.utils.WrapperPokemon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletPokemon extends HttpServlet {
    /**
     * Envía a la vista, el Pokemon solicitado por uri, en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        UtilParser up = new UtilParser();
        
        try {
            String qs = req.getQueryString();
            String decode = java.net.URLDecoder.decode(qs, StandardCharsets.UTF_8.name());
 
            WrapperPokemon wp = up.parsePokemonToWrapper(decode);

            String json = new Gson().toJson(wp);
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.println(json);            
        } 
        catch (JsonSyntaxException jse) {
            resp
                .getWriter()
                .println(String.join("No se pudo leer el JSON"));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);            
        }
        catch (PokedexException pe) {
            resp
                .getWriter()
                .println("No se pudo obtener el Pokemon de la base de datos");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (IOException ioe) {
            throw new ServletException();
        }        
    }
    
    /**
     * Agrega un Pokemon, enviado por la vista en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServicePokemon sp = new ServicePokemon();
        UtilParser up = new UtilParser();
        StringBuilder json = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

            for (String str = br.readLine(); str != null; str = br.readLine()) {
                json.append(str);
            }             
            
            Pokemon pk = up.parseJsonToPokemon(json.toString());
            sp.addPokemon(pk);
            
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
        catch (JsonSyntaxException jse) {
            resp
                .getWriter()
                .println(String.join("No se pudo leer el JSON"));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        catch (PokedexException pe) {
            resp
                .getWriter()
                .println("No se pudo guardar el Pokemon en la base de datos");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (IOException ioe) {
            throw new ServletException();
        } 
    }
    
    /**
     * Actualiza un Pokemon, enviado por la vista con los
     * atributos modificados en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServicePokemon sp = new ServicePokemon();
        ServiceEvolution se = new ServiceEvolution();
        UtilParser up = new UtilParser();
        StringBuilder json = new StringBuilder();
        
        WrapperEvolution we = new WrapperEvolution();
        
        /*
        Example
        { json_pokemon:{"id":"2","name":"Charmander","types":["Normal","Fire","Fighting"],"levelIsFound":"10"}, 
        json_evolucion:{"name":"Charizard","types":["Fighting","Dragon"],"levelNeeded":"24"} }
        */        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));  

            for (String s = br.readLine(); s != null; s = br.readLine()) {
                json.append(s);
            }
            
            String jsonPk = json.substring(json.indexOf("{\""), json.indexOf(", "));
            String jsonEv = json.substring(json.lastIndexOf("{\""), json.indexOf(" }"));
            
            try {
                we = new Gson().fromJson(jsonEv, WrapperEvolution.class);
            } catch (Exception e) {
            }
            
            Pokemon pk = up.parseJsonToPokemon(jsonPk);
            sp.updatePokemon(pk.getId(), pk);
            
            if (we.isValid()) {
                Evolution ev = up.parseJsonToEvolution(jsonEv);
                
                se.addEvolution(ev);
                sp.addPokemonEvolution(pk.getId(), ev);                
            }
            
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (JsonSyntaxException jse) {
            resp
                .getWriter()
                .println(String.join("No se pudo leer el JSON"));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);                    
        }
        catch (PokedexException pe) {
            resp
                .getWriter()
                .println("No se pudo actualizar el Pokemon en la base de datos");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        catch (IOException ioe) {
            throw new ServletException();
        }         
    }        
}