/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alexis
 */
@Entity
public class Spirite extends Medium implements Serializable {

   private String support;

    public Spirite() {
    }

    public Spirite( String denomination, String presentation, String genre,String support) {
        super(denomination, presentation, genre);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return super.toString()+"Spirite{" + "support=" + support + '}';
    }
    
}
