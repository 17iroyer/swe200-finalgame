package state;

import lifeform.LifeForm;
import weapon.Weapon;

public class NoWeaponState extends ActionState {

  public NoWeaponState(LifeForm passLife, AIContext context) {
    super(passLife, context);
  }

  public void executeAction() {
    if (actionLife.getCurrentLifePoints() > 0) {
      Weapon[] tempArray = currentAiContext.currentEnvironment.getWeapons(actionLife.getrow(), actionLife.getcol());
      if (tempArray[0] != null) {
        actionLife.pickUpWeapon(tempArray[0]);
        currentAiContext.currentEnvironment.removeWeapon(tempArray[0], actionLife.getrow(), actionLife.getcol());
        currentAiContext.setCurrentState(currentAiContext.getHasWeaponState());
      } else if (tempArray[1] != null) {
        actionLife.pickUpWeapon(tempArray[1]);
        currentAiContext.currentEnvironment.removeWeapon(tempArray[1], actionLife.getrow(), actionLife.getcol());
        currentAiContext.setCurrentState(currentAiContext.getHasWeaponState());
      } else {
        int randomDir = (int) (Math.random() * 4);

        if (randomDir == 0) {
          actionLife.setDirection('n');
          currentAiContext.currentEnvironment.move(actionLife);
        } else if (randomDir == 1) {
          actionLife.setDirection('s');
          currentAiContext.currentEnvironment.move(actionLife);
        } else if (randomDir == 2) {
          actionLife.setDirection('e');
          currentAiContext.currentEnvironment.move(actionLife);
        } else if (randomDir == 3) {
          actionLife.setDirection('w');
          currentAiContext.currentEnvironment.move(actionLife);
        }
      }
    } else {
      currentAiContext.setCurrentState(currentAiContext.getDeadState());
    }
  }
}