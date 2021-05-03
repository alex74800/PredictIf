/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Alexis
 */
@Entity
public class Employe extends Utilisateur implements Serializable {

    private boolean disponible;
    private int nombreConsultations;
    
    public Employe() {
    }

    public Employe( String nom, String prenom, String mail, String motDePasse, String telephone, String genre,boolean disponible) {
        super(nom, prenom, mail, motDePasse, telephone, genre);
        this.disponible = disponible;
        this.nombreConsultations = 0;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getNombreConsultations() {
        return nombreConsultations;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setNombreConsultations(int nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }

    @Override
    public String toString() {
        return super.toString()+" Employe{" + "disponible=" + disponible + ", nombreConsultations=" + nombreConsultations + '}';
    }
    
    
    
}
