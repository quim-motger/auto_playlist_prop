package prop.domain;

import prop.PropException;

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
        ListController lc = new ListController();
        SongController sc = new SongController();
        User user;
        String s = "";
        printInfoComplete();
        while (i != 0) {
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    userSet = new UserSet();
                    break;
                case 3:
                    ArrayList<User> users = userSet.getUsers();
                   for (User u : users) {
                        System.out.print(u.getName() + " " + u.getGender() + " " + u.getBirthdate().get(Calendar.DAY_OF_MONTH) +
                                "/" + u.getBirthdate().get(Calendar.MONTH) + "/" + u.getBirthdate().get(Calendar.YEAR) + "\n");
                    }
                    break;
                case 4:
                    String name3 = in.next();
                    String g = in.next();
                    Gender gender = Gender.valueOf(g);
                    Calendar birthdate = Calendar.getInstance();
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int day = in.nextInt();
                    birthdate.set(year, month, day);
                    user = new User(name3, gender, birthdate);
                    try {
                        userSet.addUser(user);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    String name = in.next();
                    try{
                        userSet.removeUser(name);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    String name2 = in.next();
                    try {
                        user = userSet.getUserByName(name2);
                        System.out.print(user.getName() + " " + user.getGender() + " " + user.getBirthdate().get(Calendar.DAY_OF_MONTH) +
                                "/" + user.getBirthdate().get(Calendar.MONTH) + "/" + user.getBirthdate().get(Calendar.YEAR) + "\n");
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    s = userSet.toString();
                    System.out.print(s + "\n");
                    break;
                case 8:
                    String title = in.next();
                    try {
                        lc.addList(title);
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    String title5 = in.next();
                    try {
                        lc.removeList(title5);
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    String title2 = in.next();
                    String artist = in.next();
                    String album = in.next();
                    String year2 = in.next();
                    String genre = in.next();
                    String subgenre = in.next();
                    String duration = in.next();
                    try {
                        sc.addSong(title2,artist,album,year2,genre,subgenre,duration);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    String title3 = in.next();
                    String artist2 = in.next();
                    try {
                        sc.removeSong(title3,artist2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    printInfoComplete();
            }
            System.out.print("\n");
            if (i > 1 && i < 13) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n"
                + "1:   printInfoComplete()\n"
                + "2:   new UserSet()\n"
                + "3:   ArrayList<User> getUsers()\n"
                + "4:   void addUser(User user): name MALE/FEMALE/OTHER YYYY MM DD country\n"
                + "5:   void removeUser(String name): name\n"
                + "6:   User getUserByName(String name): name\n"
                + "7:   String toString()\n"
                + "8:   lc.addList(String title): title\n"
                + "9:   lc.removeList(int id): id\n"
                + "10:  sc.addSong(String title, String artist, String album, int year,Genre genre, Genre subgenre, int duration)" +
                " title artist album YYYY id_genre id_subgenre duration(seconds)\n"
                + "11:  sc.removeSong(String title, String artist): title artist\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:   terminate program\n"
                    + "1    printInfoComplete()\n");
    }
}