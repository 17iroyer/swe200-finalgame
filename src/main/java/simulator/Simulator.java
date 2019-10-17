package simulator;

/**
 * Simulator that fills the environment with the desired number of LifeForms and runs the AI against each other
 * @author Christopher Woltz
 *
 */

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.PlasmaCannon;
import weapon.Weapon;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.GenericWeapon;
import gameplay.TimerObserver;
import gameplay.SimpleTimer;
import state.AIContext;
import java.util.Random;
import recovery.RecoveryFractional;
import recovery.RecoveryNone;
import recovery.RecoveryLinear;
import exceptions.RecoveryRateException;

public class Simulator implements TimerObserver{

	Human[] theHumans;
	Alien[] theAliens;
	GenericWeapon[] theWeapons;
	int numberOfLifeForms;
	int rows;
	int cols;
	Environment theEnvironment;
	SimpleTimer timer;
	int theTime;
	AIContext[] theAI;
	Random rand = new Random();
	int counterAI;
	int randomRow;
	int randomCol;
	int numHumans2;
	int numAliens2;
	
	/**
	 * @param numHumans, numAliens: the number of humans and the number of aliens the simulator will generate.
	 * @throws RecoveryRateException
	 */
	public Simulator(Environment e, SimpleTimer theTimer, int numHumans, int numAliens) throws RecoveryRateException {
		theEnvironment = e;
		rows = theEnvironment.getNumRows();
		cols = theEnvironment.getNumCols();
		numHumans2 = numHumans;
		numAliens2 = numAliens;
		numberOfLifeForms = numHumans + numAliens;
		theHumans = new Human[numHumans];
		theAliens = new Alien[numAliens];
		theWeapons = new GenericWeapon[numberOfLifeForms];
		theAI = new AIContext[numberOfLifeForms];
		timer = theTimer;
		timer.addTimeObserver(this);
		
		/**
		 * initialize the Humans with random armor.
		 */
		if(theHumans != null) {
			for(int i = 0; i < numHumans; i++) {
				randomRow = rand.nextInt(10);
				theHumans[i] = new Human("Trey", 10, randomRow);
			}
		}
		
		/**
		 * initialize an array of varying Alien types.
		 */
		if(theAliens != null) {
			for(int i = 0; i < numAliens; i++) {
				randomRow = rand.nextInt(3);
					if(randomRow == 0) {
						theAliens[i] = new Alien("Moo", 10, new RecoveryNone());
					}
					if(randomRow == 1) {
						theAliens[i] = new Alien("Moo2", 10, new RecoveryLinear(5));
					}
					if(randomRow == 2) {
						theAliens[i] = new Alien("Moort", 10, new RecoveryFractional(.05));
					}
			}
		}
		
		/**
		 * Create an random distribution of Weapons to spread in the Environment
		 */
		if(theWeapons != null) {
			for(int i = 0; i < numberOfLifeForms; i++) {
				randomRow = rand.nextInt(3);
					if(randomRow == 0)
						theWeapons[i] = new Pistol();
					if(randomRow == 1)
						theWeapons[i] = new ChainGun();
					if(randomRow == 2)
						theWeapons[i] = new PlasmaCannon();
			}

		}
		
		/**
		 * place humans randomly in the environment.
		 * ##methods not fully implemented. always return true
		 */
		for(int i = 0; i < numHumans; i++) {
			if(this.placeLifeFormRandom(theHumans[i]) == false) {
				this.placeLifeFormRandom(theHumans[i]);
					setAI(i);
			}
			else {
				setAI(i);
			}
		}
		
		/**
		 * place the aliens around the environment.
		 * ##methods always true for now
		 */
		for(int i = 0; i < numAliens; i++) {
			if(this.placeLifeFormRandom(theAliens[i]) == false) {
				this.placeLifeFormRandom(theAliens[i]);
					setAI(i + numHumans);
			}
			else {
				setAI(i + numHumans);
			}
		}
		
		/**
		 * place the weapons around randomly
		 * ##methods always true for now
		 */
		for(int i = 0; i < numberOfLifeForms; i++) {
			if(this.placeWeaponRandom(theWeapons[i]) == false) {
				this.placeWeaponRandom(theWeapons[i]);
			}
		}
		
	}

	/**
	 * @param value
	 * @return
	 */
	public boolean setAI(int value) {
	  if(value < numHumans2)
	  {
	    theAI[value] = new AIContext(theHumans[value], theEnvironment);
	  } else 
	  {
	    theAI[value] = new AIContext(theAliens[value - numHumans2], theEnvironment);
	  }
	  return true;
	}
	
	/**
	 * @param life
	 * @return
	 */
	public boolean placeLifeFormRandom(LifeForm life) {
	  int exitCondition = 0;
	  while (exitCondition != 1) {
	    int rowPlacer = 0;
	    int colPlacer = 0;
      rowPlacer = (int) (Math.random() * theEnvironment.getNumRows());
      colPlacer = (int) (Math.random() * theEnvironment.getNumCols());
      if (theEnvironment.getLifeForm(rowPlacer, colPlacer) == null) {
        theEnvironment.addLifeForm(life, rowPlacer, colPlacer);
        theEnvironment.updateCell(rowPlacer, colPlacer);
        exitCondition = 1;
      }
	  }
		return true;
	}
	
	/**
	 * @param weapon
	 * @return
	 */
	public boolean placeWeaponRandom(GenericWeapon weapon) {
	   int exitCondition = 0;
	   Weapon[] tempWeaponArray;
	   while (exitCondition != 1) {
	    int rowPlacer = 0;
	    int colPlacer = 0;
      rowPlacer = (int) (Math.random() * theEnvironment.getNumRows());
      colPlacer = (int) (Math.random() * theEnvironment.getNumCols());
      tempWeaponArray = theEnvironment.getWeapons(rowPlacer, colPlacer);
      if (tempWeaponArray[0] == null) {
        theEnvironment.addWeapon(weapon, rowPlacer, colPlacer);
        theEnvironment.updateCell(rowPlacer, colPlacer);
        exitCondition = 1;
      } else if (tempWeaponArray[1] == null) {
        theEnvironment.addWeapon(weapon, rowPlacer, colPlacer);
        theEnvironment.updateCell(rowPlacer, colPlacer);
        exitCondition = 1;
      }
	  }
	  return true;
	}
	
	@Override
	public void updateTime(int time) {
		for(int i = 0; i < theAI.length; i++)
		{
		  theAI[i].TakeTurn();
		}
	}
}