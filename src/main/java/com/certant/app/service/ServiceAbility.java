package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Ability;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceAbility {
    /**
     * Retorna una Habilidad, buscado por el id
     * @param abilityId
     * @return
     * @throws PokedexException 
     */        
    public Ability getAbility(Long abilityId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Ability ability;
        
        try {
            Query query = s.createQuery("from Ability a where a.id = :abilityId");
            query.setLong("abilityId", abilityId);

            ability = (Ability) query.list().get(0);        
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
            pe.setBusinessError("Imposible obtener la Habilidad por id");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }

        return ability;
    }
    
    /**
     * Retorna todas las Habilidades
     * @return
     * @throws PokedexException 
     */        
    public Set<Ability> getAbilities() throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Ability> abilities;
        
        try {
            Query query = s.createQuery("from Ability");
            abilities = new HashSet(query.list());       
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
            pe.setBusinessError("Imposible obtener las Habilidades");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return abilities;
    }
}