import java.util.Scanner;

public class PasswordSecurity {

        private static final String PW_PROMPT = "Please enter a password: ";
        private static final String PW_TOO_SHORT = "Password is too short";
        private static final String PW_VERY_WEAK = "Password strength: very weak";
        private static final String PW_WEAK = "Password strength: weak";
        private static final String PW_MEDIUM = "Password strength: medium";
        private static final String PW_STRONG = "Password strength: strong";
        private static final String SUGGESTION_PROMPT = "Here is a suggested stronger password: ";
        private static final String LETTER_RULE_SUGGESTION = "Cse";
        private static final String SYMBOL_RULE_SUGGESTION = "@!";
    
        private static final int MIN_PW_LENGTH = 8;
        private static final int VERY_WEAK_THRESHOLD = 1;
        private static final int WEAK_THRESHOLD = 2;
        private static final int MEDIUM_THRESHOLD = 3;
        private static final int STRONG_THRESHOLD = 4;
        private static final int LETTER_COUNT_THRESHOLD = 2;
        private static final int DIGIT_INTERVAL = 4;
        private static final int MOD_FACTOR = 10;

    public static void main (String[] args) {

        try (Scanner UserInput = new Scanner (System.in)) {
            System.out.print(PW_PROMPT);
            

            String FirstPassword = UserInput.nextLine();
            int userPasswordLength = FirstPassword.length(); 


            if (userPasswordLength <= 7) {
                System.out.print(PW_TOO_SHORT);
            }



            else { 
            
            //String[] Characters = FirstPassword.split(""); //list of characters
            int containsLowerCase = 0;
            int containsUpperCase = 0;
         
            //LOWERCASE Check


                for (int i = 0; i <FirstPassword.length(); i++ ) {
                    char currCharA = FirstPassword.charAt(i);
                    if (Character.isLetter(currCharA) && Character.isUpperCase(currCharA)) {
                        
                        containsLowerCase = 1;
            
                    }  
                }

                //UPPERCASE CHECK

                for (int i = 0; i <FirstPassword.length(); i++ ) {
                    char currCharB = FirstPassword.charAt(i);
                    if (Character.isLetter(currCharB) && Character.isUpperCase(currCharB)) {
                        
                        containsUpperCase = 1;

                    }  
                }
                 

             

                //checkspecial

            String containsSpecial = "!\"/.-,+*)(')&%$#:;<>=?@\\[]^_`~{}|"; 
            int SpecialCh = 0; 

             for (int a=0; a < FirstPassword.length() ; a++) {
                    char SpecialChracters = FirstPassword.charAt(a);
            
                if(containsSpecial.contains(Character.toString(SpecialChracters))) {
                    
                    SpecialCh = 1;
                 }    
            }

                //check for numbers

            String containsNumber = "1234567890"; 
            int numberTrueFalse = 0; 

             for (int b=0; b < FirstPassword.length() ; b++) {
                    char Number = FirstPassword.charAt(b);
            
                if(containsNumber.contains(Character.toString(Number))) {
                    
                    numberTrueFalse = 1;
                 }    
            }
            



               
              if ((numberTrueFalse + SpecialCh + containsLowerCase + containsUpperCase) == 4)  {

                System.out.println(PW_STRONG);
                 
                }
                 if ((numberTrueFalse + SpecialCh + containsLowerCase + containsUpperCase) == 3)  {

                    System.out.println(PW_MEDIUM);
                     
                    }
                     if ((numberTrueFalse + SpecialCh + containsLowerCase + containsUpperCase) == 2)  {

                        System.out.println(PW_WEAK);
                         
                        }
                         if ((numberTrueFalse + SpecialCh + containsLowerCase + containsUpperCase) == 1)  {

                            System.out.println(PW_VERY_WEAK);
                             
                            }
              
              
              
              
              
              //password suggestion bit   

            int letterCount = 0; 

            for (int c = 0; c <FirstPassword.length(); c++ ) {
                if (Character.isLetter(FirstPassword.charAt(c))) {
                    letterCount++;
                }
            }
            if (letterCount < 2) {
                StringBuilder ammendOne = new StringBuilder("Cse");
                ammendOne.append(FirstPassword);

                String resultString = ammendOne.toString();
                System.out.println(resultString);
            }

            
            
            
            /*String[] temp = FirstPassword.split(" ");
            int[] temp1 = new int[FirstPassword.length()];*/
           // temp1[containsLowerCase] = Integer.parseInt(temp[containsLowerCase]); 
            

           

            if (containsUpperCase == 0) {
                
                char[] charArray = FirstPassword.toCharArray();

                boolean isLetter = false;

                for (int i=0; i < charArray.length; i++) {
                       
                   if (Character.isLetter(charArray[i])) {
                    
                        isLetter = true; 
                    
                        System.out.println(isLetter + "yes");

                   }
                   

                    while (isLetter==true && containsUpperCase == 0) {

                        charArray[i] = Character.toUpperCase(charArray[i]);   
                        
                    }


                }        

                FirstPassword = String.valueOf(charArray);

                 
            }

             System.out.println(FirstPassword);

            if (containsLowerCase == 0) {
                
                /*for (int i=0; i < FirstPassword.length() ; i++) {
                    char toUpper = FirstPassword.charAt(i); 
                    boolean isLetterCap = Character.isLetter(toUpper);

                    if (isLetterCap ==true) {

                    String AmmendTwo = FirstPassword.substring(0,1).toLowerCase() +FirstPassword.substring(1);
                    
                    System.out.println(AmmendTwo)

                    }

                }    */     

                
                
                
                
            }

            


        
                

            
            
            
            
            
            
            }
        }    
    }
}  
 
