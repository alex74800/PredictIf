/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.modele.Employe;

/**
 *
 * @author Alexis
 */
public class EmployeDao {
    public void creer(Employe employe){
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    public void supprimer(Employe employe){
        JpaUtil.obtenirContextePersistance().remove(employe);
    }
    public Employe modifier(Employe employe){
         return JpaUtil.obtenirContextePersistance().merge(employe);
    }
    public Employe chercherParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }
    
    public Employe chercherParMail(String mail){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        // solve problem of query with inheritance 
        Query query;
        query = em.createQuery("SELECT c from Employe e, Utilisateur u WHERE u.id = e.id AND u.mail = :mail");
        
        query.setParameter("mail", mail);
        
        List<Employe> resultList = query.getResultList();
        Employe result = null;
        if (!resultList.isEmpty()){
            result = resultList.get(0);
        }
        
        return result;
    }
    public Employe chercherEmployeByGenre(String genre){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        Employe result = null;
        String jpql = "SELECT e from Employe e WHERE e.genre = :unGenre AND e.disponible = true ORDER BY e.nombreConsultations";
        TypedQuery query;
        
        query = em.createQuery(jpql, Employe.class);
        
        query.setParameter("unGenre", genre);
        
        List<Employe> resultList = query.getResultList();
        if (!resultList.isEmpty()){
            System.out.println("liste non vide");
            result = resultList.get(0);
        } else {
            System.out.println("Liste vidde");
        }
        
        return result;
    }
    
}
