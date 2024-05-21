import java.awt.Color;

public class Ocelot extends Leopard {

    private static final String SPECIES_NAME = "Oce";
   

    public Ocelot() {
        
        this.displayName = SPECIES_NAME;

    }
   
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

    protected Attack generateAttack(String opponent) {
        
        if ( opponent.equals("Lion") || opponent.equals("Fe") || opponent.equals("Lpd")) {

            return Attack.SCRATCH;

        }

        else {

            return Attack.POUNCE;

        }
    }
}