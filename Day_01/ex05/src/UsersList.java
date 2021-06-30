public interface UsersList {
    void addUser(User userToAdd);
    User retrieveUserByID(int id) throws UserNotFoundException;
    User retrieveUserByIndex(int Index);
    int retrieveNumbersOfUsers();
}
