package exceptions;

public class ArgumentIsNullException extends RuntimeException{
    public ArgumentIsNullException(String message) {
        super(message);
    }
}
