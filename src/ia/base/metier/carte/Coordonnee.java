/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author David Golay
 */
public class Coordonnee {
    
    private int ligne;
    private int colonne;
    
    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    public Coordonnee getVoisin(TypeMouvement mouvement) {
        Coordonnee c = null;
        switch (mouvement) {
            case TOP : 
                c = new Coordonnee(ligne-1  , colonne); break;
            case BOTTOM : 
                c = new Coordonnee(ligne+1  , colonne); break;
            case LEFT : 
                c = new Coordonnee(ligne, colonne-1); break;
            case RIGHT : 
                c = new Coordonnee(ligne, colonne+1); break;
        }
        return c;
    }
    
    public TypeMouvement getMouvementPourAller(Coordonnee destination) {
        TypeMouvement res = null;
        for (TypeMouvement mouvement : TypeMouvement.values()) {
            if (this.getVoisin(mouvement).getColonne() == destination.getColonne()) {
                if (this.getVoisin(mouvement).getLigne()== destination.getLigne()){
                    res = mouvement;
                }
            }
        }
        
        return res;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.ligne;
        hash = 67 * hash + this.colonne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnee other = (Coordonnee) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordonnee{" + "ligne=" + ligne + ", colonne=" + colonne + '}';
    }
  
}
