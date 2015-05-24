package prop.domain;

import java.util.*;

/**
 * User Driver
 * @author Carles Garcia Cabot
 */
public class UserDriver {
    public static void main(String[] args) {
        try {
            System.err.println("**********************************************************");
            System.err.println("** User");
            System.err.println("**********************************************************");
            System.err.print("\n");
            printInfo();
            System.err.println("This class needs 3 other Classes: Playback, Song," +
                    " and List \n"
                    + "Therefore, you can create a Song, a Playback and a List\n"

            + "Example of Genre: 17, 18, 19 "
            + "\nExample of Gender: MALE, FEMALE"
            + "\nExample of CountryCode: ES, FI, FR\n"
                            + "START WITH OPTION 3\n");



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
                        u = new User(in.next(), Gender.valueOf(in.next()), new GregorianCalendar(in.nextInt(),in.nextInt(),in.nextInt()));
                        break;
                    }
                    case 4:
                        //res
                    break;
                    case 5:
                        u.add(playback);
                        break;
                    case 6:
                        System.out.println(u.age());
                        break;
                    case 7:
                        u.associate(list);
                        break;
                    case 8:
                        u.clearLists();
                        break;
                    case 9:
                        u.clearRegister();
                        break;
                    case 10:
                        //nothing
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
                        // nothing
                        break;
                    case 14:
                        System.out.println(u.getGender());
                        break;
                    case 15:
                        System.out.println(u.getName());
                        break;
                    case 16:
                        TreeSet<Playback> ap = u.getPlaybackRegister();
                        for (Playback p : ap) System.out.println(p);
                        break;
                    case 17:
                        System.out.println(u.hasList(list));
                        break;
                    case 18:
                       //nothing
                        break;
                    case 19:
                        u.setBirthdate(date);
                        break;
                    case 20:
                        // nothing
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
                        u = User.valueOf(serialized, listController, songController);
                        serialized = "";
                        break;
                    case 26:
                        if (song == null) throw new NullPointerException("song is null. Create a song first");
                        playback = new Playback(song,date);
                        break;
                    case 27:
                        song = songController.getSong(in.next(),in.next());
                        break;
                    case 28:
                        songController.addSong(in.next(),in.next(),in.next(),in.nextInt(),in.nextInt(),
                                in.nextInt(),in.nextInt());
                        break;
                    case 29:
                        date = Calendar.getInstance();
                        date.set(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
                        break;
                    case 30:
                        list = new List(in.next());
                        listController.addList(list);
                        break;
                    case 31:
                        if (song == null) throw new NullPointerException("song is null. Create a song first");
                        list.addSong(song);
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
        sb.add("User(String name, Gender gender, int year, int month, int day, CountryCode country)");
        sb.add("nothing");
        sb.add("void 	add(playback)");
        sb.add("int 	age()");
        sb.add("void 	associate(list)");
        sb.add("void 	clearLists ()");
        sb.add("void clearRegister()");
        sb.add("nothing");
        sb.add("ArrayList<List> 	getAssociatedLists()");
        sb.add("Calendar 	getBirthdate()");
        sb.add("nothing");
        sb.add("Gender 	getGender()");
        sb.add("String 	getName()");
        sb.add("TreeSet<Playback> 	getPlaybackRegister()");
        sb.add("boolean 	hasList(list)");
        sb.add("nothing");
        sb.add("void 	setBirthdate(date)");
        sb.add("nothing");
        sb.add("void 	setGender(Gender gender)");
        sb.add("void 	setName(String name)");
        sb.add("nothing");
        sb.add("String 	toString()");
        sb.add("static User 	valueOf()\n");

        // OTHER CLASSES
        sb.add("playback = new Playback(Song song, Calendar date)");
        sb.add("song = songController.getSong(String title, String artist)");
        sb.add("songController.addSong(String title, String artist, String album, int year, int genre, int subgenre, int duration)");
        sb.add("date.set(int year, int month, int day, int hour, int minute, int second)");
        sb.add("list = new List(String listTitle)");
        sb.add("list.addSong(song)");
        for (int i = 0; i < sb.size(); ++i) {
            if (!sb.get(i).equals("nothing"))
                System.err.println(i + ": " + sb.get(i));
        }
    }

    private static void printInfoBrief() {
        System.err.print("0:    terminate program\n"
                + "1:    info\n");
    }


}
