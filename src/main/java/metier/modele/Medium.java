/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author Alexis
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String denomination ;
    private String presentation;
    private String genre;
    
    private int nombreConsultations;
    
    public Medium(){
        
    }
    
    public Medium(String denomination, String presentation, String genre) {
        this.denomination = denomination;
        this.presentation = presentation;
        this.genre = genre;
        this.nombreConsultations = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getGenre() {
        return genre;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNombreConsultations() {
        return nombreConsultations;
    }

    public void setNombreConsultations(int nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", denomination=" + denomination + ", presentation=" + presentation + ", genre=" + genre + ", nombreConsultations=" + nombreConsultations + '}';
    }
    
    
}