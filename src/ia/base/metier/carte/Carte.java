/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.FabriqueCase;
import ia.base.metier.carte.cases.TypeCase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author dg447135
 */
public class Carte {
    
    private HashMap<Coordonnee, Case> cases;
    private int taille;
    private Coordonnee coordonneeDepart;
    private ArrayList<Coordonnee> coordonneesMagasin;
    
    public Carte(String messageRecu) {
        
        this.cases = new HashMap<>() ;
        this.taille = (int) Math.sqrt(messageRecu.length()) ;
        for(int i=0 ; i<this.taille ; i++) {
            for(int j=0 ; j<this.taille ; j++) {
                this.ajouterCase(new Coordonnee(i,j), messageRecu.charAt(j+this.taille*i));
            }
        }     
        
        //Modifications des cases concernées en escalier 'S'
        Case escalierCentre = FabriqueCase.creer(coordonneeDepart.getVoisin(TypeMouvement.BOTTOM), 'S');
        Case escalierGauche = FabriqueCase.creer(escalierCentre.getCoordonnee().getVoisin(TypeMouvement.LEFT), 'S');
        Case escalierDroit = FabriqueCase.creer(escalierCentre.getCoordonnee().getVoisin(TypeMouvement.RIGHT), 'S');
        this.cases.replace(escalierCentre.getCoordonnee(), escalierCentre);
        this.cases.replace(escalierGauche.getCoordonnee(), escalierGauche);
        this.cases.replace(escalierDroit.getCoordonnee(), escalierDroit);
        
        //Verification des cases escaliers
//        System.out.println("Case centre escalier:" + this.cases.get(coordonneeDepart.getVoisin(TypeMouvement.BOTTOM)).toString());      
//        System.out.println("Case gauche escalier:" + this.cases.get(coordonneeDepart.getVoisin(TypeMouvement.BOTTOM).getVoisin(TypeMouvement.LEFT)).toString());
//        System.out.println("Case droite escalier:" + this.cases.get(coordonneeDepart.getVoisin(TypeMouvement.BOTTOM).getVoisin(TypeMouvement.RIGHT)).toString());

        //setup coordonnée magasin
        this.coordonneesMagasin = new ArrayList<>();
        this.coordonneesMagasin.add(0, new Coordonnee(coordonneeDepart.getLigne()+2, coordonneeDepart.getColonne() -4));
        this.coordonneesMagasin.add(1, new Coordonnee(coordonneeDepart.getLigne()+2, coordonneeDepart.getColonne() -3));
        
        //Gestions des voisins
        for(int i=0 ;i<this.taille ;i++) {
            for(int j=0 ;j<this.taille ;j++) {
                Coordonnee cooCase = new Coordonnee(i,j) ;
                for(TypeMouvement mouvement : TypeMouvement.values()) {
                    Coordonnee cooVoisin = cooCase.getVoisin(mouvement) ;
                    if(this.cases.get(cooVoisin) != null) {
                        this.cases.get(cooCase).ajouterVoisin(this.cases.get(
                        cooVoisin)) ;
                    }
                }
            }
        }
        
        
        //affichageConsole();
    }
    
    public int getTaille() {
        return cases.size();
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Coordonnee getCoordonneeDepart() {
        return coordonneeDepart;
    }
     
    private void ajouterCase(Coordonnee coordonnee, Character lettre) {
        if (lettre == 'D') {
            this.coordonneeDepart = coordonnee;
//            System.out.println("CoordonneeDépart: " + coordonneeDepart.toString());
        }    
        cases.put(coordonnee, FabriqueCase.creer(coordonnee, lettre));
    }
    
    /**
     * Cette fonction retourne la Case de la carte située au coordonnées passées
     * en parmaètre
     * @param coordonnee coordonnée de la Case à retournée
     * @return Case
     */
    public Case getCase(Coordonnee coordonnee) {
        return cases.get(coordonnee);
    }
    
    /**
     * Methode qui renvoie la liste des cases de la carte (à récupérer dans la hashmap)
     * @return 
     */
    public Collection<Case> getCases(){      
        return cases.values();
    }
    
    public void affichageConsole() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j <this.taille; j++) {
                String affichage = "E";
                Case caseEnCours = this.cases.get(new Coordonnee(i, j));
                if(caseEnCours == null){
                    System.out.println("null case");
                }
                if(caseEnCours.getType() == TypeCase.HERBE) {
                    if(caseEnCours.getObjet() == null) {
                        affichage = "H";
                        
                    }
                    else {
                        switch(caseEnCours.getObjet().getType()) {                           
                            case ARBRE : affichage = "A"; break;
                            case MAISON : affichage = "M"; break;
                            case ESCALIER : affichage = "S"; break;
                            case DEPART : affichage = "D"; break;
                        }
                    }
                }
                else if(caseEnCours.getType() == TypeCase.TERRE) {
                    affichage = "T";
                }
                System.out.print(affichage);
            }
            System.out.println("");
        }
    }  

    public ArrayList<Coordonnee> getCoordonneesMagasin() {
        return coordonneesMagasin;
    }
    
}
