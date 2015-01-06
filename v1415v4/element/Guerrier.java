package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

public class Guerrier extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Guerrier() {
		super("Guerrier", 150, 50, 1);
	}

	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
		
		int distPlusProche;
		
		// charge vers cible plus proche
    	parler("Je fonce vers " + refPlusProche, ve);
    	for (int i = 0; i < 10; i++) {
	    	deplacements.seDirigerVers(refPlusProche);
		}
    	
    	// verification de la presence de la cible et attaque
    	distPlusProche = Calculs.distanceChebyshev(ve.getPoint(), cible.getPoint());
    	if (distPlusProche <= 2) {
    		actions.interaction(refRMI, refPlusProche, ve.getControleur().getArene());
    	}
		setRechargement(90);
	}
}