package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

public class Voleur extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Voleur() {
		super("Voleur", 120, 80, 2);
	}

	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {

		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
			
				// activation de l'invisibilite
        		parler("Je me camoufle et me dirige vers mon voisin" + refPlusProche, ve);
				setInvisibilite(10);
				deplacements.seDirigerVers(refPlusProche);
				setRechargement(60);
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}

