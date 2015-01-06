

import java.rmi.RemoteException;
import java.util.Random;

import controle.Console;
import element.*;

public class TestPotionInvisibilite {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) {

		try {
			int port=5099;	//par defaut, port de l'arene=5099
			if (args.length!=0) port=Integer.parseInt(args[0]);
			String ipArene = "localhost";
			if (args.length!=0) if (args[1]!="") ipArene=args[1];
			Potion potionInvisibilite = new PotionDInvisibilite();
	
			Random r = new Random();
			new Console(potionInvisibilite, r.nextInt(100),r.nextInt(100), port,ipArene);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
