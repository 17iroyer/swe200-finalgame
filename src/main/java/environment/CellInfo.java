package environment;

import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * This class encapsulates information about a cell in the Environment The
 * intention is that this class can pass information without actually
 * interfering with the Cells in the Environment
 * 
 * @author Dr. Alice Armstrong
 *
 */
public class CellInfo {

  // private LifeForm cellLife; //the LifeForm that exists in this cell
  // private int weaponsCount; //how many weapons (not held by LifeForms) are in
  // this Cell
  // private Weapon weapon1; //a cell can hold up to 2 Weapons
  // private Weapon weapon2; //Any Weapons held by LifeForms are not counted
  private int row;
  private int col;
  private boolean hasWeapon1;
  private boolean hasWeapon2;
  private boolean hasLife; // does this cell have a lifeform in it
  private boolean hasHuman;
  private boolean hasAlien;

  public CellInfo(int row, int col, LifeForm life, boolean weapon1, boolean weapon2) {
    this.row = row;
    this.col = col;
    if (life != null) {
      hasLife = true;

      if (life instanceof Human) {
        hasHuman = true;
        hasAlien = false;
      } else {
        hasHuman = false;
        hasAlien = true;
      }
    } else {
      hasLife = false;
      hasHuman = false;
      hasAlien = false;
    }
    hasWeapon1 = weapon1;
    hasWeapon2 = weapon2;
  }

  /**
   * @return the row the cell is in
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column the cell is in
   */
  public int getCol() {
    // TODO Auto-generated method stub
    return col;
  }

  /**
   * @return true if the cell has a LifeForm in it
   */
  public boolean hasLife() {

    return hasLife;
  }

  /**
   * @return true if the cell has a weapon in position 1
   */
  public boolean hasWeapon1() {
    return hasWeapon1;
  }

  /**
   * @return true if the cell has a weapon in position 2
   */
  public boolean hasWeapon2() {
    return hasWeapon2;
  }

}
