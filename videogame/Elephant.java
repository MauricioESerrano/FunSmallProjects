import java.awt.Color;

public class Elephant extends Critter {

    private static final String SPECIES_NAME = "El";

    protected static int goalX = 0;
    protected static int goalY = 0;
   
    public Elephant() {
        super(SPECIES_NAME);
    }
   
    public Color getColor() {
        return Color.GRAY;
    }

    public Direction getMove() {
        
        int currentX = this.info.getX();
        int currentY = this.info.getY();

        int sumX = currentX - goalX;
        int sumY = currentY - goalY;

        if (sumX == 0  && sumY == 0) {
            int randX = this.random.nextInt(this.info.getWidth());
            int randY = this.random.nextInt(this.info.getHeight());
            
            goalX = randX;
            goalY = randY;

        }
        System.out.println("GoalX: " + goalX + " GoalY: " + goalY);
        System.out.println("X: " + currentX + " Y: " + currentY);

        if (Math.abs(sumX) == Math.abs(sumY) && sumX > 0 ) {

            return Direction.WEST;

        }

        if (Math.abs(sumX) == Math.abs(sumY) && sumX <= 0 ) {

            return Direction.EAST;

        }
        
        if (Math.abs(sumX) > Math.abs(sumY) && sumX > 0) {

            return Direction.WEST;
        
        }

        if (Math.abs(sumX) > Math.abs(sumY) && sumX < 0) {
            return Direction.EAST;
        }

        if (Math.abs(sumX) < Math.abs(sumY) && sumY <= 0) {

            return Direction.SOUTH;
        }

        if (Math.abs(sumX) < Math.abs(sumY) && sumY > 0) {
            return Direction.NORTH;
        }

        System.out.println("never here");
      
        
        return Direction.EAST;

    }

    public boolean eat(){
        return true; 
    }

    public void mate() {

        incrementLevel(2);

    }


    public void reset() {
        goalX = 0;
        goalY = 0;
    }
}