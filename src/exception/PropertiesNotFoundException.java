package exception;

public class PropertiesNotFoundException extends Throwable {


    public PropertiesNotFoundException() {
    }

    public PropertiesNotFoundException(String message) {
        super(message);
    }

    public PropertiesNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
