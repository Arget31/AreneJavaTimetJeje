package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;

public class Voleur extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Voleur() {
		super("Voleur", 80, 120);
	}

	public void pouvoir(VueElement ve, int refPlusProche, Integer refRMI, Actions actions, Deplacements deplacements) throws RemoteException{

		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
				setInvisibilite(5);
				setRechargement(30);
	        	parler("Je me camoufle et j'erre ...", ve);
				deplacements.seDirigerVers(0);
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}

