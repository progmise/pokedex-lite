package com.certant.app.utils;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import com.certant.app.service.ServicePokemon;
import com.certant.app.service.ServiceType;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UtilParser {
    /**
     * Retorna un WrapperPokemon, formatea los Tipos del Pokemon
     * @param pk
     * @param wp
     * @return 
     */
    public WrapperPokemon parseTypes(Pokemon pk, WrapperPokemon wp) {
        List<Type> listTypes = new ArrayList<>(pk.getTypes());
        
        Collections.sort(listTypes);
        /*
        De contar con más de un Tipo, se asignará una lista, de lo contrario, 
        ya sea si cuenta con un Tipo o ninguno, se asignará un string
        */
        if (listTypes.size() == 1) {
            String type = listTypes.get(0).getName();

            wp.setTypes(type);
        } 
        else if (listTypes.size() > 1) {
            List<String> types = new ArrayList<>();

            for (int i = 0; i < listTypes.size(); i++) {
                types.add(listTypes.get(i).getName());
            }

            wp.setTypes(types);
        } 
        else {
            String type = "";

            wp.setTypes(type);
        }
        
        return wp;
    }
    
    /**
     * Retorna un WrapperEvolution, formatea los Tipos de la Evolucion
     * @param ev
     * @param we
     * @return 
     */
    public WrapperEvolution parseTypes(Evolution ev, WrapperEvolution we) {
        List<Type> listTypes = new ArrayList<>(ev.getTypes());
        
        Collections.sort(listTypes);         
        /*
        De contar con más de un Tipo, se asignará una lista, de lo contrario, 
        ya sea si cuenta con un Tipo o ninguno, se asignará un string
        */    
        if (listTypes.size() == 1) {
            String type = listTypes.get(0).getName();

            we.setTypes(type);
        } 
        else if (listTypes.size() > 1) {
            List<String> types = new ArrayList<>();

            for (int j = 0; j < listTypes.size(); j++) {
                types.add(listTypes.get(j).getName());
            }

            we.setTypes(types);
        } 
        else {
            String type = "";

            we.setTypes(type);
        }
        
        return we;
    }
    
    /**
     * Retorna un Pokemon, formatea los Tipos del WrapperPokemon
     * @param wp
     * @param pk
     * @return
     * @throws PokedexException 
     */
    public Pokemon parseTypes(WrapperPokemon wp, Pokemon pk) throws PokedexException {
        ServiceType st = new ServiceType();
        /*
        De tratarse de una lista de Tipos, se buscará por nombre cada Tipo
        a través del Servicio, para luego asignarlo al Pokemon.
        Si se tratara de un solo elemento, el mismo será String y distinto de
        una cadena vacía, y se aplicará el mismo criterio.
        */         
        if (wp.getTypes().getClass().getSimpleName().equals("String") && !((String) wp.getTypes()).equals("")) {
            try {
                pk.getTypes().add(st.getType((String) wp.getTypes()));
            } 
            catch (Exception ex) {
                //Captura la excepcion original, y lanza una excepcion de parseo
                PokedexException pe = new PokedexException(ex);
                pe.setTechnicalError(ex.getMessage());
                pe.setParseError("Imposible parsear Tipos para Pokemon");
                throw pe;
            }
        } 
        else if (wp.getTypes().getClass().getSimpleName().equals("ArrayList")) {
            for (String stTmp : (ArrayList<String>) wp.getTypes()) {
                try {
                    pk.getTypes().add(st.getType(stTmp));
                } 
                catch (Exception ex) {
                    //Captura la excepcion original, y lanza una excepcion de parseo
                    PokedexException pe = new PokedexException(ex);
                    pe.setTechnicalError(ex.getMessage());
                    pe.setParseError("Imposible parsear Tipos para Pokemon");
                    throw pe;
                }
            }
        }
        
        return pk;
    }
    
    /**
     * Retorna una Evolucion, formatea los Tipos del WrapperEvolution
     * @param we
     * @param ev
     * @return
     * @throws PokedexException 
     */
    public Evolution parseTypes(WrapperEvolution we, Evolution ev) throws PokedexException {
        ServiceType st = new ServiceType();
        /*
        De tratarse de una lista de Tipos, se buscará por nombre cada Tipo
        a través del Servicio, para luego asignarlo a la Evolucion.
        Si se tratara de un solo elemento, el mismo será String y distinto de
        una cadena vacía, y se aplicará el mismo criterio.
        */             
        if (we.getTypes().getClass().getSimpleName().equals("String") && !((String)we.getTypes()).equals("")) {
            try {
                ev.getTypes().add(st.getType((String)we.getTypes()));
            } 
            catch (Exception ex) {
                //Captura la excepcion original, y lanza una excepcion de parseo
                PokedexException pe = new PokedexException(ex);
                pe.setTechnicalError(ex.getMessage());
                pe.setParseError("Imposible parsear Tipos para Evolucion");
                throw pe;
            }    
        }
        else if (we.getTypes().getClass().getSimpleName().equals("ArrayList")) {
            for (String stTmp : (ArrayList<String>)we.getTypes()) {
                try {
                    ev.getTypes().add(st.getType(stTmp));
                } 
                catch (Exception ex) {
                    //Captura la excepcion original, y lanza una excepcion de parseo
                    PokedexException pe = new PokedexException(ex);
                    pe.setTechnicalError(ex.getMessage());
                    pe.setParseError("Imposible parsear Tipos para Evolucion");
                    throw pe;
                }                
            }                
        }        
        
        return ev;
    }
    
    /**
     * Retorna un WrapperPokemon, formatea las Habilidades del Pokemon
     * @param pk
     * @param wp
     * @return 
     */
    public WrapperPokemon parseAbilities(Pokemon pk, WrapperPokemon wp) {
        List<Ability> listAbilities = new ArrayList<>(pk.getAbilities());
        
        Collections.sort(listAbilities);
        /*
        De contar con más de una Habilidad, se asignará una lista, de lo contrario, 
        ya sea si cuenta con una Habilidad o ninguna, se asignará un string
        */        
        if (listAbilities.size() == 1) {
            String ability = listAbilities.get(0).getName();

            wp.setAbilities(ability);
        } 
        else if (listAbilities.size() > 1) {
            List<String> abilities = new ArrayList<>();

            for (int i = 0; i < listAbilities.size(); i++) {
                abilities.add(listAbilities.get(i).getName());
            }

            wp.setAbilities(abilities);
        } 
        else {
            String ability = "";

            wp.setAbilities(ability);
        }
        
        return wp;
    }
    
    /**
     * Retorna un WrapperPokemon, formatea las Evoluciones del Pokemon
     * @param pk
     * @param wp
     * @return 
     */
    public WrapperPokemon parseEvolutions(Pokemon pk, WrapperPokemon wp) {
        List<Evolution> listEvolutions = new ArrayList<>(pk.getEvolutions());
        
        Collections.sort(listEvolutions);
        /*
        De contar con más de una Evolucion, se asignará una lista, de lo contrario, 
        ya sea si cuenta con una Evolucion o ninguna, se asignará un string
        */    
        if (listEvolutions.size() == 1) {
            String evolution = listEvolutions.get(0).getName();

            wp.setEvolutions(evolution);
        } 
        else if (listEvolutions.size() > 1) {
            List<String> evolutions = new ArrayList<>();

            for (int i = 0; i < listEvolutions.size(); i++) {
                evolutions.add(listEvolutions.get(i).getName());
            }

            wp.setEvolutions(evolutions);
        } 
        else {
            String evolution = "";

            wp.setEvolutions(evolution);
        }
        
        return wp;
    }
    
    /**
     * Retorna un Pokemon, a partir de un JSON
     * @return
     * @throws PokedexException 
     */
    public Pokemon parseJsonToPokemon(String json) throws PokedexException {
        /*
        A partir de un envoltorio de Pokemon, generado con los datos almacenados 
        de un JSON, se genera un Pokemon
        */ 
        WrapperPokemon wp = new Gson().fromJson(json, WrapperPokemon.class);

        Pokemon pk = new Pokemon();
        
        pk.setId(wp.getId());
        pk.setName(wp.getName());
        pk = parseTypes(wp, pk);
        pk.setLevelIsFound(wp.getLevelIsFound());
        
        return pk;
    }
    
    /**
     * Retorna una Evolucion, a partir de un JSON
     * @param json
     * @return
     * @throws PokedexException 
     */
    public Evolution parseJsonToEvolution(String json) throws PokedexException {
        /*
        A partir de un envoltorio de Evolucion, generado con los datos almacenados 
        de un JSON, se genera una Evolucion
        */        
        WrapperEvolution we = new Gson().fromJson(json, WrapperEvolution.class);
        
        Evolution ev = new Evolution();
        
        ev.setName(we.getName());
        ev = parseTypes(we, ev);
        ev.setLevelNeeded(we.getLevelNeeded());
        
        return ev;
    }
    
    /**
     * Retorna un WrapperPokemon, a partir de un JSON
     * @param json
     * @return
     * @throws PokedexException 
     */
    public WrapperPokemon parsePokemonToWrapper(String json) throws PokedexException {
        /*
        A partir de un envoltorio de Pokemon, generado con los datos almacenados 
        de un JSON, se recupera un Pokemon con el Servicio, y se completan
        los datos restantes del envoltorio de Pokemon
        */         
        ServicePokemon sp = new ServicePokemon();
        
        WrapperPokemon wp = new Gson().fromJson(json, WrapperPokemon.class);
        Pokemon pk = sp.getPokemon(wp.getName());
        
        wp.setId(pk.getId());
        wp = parseTypes(pk, wp);
        wp.setLevelIsFound(pk.getLevelIsFound());
        
        return wp;
    }
    
    /**
     * Retorna un Map, a partir de un Enumeration y un Request
     * @param params
     * @param req
     * @return 
     */
    public Map<String, String> parseEnumerationToMap(Enumeration<String> params, HttpServletRequest req) {
        Map<String, String> mapParams = new HashMap<>();
        
        while (params.hasMoreElements()) {
            String keyParam = params.nextElement();
            String valParam = req.getParameter(keyParam);
            mapParams.put(keyParam, valParam);
        }
        
        return mapParams;
    }
}