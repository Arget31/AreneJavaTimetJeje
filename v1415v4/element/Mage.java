package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;

public class Mage extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Mage() {
		super("Mage", 80, 130);
	}

	public void pouvoir(VueElement ve, int refPlusProche, Integer refRMI, Actions actions, Deplacements deplacements) throws RemoteException{
	
		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
	        	parler("Je me teleporte !", ve);
				deplacements.seTeleporter();
				setRechargement(60);
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}