package com.certant.app.controllers;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Type;
import com.certant.app.service.ServiceType;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletType extends HttpServlet {
    /**
     * Envía a la vista, el listado de Tipos en formato JSON
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ServiceType st = new ServiceType();
        
        try {
            List<Type> types = new ArrayList<>(st.getTypes());
            
            for (Type tmpType : types) {
                tmpType.setEvolutions(null);
                tmpType.setPokemons(null);
            }

            Collections.sort(types);

            String json = new Gson().toJson(types);            
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.println(json);            
        } 
        catch (PokedexException pe) {
            resp
                .getWriter()
                .println("No se pudo obtener los Tipos de la base de datos");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }      
    }    
}