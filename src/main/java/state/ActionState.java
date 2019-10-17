package state;

import lifeform.LifeForm;

/**
 * @author zs3623 Generic Version of the States
 */

public abstract class ActionState {
  protected LifeForm actionLife;
  protected AIContext currentAiContext;

  /**
   * Constructor for ActionState
   * 
   * @param selectLife
   */

  public ActionState(LifeForm selectLife, AIContext context) {
    actionLife = selectLife;
    currentAiContext = context;
  }

  public abstract void executeAction();
}
