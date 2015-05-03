package prop.domain;

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
        Song s1 = null;
        Song s2 = null;
        User u = null;
        Calendar birthday = Calendar.getInstance();
        Relation r = null;
        Relation r1 = null;
        Relation r2 = null;

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
                        r = new SimpleRelation(in.next(),in.next(),in.next());
                        break;
                    case 3:
                        r = new AND(r1, r2);
                        break;
                    case 4:
                        r = new OR(r1, r2);
                        break;
                    case 5:
                        r = new NOT(r1);
                        break;
                    case 6:
                        r1 = r;
                        break;
                    case 7:
                        r2 = r;
                        break;
                    case 8:
                        System.out.println(r.evaluateSongs(s1,s2));
                        break;
                    case 9:
                        System.out.println(r.evaluateUser(u));
                        break;
                    case 10:
                        s1 = new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()), in.nextInt());
                        break;
                    case 11:
                        s2 = new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()), in.nextInt());
                        break;
                    case 12:
                        u = new User(in.next(), Gender.valueOf(in.next()), birthday, CountryCode.getByCode(in.next()));
                        break;
                    case 13:
                        birthday.set(in.nextInt(), in.nextInt(), in.nextInt());
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
        sb.append("2:  r = SimpleRelation(String type, String attribute, String value)\n");
        sb.append("3:  r = AND(r1,r2)\n");
        sb.append("4:  r = OR(r1,r2)\n");
        sb.append("5:  r = NOT(r1)\n");
        sb.append("6:  r1 = r\n");
        sb.append("7:  r2 = r\n");
        sb.append("8:  r.evaluateSongs(s1,s2)\n");
        sb.append("9:  r.evaluateUser(u)\n");
        sb.append("10:  s1 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("11:  s2 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        sb.append("12:  u = new User(String name, Gender gender, Calendar birthdate, CountryCode country)\n");
        sb.append("13:  birthday.set(int year,int month,int date)\n");
        sb.append("Gender must be MALE or FEMALE or OTHER, Country needs to be introduced by CountryCode (ex: ES, FR...) and Genre by number\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

}