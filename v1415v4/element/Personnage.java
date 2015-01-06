/**
 * 
 */
package element;


import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import utilitaires.Calculs;

/**
 * Un personnage: un element possedant des caracteristiques et etant capable
 * de jouer une strategie. 
 */
public class Personnage extends Element implements IPersonnage {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Reference du leader de ce personnage, -1 si aucun.
	 */
	private int leader;
	
	/**
	 * Reference des personnages de l'equipe de ce personnage. 
	 * Vide si le leader n'est pas egal a -1.
	 */
	private ArrayList<Integer> equipe;

	/**
	 * Constructeur d'un personnage avec un nom et une quantite de force et de charisme.
	 * Au depart, le personnage n'a ni leader ni equipe.
	 * @param nom
	 * @param force
	 * @param charisme
	 */
	public Personnage(String nom, int force, int charisme, int vitesse) {
		super(nom);
		ajouterCaract("force", force);
		ajouterCaract("charisme", charisme);
		ajouterCaract("vitesse", vitesse);
		ajouterCaract("invisibilite", 0);
		ajouterCaract("rechargement",0);

		leader = -1;
		equipe = new ArrayList<Integer>();
	}
	
	/**
	 * Retourne la valeur de force.
	 * @return bonus de force
	 */
	public int getForce() {
		return getCaract("force");
	}
	
	/**
	 * Attribue la valeur de force.
	 */
	public void setForce(int force) {
		ajouterCaract("force", force);
	}
	
	/**
	 * Retourne la valeur de charisme.
	 * @return bonus de charisme
	 */
	public int getCharisme() {
		return getCaract("charisme");
	}
	
	/**
	 * Attribue la valeur de charisme.
	 */
	public void setCharisme(int charisme) {
		ajouterCaract("charisme", charisme);
	}
	
	/**
	 * Retourne la valeur de vitesse.
	 * @return bonus de vitesse
	 */
	public int getVitesse() {
		return getCaract("vitesse");
	}

	/**
	 * Retourne la valeur de invisibilite.
	 * @return bonus de invisibilite
	 */
	public int getInvisibilite() {
		return getCaract("invisibilite");
	}
	
	/**
	 * Attribue la valeur de invisibilite.
	 */
	public void setInvisibilite(int invisibilite) {
		ajouterCaract("invisibilite", invisibilite);
	}
	
	/**
	 * Decremente la valeur de invisibilite.
	 */
	public void majInvisibilite() {
		int invisibilite = getInvisibilite();
		if (invisibilite > 0) setInvisibilite(invisibilite - 1);
	}
	
	/**
	 * Retourne la valeur de rechargement.
	 * @return bonus de rechargement
	 */
	public int getRechargement() {
		return getCaract("rechargement");
	}
	
	/**
	 * Attribue la valeur de rechargement.
	 */
	public void setRechargement(int rechargement) {
		ajouterCaract("rechargement", rechargement);
	}
	
	/**
	 * Decremente la valeur de rechargement.
	 */
	public void majRechargement() {
		int rechargement = getRechargement();
		if (rechargement > 0) setRechargement(rechargement - 1);
	}
	
	/**
	 * Retourne le leader.
	 * @return leader (-1 si aucun)
	 */
	public int getLeader() {
		return leader;
	}

	/**
	 * Retourne la liste des personnages de l'equipe.
	 * @return equipe
	 */
	public ArrayList<Integer> getEquipe() {
		return equipe;
	}
	

	@Override
	public String toString(){
		String lead = (leader != -1)? ", leader: " + leader: "";
		String eq = "";
		
		if(!equipe.isEmpty()) {
			eq += ", equipe: ";
			
			for(int i = 0; i < equipe.size(); i++) {
				eq += equipe.get(i);
				
				if(i < equipe.size() - 1) {
					eq += " ";
				}
			}
		}
		
		return super.toString() + "[" + getForce() + ", " + getCharisme() + lead + eq + "](" + getRechargement() + ")";
	}
	
	
	
	
	@Override
	public void setLeader(int ref) throws RemoteException {
		leader = ref;
	}
	
	@Override
	public void clearLeader() throws RemoteException {
		leader = -1;
	}
	
	@Override
	public void ajouterEquipe(int ref) throws RemoteException {
		equipe.add((Integer) ref);
	}

	@Override
	public void enleverEquipe(int ref) throws RemoteException {
		equipe.remove((Integer) ref);
	}

	@Override
	public void enleverTouteEquipe() throws RemoteException {
		equipe.clear();
	}
	
	public void pouvoir(VueElement ve, Integer refRMI, VueElement cible, int refPlusProche,
			Actions actions, Deplacements deplacements, Hashtable<Integer,VueElement> voisins) throws RemoteException {
		//pouvoir est redefinie selon le type de personnage.
	}
	
	
	/**
	 * Met en place la strategie. On ne peut utiliser que les methodes de la 
	 * classe Actions.
	 * @param ve vue de l'element
	 * @param voisins element voisins de cet element
	 * @param refRMI reference attribuee a cet element par le serveur
	 * @throws RemoteException
	 */
	public void strategie(VueElement ve, Hashtable<Integer,VueElement> voisins, Integer refRMI) throws RemoteException {
        Actions actions = new Actions(ve, voisins); //je recupere les voisins (distance < 10)
        Deplacements deplacements = new Deplacements(ve,voisins);
        int vitesse = getVitesse();
        
        for (int i = 0; i < vitesse; i++) {
	        if (voisins.size()== 0) { // je n'ai pas de voisins, j'erre
	        	parler("J'erre...", ve);
	        	deplacements.seDirigerVers(0); //errer    
	        } 
	        else {
				VueElement cible = Calculs.chercherElementProche(ve, voisins);
				
				int distPlusProche = Calculs.distanceChebyshev(ve.getPoint(), cible.getPoint());
				
				int refPlusProche = cible.getRef();
				Element elemPlusProche = cible.getControleur().getElement();
				
				// dans la meme equipe ?
				boolean memeEquipe = false;
				
				if(elemPlusProche instanceof Personnage) {
					memeEquipe = (getLeader() != -1 && getLeader() == ((Personnage) elemPlusProche).getLeader()) || // meme leader
							getLeader() == refPlusProche || // cible est le leader de this
							((Personnage) elemPlusProche).getLeader() == refRMI || // this est le leader de cible
							((Personnage) elemPlusProche).getInvisibilite()>0;//cible invisible.
				}
				
				if(distPlusProche <= 2) { // si suffisamment proches
					if(elemPlusProche instanceof Potion) { // potion
						// ramassage
						parler("Je ramasse une potion", ve);
						actions.ramasser(refRMI, refPlusProche, ve.getControleur().getArene());
						
					} else { // personnage
						if(!memeEquipe) { // duel seulement si pas dans la meme equipe (pas de coup d'etat possible dans ce cas)
							// duel
							parler("Je fais un duel avec " + refPlusProche, ve);
							actions.interaction(refRMI, refPlusProche, ve.getControleur().getArene());
							setInvisibilite(0);
						} else {
				        	parler("J'erre...", ve);
				        	deplacements.seDirigerVers(0); // errer
						}
					}
				} else { // si voisins, mais plus eloignes
					if(!memeEquipe) { // potion ou enemmi
						if(elemPlusProche instanceof Personnage && getRechargement()==0){
								pouvoir(ve, refRMI, cible, refPlusProche, actions, deplacements, voisins);
						}
						else{
							// je vais vers le plus proche
				        	parler("Je vais vers mon voisin " + refPlusProche, ve);
				        	deplacements.seDirigerVers(refPlusProche);
						}
					} else{
			        	parler("J'erre...", ve);
			        	deplacements.seDirigerVers(0); // errer
					}
				}
	        }
        }
        majInvisibilite();
        majRechargement();
	}
}
