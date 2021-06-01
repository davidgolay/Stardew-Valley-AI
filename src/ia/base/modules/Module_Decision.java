package ia.base.modules;

import ia.base.IA;
import ia.base.metier.actions.Action;
import ia.base.modules.automate.Automate;


/**
 * Module en charge de la prise de décision
 * @author Matthieu Simonet & David Golay
 */
public class Module_Decision extends Module {

    private Automate automate;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Decision(IA ia) {
        super(ia);
        this.automate = new Automate(this);
    }

    /**
     * Méthode principale de prise de décision
     * @param messageRecu dernier message reçu par l'IUA
     * @return le message à envoyer au serveur
     */
    public String determinerNouvelleAction(String messageRecu) {
        String messageAEnvoyer = "END";
        
        //on récupère l'action renvoyée par la methode evoluer de l'automate
        Action nouvelleAction = this.automate.evoluer();
        // on demande au module memoire d'effectuer cette action
        this.automate.getModuleMemoire().effectuerAction(nouvelleAction);
        //on recupère le message de cette action et on le retourne
        messageAEnvoyer = nouvelleAction.getMessage();
        return messageAEnvoyer;
    }

}