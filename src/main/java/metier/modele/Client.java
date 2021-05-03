/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Alexis
 */
@Entity
public class Client extends Utilisateur implements Serializable {

    
    private Date dateNaissance;
    private String adresse;
    private String zodiaque;
    private String signeChinois;
    private String couleur;
    private String animalTotem;
    @OneToMany(mappedBy="client")
    private List<Consultation> consultations;

    public Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse, String telephone, String genre, Date dateNaissance, String adresse) {
        super(nom,prenom,mail,motDePasse,telephone,genre);
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
    }


    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getZodiaque() {
        return zodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }
    
   

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setZodiaque(String zodiaque) {
        this.zodiaque = zodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public void addToList(Consultation consultation){
        this.consultations.add(consultation);
        if(consultation.getClient()!=this){
            consultation.setClient(this);
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Client{" + "dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", zodiaque=" + zodiaque + ", signeChinois=" + signeChinois + ", couleur=" + couleur + ", animalTotem=" + animalTotem + '}';
    }
    
    
}