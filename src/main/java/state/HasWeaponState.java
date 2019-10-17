package state;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * @author zs3623 Called when a lifeform has a weapon
 */
// need to make it so own race doesnt attack each other and to recall search if
// no target is found
public class HasWeaponState extends ActionState {

  int row, col, distance;

  /**
   * Constructor for HasWeaponState
   * 
   * @param passLife
   */
  public HasWeaponState(LifeForm passLife, AIContext context) {
    super(passLife, context);

  }

  public void executeAction() {

    row = actionLife.getrow();
    col = actionLife.getcol();
    String type = actionLife.getType();
    int alreadyFired = 0;

    Environment currentEnvironment = currentAiContext.currentEnvironment;
    Weapon tempWeapon = actionLife.getWeapon();

    if (actionLife.getCurrentLifePoints() > 0) {
      // based of attack command the lifeform aquires target and attacks
      if (actionLife.getDirection() == 'n') {
        // checks each row north for lifeforms, if one is found it is attacked
        for (int i = row; i >= 0; i--) {
          // Checks if a cell has a lifeform in it
          if (currentEnvironment.getLifeForm(i, col) != null && currentEnvironment.getLifeForm(i, col) != actionLife) {
            try {
              distance = (int) Math
                  .round(currentEnvironment.getDistance(actionLife, currentEnvironment.getLifeForm(i, col)));
            } catch (EnvironmentException e) {
              System.out.println("attackCommand north is throwing an environment exception");
            }

          }
          try {
            // need reload
            if (tempWeapon.getCurrentAmmo() > 0) {
              if (currentEnvironment.getLifeForm(i, col) == null) {
                if (actionLife.getDirection() == 'n') {
                  actionLife.setDirection('e');
                } else if (actionLife.getDirection() == 'e') {
                  actionLife.setDirection('s');
                }
                if (actionLife.getDirection() == 's') {
                  actionLife.setDirection('w');
                } else {
                  actionLife.setDirection('n');
                }
              } else if (type != currentEnvironment.getLifeForm(i, col).getType()) {
                actionLife.attack(currentEnvironment.getLifeForm(i, col), distance);
              } else if (type == currentEnvironment.getLifeForm(i, col).getType()) {
                search();
              }
            } else if (tempWeapon.getCurrentAmmo() == 0) {
              currentAiContext.setCurrentState(currentAiContext.getOutOfAmmoState());
            }
            if (currentEnvironment.getLifeForm(i, col) != null
                && currentEnvironment.getLifeForm(i, col).getCurrentLifePoints() == 0) {
              currentEnvironment.removeLifeForm(i, col);
            }
            alreadyFired = 1;
          } catch (WeaponException e) {
            System.out.println("attackCommand north is throwing a weapon exception");
          }
        }
        if (alreadyFired == 0) {
          search();
        }

      }

      else if (actionLife.getDirection() == 'e') {
        for (int i = col; i < currentEnvironment.getNumCols(); i++) {
          // Checks if a cell has a lifeform in it
          if (currentEnvironment.getLifeForm(row, i) != null && currentEnvironment.getLifeForm(row, i) != actionLife) {
            try {
              distance = (int) Math
                  .round(currentEnvironment.getDistance(actionLife, currentEnvironment.getLifeForm(row, i)));
            } catch (EnvironmentException e) {
              System.out.println("attackCommand east is throwing an environment exception");
            }
            try {
              if (tempWeapon.getCurrentAmmo() > 0) {
                if (currentEnvironment.getLifeForm(i, col) == null) {
                  if (actionLife.getDirection() == 'n') {
                    actionLife.setDirection('e');
                  } else if (actionLife.getDirection() == 'e') {
                    actionLife.setDirection('s');
                  }
                  if (actionLife.getDirection() == 's') {
                    actionLife.setDirection('w');
                  } else {
                    actionLife.setDirection('n');
                  }
                } else if (currentEnvironment.getLifeForm(row, i) != null
                    && currentEnvironment.getLifeForm(row, i) != actionLife) {
                  actionLife.attack(currentEnvironment.getLifeForm(i, col), distance);
                } else if (type == currentEnvironment.getLifeForm(i, col).getType()) {
                  search();
                }
              } else {
                currentAiContext.setCurrentState(currentAiContext.getOutOfAmmoState());
              }
              if (currentEnvironment.getLifeForm(row, i).getCurrentLifePoints() == 0) {
                currentEnvironment.removeLifeForm(row, i);
              }
              alreadyFired = 1;
            } catch (WeaponException e) {
              System.out.println("attackCommand east is throwing a weapon exception");
            }
          }
        }
        if (alreadyFired == 0) {
          search();
        }
      } else if (actionLife.getDirection() == 's') {
        for (int i = col; i < currentEnvironment.getNumCols(); i++) {
          // Checks if a cell has a lifeform in it
          if (currentEnvironment.getLifeForm(row, i) != null && currentEnvironment.getLifeForm(row, i) != actionLife) {
            try {
              distance = (int) Math
                  .round(currentEnvironment.getDistance(actionLife, currentEnvironment.getLifeForm(row, i)));
            } catch (EnvironmentException e) {
              System.out.println("attackCommand east is throwing an environment exception");
            }
            try {
              if (tempWeapon.getCurrentAmmo() > 0) {
                if (currentEnvironment.getLifeForm(i, col) == null) {
                  if (actionLife.getDirection() == 'n') {
                    actionLife.setDirection('e');
                  } else if (actionLife.getDirection() == 'e') {
                    actionLife.setDirection('s');
                  }
                  if (actionLife.getDirection() == 's') {
                    actionLife.setDirection('w');
                  } else {
                    actionLife.setDirection('n');
                  }
                } else if (currentEnvironment.getLifeForm(row, i) != null
                    && currentEnvironment.getLifeForm(row, i) != actionLife) {
                  actionLife.attack(currentEnvironment.getLifeForm(i, col), distance);
                } else if (type == currentEnvironment.getLifeForm(i, col).getType()) {
                  search();
                }
              } else {
                currentAiContext.setCurrentState(currentAiContext.getOutOfAmmoState());
              }
              if (currentEnvironment.getLifeForm(row, i).getCurrentLifePoints() == 0) {
                currentEnvironment.removeLifeForm(row, i);
              }
              alreadyFired = 1;
            } catch (WeaponException e) {
              System.out.println("attackCommand east is throwing a weapon exception");
            }
          }
        }
        if (alreadyFired == 0) {
          search();
        }
      }
      if (actionLife.getDirection() == 'w') {
        // checks each row north for lifeforms, if one is found it is attacked
        for (int i = row; i >= 0; i--) {
          // Checks if a cell has a lifeform in it
          if (currentEnvironment.getLifeForm(i, col) != null && currentEnvironment.getLifeForm(i, col) != actionLife) {
            try {
              distance = (int) Math
                  .round(currentEnvironment.getDistance(actionLife, currentEnvironment.getLifeForm(i, col)));
            } catch (EnvironmentException e) {
              System.out.println("attackCommand north is throwing an environment exception");
            }
            try {
              if (tempWeapon.getCurrentAmmo() > 0) {
                if (currentEnvironment.getLifeForm(i, col) == null) {
                  System.out.println(actionLife.getDirection());
                  if (actionLife.getDirection() == 'n') {
                    actionLife.setDirection('e');
                  } else if (actionLife.getDirection() == 'e') {
                    actionLife.setDirection('s');
                  }
                  if (actionLife.getDirection() == 's') {
                    actionLife.setDirection('w');
                  } else {
                    actionLife.setDirection('n');
                  }
                  System.out.println(actionLife.getDirection());
                } else if (type != currentEnvironment.getLifeForm(i, col).getType()) {
                  actionLife.attack(currentEnvironment.getLifeForm(i, col), distance);
                } else if (type == currentEnvironment.getLifeForm(i, col).getType()) {
                  search();
                }
              } else {
                currentAiContext.setCurrentState(currentAiContext.getOutOfAmmoState());
              }
              if (currentEnvironment.getLifeForm(i, col).getCurrentLifePoints() == 0) {
                currentEnvironment.removeLifeForm(i, col);
              }
              alreadyFired = 1;
            } catch (WeaponException e) {
              System.out.println("attackCommand north is throwing a weapon exception");
            }
          }
        }
        if (alreadyFired == 0) {
          search();
        }
      }
      /**
       * move in a random direction and a 50% chance to move to a new cell
       */

    } else {
      currentAiContext.setCurrentState(currentAiContext.getDeadState());

    }

  }

  public void search() {

    int randomDir = (int) (Math.random() * 4);
    int randomMove = (int) (Math.random() * 2);

    if (randomDir == 0) {
      actionLife.setDirection('n');
      if (randomMove == 1) {
        currentAiContext.currentEnvironment.move(actionLife);
      }
    } else if (randomDir == 1) {
      actionLife.setDirection('s');
      if (randomMove == 1) {
        currentAiContext.currentEnvironment.move(actionLife);
      }
    } else if (randomDir == 2) {
      actionLife.setDirection('e');
      if (randomMove == 1) {
        currentAiContext.currentEnvironment.move(actionLife);
      }
    } else if (randomDir == 3) {
      actionLife.setDirection('w');
      if (randomMove == 1) {
        currentAiContext.currentEnvironment.move(actionLife);
      }
    }
  }
}
