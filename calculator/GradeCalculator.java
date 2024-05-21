import java.util.Scanner;
public class GradeCalculator {
    public static void main(String[] args) {
        
        System.out.println("input pa scores here"); //TODO: remove

        Scanner keyboard = new Scanner(System.in);
 
        
        String firstGrades = keyboard.nextLine(); //gets the input string "1 2 3 4"
        String[] grades1 = firstGrades.split(" "); //converts to string array: [1,2,3,4]
        int[] grades1Int = new int[grades1.length]; //creates int array of same size as above
        boolean legalInputs = true;
            

        for(int i = 0; i < grades1.length; i++) {
            grades1Int[i] = Integer.parseInt(grades1[i]);
            if ( grades1Int[i] < 0 || grades1Int[i] > 100) {
                legalInputs = false;
            }
        }

        

        if (legalInputs == false) {
            System.out.println("invalid input");
        }
        else {


            System.out.println("input midterm final"); //TODO: remove


            String secondGrades = keyboard.nextLine();
            String[] grades2 = secondGrades.split(" ");
            int[] grades2Int = new int[grades2.length];
            boolean legalInputs2 = true;
            
            for(int i = 0; i < grades2.length; i++) {
                grades2Int[i] = Integer.parseInt(grades2[i]);
                if ( grades2Int[i] < 0 || grades2Int[i] > 100) {
                    legalInputs2 = false;
            }
        }

            if (legalInputs2 == false) {
                System.out.println("invalid input");
            }
                else {

            
            

            double finalGrade;
        // int finalLetterGrade;
            
            double accumulation = 0;

            for(int i = 1; i < grades1Int.length; i++) {
                accumulation += grades1Int[i];      
            }
            
            
            finalGrade = (double)(accumulation / grades1Int[0])  * 0.5 + (grades2Int[0] * 0.125) + (grades2Int[1] * 0.375);
            System.out.println(finalGrade);

            




            if (finalGrade>=90 && finalGrade<=100) {
                System.out.println("A");
                }

            if (finalGrade>=80 && finalGrade<90) {
            System.out.println("B"); 
                }

            if (finalGrade>=70 && finalGrade<80) {
                System.out.println("C");
                }
            if (finalGrade>=60 && finalGrade<70) {
                System.out.println("D");
                }
            if (finalGrade>=0 && finalGrade<60) {
                System.out.println("F");
                }


            }
        }
    }
}
        
//add the invalid part for the second grades values inputs uknow?