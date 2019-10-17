package lifeform;

import exceptions.RecoveryRateException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;

public class Alien extends LifeForm implements TimerObserver {
  
  private int maxLifePoints;
  private int recoveryRate;
  private RecoveryBehavior recoveryBehavior;
  private int myTime;
  
  public Alien(String name, int maxLifePoints) throws RecoveryRateException {
    this(name, maxLifePoints, null, 0);
  }
  
  public Alien(String name, int maxLifePoints, 
               RecoveryBehavior behavior) throws RecoveryRateException {
    this(name, maxLifePoints, behavior, 0);
  }
  
  /**
   * Constructor for a full alien
   * @param name
   * @param maxLifePoints
   * @param behavior
   * @param recoveryRate
   * @throws RecoveryRateException
   */
  public Alien(String name, int maxLifePoints, 
               RecoveryBehavior behavior, int recoveryRate) throws RecoveryRateException {
    super(name, maxLifePoints, 10);
    this.maxLifePoints = maxLifePoints;
    this.recoveryBehavior = behavior;
    
    if (recoveryRate < 0) {
      throw new RecoveryRateException("Recovery Rate is below 0");
    }
    
    this.recoveryRate = recoveryRate;
    this.myTime = 0;
    this.maxSpeed = 2;
    myType = "Alien";
  }
  
  void setRecoveryRate(int recoveryRate) throws RecoveryRateException {
    if (recoveryRate < 0) {
      throw new RecoveryRateException("Recovery Rate is below 0");
    }
    
    this.recoveryRate = recoveryRate;
    this.maxSpeed = 2;
  }
  
  /**
   * Get the current recovery rate
   * @return recovery rate of the alien
   */
  public int getRecoveryRate() {
    return recoveryRate;
  }
  
  /**
   * Get the max life points
   * @return maxLifePoints the alien can have
   */
  public int getMaxLifePoints() {
    return this.maxLifePoints;
  }
  
  /**
   * Recover a certain amount of life
   */
  protected void recover() {
    if (recoveryBehavior != null) {
      this.currentLifePoints = 
           recoveryBehavior.calculateRecovery(this.currentLifePoints, this.maxLifePoints);
    }
  }
  
  /**
   * Set the current time of the Alien to the timer's
   * Recover if the recovery rate is even with the 
   * current time
   */
  public void updateTime(int time) {
    myTime = time;
    if (recoveryRate != 0 && myTime % recoveryRate == 0) {
      this.recover();
    }
  }
  
  public int getTime() {
    return myTime;
  }

}
