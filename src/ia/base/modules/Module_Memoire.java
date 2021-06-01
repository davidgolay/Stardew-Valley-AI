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
    private HashMap<TypeRessource, Integer> stockMagasin;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
        this.inventaire = new HashMap<>();
        this.stockMagasin = null;
        for (TypeRessource ressources : TypeRessource.values()) {
            if(ressources != TypeRessource.GOLD)
            this.inventaire.put(ressources, 0);
            
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
        if(null != action.getType()) switch (action.getType()) {
            case MOUVEMENT:
                this.joueur.deplacer(action.getDirection()) ;
                break;
                
            case RECOLTE:
                if(action.getDirection() != null) {
                    Case caseDestination = this.carte.getCase(this.getCaseJoueur().
                            getCoordonnee().getVoisin(action.getDirection())) ;
                    caseDestination.setObjet(null) ;
                }   
                break;
                
            case ACTIONSTATIQUE:
                this.stockMagasin = null;
                break;
                
            case ACHAT:
                if(action.getTypeRessource() == TypeRessource.PARSNIPSEED){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 20);
                    this.inventaire.put(TypeRessource.PARSNIPSEED, inventaire.get(TypeRessource.PARSNIPSEED) + 1);
                    this.stockMagasin.put(TypeRessource.PARSNIPSEED, stockMagasin.get(TypeRessource.PARSNIPSEED) - 1 );
                    
                }else if(action.getTypeRessource() == TypeRessource.CAULIFLOWERSEED){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 80);
                    this.inventaire.put(TypeRessource.CAULIFLOWERSEED, inventaire.get(TypeRessource.CAULIFLOWERSEED) + 1);
                    this.stockMagasin.put(TypeRessource.CAULIFLOWERSEED, stockMagasin.get(TypeRessource.CAULIFLOWERSEED) - 1);
                }
                break;
                
//            default:
//                break;
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
    
     /**
     * Methode qui récupére la quantité d'une ressource de 
     * l'inventaire
     * @param type type de ressource
     * @return la quantité de la ressource
     */
    public int getQuantiteRessource(TypeRessource type){
        return this.inventaire.get(type);
    }
    
    public void genererStockMagasin(int nbGrainePanais, int nbGraineChouxFleur){
        //initialisation du stock magasin
        this.stockMagasin = new HashMap<>();
        stockMagasin.put(TypeRessource.PARSNIPSEED, nbGrainePanais);
        stockMagasin.put(TypeRessource.CAULIFLOWERSEED, nbGraineChouxFleur);
    }
    
    public boolean hasStockMagasin(){
        boolean res = true;
        if(this.stockMagasin == null){
            res = false;
        }
        return res;
    }
    
    public int getStockMagasin(TypeRessource type){
        return this.stockMagasin.get(type);
    }
}
