package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;

import utilitaires.Calculs;

public class Guerrier extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Guerrier() {
		super("Guerrier", 150, 50);
	}

	public void pouvoir(VueElement ve, int refPlusProche, Integer refRMI, Actions actions, Deplacements deplacements) throws RemoteException{
		
		VueElement cible = ve.getControleur().getArene().consoleFromRef(refPlusProche).getVueElement();
		int distPlusProche;
		
		setRechargement(30);
    	parler("Je fonce vers " + refPlusProche, ve);
    	for (int i = 0; i < 10; i++) {
	    	deplacements.seDirigerVers(refPlusProche);
		}
    	
    	distPlusProche = Calculs.distanceChebyshev(ve.getPoint(), cible.getPoint());
    	if (distPlusProche <= 2) {
    		actions.interaction(refRMI, refPlusProche, ve.getControleur().getArene());
    	}
	}
}