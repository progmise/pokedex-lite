package com.certant.app.controllers;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Pokemon;
import com.certant.app.service.ServicePokemon;
import com.certant.app.utils.UtilParser;
import com.certant.app.utils.WrapperPokemon;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletList extends HttpServlet {
    /**
     * Envía a la vista, el listado de Pokemones en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServicePokemon sp = new ServicePokemon();
        PrintWriter out = resp.getWriter();
        UtilParser up = new UtilParser();
        
        List<WrapperPokemon> listWp = new ArrayList<>();
        
        try {
            Map<String, String> mapParams = up.parseEnumerationToMap(req.getParameterNames(), req);

            Integer startPosition = Integer.parseInt(mapParams.get("pagina"))*10;
            Integer maxResults = Integer.parseInt(mapParams.get("cantidad"));

            List<Pokemon> pokemons = new ArrayList<>(sp.getPokemons(startPosition, maxResults));

            Collections.sort(pokemons);

            for (int i = 0; i < pokemons.size(); i++) {
                Pokemon pk = pokemons.get(i);
                WrapperPokemon wp = new WrapperPokemon();           

                wp.setId(pk.getId());
                wp.setName(pk.getName());
                wp = up.parseTypes(pk, wp);
                wp.setLevelIsFound(pk.getLevelIsFound());

                listWp.add(wp);
            }

            String json = new Gson().toJson(listWp);
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.println(json);            
        } 
        catch (PokedexException pe) {
            resp
                .getWriter()
                .println("No se pudo obtener el listado de Pokemons de la base de datos");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }        
    }
}