import java.awt.Color;

public class Turtle extends Critter {
    private static final String SPECIES_NAME = "Tu";

    public Turtle() {
        super(SPECIES_NAME);
    }

    public Direction getMove() {
        return Direction.WEST;
    }

    public Attack getAttack(String opponent){
        
        int random = this.random.nextInt(2);
        int forfeit = 0;
        
        if (random == forfeit) {

            return Attack.FORFEIT;
        }

        else {

            return Attack.ROAR;

        }

    }

    public boolean eat(){
        
        if(this.info.getNeighbor(Direction.NORTH).equals("Tu") || this.info.getNeighbor(Direction.NORTH).equals(" ") || this.info.getNeighbor(Direction.NORTH).equals(".") ) {

            return true;

        }

        if(this.info.getNeighbor(Direction.NORTH).equals("Tu") || this.info.getNeighbor(Direction.NORTH).equals(" ")|| this.info.getNeighbor(Direction.NORTH).equals(".") ) {

            return true;

        }

        if(this.info.getNeighbor(Direction.NORTH).equals("Tu") || this.info.getNeighbor(Direction.NORTH).equals(" ")|| this.info.getNeighbor(Direction.NORTH).equals(".") ) {

            return true;

        }

        if(this.info.getNeighbor(Direction.NORTH).equals("Tu") || this.info.getNeighbor(Direction.NORTH).equals(" ")|| this.info.getNeighbor(Direction.NORTH).equals(".") ) {

            return true;

        }

        else {
            return false;
        }

    }

    public Color getColor() {
        return Color.GREEN;
    }
}