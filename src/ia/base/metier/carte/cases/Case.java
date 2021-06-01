/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objets.Objet;
import java.util.ArrayList;

/**
 *
 * @author dg447135
 */
public abstract class Case {
    
    private Coordonnee coordonnee;
    private Objet objet;
    private ArrayList<Case> voisins;
    
    public Case(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
        this.objet = null;
        this.voisins = new ArrayList<>();
    }
    
    public abstract TypeCase getType();

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public ArrayList<Case> getVoisins() {
        return voisins;
    }
    
    public void ajouterVoisin(Case voisin) {
        this.voisins.add(voisin);
    }
    
    public TypeMouvement getMouvementPourAller(Case arrivee) {       
        TypeMouvement move = null;
        //colonnes de départ et d'arrivée
        int cD = this.getCoordonnee().getColonne();
        int cA = arrivee.getCoordonnee().getColonne();
        //lignes de départ et d'arrivée
        int lD = this.getCoordonnee().getLigne();
        int lA = arrivee.getCoordonnee().getLigne();
        
        // Cas Haut Bas
        if( cD == cA) {
            if(lD > lA){
                move = TypeMouvement.TOP;
            }
            else if (lD < lA){
                move = TypeMouvement.BOTTOM;
            }
        } else if (lD == lA){
            if(cD > cA){
                move = TypeMouvement.LEFT;
            } else if(cD < cA){
                move = TypeMouvement.RIGHT;
            }          
        }
        //return this.coordonnee.getMouvementPourAller(arrivee.getCoordonnee());
        return move;       
    }

    
    public abstract boolean estAccessible();
    
    
    @Override
    public String toString(){
        String res = "[CASE]: ";
        res += this.getType() +", ";
        if(this.getObjet() !=null){
            res += this.getObjet().getType();
        }
        return res;
    }
 
}
