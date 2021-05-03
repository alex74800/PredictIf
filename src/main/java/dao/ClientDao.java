/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Alexis
 */
import metier.modele.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alexis
 */
public class ClientDao {
    public void creer(Client client){
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    public void supprimer(Client client){
        JpaUtil.obtenirContextePersistance().remove(client);
    }
    public Client modifier(Client client){
         return JpaUtil.obtenirContextePersistance().merge(client);
    }
    public Client chercherParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    public List<Client> chercherTous(){
        String s = "Select c from Client c order by c.nom,c.prenom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Client.class);
        return query.getResultList();
    }
    public Client chercherParMail(String mail){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        // solve problem of query with inheritance 
        Query query;
        query = em.createQuery("SELECT c from Client c, Utilisateur u WHERE u.id = c.id AND u.mail = :mail");
        
        query.setParameter("mail", mail);
        
        List<Client> resultList = query.getResultList();
        Client result = null;
        if (!resultList.isEmpty()){
            result = resultList.get(0);
        }
        
        return result;
    }
    
}
