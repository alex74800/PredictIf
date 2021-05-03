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
public class Astrologue extends Medium implements Serializable {

    private String formation;
    private String promotion;

    public Astrologue() {
        
    }

    public Astrologue( String denomination, String presentation, String genre,String formation, String promotion) {
        super(denomination, presentation, genre);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString()+"Astrologue{" + "formation=" + formation + ", promotion=" + promotion + '}';
    }
    
    
}
