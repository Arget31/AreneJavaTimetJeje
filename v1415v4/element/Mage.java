package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;

public class Mage extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Mage() {
		super("Mage", 120, 80);
	}

	public void pouvoir(VueElement ve, int refPlusProche, Integer refRMI, Actions actions, Deplacements deplacements) throws RemoteException{
	
		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
				setRechargement(60);
	        	parler("Je me teleporte !", ve);
				deplacements.seTeleporter();
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}