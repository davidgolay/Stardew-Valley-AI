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
public class ObjetMaison extends Objet {

    public ObjetMaison(Case position) {
        super(position);
    }

    @Override
    public TypeObjet getType() {
        return TypeObjet.MAISON;
    }
    
    @Override
    public boolean EstBloquant() {
        return true;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        return loot;
    }
    
}
