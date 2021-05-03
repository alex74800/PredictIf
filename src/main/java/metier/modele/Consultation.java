/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alexis
 */
@Entity
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateDemande;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;

    public Consultation() {
    }

    public Consultation(Client client, Employe employe, Medium medium,Date dateDemande) {
        this.dateDemande = dateDemande;
        this.dateDebut = null;
        this.dateFin = null;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }

    
    
}
