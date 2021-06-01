package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.Objet;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 * Module en charge de la mémorisation et de la restitution des informations obtenues
 * @author Matthieu
 */
public class Module_Memoire extends Module  {
    
    private Carte carte;
    private Joueur joueur;
    private HashMap<TypeRessource, Integer> inventaire;   
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
        this.inventaire = new HashMap<>();
        for (TypeRessource ressources : TypeRessource.values()) {
            //si ce n'est pas de l'or
            if(ressources != TypeRessource.GOLD)
            this.inventaire.put(ressources, 0);
            
            //si c'est de l'or
            else if (ressources == TypeRessource.GOLD){
                this.inventaire.put(ressources, 500);
            }           
        }
    }
    
    public void genererCarte(String messageRecu) {
        this.carte = new Carte(messageRecu);
        genererJoueur(carte.getCoordonneeDepart());
    }
    
    public Case getCaseJoueur(){
        return this.getCarte().getCase(this.getJoueur().getCoordonnee());
    }
    
    public void effectuerAction(Action action){
        if(action.getType() == TypeAction.MOUVEMENT) {
            this.joueur.deplacer(action.getDirection()) ;
        }
        else if(action.getType() == TypeAction.RECOLTE) {
            if(action.getDirection() != null) {
                Case caseDestination = this.carte.getCase(this.getCaseJoueur().
                getCoordonnee().getVoisin(action.getDirection())) ;
                caseDestination.setObjet(null) ;
            }
        }
    }
    
    public boolean hasCarte() {
        boolean b = false;
        if (this.carte != null) {
            b = true;
//            System.out.println("Le module memoire possède une instance de Carte");
        }
        return b;
    }
    
    public boolean hasJoueur() {
        boolean b = false;
        if (this.joueur != null) {
            b = true;
//            System.out.println("Le module memoire possède une instance de Joueur");
        }
        return b;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }
    
    public void genererJoueur(Coordonnee coordonnee) {
        this.joueur = new Joueur(coordonnee);
    }

    public Carte getCarte() {
        return carte;
    }
    
    private void recolter(Objet objet){
        if(objet != null) {
            for (TypeRessource ressource : this.inventaire.keySet()) {
                for (TypeRessource ressourceObjet : objet.getLoot().keySet()) {
                    if(ressource == ressourceObjet) {
                        int oldValue = inventaire.get(ressource);
                        int newValue = oldValue + objet.getLoot().get(ressource);
                        this.inventaire.put(ressource, newValue);
                    }
                }
            }
            //this.inventaire.putAll(objet.getLoot());
        }
    }
    

//    private void recolter(Objet objet){
//        if(objet != null){ 
//            for (TypeRessource ressourceInven: inventaire.keySet()) {
//                for (TypeRessource ressourceObjet : objet.getLoot().keySet()) {
//                   if(ressourceInven == ressourceObjet){
//                       this.inventaire.put(ressourceObjet, inventaire.get(ressourceObjet) + objet.getLoot().get(ressourceObjet));
//                   } 
//                }
//            }
//        }       
//    }
    
    
    

}
