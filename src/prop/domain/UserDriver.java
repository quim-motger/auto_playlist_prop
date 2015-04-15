package prop.domain;

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

        User u = new User();
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
                    System.out.println(u.getAssociatedLists());
                    break;
                case 3:
                    System.out.println(u.getPlaybackRegister());
                    break;
                case 4:
                    System.out.println(u.getCountry());
                    break;
                case 5:
                    System.out.println(u.getBirthdate());
                    break;
                case 6:
                    System.out.println(u.getGender());
                    break;
                case 7:
                    String name = in.next();
                    u.setName(name);
                    break;
                case 8:
                    String sge = in.next();
                    u.setGender(Gender.valueOf(sge));
                    break;
                case 9:
                    int year = in.nextInt();
                    //song.setYear(year);
                    break;
                case 12:
                    String album = in.next();
                    //song.setAlbum(album);
                    break;
                case 13:
                    int genre = in.nextInt();
                    //song.setGenre(Genre.getGenreById(genre));
                    break;
                case 14:
                    int subgenre = in.nextInt();
                    //song.setGenre(Genre.getGenreById(subgenre));
                    break;
                case 15:
                    int duration = in.nextInt();
                    //song.setDuration(duration);
                    break;
                case 16:

            }
            StringBuilder sb = new StringBuilder();
            sb.append("0:  terminate program\n");
            sb.append("1:  info\n");
            System.out.print(sb.toString());
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
}
