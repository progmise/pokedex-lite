package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicePokemon {
    private final ServiceType st = new ServiceType();

    /**
     * Agrega un Pokemon
     * @param pokemon
     * @throws PokedexException 
     */
    public void addPokemon(Pokemon pokemon) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        /*
        Se recorre el Set de Tipos, Habilidades y Evoluciones 
        recuperados del Pokemon para persistir en cada uno de los Tipos,
        Habilidades y Evoluciones, el Pokemon, ya que estan relacionados
        */
        try {
            for (Type tmpTp : pokemon.getTypes()) {
                tmpTp.getPokemons().add(pokemon);
                s.update(tmpTp);
            }

            for (Ability tmpAy : pokemon.getAbilities()) {
                tmpAy.getPokemons().add(pokemon);
                s.update(tmpAy);
            }

            for (Evolution tmpEv : pokemon.getEvolutions()) {
                tmpEv.setPokemon(pokemon);
                s.update(tmpEv);
            }        

            s.save(pokemon);            
        } 
        catch (Exception ex) {
            try {
                // Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            // Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible agregar el Pokemon");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
    }

    /**
     * Retorna un Pokemon, buscado por el nombre
     * @param name
     * @return
     * @throws PokedexException 
     */
    public Pokemon getPokemon(String name) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Pokemon pokemon;
        
        try {
            Query query = s.createQuery("from Pokemon p where p.name = :name");
            query.setString("name", name);

            pokemon = (Pokemon) query.list().get(0);             
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener el Pokemon por nombre");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemon;
    }

    /**
     * Retorna un Pokemon, buscado por el id
     * @param pokemonId
     * @return
     * @throws PokedexException 
     */
    public Pokemon getPokemon(Long pokemonId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Pokemon pokemon;
        
        try {
            Query query = s.createQuery("from Pokemon p where p.id = :pokemonId");
            query.setLong("pokemonId", pokemonId);

            pokemon = (Pokemon) query.list().get(0);           
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener el Pokemon por id");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return pokemon;
    }    

    /**
     * Retorna todos los Pokemones
     * @return
     * @throws PokedexException 
     */
    public Set<Pokemon> getPokemons() throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Pokemon> pokemons;
        
        try {
            Query query = s.createQuery("from Pokemon");
            pokemons = new HashSet<>(query.list());       
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener los Pokemons");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemons;
    }
    
    /**
     * Retorna los Pokemones, desde una posición inicial
     * y con una máximo de resultados
     * @param startPosition
     * @param maxResults
     * @return
     * @throws PokedexException 
     */
    public Set<Pokemon> getPokemons(Integer startPosition, Integer maxResults) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Pokemon> pokemons;
        
        try {
            Query query = s.createQuery("from Pokemon");
            query.setFirstResult(startPosition);
            query.setMaxResults(maxResults);
            pokemons = new HashSet<>(query.list());       
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener los Pokemons");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemons;
    }    
        
    /**
     * Actualiza los datos de un Pokemon, a partir del mismo Pokemon modificado
     * El Pokemon original se busca por id
     * @param pokemonId
     * @param modifiedPokemon
     * @throws PokedexException 
     */
    public void updatePokemon(Long pokemonId, Pokemon modifiedPokemon) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);

        for (Type tmpTp : pokemon.getTypes()) {
            removePokemonType(pokemon.getId(), tmpTp.getId());
        }
      
        for (Type tmpTp : modifiedPokemon.getTypes()) {
            addPokemonType(pokemon.getId(), tmpTp.getId());
        }
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        try {
            pokemon.setName(modifiedPokemon.getName());
            
            pokemon.setLevelIsFound(modifiedPokemon.getLevelIsFound());

            s.update(pokemon);            
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible actualizar el Pokemon");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
    }
    
    /**
     * Actualiza el nombre de un Pokemon, a partir de un nombre ingresado
     * El Pokemon original, se busca por id
     * @param pokemonId
     * @param nameExpected
     * @throws PokedexException 
     */
    public void modifyPokemonName(Long pokemonId, String nameExpected) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
           
        try {
            pokemon.setName(nameExpected);
            s.update(pokemon);   
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible modificar el nombre del Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    /**
     * Actualiza el nivel de un Pokemon, a partir de un nivel ingresado
     * El Pokemon original, se busca por id
     * @param pokemonId
     * @param lvlExpected
     * @throws PokedexException 
     */
    public void modifyPokemonLevel(Long pokemonId, Integer lvlExpected) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
           
        try {
            pokemon.setLevelIsFound(lvlExpected);
            s.update(pokemon);   
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible modificar el nivel del Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    /**
     * Agrega un Tipo a un Pokemon, a partir del id de un Tipo
     * El Pokemon original, se busca por id
     * @param pokemonId
     * @param typeId
     * @throws PokedexException 
     */
    public void addPokemonType(Long pokemonId, Long typeId) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        Type type = st.getType(typeId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        /*
        Se agregan las relaciones tanto desde el punto de vista del Pokemon
        para con los Tipos y para el Tipo para con los Pokemons
        */
        try {
            pokemon.getTypes().add(type);
            s.update(pokemon);

            type.getPokemons().add(pokemon);
            s.update(type);
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible agregar el tipo al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    /**
     * Elimina un Tipo a un Pokemon, a partir del id de un Tipo
     * El Pokemon original, se busca por id
     * @param pokemonId
     * @param typeId
     * @throws PokedexException 
     */
    public void removePokemonType(Long pokemonId, Long typeId) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        Type type = st.getType(typeId); 
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
     
        /*
        Se eliminan las relaciones tanto desde el punto de vista del Pokemon
        para con los Tipos y para el Tipo para con los Pokemons
        */        
        try {
            pokemon.getTypes().remove(type);
            s.update(pokemon);

            type.getPokemons().remove(pokemon);
            s.update(type);   
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible quitar el tipo al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();
        }
    }
    
    /**
     * Agrega una Evolucion a un Pokemon, a partir de una Evolucion
     * El Pokemon original, se busca por id
     * @param pokemonId
     * @param evolution
     * @throws PokedexException 
     */
    public void addPokemonEvolution(Long pokemonId, Evolution evolution) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction(); 
          
        /*
        Se agregan las relaciones tanto desde el punto de vista del Pokemon
        para con la Evolucion y para la Evolucion con el Pokemon
        */        
        try {
            pokemon.getEvolutions().add(evolution);
            s.update(pokemon);

            evolution.setPokemon(pokemon);
            s.update(evolution);    
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible agregar la evolucion al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
}