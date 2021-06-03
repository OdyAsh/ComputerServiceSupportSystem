/*
 * Group 1: Computer Service Support System (24)
 */
package ExceptionHandling;

/**
 *
 * @author Ashraf 196280
 */
public class MyException extends Exception{

    public String message;

    public MyException(String message){
        this.message = message;
    }

    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }
}