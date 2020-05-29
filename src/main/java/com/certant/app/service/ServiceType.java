package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Type;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceType {
    /**
     * Retorna un Tipo, buscado por el nombre
     * @param name
     * @return
     * @throws PokedexException 
     */    
    public Type getType(String name) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Type type;

        try {
            Query q1 = s.createQuery("from Type t where t.name = :name");
            q1.setString("name", name);

            type = (Type) q1.list().get(0);            
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
            pe.setBusinessError("Imposible obtener el Tipo por nombre");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return type;
    }
    
    /**
     * Retorna un Tipo, buscado por el id
     * @param typeId
     * @return
     * @throws PokedexException 
     */    
    public Type getType(Long typeId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Type type;

        try {
            Query q1 = s.createQuery("from Type t where t.id = :typeId");
            q1.setLong("typeId", typeId);

            type = (Type) q1.list().get(0);            
        } catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener el Tipo por id");
            throw pe;            
        } finally {
            t.commit();
            s.close();
        }
        
        return type;
    }

    /**
     * Retorna todos los Tipos
     * @return
     * @throws PokedexException 
     */    
    public Set<Type> getTypes() throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Type> types;
        
        try {
            Query q1 = s.createQuery("from Type");
            types = new HashSet<>(q1.list());            
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
            pe.setBusinessError("Imposible obtener los Tipos");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return types;
    } 
}