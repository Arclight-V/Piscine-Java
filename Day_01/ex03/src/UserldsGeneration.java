public class UserldsGeneration {
    private static UserldsGeneration instance;
    private int Identifier = -1;

    private UserldsGeneration() {}

    public static UserldsGeneration getInstance() {
        if (instance == null) {
            instance = new UserldsGeneration();
        }
        return instance;
    }

    public int generateId() {
        return ++Identifier;
    }

}
