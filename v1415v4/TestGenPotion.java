import java.rmi.RemoteException;
import java.util.Random;

import controle.Console;
import element.*;

public class TestGenPotion {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws InterruptedException {

		try {
			int port=5099;	//par defaut, port de l'arene=5099
			Random r = new Random();
			Potion elixir[]= new Potion[20];
			int chance;
			if (args.length!=0) port=Integer.parseInt(args[0]);
			String ipArene = "localhost";
			if (args.length!=0) if (args[1]!="") ipArene=args[1];
			for(int i=0;i<5;i++){
				chance=r.nextInt(4);
				switch(chance){
					case 0:
						elixir[i]=new PotionDeCharisme();	
						new Console(elixir[i], r.nextInt(100),r.nextInt(100), port,ipArene);
						break;
					case 1:
						elixir[i]=new PotionDInvisibilite();	
						new Console(elixir[i], r.nextInt(100),r.nextInt(100), port,ipArene);
						break;
					case 2:
						elixir[i]=new PotionDeVitesse();	
						new Console(elixir[i], r.nextInt(100),r.nextInt(100), port,ipArene);
						break;
					case 3:
						elixir[i]=new PotionDeForce();	
						new Console(elixir[i], r.nextInt(100),r.nextInt(100), port,ipArene);
						break;
					default:
				}
				Thread.sleep(1000);
			}	
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}