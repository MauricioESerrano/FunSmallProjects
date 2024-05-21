 /**
  * provides getters to get the properties about the Critter and simulation.
  */
  public interface CritterInfo {
    /**
     * Returns the animal that is 1 square in the given direction away
     * from this animal.
     * 
     * @param direction the direction.
     * @return the neighbor of this animal.
     */   
    public String getNeighbor(Critter.Direction direction);

    /**
     * Returns the height of the game simulation world.
     * 
     * @return the height of arena.
     */    
    public int getHeight();

    /**
     * Returns the width of the game simulation world.
     * 
     * @return the width of arena.
     */
    public int getWidth();

    /**
     * Returns this animal's current x-coordinate.
     * 
     * @return the current x-coordinate.
     */
    public int getX();

    /**
     * Returns this animal's current y-coordinate.
     * 
     * @return the current y-coordinate.
     */
    public int getY();

    /**
     * Returns whether or not this animal is currently alive.
     * 
     * @return if the animal is alive.
     */
    public boolean isAlive();

    /**
     * Returns whether or not this animal is currently awake.
     * 
     * @return if the animal is awake.
     */
    public boolean isAwake();
}
