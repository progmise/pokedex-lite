package com.certant.app.controllers;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.service.ServicePokemon;
import com.certant.app.utils.UtilParser;
import com.certant.app.utils.WrapperEvolution;
import com.certant.app.utils.WrapperPokemon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSearcher extends HttpServlet {
    /**
     * Envía a la vista, el Pokemon solicitado por uri
     * con los parámetros solicitados, en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServicePokemon sp = new ServicePokemon();
        PrintWriter out = resp.getWriter();
        UtilParser ap = new UtilParser();
        
        List<WrapperEvolution> listWe = new ArrayList<>();
        String json = new String();
        
        try {
            String qs = req.getQueryString();     
            String decode = java.net.URLDecoder.decode(qs, StandardCharsets.UTF_8.name());

            /*
            Example
            {"name":"Bulbasaur","searchOptions":"nombreTiposNivel"}
            */

            WrapperPokemon wp = new Gson().fromJson(decode, WrapperPokemon.class);
            
            Pokemon pk = sp.getPokemon(wp.getName());
            
            List<Evolution> listEvs = new ArrayList<>(pk.getEvolutions());

            Collections.sort(listEvs);
            
            switch (wp.getSearchOption().getValue()) {
                case 0:              
                    wp = ap.parseTypes(pk, wp);                
                    wp.setLevelIsFound(pk.getLevelIsFound());

                    json = new Gson().toJson(wp);
                    break;                
                case 1:              
                    wp = ap.parseAbilities(pk, wp);
                    wp = ap.parseEvolutions(pk, wp);

                    json = new Gson().toJson(wp);
                    break;
                case 2:               
                    for (int i = 0; i < listEvs.size(); i++) {
                        Evolution ev = listEvs.get(i);
                        WrapperEvolution we = new WrapperEvolution();            

                        we.setName(ev.getName());                    
                        we = ap.parseTypes(ev, we);
                        we.setLevelNeeded(ev.getLevelNeeded());

                        listWe.add(we);
                    }
                    wp.setEvolutions(listWe);

                    json = new Gson().toJson(wp);
                    break;
                default:
                    throw new AssertionError();
            }            
            
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
}
