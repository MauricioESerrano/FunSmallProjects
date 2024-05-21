public class Feline extends Critter {
    
    private static final String SPECIES_NAME = "Fe";
    
    private int moveCount = -1; //counter for getMove method before random direction
    private int eatCount; //counter for eating
    private Direction currDir; //current direction

    public Feline() {
        super(SPECIES_NAME);
    }

    public Direction getMove() {
        

        if (moveCount == -1) {

            moveCount +=1;
            int randomInt = this.random.nextInt(4); //in 0, ex 4

            if (Direction.values()[randomInt] == Direction.NORTH) {
                // System.out.println("1");
                currDir = Direction.NORTH;
                return Direction.NORTH;
            }
            else if (Direction.values()[randomInt] == Direction.WEST) {

                // System.out.println("2");
                currDir = Direction.WEST;
                return Direction.WEST;
            }
            else if (Direction.values()[randomInt] == Direction.SOUTH) {
                // System.out.println("3");
                currDir = Direction.SOUTH;
                return Direction.SOUTH;
            }
            else if (Direction.values()[randomInt] == Direction.EAST) {
                // System.out.println("4");
                currDir = Direction.EAST;
                return Direction.EAST;
            }
        }


        // if (moveCount < 5) {
        //    moveCount +=1;
        //     System.out.println("YURcurr + movecount" + currDir + moveCount);
        //     return currDir;
        // }
        
        moveCount +=1;

        if (moveCount%5 == 0) {
        
            int randomInt = this.random.nextInt(4); //in 0, ex 4
            
            if (Direction.values()[randomInt] == Direction.NORTH) {
                // System.out.println("a");
                currDir = Direction.NORTH;
                return Direction.NORTH;
            }
            else if (Direction.values()[randomInt] == Direction.WEST) {
                // System.out.println("b");
                currDir = Direction.WEST;
                return Direction.WEST;
            }
            else if (Direction.values()[randomInt] == Direction.SOUTH) {
                // System.out.println("c");
                currDir = Direction.SOUTH;
                return Direction.SOUTH;
            }
            else if (Direction.values()[randomInt] == Direction.EAST) {
                // System.out.println("d");
                currDir = Direction.EAST;
                return Direction.EAST;
            }
        }

        // System.out.println("curr + movecount" + currDir + moveCount);
        return currDir;

    }    

    public boolean eat() {
        
        eatCount +=1;
        
        if (eatCount % 3 == 0) {
            
            
            return true;
           }
           
           return false;
    }

    public Attack getAttack(String opponent){
       return Attack.POUNCE;
    }
}