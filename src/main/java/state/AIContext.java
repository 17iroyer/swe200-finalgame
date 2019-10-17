package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;
import state.DeadState;

public class AIContext implements TimerObserver {

  private LifeForm currentRunner;
  public Environment currentEnvironment;
  private ActionState deadState;
  private ActionState hasWeaponState;
  private ActionState noWeaponState;
  private ActionState outOfAmmoState;
  private ActionState state;

  public AIContext(LifeForm r, Environment enviro) {
    currentRunner = r;
    currentEnvironment = enviro;
    deadState = new DeadState(currentRunner, this);
    hasWeaponState = new HasWeaponState(currentRunner, this);
    noWeaponState = new NoWeaponState(currentRunner, this);
    outOfAmmoState = new OutofAmmoState(currentRunner, this);
    if (currentRunner.hasWeapon() == false) {
      setCurrentState(noWeaponState);
    } else if (currentRunner.hasWeapon() == true && currentRunner.getWeapon().getCurrentAmmo() > 0) {
      setCurrentState(hasWeaponState);
    } else if (currentRunner.hasWeapon() == true && currentRunner.getWeapon().getCurrentAmmo() <= 0) {
      setCurrentState(outOfAmmoState);
    } else {
      setCurrentState(deadState);
    }

  }

  public void setCurrentState(ActionState state) {
    this.state = state;
  }

  public void TakeTurn() {
    state.executeAction();
    int rows = currentEnvironment.getNumRows();
    int columns = currentEnvironment.getNumCols();
    // Code Below might have to be changed
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        currentEnvironment.updateCell(i, j);
      }
    }
  }

  public ActionState getDeadState() {
    return deadState;
  }

  public ActionState getHasWeaponState() {
    return hasWeaponState;
  }

  public ActionState getNoWeaponState() {
    return noWeaponState;
  }

  public ActionState getOutOfAmmoState() {
    return outOfAmmoState;
  }

  public ActionState getCurrentState() {
    return state;
  }

  @Override
  public void updateTime(int time) {

  }

}
