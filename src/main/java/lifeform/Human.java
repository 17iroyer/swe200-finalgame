package lifeform;

public class Human extends LifeForm {
  
  private int currentArmor;
  
  /**
   * Constructor for a Human
   * @param name of human
   * @param lifePoints that the human has
   * @param armor that the human has
   */
  public Human(String name, int lifePoints, int armor) {
    super(name, lifePoints, 5);
    this.setArmorPoints(armor);
    this.maxSpeed = 3;
    myType = "Human";
  }
  
  /**
   * Gets the current Armor
   * @return currentArmor
   */
  public int getArmorPoints() {
    return currentArmor;
  }
  
  /**
   * Sets the armor the human has
   * @param points amount of armor points to set
   */
  public void setArmorPoints(int points) {
    if (points >= 0) {
      this.currentArmor = points;
    } else {
      this.currentArmor = 0;
    }
  }
  
  /**
   * Take damage with consideration to armor
   * @param damage to be taken
   */
  @Override
  public void takeHit(int damage) {
    if (damage > currentArmor) {
      currentLifePoints -= (damage - currentArmor);
    }
    
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }
  
}
