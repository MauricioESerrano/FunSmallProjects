/* * This file is used to for string addition and multiplication using
 * a variety of different helper methods in order to achieve thy calculations.
 */

 /**
 * This class stores the  helper methods needed to evaulate the the calculations of the 
 * program. 
 */

public class Calculator { 
    
   /*this method extracts the whole number (left side of the 
   decimal)*/
    public static String extractWholeNumber(String number) {  
       
       //if decimal does not exist, return the number
        if( number.indexOf('.') == -1) {
            return number;
         }

        //if the first character of the string is a 
        //decimal, return 0. 
        if( number.charAt(0) == '.' ) {
            return "0";
         }
        
         //this part grabs the all the numbers from left of decimal and the 
         // return returns it to number
         else {
            
            number = number.substring(0, number.indexOf("."));       
        
            return number; 
         }
    }

    
        //this method here grabs the numbers left of the decimal 
    public static String extractDecimal(String number) {
    
        // this portion checks if the decimal exists, if it doesnt,
        //return 0. the second part checks if the last charcter is a decimal,which
        //also returns 0.
        if (number.indexOf(".") == -1 || number.charAt(number.length()-1) == '.'){
            return "0"; 
        }
        //if code passes previous if , then this substring grabs all right of decimal . 
         int index = number.indexOf(".");
        number = number.substring(index + 1, number.length());    
        return number;
    }



   //this method here adds 0's to the beginning of the whole number aspect. 
   public static String prependZeros(String number, int numZeros) {

        //goes through the loop of number
        for (int i = 0; i < numZeros; i++) {
            //string builder in order to build new string
            StringBuilder zeros = new StringBuilder("0");
            //add zeros using append to the front. 
            zeros.append(number);
           //convert the new string builder to string. 
            String resultString = zeros.toString();
            //change number  to new result string.
            number = resultString;
        }
        //return number back. 
        return number; 
    }


    //this method here appends 0's (adds to decimals). 
    public static String appendZeros(String number, int numZeros) {
        //temporary creation of string builder
        StringBuilder temporaryUse = new StringBuilder();
       
       //goes the the amount of numzeros exist
       //and append based on that loop
        //String zeros = "0";
        for (int i = 0; i < numZeros; i++) {

           temporaryUse.append("0");
            
                 
        }
        //converts the string back to loop 
        temporaryUse.toString();
        //reassigns temp use to n
        number = number + temporaryUse;  
        return number; 
    }

   
     //removes all leading or trailing zeros from whole number
     //and decimal number extract
     public static String stripZeros(String number) {
        
        int i;

        //goes through the loop and deterimnes if there is a decimal 
        //from index 0 to end index (length). 
        for (i = 0; i < extractWholeNumber(number).length()-1 && extractWholeNumber(number).length() > 1; i++) {
            if (extractWholeNumber(number).charAt(i) != '0') {
                break;
            }
        }

        //if it does, strip zeros from whole number
        String wholenumber = extractWholeNumber(number).substring(i);
    
       //sets j to length of extract decimal 
       int j = extractDecimal(number).length();
    
       //starts a decimalnumber string socan use after loop aswell.
       String decimalnumber; 
      
      //if length is 1, then just add whole plus decimal since no change is needed.
       if (j == 1) { 
           return wholenumber + "." + extractDecimal(number); 
       }
        //if change is needed, then go thtough for loop in order to remove 
        //leading zeros from decimal. 
        else {

            for (i = extractDecimal(number).length()-1; i >= 0; i--) {
                // if i hits a nonzero number, stop.
                if (extractDecimal(number).charAt(i) != '0') {
                break;
                }
            }
            //removes trailing zeros
            decimalnumber = extractDecimal(number).substring(0, i+1);
            if(i == -1){
              
                //loop doesnt encounter a zero
                // "runs past decimal place"
                decimalnumber = extractDecimal(number).substring(0, i+1) + "0";
            }
        }
        //sets number equal to the result of strip from both
        //whole number and decimal number. 
        number = wholenumber + "." + decimalnumber;   
    
       return number;
    
    }

    

    //determines if carryout is true or not.     
    public static boolean carryOut(char firstDigit, char secondDigit, boolean carryIn) {

    //values needed to convert from char to int     
    int A_int = firstDigit - '0'; 
    int B_int = secondDigit - '0';
    //ifcarryin is true, add 1, else 0. 
    int carryInToInt = carryIn ? 1 : 0; 
    
    //adds result of first digit and second digit and carry
    int addition = A_int + B_int + carryInToInt;

        //if addition is greater than 9, return true.   
        if ( addition > 9 ) {
            return true;
        }
        //return false if addition is lessthan 9.
        else {
            return false;
        }
    }   
   
   //this method adds up the first and second digit of the last numbers in the string
    public static char addDigits(char firstDigit, char secondDigit, boolean carryIn) {
       
       //calculations to convert char to int. 
       int A_int = firstDigit - '0'; 
        int B_int = secondDigit - '0';
        //if carryin true, 1, if not. 0 
        int carryInToInt = carryIn ? 1 : 0; 

        //further calculations
        int addition = A_int + B_int + carryInToInt;
        //range set to 10 in order to remove 
        int Range = 10; 
        
        //this portion here checks the result of the add
        //if carryin is true, sub 10 from. 
        char result; 
         if (carryOut(firstDigit, secondDigit, carryIn)) {
        
           int finalInt = (addition)-Range;
            //convert int back to 
            result = (char)(finalInt + '0');
        }
        else {
            result = (char) (addition + '0');
        } 
        return result;
    }

    //main method, use of all previous methods. 
    public static String add(String firstNumber, String secondNumber) {

        
        //first call to strip zeros. 
        firstNumber = stripZeros(firstNumber);
        secondNumber = stripZeros(secondNumber);        

        //checks if firstnumber is greater than length 0f second number 
        //if so, then prepend the second number. 
        if (extractWholeNumber(firstNumber).length() > extractWholeNumber(secondNumber).length()) {
           
            //subtracts first from second to see how much to prepend. 
            secondNumber = prependZeros(secondNumber, 
                
            extractWholeNumber(firstNumber).length() - extractWholeNumber(secondNumber).length());
           

        }
        //checks if secondnumber is greater than length 0f first number 
        //if so, then prepend the first number. 
        else if (extractWholeNumber(firstNumber).length() < extractWholeNumber(secondNumber).length()) {

            //check the length of second minus first to see how much to prepend. 
            firstNumber = prependZeros(firstNumber,
            
            extractWholeNumber(secondNumber).length() - extractWholeNumber(firstNumber).length());
           
        
           
        }

        //checks if first decimal in length is greater than length of second decimal 
        if (extractDecimal(firstNumber).length() > extractDecimal(secondNumber).length()) {
            
            //append zeros if true, and subtract length of decimal from second number
            //by first in order to add appriate amount of zeros. 
            secondNumber = appendZeros(secondNumber, 
                extractDecimal(firstNumber).length() - extractDecimal(secondNumber).length());

        }

        //checks if second decimal in length is greater than length of first decimal 
        else if (extractDecimal(firstNumber).length() < extractDecimal(secondNumber).length()) {
            
                //append zeros if true, and subtract length of decimal from first number
                //by second in order to add appriate amount of zeros. 
                firstNumber = appendZeros(firstNumber,
                    extractDecimal(secondNumber).length() - extractDecimal(firstNumber).length());
        }


        //int i and j for first num length and 
        //second num length respectively. 
        int i;
        int j;

        //temp use of the term boolean for this loop case. 
        //this acts for carry in boolean. 
        boolean temp = false; 

        //calling of string result. 
        String result = ""; 



            //addition of the two string using the add method. 
            //this reverse the string in order to go from left to right. 
            for (i = firstNumber.length(), j = secondNumber.length(); i >= 1 && j >= 1; i-- , j--) {
                if(firstNumber.charAt(i-1 ) == '.' || secondNumber.charAt(j-1) == '.'){
                    result += ".";
                    continue;
                }
                result += addDigits(firstNumber.charAt(i-1),secondNumber.charAt(j-1) , temp);
                temp = carryOut(firstNumber.charAt(i-1), secondNumber.charAt(j-1), temp);
             }
        //if carry in true, add 1. 
        if(temp){
            result += "1";
        }
        //this reverses the string back to forward. 
        result = new StringBuilder(result).reverse().toString();

        //if decimal does not exist, add 1. 
        if(result.indexOf('.') == -1){
            result += ".0";
        }
        
        return result;     
    }
    
    //multiply method which calls add n times. 
    public static String multiply(String number, int numTimes) {

        //if numtimes is negative, just return number. 
        if (numTimes < 0) {
            return number;
        }

        //strip number zeros. 
        number = stripZeros(number);
        
        //to return set equal to number.
        
        String toReturn = number;
        for (int i = 0; i < numTimes - 1; i++) {
            //to return updates after intial loop
            //which allows this loop to stack without
            //losing memory. 
            toReturn = add(toReturn, number);  
            
        }
        //returns toReturn as a result of multiply 
        return toReturn;

    } 
  
    //main method where all test cases would be handled.
    public static void main(String[] args) {

        System.out.println(stripZeros("10"));

    }
}
