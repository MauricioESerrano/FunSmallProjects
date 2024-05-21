/* This file is essentially a LL for strings. we made this file to avoid creating new arrays everytime we want to edit string. instead we alter it slightly by assigning next values.
 */

//class for is the main body for the DIY LL of stringbuilding.
public class MyStringBuilder {
    
    /** Instance variables */
    private CharNode start;
    private CharNode end;
    private int length;

     /**
     * The constructor initializes the instance variables and creates an MSB from paramater
     * 
     * @param char the single char that will produce the MSB
     */
    public MyStringBuilder(char ch) {
        
        start = new CharNode(ch); //head of linked list
        start.setNext(end); //set next
        end = start; //tail of linked list
        length = 1; //initiate length
        
    }

    /**
     * The constructor initializes the instance variables and creates an MSB from paramater
     * 
     * @param string the str that will produce the MSB
     */
    public MyStringBuilder(String str) {

        //edge case if str is null. throw exception
        if (str == null) {
            throw new NullPointerException();
        }

        //edge case if str is empty, then just return with nothing else
        if (str == "") {
            return;
        }
        
        //set an int to 1
        int oneLength = 1;
        
        //that 1 into now checks if str is just 1 length
        if (oneLength == str.length()) {
            
            //if one length, then create a new node and initalize everything from instance variables
            length = 1;
            start = new CharNode(str.charAt(0));
            end = start;

        }
        
        //if length is greater than 1 then follow append char method
        else {
            
            //intialize instance
            length = 1;
            start = new CharNode(str.charAt(0));
            end = start;

            //append from char append
            for (int i = 1; i < str.length(); i++) {

                append(str.charAt(i));

            }
        }
    }
    
    /**
     * The constructor initializes the instance variables and creates an MSB from paramater via deep copying
     * @param MSB parameter is an other msb
     */
    public MyStringBuilder(MyStringBuilder other) {

        //edgecase if other is null, throw nullpointerexception
       if (other == null) {
        throw new NullPointerException();
       }

       //initialize instance for new deep copy of msb
       start = new CharNode(other.start.getData());
       end = start;
       length = 1;

       //for the rest of deep copy, follow append char method
       for (int i = 1; i < other.length; i++) {

        append(other.toString().charAt(i));

       }

    }

    /**
     * THIS Method returns length instance variable
     */
    public int length() {
        //return length
        return length;
    }

    /**
     * this method adds parameter to the end of an existing msb
     * 
     * @param ch char is the parameter
     */
    public MyStringBuilder append(char ch) {

        //edgecase if length is 0, then start anew
        if (length == 0) {

        start = new CharNode(ch); //head of linked list
        end = start; //tail of linked list
        length = 1;

        return this;

        }
    
        //if length is not 0, then append char like normal by creating new node
        CharNode newEnd = new CharNode(ch);
        //set old end to new end
        end.setNext(newEnd);
        //set new end to end
        end = newEnd;
        //add length 1
        length += 1;
        //return msb
        return this; 

    }

    /**
     * this method adds parameter to the end of an existing msb
     * 
     * @param string str is the parameter
     */
    public MyStringBuilder append(String str) {
        //edgecase if str is null, throw exception
        if (str == null ) {
            throw new NullPointerException();
        }

        //edgecase if str is empty, return msb
        if (str == "") {
            return this;
        }

        //for all elements of the str string, append them using char append
        for (int i = 0; i < str.length(); i++) {

        append(str.charAt(i));
    
        }
        //return msb
        return this;
    }

    /**
     * this method makes "this" msb into a string 
     */
    public String toString() {

        //edgecase if length is empty
        if (length == 0) {
            //return empty string.
            return "";
        }

        //set currchar to start char
        CharNode currChar = start;
        
        //create string from first node char
        String currString = Character.toString(currChar.getData());

        //get all remaining nodes chars and add them to currstring
        for (int i = 0; i < length - 1; i++) {
            currChar = currChar.getNext();
            
            currString += Character.toString(currChar.getData());
        }
        
        //return currstring
        return currString;

    }

    /**
     * this method creates string from a certain desired index
     * 
     * @param int  start index of desired start 
     */
    public String subString(int startIdx) {

        //edge case if strtidx dne or if strxidx >= length throw exception
        if (startIdx == -1 || startIdx >= length) {

            throw new IndexOutOfBoundsException();

        }

        //set currchar to start.
        CharNode currChar = start;

        //go through LL and get to index where currchar = wanted index
        for (int i = 0; i < startIdx; i++) {
            
            currChar = currChar.getNext();
            
        }

        //create string from start of first index
        String currString = Character.toString(currChar.getData());

        //go through remaining nodes and add them to currstring
        for (int i = 0; i < length() - startIdx -1; i++) {
            
            currChar = currChar.getNext();
            currString += Character.toString(currChar.getData());
            
        }

        //return currstring
        return currString;


    }

    /**
     * this method starts a string from msb at startindex and ends the string at desired endindex
     * 
     * @param int start index
     * @param int end index
     */
    public String subString(int startIdx, int endIdx) {

        //edgecase if startindex = endindex
        if (startIdx == endIdx) {
            //return emptystring
            return "";
        }

        //edges cases for substring. return indexoutofbounds
        if (startIdx < 0 || startIdx > length || endIdx > length || endIdx < startIdx) {

            throw new IndexOutOfBoundsException();
        }

        //st currchar to start node
        CharNode currChar = start;

        //go through LL and find wanted startindex
        for (int i = 0; i < startIdx; i++) {
            
            currChar = currChar.getNext();
            
        }

        //make wanted startindex into string
        String currString = Character.toString(currChar.getData());
        //go through LL and add chars to string until u reach desired endindex
        for (int i = startIdx; i < endIdx -1; i++) {

            currChar = currChar.getNext();
            currString += Character.toString(currChar.getData());
            
        }
        //return currstring
        return currString;
    }
}
