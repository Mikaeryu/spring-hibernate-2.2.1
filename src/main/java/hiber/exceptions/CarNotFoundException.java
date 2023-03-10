package hiber.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super();
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
