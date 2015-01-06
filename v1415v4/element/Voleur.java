package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class Voleur extends Personnage {

	private static final long serialVersionUID = 1L;
	
	public Voleur() {
		super("Voleur", 80, 120);
	}

	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
			Point tamp = new Point();
		if(!actions.simulation(refRMI,refPlusProche,ve.getControleur().getArene())){
        		parler("Je me camoufle et me dirige vers mon voisin" + refPlusProche, ve);
				setInvisibilite(10);
				tamp.x=cible.getPoint().x;
				tamp.y=cible.getPoint().y;
				tamp.x=99-tamp.x;
				tamp.y=99-tamp.y;
				for(int i=0;i<5;i++){
					deplacements.seDirigerVers(tamp);
				}
				setRechargement(60);
		}
		else{
        	parler("Je vais vers mon voisin " + refPlusProche, ve);
        	deplacements.seDirigerVers(refPlusProche);
		}
	}
}

