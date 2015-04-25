package prop.domain;

import java.util.Scanner;

public class ListControllerDriver {

    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** ListController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        ListController listController = null;
        SongController songController = new SongController();
        String title,artist,path;
        int id;
        Scanner in = new Scanner(System.in);

        int i = -1;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    listController = new ListController();
                    break;
                case 3:
                    System.out.println(listController.size());
                    break;
                case 4:
                    title = in.next();
                    listController.addList(title);
                    break;
                case 5:
                    id = in.nextInt();
                    listController.removeList(id);
                    break;
                case 6:
                    id = in.nextInt();
                    title = in.next();
                    listController.setTitle(id,title);
                    break;
                case 7:
                    id = in.nextInt();
                    title = in.next();
                    artist = in.next();
                    listController.addSong(id,title,artist,songController);
                    break;
                case 8:
                    id = in.nextInt();
                    title = in.next();
                    artist = in.next();
                    listController.removeSong(id,title,artist);
                    break;
                case 9:
                    id = in.nextInt();
                    List list = listController.getList(id);
                    System.out.println(list.toString());
                    break;
                case 10:
                    id = in.nextInt();
                    System.out.print(listController.getListString(id));
                    break;
                case 11:
                    System.out.print(listController.getListSetString());
                    break;
                case 12:
                    path = in.next();
                    listController.save(path);
                    break;
                case 13:
                    path = in.next();
                    listController.load(path);
                    break;
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  ListController()\n");
        sb.append("3:  int size()\n");
        sb.append("4:  void addList(String title)\n");
        sb.append("5:  boolean removeList(int id)\n");
        sb.append("6:  void setTitle(int id, String title)\n");
        sb.append("7:  void addSong(int id, String title, String artist, SongController songController)\n");
        sb.append("8:  void removeSong(int id, String title, String artist)\n");
        sb.append("9:  List getList(int id)\n");
        sb.append("10: String getListString(int id)\n");
        sb.append("11: String getListSetString()\n");
        sb.append("12: void save(String path)\n");
        sb.append("13: void load(String path)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}
