public class UserNotFoundException extends InterruptedException {
    public UserNotFoundException() {
        super("User not found\n");
    }
}
