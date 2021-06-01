/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author David
 */
public class Dijkstra extends AlgorithmeCalculDistance {
    
    private HashMap<Case, Boolean> estVisite;
    private HashMap<Case, Case> predecesseur;
    private int infini;

    public Dijkstra(Carte carte) {
        super(carte);
        this.estVisite = new HashMap<>();
        this.predecesseur = new HashMap<>();
        //infini = 1+ (nombre d’arêtes du graphe * coût maximum d’une arrête)
        this.infini = 1+carte.getTaille()*carte.getTaille()*16;
        
    }
    
    private int coutMouvementVers(Case destination){
        //par default, le cout est fixé à l'infini
        int res = this.infini;
        int coutDeplacement = 1;
        //si la destination est accessible
        if(destination.estAccessible()){
            //on ajoute le cout de deplacement
            res = coutDeplacement;
            //si la case de destination contient un objet
            if(destination.getObjet()!= null){
                //si l'objet est recoltable
                if(destination.getObjet().estRecoltable()){
                    res += destination.getObjet().coutRecolte();               
                }
            }
        }      
        return res;
    }
    
    private void initialisation(Case depart) {
        for (Case v : getCarte().getCases()) {
            setDistance(v, infini);
            estVisite.put(v, false);
            predecesseur.put(v, null);
        }
        setDistance(depart, 0);
    }
    
    private void relachement(Case a, Case b){
        if(getDistance(b) > getDistance(a) + coutMouvementVers(b)) {
            setDistance(b, getDistance(a) + coutMouvementVers(b));
            predecesseur.put(b, a);
        }
    }
    
    private Case getCaseLaPlusProche() {
        int distanceMin = infini;
        Case res = null;
        for (Case c : super.getCarte().getCases()) {
            if( (estVisite.get(c) == false) && (getDistance(c)<distanceMin)) {                
                distanceMin = this.getDistance(c);
                res = c;
            }
        }
        return res;       
    }

    @Override
    public void calculerDistancesDepuis(Case depart) {
        initialisation(depart);
        Case caseLaPlusProche = getCaseLaPlusProche();
        while(caseLaPlusProche != null){
            estVisite.put(caseLaPlusProche, true);
            for (Case v : caseLaPlusProche.getVoisins()) {
                relachement(caseLaPlusProche, v);
            }
            caseLaPlusProche = getCaseLaPlusProche();           
        }
    }
    
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee){
        ArrayList<TypeMouvement> mouvements = new ArrayList<>();
        Case casePrecedente = null; //initialisation
        Case caseEnCours = arrivee; 
        //faire instructions tant que la case précedente est non null
        do{
            // la case précédente récupère le predecesseur de la case en cours
            casePrecedente = this.predecesseur.get(caseEnCours);
            //si la case précédente est non null
            if(casePrecedente != null){
                // alors on ajoute le mouvement pour aller à la case en cours
                // depuis la case précédente
                mouvements.add(casePrecedente.getMouvementPourAller(caseEnCours));
                //la case en cours devient la case précédente
                caseEnCours = casePrecedente;
            }
        }while(casePrecedente != null);
        //on inverse la liste de mouvement
        Collections.reverse(mouvements);
        return mouvements;
    }
}
