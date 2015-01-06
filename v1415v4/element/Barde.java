package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import serveur.IArene;

public class Barde extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Barde() {
		super("Barde", 140, 150, 1);
	}
	
	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
		
		ArrayList<Integer> equipe = getEquipe();
		//int membre;
		//Point pointBarde = ve.getPoint(), ptvide = new Point(0,0);
		//VueElement vmembre;
		IElement membre;
		IArene arene = ve.getControleur().getArene();
		
		// active pouvoir si equipe non vide
		if (!equipe.isEmpty()) {

			// inverse position du barde avec membre d'equipe aleatoire
			/*Random r=new Random();
            membre = equipe.get(r.nextInt(equipe.size()));
            vmembre = arene.consoleFromRef(membre).getVueElement();
            ve.setPoint(vmembre.getPoint());
            vmembre.setPoint(ptvide);
			setRechargement(30);*/
			for (int i = 0; i < equipe.size(); i++) {
				membre = arene.consoleFromRef(equipe.get(i)).getElement();
				((Personnage) membre).setCharisme(((Personnage) membre).getCharisme() + 1);
				((Personnage) membre).setForce(((Personnage) membre).getForce() + 1);
			}
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
		else{
	        	parler("Je vais vers mon voisin " + refPlusProche, ve);
	        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}
