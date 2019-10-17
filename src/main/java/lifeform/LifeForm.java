package lifeform;

import exceptions.WeaponException;
import weapon.Weapon;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form
 */

public abstract class LifeForm {
  protected String myName;
  protected String myType;
  protected int currentLifePoints;
  protected int attack;
  protected Weapon weapon;
  protected int row = -1;
  protected int col = -1;
  protected char currentDirection;
  protected int maxSpeed;
  protected int maxLifePoints;

  /**
   * Create an instance
   * 
   * @param name
   *          the name of the life form
   * @param points
   *          the current staring life points of the life form
   */
  public LifeForm(String name, int points) {
    this(name, points, 1);
  }

  /**
   * Create and instance
   * 
   * @param name
   *          of the life form
   * @param points
   *          of life the life form has
   * @param attack
   *          strength of the life form
   */
  public LifeForm(String name, int points, int attack) {
    myName = name;
    currentLifePoints = points;
    maxLifePoints = points;
    this.attack = attack;
    this.currentDirection = 'n';
  }

  /**
   * @return the name of the life form
   */
  public String getName() {
    return myName;
  }
  
  /**
   * Sets the lifeforms life
   */
  public void setLifePoints(int setHealth) {
    currentLifePoints = setHealth;
  }

  /**
   * returns the currently held weapon
   * 
   * @return
   */
  public Weapon getWeapon() {
    return weapon;
  }

  /**
   * @return the amount of current life points the life form has
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }
  
  public int getMaxLifePoints()
  {
    return maxLifePoints;
  }

  /**
   * Make the life form take damage
   * 
   * @param damage
   *          for the life form to take
   */
  public void takeHit(int damage) {
    currentLifePoints = currentLifePoints - damage;

    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

  /**
   * Gets the attack strength of the life form
   * 
   * @return attack of the life form
   */
  public int getAttackStrength() {
    return attack;
  }

  /**
   * determines whether or not the lifeform currently has a weapon
   * 
   * @return false for no weapon and true for weapon
   */

  public boolean hasWeapon() {
    if (weapon == null) {
      return false;
    } else {
      return true;
    }

  }

  /**
   * method for picking up a weapon
   * 
   * @param weapon1
   *          new weapon to be picked up
   * @return false for already has a weapon and true for a weapon being obtained
   */
  public boolean pickUpWeapon(Weapon weapon1) {
    if (getWeapon() != null) {
      return false;
    } else {
      weapon = weapon1;
      return true;
    }
  }

  /**
   * the attack method that has one lifeform attack another with a weapon or not,
   * attacker must be alive and to attack with weapon the lifefrom must have a
   * weapon, have ammo, and be within range.
   * 
   * @param opponent
   *          the lifeform being attacked
   * @param distance
   *          the distance from the attacker to opponent
   * @throws WeaponException
   */
  public void attack(LifeForm opponent, int distance) throws WeaponException {
    if (currentLifePoints > 0) {
      if (hasWeapon() == true) {
        opponent.takeHit(weapon.fire(distance));
      }
      if (distance >= 0) {
        if (distance <= 5 && hasWeapon() == true && weapon.getCurrentAmmo() == 0) {
          opponent.takeHit(getAttackStrength());
        }

        if (distance <= 5 && hasWeapon() == false) {
          opponent.takeHit(getAttackStrength());
        }
      }
    }
  }

  /**
   * dropWeapon method that drops the current weapon being held
   * 
   * @return the weapon that was held
   */

  public Weapon dropWeapon() {
    Weapon temp = weapon;
    weapon = null;
    return temp;
    
  }

  /**
   * allows lifeform to reload
   */
  public void reload() {
    if (hasWeapon() == true) {
      weapon.reload();
    }
  }

  public int getrow() {
    return row;

  }

  public int getcol() {
    return col;
  }

  /**
   * Sets the location for row and col
   * 
   * @param row1
   * @param col1
   */

  public void setLocation(int row1, int col1) {
    if (row1 >= 0 && col1 >= 0) {
      row = row1;
      col = col1;
    }
  }

  /**
   * Returns the current direction of the lifeform
   * 
   * @return character of the direction
   */
  public char getDirection() {
    return currentDirection;
  }

  /**
   * Set the direction of the life form
   * 
   * @param direction
   *          to set
   */
  public void setDirection(char direction) {
    currentDirection = direction;
  }

  /**
   * Returns the value of maxSpeed
   * 
   * @return maxSpeed value
   */
  public int getMaxSpeed() {
    return maxSpeed;
  }

  public String getType() { 
    return myType;
  }
}
