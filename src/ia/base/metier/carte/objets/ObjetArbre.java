/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objets;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 *
 * @author David
 */
public class ObjetArbre extends Objet {

    public ObjetArbre(Case position) {
        super(position);
    }

    @Override
    public TypeObjet getType() {
        return TypeObjet.ARBRE;
    }

    @Override
    public boolean EstBloquant() {
        return false;
    }
    
    @Override
    public boolean estRecoltable() {
        return true;
    }
    
    @Override
    public int coutRecolte(){
        return 2;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        //un arbre vaut 12 uniés de bois
        loot.put(TypeRessource.BOIS, 12);
        return loot;
    }
    
}
