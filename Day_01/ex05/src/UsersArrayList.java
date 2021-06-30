public class UsersArrayList implements UsersList {
    private static final int defaultArraySize = 10;
    private static final int multiplier = 2;
    private static int capacity = 10;
    private static int size = 0;

    static private User users[] = new User[defaultArraySize];

    public UsersArrayList() {};

    @Override
    public void addUser(User userToAdd) {
        if (size == capacity) {
            User newArray[] = new User[size * multiplier];
            int i = 0;
            for (; i < size; ++i) {
                newArray[i] =  users[i];
            }
            newArray[i] = userToAdd;
            users = newArray;
            capacity *= multiplier;
            ++size;

        } else {
            users[size] = userToAdd;
            ++size;
        }
    }

    @Override
    public User retrieveUserByID(int id) throws UserNotFoundException {
        for(int i = 0; i < size; ++i) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveUserByIndex(int Index) {
        if (size < Index || Index < 0) {
            return null;
        } else {
            return users[Index];
        }
    }

    @Override
    public int retrieveNumbersOfUsers() {
        return size;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static void setCapacity(int capacity) {
        UsersArrayList.capacity = capacity;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        UsersArrayList.size = size;
    }

    public static int getDefaultArraySize() {
        return defaultArraySize;
    }

    public static int getMultiplier() {
        return multiplier;
    }
}
