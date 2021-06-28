public class Program {
    public static void main(String []argv) throws UserNotFoundException {

        UsersArrayList usersList = new UsersArrayList();

        for(int i = 0; i < 12; ++i) {
            usersList.addUser(new User(String.valueOf(i), i));
        }

        System.out.println("Numbers of Users: " + usersList.retrieveNumbersOfUsers() + "\n");
        System.out.println("Test retrieveUserByIndex:\n");
        for(int i = 0; i < 13; ++i) {
            User retUser = usersList.retrieveUserByIndex(i);
            if (retUser != null) {
                retUser.printUser();
            }
        }

        System.out.println("Test retrieveUserByID:\n");
        for(int i = 0; i < 13; ++i) {
            User retUser = usersList.retrieveUserByID(i);
            if (retUser != null) {
                retUser.printUser();
            }
        }
    }
}
