package prop.domain;

import java.io.IOException;
import java.util.*;

/**
 * User Driver
 * @author Carles Garcia Cabot
 */
public class UserDriver {
    public static void main(String[] args) {
        try {
            System.out.println("**********************************************************");
            System.out.println("** User");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();
            System.out.println("This class needs 5 other Classes: Playback, Song, SongController," +
                    " List and List Controller\n"
                    + "Therefore, you can create a Song, a Playback and a List\n"
                    + "START WITH OPTION 3");

            User u = null;
            Song song = null;
            SongController songController = new SongController();
            Playback playback = null;
            List list = null;
            ListController listController = new ListController();
            Calendar date = Calendar.getInstance();
            String serialized = "";

            int nextId = 0;

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
                        u = new User();
                        break;
                    case 3: {
                        u = new User(in.next(), Gender.valueOf(in.next()), new GregorianCalendar(in.nextInt(),in.nextInt(),in.nextInt()),
                                CountryCode.valueOf(in.next()));
                        break;
                    }
                    case 4:
                        //res
                    break;
                    case 5:
                        if (playback == null) throw new NullPointerException("playback is null. Create a playback first");
                        u.add(playback);
                        break;
                    case 6:
                        System.out.println(u.age());
                        break;
                    case 7:
                        if (list == null) throw new NullPointerException("list is null. Create a list first");
                        u.associate(list);
                        break;
                    case 8:
                        u.clearLists();
                        break;
                    case 9:
                        u.clearRegister();
                        break;
                    case 10:
                        if (list == null) throw new NullPointerException("list is null. Create a list first");
                        u.disassociate(list);
                        break;
                    case 11:
                        ArrayList<List> al = u.getAssociatedLists();
                        for (List l : al) System.out.println(l.toString());
                        break;
                    case 12:
                        Calendar d = u.getBirthdate();
                        System.out.println(d.get(Calendar.YEAR) + " " + d.get(Calendar.MONTH) + " " + d.get(Calendar.DAY_OF_MONTH));
                        break;
                    case 13:
                        System.out.println(u.getCountry());
                        break;
                    case 14:
                        System.out.println(u.getGender());
                        break;
                    case 15:
                        System.out.println(u.getName());
                        break;
                    case 16:
                        TreeSet<Playback> ap = u.getPlaybackRegister();
                        System.out.println("PlaybackRegister Size: " + ap.size());
                        for (Playback p : ap) System.out.println(p);
                        break;
                    case 17:
                        if (list == null) throw new NullPointerException("list is null. Create a list first");
                        System.out.println(u.hasList(list));
                        break;
                    case 18:
                       //nothing
                        break;
                    case 19:
                        u.setBirthdate(date);
                        break;
                    case 20:
                        u.setCountry(CountryCode.valueOf(in.next()));
                        break;
                    case 21:
                        u.setGender(Gender.valueOf(in.next()));
                        break;
                    case 22:
                        u.setName(in.next());
                        break;
                    case 23:
                        //nothing;
                        break;
                    case 24:
                        try {
                            serialized = u.toString();
                            System.out.println(serialized);
                            break;
                        }
                        catch (NullPointerException exc) {
                            System.err.println("Error: User is empty");
                            throw new Exception();
                        }
                    case 25:
                        if (serialized.equals("")) throw new Exception("Error: must do toString before valueOf");
                        u = u.valueOf(serialized, listController, songController);
                        break;
                    case 26:
                        if (song == null) throw new NullPointerException("song is null. Create a song first");
                        playback = new Playback(song,date);
                        break;
                    case 27:
                        song = songController.getSong(in.next(),in.next());
                        break;
                    case 28:
                        songController.addSong(in.next(),in.next(),in.next(),in.nextInt(),Genre.valueOf(in.next()),
                                Genre.valueOf(in.next()),in.nextInt());
                        break;
                    case 29:
                        date = Calendar.getInstance();
                        date.set(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
                        break;
                    case 30:
                        list = new List(in.next());
                        list.editId(nextId++);
                        break;
                    case 31:
                        if (song == null) throw new NullPointerException("song is null. Create a song first");
                        list.addSong(song);
                        break;
                    case 32:
                        if (list == null) throw new NullPointerException("list is null. Create a list first");
                        listController.addList(list);
                        break;
                    default:
                        printInfo();
                }
                printInfoBrief();
            }
        }
        catch(Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("User()");
        sb.add("User(String name, Gender gender, Calendar birthdate, CountryCode country)");
        sb.add("nothing");
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
        sb.add("nothing");
        sb.add("void 	setBirthdate(date)");
        sb.add("void 	setCountry(CountryCode country)");
        sb.add("void 	setGender(Gender gender)");
        sb.add("void 	setName(java.lang.String name)");
        sb.add("nothing");
        sb.add("java.lang.String 	toString()");
        sb.add("static User 	valueOf(java.lang.String origin, ListController listController, SongController songController)");

        // OTHER CLASSES
        sb.add("\nMETHODS FOR OTHER CLASSES\nplayback = new Playback(Song song, Calendar date)");
        sb.add("song = songController.getSong(String title, String artist)");
        sb.add("songController.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)");
        sb.add("date.set(int year, int month, int day, int hour, int minute, int second)");
        sb.add("list = new List(String listTitle)");
        sb.add("list.addSong(song)");
        sb.add("listController.add(list)");
        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n"
                + "1:    info\n");
    }


}
