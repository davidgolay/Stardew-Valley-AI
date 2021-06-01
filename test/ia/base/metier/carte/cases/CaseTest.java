/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objets.FabriqueObjet;
import ia.base.metier.carte.objets.ObjetMaison;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class CaseTest {
    
    public CaseTest() {
    }

    /**
     * Test of getType method, of class Case.
     */
    @Test
    public void testGetType() {
    }

    /**
     * Test of getCoordonnee method, of class Case.
     */
    @Test
    public void testGetCoordonnee() {
    }

    /**
     * Test of setCoordonnee method, of class Case.
     */
    @Test
    public void testSetCoordonnee() {
    }

    /**
     * Test of getObjet method, of class Case.
     */
    @Test
    public void testGetObjet() {
    }

    /**
     * Test of setObjet method, of class Case.
     */
    @Test
    public void testSetObjet() {
    }

    /**
     * Test of getVoisins method, of class Case.
     */
    @Test
    public void testGetVoisins() {
    }

    /**
     * Test of ajouterVoisin method, of class Case.
     */
    @Test
    public void testAjouterVoisin() {
    }

    /**
     * Test of estAccessible method, of class Case.
     */
    @Test
    public void testEstAccessible() {
        //CASE EAU
        CaseEau eau = new CaseEau(new Coordonnee(1,1));
        assertEquals(false, eau.estAccessible()); 
        //CASE TERRE
        CaseTerre terre = new CaseTerre(new Coordonnee(1,1));
        assertEquals(true, terre.estAccessible());        
        CaseHerbe herbe = new CaseHerbe(new Coordonnee(1,1));
        //CASE HERBE VIDE
        assertEquals(herbe.estAccessible(), true);       
        //CASE HERBE AVEC MAISON
        herbe.setObjet(FabriqueObjet.creer(herbe, 'M'));
        assertEquals(herbe.estAccessible(), false);
        //CASE HERBE AVEC ARBRE
        herbe.setObjet(FabriqueObjet.creer(herbe, 'A'));
        assertEquals(herbe.estAccessible(), false);
        //CASE HERBE AVEC ESCALIER
        herbe.setObjet(FabriqueObjet.creer(herbe, 'S'));
        assertEquals(herbe.estAccessible(), true);
        //CASE HERBE AVEC DEPART
        herbe.setObjet(FabriqueObjet.creer(herbe, 'D'));
        assertEquals(herbe.estAccessible(), true);
        
    }

    public class CaseImpl extends Case {

        public CaseImpl() {
            super(null);
        }

        public TypeCase getType() {
            return null;
        }

        public boolean estAccessible() {
            return false;
        }
    }
    
}
