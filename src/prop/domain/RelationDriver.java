package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * RelationDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 02/05/15
 */
public class RelationDriver {

    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Relation");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        SongSet ss = new SongSet();
        UserSet us = new UserSet();
        List l = new List();
        Calendar birthday = Calendar.getInstance();
        Relation r = null;
        Relation r1 = null;
        Relation r2 = null;
        ArrayList<Song> songs;

        printInfoComplete();
        
        int i = -1;
        while (i != 0) {
            while (i != 0) {
                i = in.nextInt();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfoComplete();
                        break;
                    case 2:
                        r = new SimpleRelation(ss,us,in.next(), in.next());
                        break;
                    case 3:
                        r = new AND(r1, r2);
                        break;
                    case 4:
                        r = new OR(r1, r2);
                        break;
                    case 5:
                        r = new NOT(r1,ss);
                        break;
                    case 6:
                        r1 = r;
                        break;
                    case 7:
                        r2 = r;
                        break;
                    case 8:
                        songs = r.evaluate();
                        for (Song song : songs) {
                            System.out.println(song.getTitle() + " " + song.getArtist());
                        }
                        break;
                    case 9:
                        Song s = new Song(
                                in.next(),
                                in.next(),
                                in.next(),
                                in.nextInt(),
                                Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()),
                                in.nextInt());
                        try {
                            ss.addSong(s);
                        } catch (PropException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 10:
                        try {
                            ss.removeSong(in.next(), in.next());
                        } catch (PropException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 11:
                        User u = new User(
                                in.next(),
                                Gender.valueOf(in.next()),
                                birthday
                        );
                        us.addUser(u);
                        break;
                    case 12:
                        us.removeUser(in.next());
                        break;
                    case 13:
                        l = new List(in.next());
                        break;
                    case 14:
                        l.addSong(new Song(in.next(),
                                in.next(),
                                in.next(),
                                in.nextInt(),
                                Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()),
                                in.nextInt()));
                        break;
                    case 15:
                        User uu = us.getUserByName(in.next());
                        uu.associate(l);
                        break;
                    case 16:
                        ArrayList<Song> songss = ss.getSongSet();
                        for (Song song : songss) {
                            System.out.println(song.getTitle() + " " + song.getArtist());
                        }
                        break;
                    default:
                        printInfoComplete();
                }
            }


        }
    }


    private static void printInfoComplete() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  r = SimpleRelation(SongSet ss, UserSet us, String attribute, String value): attribute value\n");
        sb.append("3:  r = AND(r1,r2)\n");
        sb.append("4:  r = OR(r1,r2)\n");
        sb.append("5:  r = NOT(r1,ss)\n");
        sb.append("6:  r1 = r\n");
        sb.append("7:  r2 = r\n");
        sb.append("8:  r.evaluate()\n");
        sb.append("9:  ss.addSong(Song song): title artist album YYYY genre_id subgenre_id duration\n");
        sb.append("10: ss.removeSong(String title, String artist): title artist\n");
        sb.append("11: us.addUser(User user): name MALE/FEMALE/OTHER\n");
        sb.append("12: us.removeUser(String name): name\n");
        sb.append("13: l = new List(String title): title\n");
        sb.append("14: l.addSong(Song song): title artist album YYYY genre_id subgenre_id duration\n");
        sb.append("15: u.associate(List l)\n");
        sb.append("16: ss.getSongSet()\n");
        sb.append("Gender must be MALE or FEMALE or OTHER and Genre a number\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}