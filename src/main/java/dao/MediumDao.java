/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Medium;

/**
 *
 * @author Alexis
 */
public class MediumDao {
    public void creer(Medium medium){
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    public void supprimer(Medium medium){
        JpaUtil.obtenirContextePersistance().remove(medium);
    }
    public Medium modifier(Medium medium){
         return JpaUtil.obtenirContextePersistance().merge(medium);
    }
    public Medium chercherParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    public List<Medium> chercherParType(String type,String genre){
        Query query = JpaUtil.obtenirContextePersistance().createQuery("Select m from Medium m where m.genre= :genre and TYPE(m) = "+type);
        
        query.setParameter("genre", genre);
        return query.getResultList();
    }
    
    
}
