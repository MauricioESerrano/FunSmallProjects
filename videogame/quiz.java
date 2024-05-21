import java.util.Random;

public class quiz {

    protected static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) {
        

        for (int i = 0; i < 20; i++) {

            int randomInt = random.nextInt(4);
            System.out.println(randomInt);

        }
       
    }
}