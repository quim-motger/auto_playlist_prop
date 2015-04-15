package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Set Driver
 * @author joaquim.motger
 */
public class UserSetDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** User Set");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        int i = -1;
        UserSet userSet = null;
        User user;
        while (i != 0) {
            printInfo();
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    userSet = new UserSet();
                    break;
                case 2:
                    ArrayList<User> users = userSet.getUsers();
                    for (User u : users) {
                        System.out.print(u.toString());
                    }
                    break;
                case 3:
                    break;
                case 4:
                    System.out.print("Remove user\n");
                    System.out.print("Name:");
                    String name = in.next();
                    boolean b = userSet.removeUser(name);
                    if (b) System.out.print("User removed\n");
                    else System.out.print("User doesn't exist\n");
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        System.out.print("0:   terminate program\n"
                        + "1:   UserSet()\n"
                        + "2:   ArrayList<User> getUsers()\n"
                        + "3:   boolean addUser(User user)\n"
                        + "4:   boolean removeUser(String name)\n"
                        + "5:   User getUserByName(String name)\n"
                        + "6:   User(String name, Gender gender, Calendar birthdate, CountryCode country)\n");
    }
}