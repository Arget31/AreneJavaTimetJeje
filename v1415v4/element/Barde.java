package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import utilitaires.Calculs;
import serveur.IArene;

public class Barde extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Barde() {
		super("Barde", 50, 150);
	}
	
	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
		
		ArrayList<Integer> equipe = getEquipe();
		int membre, i;
		boolean favorable = false;
		Point dest;
		IArene arene = ve.getControleur().getArene();
		
		if (!equipe.isEmpty()) {

			membre = i = 0;
			dest = Calculs.meilleurPoint(ve.getPoint(), cible.getPoint(), voisins);
			while (i < equipe.size() && !favorable) {
				membre = equipe.get(i++);
				favorable = actions.simulation(membre,refPlusProche,arene);
			}
			if (!favorable) membre = equipe.get(0);
			arene.consoleFromRef(membre).getVueElement().setPoint(dest);
			setRechargement(80);
		}
	}
}
