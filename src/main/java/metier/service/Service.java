/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.JpaUtil;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.MediumDao;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Spirite;
import metier.modele.Astrologue;
import metier.modele.Consultation;
import metier.service.Util.Astro;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.service.Util.Message;

/**
 *
 * @author rbelateche
 */
public class Service {
    
    public Long inscrireClient (Client client){
        
        Long res = null;
        
        JpaUtil.creerContextePersistance();
        
        try {
            ClientDao clientDao = new ClientDao();
            Astro astro = new Astro();
            
            //transaction be open once for all services
            JpaUtil.ouvrirTransaction();
            
            
            
            List<String> profil = astro.getProfil(client.getPrenom(), client.getDateNaissance());
            client.setZodiaque(profil.get(0));
            client.setSigneChinois(profil.get(1));
            client.setCouleur(profil.get(2));
            client.setAnimalTotem(profil.get(3));
            
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            
            Message.envoyerMail("contact@predictif.if", client.getMail(), "Bienvenue chez PREDICT'IF",
                    "Bonjour "+client.getPrenom()+", nous vous confirmons votre inscription au service PREDICT'IF."
                            + "Rendez-vous vite sur notre site pour consulter votre profile astrologique et profiter des dons incroyables de nos mediums.");
            
            res = client.getId();
        } catch(Exception ex) {
            //ex.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            
            Message.envoyerMail("contact@predictif.if", client.getMail(), "Echec de l'inscription au service PREDICT'IF",
                    "Bonjour "+client.getPrenom()+", votre inscription au service PREDICT'IF a malencontresement échoué..."
                            + "Merci de recommencer ultérieurement.");
            
            res = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public Client chercherClientParId(Long id){        
        JpaUtil.creerContextePersistance();
        Client client = null;
        try {
            ClientDao clientDao = new ClientDao();
                  
            client = clientDao.chercherParId(id);
            
            
        } catch(Exception ex) {
            
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherClientParId(id)", ex);           
            client = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    
    //authentifier Client 
    public Client authentifierClient(String mail, String motDePasse){
        JpaUtil.creerContextePersistance();
        Client result = null;
        try {
            ClientDao clientDao = new ClientDao();      
            Client client = clientDao.chercherParMail(mail);
            //check that passwords are equal
            if (client!=null){
                if (client.getMotDePasse().equals(motDePasse)){
                    result = client;
                }
            }
        } catch(Exception ex) {
           
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service connecterClient(mail, motDePasse)", ex);           
            result = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    
    
    //ne modifie pas le mail, car se base sur ça pour trouver le client en question 
    public void modifierProfilClient(Client client){
        JpaUtil.creerContextePersistance();
        
        try{
            JpaUtil.ouvrirTransaction();
            ClientDao clientDao = new ClientDao();
            clientDao.modifier(client);
            JpaUtil.validerTransaction();
            
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service modifierProfilClient", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
     
    
    
    /* Partie Employés */
    
    public Employe chercherEmployeParId(Long id){
        JpaUtil.creerContextePersistance();
        Employe employe = null;
        try {
            EmployeDao employeDao = new EmployeDao();     
            employe = employeDao.chercherParId(id);
        } catch(Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherEmployeParId(id)", ex);           
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employe;
    }
    
    public Employe connecterEmploye(String mail, String motDePasse){
        JpaUtil.creerContextePersistance();
        Employe result = null;
        try{
            EmployeDao employeDao = new EmployeDao();
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null){
                if (employe.getMotDePasse().equals(motDePasse)){
                    result = employe;
                }
            }
        } catch(Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service connecterEmploye(mail, motDePasse)", ex);           
            result = null;    
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    
    /* Partie Mediums */ 
    public Medium chercherMediumParId(Long id){
        JpaUtil.creerContextePersistance();
        Medium medium = null;
        try {
           
            
            MediumDao mediumDao = new MediumDao();
            medium = mediumDao.chercherParId(id);

        } catch(Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherMediumParId(id)", ex);           
            medium = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return medium;
    }
    
    
    public List<Medium> listerMedium(String type,String genre){
        //type ne peut être que Cartomancien,Spirite,Astrologue
        JpaUtil.creerContextePersistance();
        MediumDao mediumDao = new MediumDao();
        List<Medium> liste = null;
        
        try{
            liste = mediumDao.chercherParType(type,genre);
            Logger.getAnonymousLogger().log(Level.INFO,"succès");
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.SEVERE,"fail",e);
        }
        finally{
            JpaUtil.fermerContextePersistance();  
        }
        return liste;
    }
    
    
    public List<String> getPredictions(Client client,int amour,int sante,int travail){
        Astro AstroApi = new Astro();
        List<String> predictions = null;
        try {
            predictions = AstroApi.getPredictions(client.getCouleur(), client.getAnimalTotem(), amour, sante, travail);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return predictions;
    }
    
    
    
    /* Génération d'employés et mediums en dur */
    
    public void genererEmployesMedium(){
        JpaUtil.creerContextePersistance();
        try{

            JpaUtil.ouvrirTransaction();

            MediumDao mediumDao = new MediumDao();

            Medium carto1 = new Cartomancien("Rayane le jeu de carte", "blablabla", "M");
            Medium carto2 = new Cartomancien("Ewen le jeu de carte", "blablabla", "M");
            Medium carto3 = new Cartomancien("Alexis le jeu de carte", "blablabla", "M");
            mediumDao.creer(carto1);
            mediumDao.creer(carto2);
            mediumDao.creer(carto3);


            Medium spir1 = new Spirite("Alexis le bouliste", "blablabla", "M","boule de cristal");
            Medium spir2 = new Spirite("Rayane le bouliste", "blablabla", "M","boule de cristal");
            Medium spir3 = new Spirite("Ewen le bouliste", "blablabla", "M","boule de cristal");
            mediumDao.creer(spir1);
            mediumDao.creer(spir2);
            mediumDao.creer(spir3);

            Medium astro1 = new Astrologue("Rayane l'astrologue", "blablabla", "M","insa Lyon","01/01/2000");
            Medium astro2 = new Astrologue("Alexis l'astrologue", "blablabla", "M","insa Lyon","01/01/2000");
            Medium astro3 = new Astrologue("Ewen l'astrologue", "blablabla", "M","insa Lyon","01/01/2000");
            mediumDao.creer(astro1);
            mediumDao.creer(astro2);
            mediumDao.creer(astro3);

            Employe employe1 = new Employe("Chaillan", "Ewen", "mailtest1","mdp","062486","M",true);
            Employe employe2 = new Employe("Metwalli", "Alexis", "mailtest2","mdp","062486","M",true);
            Employe employe3 = new Employe("Belateche", "Rayane", "mailtest3","mdp","062486","M",true);
            EmployeDao employeDao = new EmployeDao();
            employeDao.creer(employe1);
            employeDao.creer(employe2);
            employeDao.creer(employe3);

            JpaUtil.validerTransaction();

        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service genererEmployesMedium()", ex);           
          
        } finally {
            JpaUtil.fermerContextePersistance(); 
        }
    }
    
    
    
    
    /* Partie Consultation */
    
    public Consultation creerConsultation(Client client, Medium medium){
        JpaUtil.creerContextePersistance();
        Consultation consult = null;
        Employe employe = null;
        try{
            EmployeDao employeDao = new EmployeDao();
            ConsultationDao consultationDao = new ConsultationDao();            
            employe = employeDao.chercherEmployeByGenre(medium.getGenre());
            
            if (employe != null){
                System.out.println("employe trouve");
                JpaUtil.ouvrirTransaction();
                Date dateActuelle = new Date();
                consult = new Consultation(client, employe, medium,dateActuelle);   
                consultationDao.creer(consult);
                client.addToList(consult);
                ClientDao clientDao = new ClientDao();
                clientDao.modifier(client);
                medium.setNombreConsultations(medium.getNombreConsultations()+1);
                employe.setNombreConsultations(employe.getNombreConsultations()+1);
                employe.setDisponible(false);
                MediumDao mediumDao = new MediumDao();
                mediumDao.modifier(medium);
                employeDao.modifier(employe);
                JpaUtil.validerTransaction();
                
                Message.envoyerNotification(employe.getTelephone(), 
                        "Bonjour "+employe.getPrenom()+". Consultation requise pour "
                                +client.getPrenom()+" "+client.getNom()+". Médium à incarner :"
                                +medium.getDenomination());
            } else {
                System.out.println("Non trouve");
            }
            
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConsultation(client, medium)", ex);           
            consult = null;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consult;
       
    }
    
    
    public Consultation commencerConsultation(Consultation consultation){
        JpaUtil.creerContextePersistance();
        ConsultationDao consdao = new ConsultationDao();
        EmployeDao empdao = new EmployeDao();
        Consultation result;
        try{
            JpaUtil.ouvrirTransaction();
            Date dateDebut = new Date();//On va préciser la veritable heure de début de séance
            consultation.setDateDebut(dateDebut);
            result = consdao.modifier(consultation);
            JpaUtil.validerTransaction();
            
            Client client = consultation.getClient();
            Employe employe = consultation.getEmploye();
            Medium medium = consultation.getMedium();
            Message.envoyerNotification(client.getTelephone(),
                        "Bonjour "+client.getPrenom()+". J'ai bien reçu votre demande de consultation du "
                +consultation.getDateDemande()+". Vous pouvez dès à présent me contacter au "+employe.getTelephone()
                +". A tout de suite ! Médiumiquement vôtre, "+medium.getDenomination());
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(consultation)", ex);           
            result = null;
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    
    public void finirConsultation(Consultation consultation,String commentaire){
        JpaUtil.creerContextePersistance();
        ConsultationDao consdao = new ConsultationDao();
        EmployeDao empdao = new EmployeDao();
        try{
            JpaUtil.ouvrirTransaction();
            Date dateFin = new Date();//heure actuelle par défaut
            consultation.setDateFin(dateFin);
            consultation.setCommentaire(commentaire);
            Employe employe = consultation.getEmploye();
            employe.setDisponible(true);
            consdao.modifier(consultation);
            empdao.modifier(employe);
            JpaUtil.validerTransaction();
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(consultation)", ex);           
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    
    public Consultation chercherConsultationParId(Long id){
        JpaUtil.creerContextePersistance();
        Consultation consultation = null;
        try {
            ConsultationDao consultationdao = new ConsultationDao();
            consultation = consultationdao.chercherParId(id);

        } catch(Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherConsultationParId(id)", ex);           
            consultation = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
    }
   
}