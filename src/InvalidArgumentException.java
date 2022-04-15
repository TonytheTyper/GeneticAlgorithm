public class InvalidArgumentException extends Exception {

    public InvalidArgumentException() {
        super("Too many items. Can only brute force on 10 items or less.");
    }
}
