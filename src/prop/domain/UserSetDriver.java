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
        UserSet userSet = new UserSet();
        User user = null;
        printInfoComplete();
        while (i != 0) {
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                case 2:
                    ArrayList<User> users = userSet.getUsers();
                    for (User u : users) {
                        System.out.print(u.toString());
                    }
                    break;
                case 3:
                    try {
                        userSet.addUser(user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    String name = in.next();
                    try{
                        userSet.removeUser(name);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    String name2 = in.next();
                    user = userSet.getUserByName(name2);
                    System.out.print(user.toString());
                    break;
                case 6:
                    String name3 = in.next();
                    String g = in.next();
                    Gender gender = Gender.valueOf(g);
                    Calendar birthdate = Calendar.getInstance();
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    birthdate.set(year, month, day);
                    CountryCode country = CountryCode.getByCode(in.nextInt());
                    user = new User(name3, gender, birthdate, country);
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 7) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   ArrayList<User> getUsers()\n"
                + "3:   void addUser(User user)\n"
                + "4:   void removeUser(String name)\n"
                + "5:   User getUserByName(String name)\n"
                + "6:   user = new User(String name, Gender gender, Calendar birthdate, CountryCode country)\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                    + "1    printInfoComplete()\n");
    }
}