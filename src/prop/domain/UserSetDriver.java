package prop.domain;

import java.util.ArrayList;
import java.util.Calendar;
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
                    System.out.print("Name:");
                    String name2 = in.next();
                    user = userSet.getUserByName(name2);
                    System.out.print(user.toString());
                    break;
                case 6:
                   /* System.out.print("Name:");
                    String name3 = in.next();
                    System.out.print("Gender (FEMALE/MALE/OTHER):");

                    Calendar birthdate = Calendar.getInstance();
                    System.out.print("Birthdate\n");
                    System.out.print("Year:");
                    int year = in.nextInt();
                    System.out.print("Month:");
                    int month = in.nextInt();
                    System.out.print("Day:");
                    int day = in.nextInt();
                    birthdate.set(year,month,day);
                    System.out.print("Country:");
                    CountryCode country = CountryCode.getByCode(in.nextInt());
                    user = new User(name3, gender, birthdate, country);*/
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