package prop.domain;

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
            }
            printInfoBrief();
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  ArrayList<List> getAssociatedLists()\n");
        sb.append("3:  ArrayList<Playback> getPlaybackRegister()\n");
        sb.append("4:  CountryCode getCountry()\n");
        sb.append("5:  Calendar getBirthdate())\n");
        sb.append("6:  Gender getGender()\n");
        sb.append("7:  void setName(String name)\n");
        sb.append("8:  void setGender(Gender gender)\n");
        sb.append("9:  void setBirthdate(Calendar birthdate)\n");
        sb.append("10: void setCountry(CountryCode country)\n");
        sb.append("11: void setPlaybackRegister(ArrayList<Playback> playbackRegister)\n");
        sb.append("12: void setAssociatedLists(ArrayList<List> associatedLists)\n");
        sb.append("13: int age()\n");
        sb.append("14: void add(Playback play)\n");
        sb.append("15: void associate(List list)\n");
        sb.append("16: boolean disassociate(List list)\n");
        sb.append("17: boolean hasList(List list)\n");
        sb.append("18: String getName()\n");
        sb.append("19: void clearRegister()\n");
        sb.append("20: void clearLists()\n");
      //  sb.append("21: User(String name, Gender gender, Calendar birthdate, CountryCode country)\n");
      //  sb.append("22  User(String name, Gender gender, Calendar birthdate, CountryCode country, ArrayList<Playback> playbackRegister, ArrayList<List> associatedLists)\n");
        sb.append("  : \n");
        sb.append("  : \n");
        System.out.print(sb.toString());
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    printInfoComplete()\n");
    }


}
