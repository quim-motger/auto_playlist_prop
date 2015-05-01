package prop.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * User Driver
 * @author Carles Garcia Cabot
 */
public class UserDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** User");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        User u = null;
        Scanner in = new Scanner(System.in);
        int i = -1;
        while(i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    u = new User();
                    break;
                case 3: {
                    String name = in.next();
                    Gender gender = Gender.valueOf(in.next());
                    System.out.println("Write day, month and year of birthdate separated by spaces");
                    int day = in.nextInt();
                    int month = in.nextInt();
                    int y = in.nextInt();
                    u.setBirthdate(new GregorianCalendar(y, month, day));
                    u.setCountry(CountryCode.valueOf(in.next()));
                    break;
                }
                case 4:
                    // TODO: User constructor with all members
                    break;
                case 5:
                    // TODO: add playback
                    break;
                case 6:
                    // TODO: associate list
                    break;
                case 7:
                    u.clearLists();
                    break;
                case 8:
                    u.clearRegister();
                    break;
                case 9:
                    // TODO disassociate list
                    break;
                case 10:
                    u.getAssociatedLists().toString();
                    break;
                case 11:
                    u.getBirthdate().toString();
                    break;
                case 12:
                    u.getCountry().getName().toString();
                    break;
                case 13:
                    u.getGender().toString();
                    break;
                case 14:
                    u.getName().toString();
                    break;
                case 15:
                    u.getPlaybackRegister().toString();
                    break;
                case 16:
                    // todo: hasList
                    break;
                case 17:
                    // todo setAssociatedLists
                    break;
                case 18: {
                    System.out.println("Write day, month and year of birthdate separated by spaces");
                    int day = in.nextInt();
                    int month = in.nextInt();
                    int y = in.nextInt();
                    u.setBirthdate(new GregorianCalendar(y, month, day));
                    break;
                }
                case 19:
                    u.setCountry(CountryCode.valueOf(in.next()));
                    break;
                case 20:
                    u.setGender(Gender.valueOf(in.next()));
                    break;
                case 21:
                    u.setName(in.next());
                    break;
                case 22:
                    // todo setPlaybackREgister
                    break;
                case 23:
                    System.out.println(u.toString());
                    break;
                // faltaria valueOf pero es molt dificil de testejar
                default:
                    printInfo();
            }
            printInfoBrief();
        }
    }

    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("User()");
        sb.add("User(String name, Gender gender, Calendar birthdate, CountryCode country)");
        sb.add("User(String name, Gender gender, Calendar birthdate, CountryCode country, TreeSet<Playback> playbackRegister," +
                        "ArrayList<List> associatedLists");
        sb.add("void 	add(Playback play)");
        sb.add("int 	age()");
        sb.add("void 	associate(List list)");
        sb.add("void 	clearLists ()");
        sb.add("void clearRegister()");
        sb.add("boolean 	disassociate(List list)");
        sb.add("java.util.ArrayList<List> 	getAssociatedLists()");
        sb.add("java.util.Calendar 	getBirthdate()");
        sb.add("CountryCode 	getCountry()");
        sb.add("Gender 	getGender()");
        sb.add("java.lang.String 	getName()");
        sb.add("java.util.TreeSet<Playback> 	getPlaybackRegister()");
        sb.add("boolean 	hasList(List list)");
        sb.add("void 	setAssociatedLists(java.util.ArrayList<List> associatedLists)");
        sb.add("void 	setBirthdate(java.util.Calendar birthdate)");
        sb.add("void 	setCountry(CountryCode country)");
        sb.add("void 	setGender(Gender gender)");
        sb.add("void 	setName(java.lang.String name)");
        sb.add("void 	setPlaybackRegister(java.util.TreeSet<Playback> playbackRegister)");
        sb.add("java.lang.String 	toString()");
        sb.add(" static User 	valueOf(java.lang.String origin, ListController listController, SongController songController)");


        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    info\n");
    }


}
