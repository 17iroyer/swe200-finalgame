package state;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * Called after a Lifeform dies
 * 
 * @author zs3623
 */

public class DeadState extends ActionState {

  int counter = 0;
  
  public DeadState(LifeForm passLife, AIContext context) {
    super(passLife, context);
  }

  public void executeAction() {
    Environment currentEnvironment = currentAiContext.currentEnvironment;
    Weapon tempWeapon;
    Weapon[] tempWeaponArray;
    int exitCondition = 0;
    int exitConditionTwo = 0;
    int rowPlacer = 0, colPlacer = 0;
     
      /**
       *  Places lifeforms weapon in a random spot
       */
      
      if (actionLife.hasWeapon()) {
        tempWeapon = actionLife.dropWeapon();
        actionLife.setLifePoints(actionLife.getMaxLifePoints());
        while (exitCondition != 1) {
          rowPlacer = (int) (Math.random() * currentEnvironment.getNumRows());
          colPlacer = (int) (Math.random() * currentEnvironment.getNumCols());
          tempWeaponArray = currentEnvironment.getWeapons(rowPlacer, colPlacer);
          if (tempWeaponArray[0] == null) {
            currentEnvironment.addWeapon(tempWeapon, rowPlacer, colPlacer);
            exitCondition = 1;
          } else if (tempWeaponArray[1] == null) {
            currentEnvironment.addWeapon(tempWeapon, rowPlacer, colPlacer);
            exitCondition = 1;
          }
        }
      }
      
      
      /**
       * Places lifeform in random spot in environment
       */
      if(counter > 0)
      {
         while (exitConditionTwo != 1) {
           rowPlacer = (int) (Math.random() * currentEnvironment.getNumRows());
           colPlacer = (int) (Math.random() * currentEnvironment.getNumCols());
           if (currentEnvironment.getLifeForm(rowPlacer, colPlacer) == null) {
             currentEnvironment.addLifeForm(actionLife, rowPlacer, colPlacer);
             exitConditionTwo = 1;
           }
         }
         currentAiContext.setCurrentState(currentAiContext.getNoWeaponState());
      }
      counter++;
      if(counter == 2)
      {
        counter = 0;
      }
    }
}
