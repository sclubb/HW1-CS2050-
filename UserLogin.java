/*
 * Steven Clubb
 * CS 2050-001
 * 9/3/2017
 * 
 */
package user.login;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program will take a login ID from the user and test 
 * to see if user login matches the specified criteria. There must be
 * at least one lower case letter, one uppercase letter, one digit and one
 * special character from the following: !@#$
 */
public class UserLogin {
    //Creating the instance level variables here. 
    //Will be used throughout the program.
    String userName = "";
    boolean hasLower;
    boolean hasUpper;
    boolean LoginLength;
    boolean hasDigit;
    boolean hasSpecial;
    String printValidity;
    StringBuffer sb = new StringBuffer();
    
    
    public static void main(String[] args) {
        
        //active will be the trigger to break the loop.
        boolean active = true;
        //Creating an object to access the methods with the instance variables.
        UserLogin b = new UserLogin();
        b.greetUser();
        while (active){
             Scanner kb = new Scanner(System.in);
             b.userName = b.readUser();
             b.hasLower = b.checkLowercase(b.userName, b.hasLower);
             b.hasUpper = b.checkUppercase(b.userName, b.hasUpper);
             b.LoginLength = b.checkLength(b.userName, b.LoginLength);
             b.hasDigit = b.checkDigit(b.userName);
             b.hasSpecial = b.checkSpecial(b.userName);
             b.checkValidity(b.userName);
             b.addToReport();
        
             System.out.println("\nDo you have more User Logins? Type No to quit.\n");
             //This will break the loop if user types No.
             String cont = kb.next();
             if (cont.equals("No")){
                 active = false;
             }
             
        }
        
        
    }
    
    //Method to introduce the user to this program. Explaining what will happen.
    public void greetUser(){
        
        System.out.println(" This program will ask for a User Login." + 
                 "The User Login must contain at least one lowercase letter,\n "
                + "one uppercase letter, one digit and one of the following "
                + "special characters: !@#$"
                + "\n Length must be at least 5 characters.");
    }
    
    //Method that will recieve the User Login from the user.
    public String readUser(){
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n Please enter a valid User Login.");
        
        System.out.print("Login: ");
        String LoginFromUser = keyboard.nextLine();
        
        return LoginFromUser;
    }
    //Method that checks for lowercase letters as a boolean.
    public boolean checkLowercase(String loginCase, boolean hasLowercase){
        
        hasLowercase = !loginCase.equals(loginCase.toUpperCase());
        return hasLowercase;
    }
      
    //Method that checks for uppercase letters.
    public boolean checkUppercase(String loginCase, boolean hasUppercase){
        
        hasUppercase = !loginCase.equals(loginCase.toLowerCase());
        
        return hasUppercase;
    }
    //Checks for lenght. Must be 5 or higher.
    public boolean checkLength(String userName, boolean atLeast5){
        
        atLeast5 = userName.length() >= 5;
        
        return atLeast5;
    }
    //Checks for a digit in the String.
    public boolean checkDigit(String userName){
        
        boolean hasNum = false;
        //for loop to cycle through the string, 
        //checking every character.
        for (int i = 0; i < userName.length(); i++){
            
           Character c = userName.charAt(i);
           hasNum = Character.isDigit(c);
           
          //Will stop the loop if a number is in the string.
           if (hasNum){
               break;
           }
        }
        
       return hasNum;
    }
    //checks for Special Characters. Only !@#$
    public boolean checkSpecial (String userName){
        
        boolean hasSpecial = false;
        //Cycles through entire string.
        for (int i = 0; i < userName.length(); i++){
            Character special = userName.charAt(i);
            if (userName.contains("!")){
            hasSpecial = true;  
             }else if(userName.contains("@")){
            hasSpecial = true;
             }else if(userName.contains("#")){
            hasSpecial = true;
             }else if(userName.contains("$")){
            hasSpecial = true;
             }
            
        }
        return hasSpecial;
    }
    //Checks every requirement to be valid and concatenates it to the String Buffer.
    public String checkValidity(String LoginFromUser){
        boolean LoginValidity = true;
        printUser(userName);
        //If statement to determine validity.
        if(!hasUpper || !hasLower || !hasDigit || !LoginLength || !hasSpecial){
            
            LoginValidity = false;
        }
        //Concatenate Validity if valid
        //else add invalid.
        if(LoginValidity){
            printValidity = ("(Valid)" + "\r\n");
            System.out.print(printValidity);
            sb.append(printValidity);
        }else {
            printValidity = ("(Invalid)" + "\r\n");
            System.out.print(printValidity);
            sb.append(printValidity);
        }
       //Notify if uppercase is in string or not
        if(hasUpper){
            sb.append("-- uppercase OK\r\n");
        }else {
            sb.append("-- no uppercase\r\n");
        }
        //Notify user if length is at minimun.
        if(LoginLength){
            sb.append("-- length OK\r\n");
        }else {
            sb.append("-- too short (minimun of 5 characters\r\n");
        }
        //Notify user if string contains digit or not.
        if(hasDigit){
            sb.append("-- digit OK\r\n");
        }else{
            sb.append("-- no digit\r\n");
        }
        //Notify user if string contains lowercase
       if(hasLower){
          sb.append("-- lowercase OK\r\n");
      }else{
          sb.append("-- no lowercase\r\n");
      }  
       //Notify user if string has one of the Special Characters
       if(hasSpecial){
           sb.append("-- special character OK\r\n");
       }else{
           sb.append("-- no special character\r\n");
       }
        
        return LoginFromUser;
    }
    //Concatenates user name to String buffer
    public void printUser(String userName){
        
        sb.append(userName + "  ");

    }
    //Method to print String Buffer to report.
    public void addToReport (){
        try {
            PrintWriter out = new PrintWriter("HW1StevenClubbTestFile.txt");
            out.print(sb.toString());
            out.close();
        }
        catch (Exception e){
            
        }
        
    }
}
