/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUtil;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;
/**
 *
 * @author Alexis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void testerInscriptionClient(){
        JpaUtil.init();
        Service service = new Service();
        Client c = new Client("Meldrum","JJ","mail","mdp","06727272","F",new Date("27/06/1999"),"Villeurbanne");
         if(service.inscrireClient(c) != null){
             System.out.println("> Succès Inscription");
             System.out.println(c.toString());
         } else {
             System.out.println("Une erreur est survenue");
         }
         JpaUtil.destroy();
    }
    
    public static void testerAuthentificationClient(){
        JpaUtil.init();
        Service service = new Service();
        Client client = service.authentifierClient("mail", "truc");
        if(client != null){
            System.out.println(client.toString());
        } else {
            System.out.println("Mail ou mot de passe erronés");
        }
        JpaUtil.destroy();
    }
    public static void inscription(){
        JpaUtil.init();
        Service service = new Service();
        
        System.out.println("Entrez votre nom: ");
        String nom = Saisie.lireChaine("");
        System.out.println("Entrez votre prenom: ");
        String prenom = Saisie.lireChaine("");
        System.out.println("Entrez votre mail: ");
        String mail = Saisie.lireChaine("");
        System.out.println("Entrez votre mot de passe: ");
        String mdp = Saisie.lireChaine("");
        System.out.println("Entrez votre numero de telephone: ");
        String tel = Saisie.lireChaine("");
        System.out.println("Entrez votre genre (F | M): ");
        String genre = Saisie.lireChaine("");
        System.out.println("Entrez votre date de naissance: ");
        String date = Saisie.lireChaine("");
        System.out.println("Entrez votre adresse: ");
        String adresse = Saisie.lireChaine("");
        Client c = new Client(nom,prenom,mail,mdp,tel,genre,new Date(date),adresse);
         if(service.inscrireClient(c) != null){
             System.out.println("> Succès Inscription");
             System.out.println(c.toString());
         } else {
             System.out.println("Une erreur est survenue");
         }
         JpaUtil.destroy();
    }
    public static void rechercherClientParId(){
        JpaUtil.init();
        Service service = new Service();
        System.out.println("Entrez l'id du client (Attention à ne rentrer que des entiers): ");
        Integer id = Saisie.lireInteger("");
        Client client  = service.chercherClientParId(Long.valueOf(id));
        if(client!=null){
            System.out.println("> Succès Recherche");
            System.out.println(client.toString());
        } else {
            System.out.println("Une erreur est survenue");
        }
        JpaUtil.destroy();
    }
    public static void chercherMediumParGenreType(){
        JpaUtil.init();
        Service service = new Service();
        service.genererEmployesMedium();
        System.out.println("Entrez le type: ");
        String type = Saisie.lireChaine("");
        System.out.println("Entrez le genre: ");
        String genre = Saisie.lireChaine("");
        List<Medium> list = service.listerMedium(type, genre);
        list.forEach(list1 -> {
            System.out.println(list1.toString());
        });
        JpaUtil.destroy();
    }
    public static void testerConsultations(){
        JpaUtil.init();
        Service service = new Service();
        service.genererEmployesMedium();
        Client client = new Client("Meldrum","JJ","mail","mdp","06727272","F",new Date("27/06/1999"),"Villeurbanne");
        service.inscrireClient(client);
        System.out.println(client.toString());
        Medium medium = service.listerMedium("Cartomancien", "M").get(0);
        System.out.println(medium.toString());
        Consultation consultation = service.creerConsultation(client, medium);
        System.out.println(consultation.toString());
        service.commencerConsultation(consultation);
        System.out.println("Consultation commencée");
        System.out.println(consultation.toString());
        System.out.println(consultation.getEmploye().toString());
        service.finirConsultation(consultation, "C'était nul");
        System.out.println("Consultation terminée");
        System.out.println(consultation.toString());
        System.out.println(consultation.getEmploye().toString());
        JpaUtil.destroy();
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        inscription();
    }
    
}
