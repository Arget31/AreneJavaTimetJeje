package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

public class Mage extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Mage() {
		super("Mage", 80, 130);
	}

	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException{
	
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