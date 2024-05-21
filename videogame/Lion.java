import java.awt.Color;

public class Lion extends Feline {

    private static final String SPECIES_NAME = "Lion";
    private static final String SPECIES_NAME_SLEEP = "noiL";
    private int wins;
    private int loop = 0;


    public Lion() {
        this.displayName = SPECIES_NAME;
    }

    public Direction getMove() {

        if (loop < 5) {

            loop += 1;
            return Direction.EAST;

        }
        if (loop < 10) {

            loop += 1;
            return Direction.SOUTH;

        }
        if (loop < 15) {

            loop += 1;
            return Direction.WEST;

        }
        if (loop < 20) {

            loop += 1;
            return Direction.NORTH;

        }

        loop = 0;

        return getMove();

    }

   
    public Color getColor() {
        return Color.YELLOW;
    }

    public boolean eat() {
        
       if (wins == 1) {

        wins = 0;
        return true;

       }

        return false;

    }

    public void sleep() {

        wins = 0;

        this.displayName = SPECIES_NAME_SLEEP;
        
    }

    public void wakeup() {

        this.displayName = SPECIES_NAME;

    }

    public void win() {

        wins += 1;

    }
}