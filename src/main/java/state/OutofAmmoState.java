package state;

import lifeform.LifeForm;

public class OutofAmmoState extends ActionState {

  public OutofAmmoState(LifeForm passLife, AIContext context) {
    super(passLife, context);
  }

  /**
   * Executes tha out of ammo action
   */
  public void executeAction() {
    if (actionLife.getCurrentLifePoints() > 0) {
      actionLife.reload();
      currentAiContext.setCurrentState(currentAiContext.getHasWeaponState());
    } else {
      currentAiContext.setCurrentState(currentAiContext.getDeadState());
    }
  }
}