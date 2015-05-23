package prop.domain;

import prop.PropException;

import java.io.IOException;
import java.util.Scanner;

/**
 * UserControllerDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 19/04/15
 */
public class UserControllerDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** UserController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();

        UserController userController = null;
        SongController songController = new SongController();
        ListController listController = new ListController();
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
                                in.nextInt(),
                                in.nextInt(),
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
                    try {
                        userController.editUser(
                                in.next(),
                                in.next(),
                                in.next()
                        );
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println(UserController.obtainAttributes(in.next()));
                    break;
                case 7:
                    try {
                        System.out.println(userController.obtainUserToString(in.next()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println(userController.obtainUserSetToString());
                    break;
                case 9:
                    try {
                        userController.playSong(in.next(), in.next(), in.next(), songController);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        userController.save(in.next());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        userController.load(in.next(), listController, songController);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
                case 13:
                    try {
                        userController.associateListToUser(listController, in.next(), in.next());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 14:
                    try {
                        userController.disassociateListFromUser(listController, in.next(), in.next());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 15:
                    try {
                        System.out.println(userController.obtainListsAssociated(in.next()));
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 16:
                    try {
                        listController.addList(in.next());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 17:
                    System.out.println(listController.getListSetString());
                    break;
                case 18:
                    System.out.print(userController.findUsers(in.next()));
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
        sb.append("3:  void addUser(String name, String gender, int year, int month, int date, String countryCode)\n");
        sb.append("4:  void removeUser (String name)\n");
        sb.append("5:  void editUser(String name, String attribute, String value)\n");
        sb.append("6:  String obtainAttributes(String delimiter)\n");
        sb.append("7:  String obtainUserToString(String name)\n");
        sb.append("8:  String obtainUserSetToString()\n");
        sb.append("9:  void playSong(String title, String artist, String name, SongController songController)\n");
        sb.append("10:  void save (String path)\n");
        sb.append("11:  void load (String path)\n");
        sb.append("12:  songController.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("13:  void associateListToUser(ListController listController, int listId, String userName)\n");
        sb.append("14:  void disassociateListToUser(ListController listController, int listId, String userName)\n");
        sb.append("15:  String obtainListsAssociated(String userName)\n");
        sb.append("16:  listController.addList(String title)\n");
        sb.append("17:  String listController.getListSetString()\n");
        sb.append("18:  String findUsers(String prefix)\n");
        System.out.println(sb.toString());
    }
}
