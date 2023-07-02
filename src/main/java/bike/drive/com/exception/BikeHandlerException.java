package bike.drive.com.exception;

public class BikeHandlerException extends RuntimeException{
    private Integer code;
    private String message;

    public BikeHandlerException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
