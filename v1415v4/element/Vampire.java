package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Random;

public class Vampire extends Personnage {
	
	private static final long serialVersionUID = 1L;
	
	public Vampire() {
		super("Vampire", 100, 100, 1);
	}
	//pouvoir basé sur l'aléatoire, le vampire a 50% de chance de voler des caractéristique a sa cible, il peut aussi simmplemen les réduire ou bien 25% de chance de les augmenter
	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
		
		int forceCible = cible.getControleur().getElement().getCaract("force");
		int charismeCible = cible.getControleur().getElement().getCaract("charisme");
		
		Hashtable<String, Integer> nouvellesValeursCible = new Hashtable<String, Integer>();
		Random r= new Random();
		int chance=r.nextInt(100);
		if(chance>50){
			nouvellesValeursCible.put("force", forceCible - 10);
			nouvellesValeursCible.put("charisme", charismeCible - 10);
			ajouterCaract("force",getCaract("force")+10);
			ajouterCaract("charisme",getCaract("charisme")+10);
		}
		else if(chance>25){
			nouvellesValeursCible.put("force", forceCible - 10);
			nouvellesValeursCible.put("charisme", charismeCible - 10);
		}
		else{
			nouvellesValeursCible.put("force", forceCible + 10);
			nouvellesValeursCible.put("charisme", charismeCible + 10);
		}
		cible.getControleur().majCaractElement(nouvellesValeursCible);
		setRechargement(60);	
	}
}

