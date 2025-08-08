/**
 * This package contains an exception class that throws a custom message.
 */
package CustomException;

public class CustomException extends Exception{
    public CustomException(String msg) {
        super(msg);
    }
}
