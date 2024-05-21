import java.awt.Color;

public class Leopard extends Feline {

    protected static int confidence = 0;

    private static final String SPECIES_NAME = "Lpd";
   

    public Leopard() {
        
        this.displayName = SPECIES_NAME;

    }

    public Direction getMove() {

        if (this.info.getNeighbor(Direction.NORTH).equals(".") || this.info.getNeighbor(Direction.NORTH).equals("Patrick"))  {

            return Direction.NORTH;

        }

        if (this.info.getNeighbor(Direction.SOUTH).equals(".") || this.info.getNeighbor(Direction.SOUTH).equals("Patrick"))  {

            return Direction.SOUTH;

        }

        if (this.info.getNeighbor(Direction.EAST).equals(".") || this.info.getNeighbor(Direction.EAST).equals("Patrick"))  {

            return Direction.EAST;

        }

        if (this.info.getNeighbor(Direction.WEST).equals(".") || this.info.getNeighbor(Direction.WEST).equals("Patrick"))  {

            return Direction.NORTH;

        }

        int randomInt = this.random.nextInt(4); //in 0, ex 4
            
            if (Direction.values()[randomInt] == Direction.NORTH) {
                
                return Direction.NORTH;
            }
            else if (Direction.values()[randomInt] == Direction.WEST) {

                return Direction.WEST;
            }
            else if (Direction.values()[randomInt] == Direction.SOUTH) {
                
                return Direction.SOUTH;
            }
            else if (Direction.values()[randomInt] == Direction.EAST) {
                
                return Direction.EAST;
            }

        return getMove();

    }

   
    public Color getColor() {
        return Color.RED;
    }

    public boolean eat() {
        
        // int chanceEating = (confidence*1); 
        
        String[] chances = new String[11]; //idk if you want eleven

        for (int i = 0; i < confidence ; i++) {

            chances[i] = "a";

        }
        int index = this.random.nextInt(11); // 0 - 10
        if(chances[index] == null){
            return false;
        }
        if (chances[index].equals("a")) {

            return true;

        }       

        return false;
    }


    public void win() {

        if (confidence < 10) {

            confidence += 1;

        }
   

    }

    public void lose() {

        if (confidence > 0) {

            confidence -= 1;

        }
    }

    public Attack getAttack(String opponent){
    
        if (confidence > 5 || opponent.equals("Tu")) {
            
            return Attack.POUNCE;
        }

        return generateAttack(opponent);

    }

    protected Attack generateAttack(String opponent) {
       
        if (opponent.equals("Patrick")) {
            return Attack.FORFEIT;
        }

        int randomInt = this.random.nextInt(3); //in 0, ex 3

        if (randomInt == 0) {

            return Attack.POUNCE;

        }

        if (randomInt == 1) {

            return Attack.ROAR;

        }
        if (randomInt == 2) {

            return Attack.SCRATCH;

        }

        return Attack.FORFEIT;
    }

    public void reset() {
        
        confidence = 0;
    }
}