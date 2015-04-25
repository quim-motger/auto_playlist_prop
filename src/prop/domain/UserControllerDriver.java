package prop.domain;

import java.util.Scanner;

/**
 * UserControllerDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 19/04/15
 */
public class UserControllerDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** UserController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        UserController userController = null;
        SongController songController = new SongController();
        Scanner in = new Scanner(System.in);
        
        int i = -1;
        while (i!=0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    userController = new UserController();
                    break;
                case 3:
                    try {
                        userController.addUser(
                                in.next(),
                                in.next(),
                                in.nextLong(),
                                in.nextInt()
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        userController.removeUser(in.next());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    userController.editUser(
                            in.next(),
                            in.next(),
                            in.next()
                    );
                    break;
                case 6:
                    System.out.println(UserController.obtainAttributes(in.next()));
                    break;
                case 7:
                    System.out.println(userController.obtainUserToString(in.next()));
                    break;
                case 8:
                    System.out.println(userController.obtainUserSetToString());
                    break;
                case 9:
                    userController.playSong(in.next(), in.next(), in.next(), songController);
                    break;
                case 10:
                    userController.save(in.next());
                    break;
                case 11:
                    userController.load(in.next());
                    break;
                case 12:

                    try {
                        songController.addSong(
                                in.next(),
                                in.next(),
                                in.next(),
                                in.nextInt(),
                                Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()),
                                in.nextInt()
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    printInfo();
            }
            printInfoBrief();
            }
        }

    private static void printInfoBrief() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        System.out.println(sb.toString());
    }
    

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  UserController()\n");
        sb.append("3:  void addUser(String name, String gender, long birthday, int countryCode)\n");
        sb.append("4:  void removeUser (String name)\n");
        sb.append("5:  void editUser(String name, String attribute, String value)\n");
        sb.append("6:  String obtainAttributes(String delimiter)\n");
        sb.append("7:  String obtainUserToString(String name)\n");
        sb.append("8:  String obtainUserSetToString()\n");
        sb.append("9:  void playSong(String title, String artist, String name, SongController songController)\n");
        sb.append("10:  void save (String path)\n");
        sb.append("11:  void load (String path)\n");
        sb.append("12:  songController.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.println(sb.toString());
    }
}
