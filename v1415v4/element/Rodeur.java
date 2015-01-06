package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

public class Rodeur extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Rodeur() {
		super("Rodeur", 80, 130, 1);
	}

	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {

		Element eltcible = cible.getControleur().getElement();
		int forceCible, charismeCible;
		
		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
				// teleportation sur une position aleatoire 
	        	parler("J'analyse ma cible et je bat en retrait !", ve);
	        	forceCible = eltcible.getCaract("force");
	        	charismeCible = eltcible.getCaract("charisme");
	        	if (forceCible > charismeCible) setCharisme(getCharisme() + (charismeCible / 10));
	        	else setForce(getForce() + (forceCible / 10));
	        	for (int i = 0; i < 5; i++) {
	        		deplacements.fuir(cible.getPoint(), ve.getPoint());
	        	}
				setRechargement(40);
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}