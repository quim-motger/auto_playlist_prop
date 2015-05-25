package prop.domain;

import java.util.HashMap;

/**
 * The genre list as defined in http://www.id3.org/id3v2.3.0
 * @author oscar.manas
 */
public enum Genre {

    ACapella(0,"A capella"),
    Acid(1,"Acid"),
    AcidPunk(2,"Acid Punk"),
    AcidJazz(3,"Acid Jazz"),
    Acoustic(4,"Acoustic"),
    Alternative(5,"Alternative"),
    AlternativeRock(6,"Alternative Rock"),
    Ambient(7,"Ambient"),
    Avantgarde(8,"Avantgarde"),
    Ballad(9,"Ballad"),
    Bass(10,"Bass"),
    Bebop(11,"Bebop"),
    BigBand(12,"Big Band"),
    Bluegrass(13,"Bluegrass"),
    Blues(14,"Blues"),
    BootyBass(15,"Booty Bass"),
    Cabaret(16,"Cabaret"),
    Celtic(17,"Celtic"),
    ChamberMusic(18,"Chamber Music"),
    Chanson(19,"Chanson"),
    Chorus(20,"Chorus"),
    ChristianRap(21,"Christian Rap"),
    Classical(22,"Classical"),
    ClassicRock(23,"Classic Rock"),
    Club(24,"Club"),
    ClubHouse(25,"Club-House"),
    Comedy(26,"Comedy"),
    Country(27,"Country"),
    Cult(28,"Cult"),
    Dance(29,"Dance"),
    DanceHall(30,"Dance Hall"),
    Darkwave(31,"Darkwave"),
    DeathMetal(32,"Death Metal"),
    Disco(33,"Disco"),
    Dream(34,"Dream"),
    DrumAndBass(35,"Drum & Bass"),
    DrumSolo(36,"Drum Solo"),
    Duet(37,"Duet"),
    EasyListening(38,"Easy Listening"),
    Electronic(39,"Electronic"),
    Ethnic(40,"Ethnic"),
    Eurodance(41,"Eurodance"),
    EuroHouse(42,"Euro-House"),
    EuroTechno(43,"Euro-Techno"),
    FastFusion(44,"Fast Fusion"),
    Folk(45,"Folk"),
    Folklore(46,"Folklore"),
    FolkRock(47,"Folk-Rock"),
    Freestyle(48,"Freestyle"),
    Funk(49,"Funk"),
    Fusion(50,"Fusion"),
    Game(51,"Game"),
    Gangsta(52,"Gangsta"),
    GoaTrance(53,"Goa Trance"),
    Gospel(54,"Gospel"),
    Gothic(55,"Gothic"),
    GothicRock(56,"Gothic Rock"),
    Grunge(57,"Grunge"),
    HardcoreTechno(58,"Hardcore Techno"),
    HardRock(59,"Hard Rock"),
    HipHop(60,"Hip-Hop"),
    House(61,"House"),
    Humour(62,"Humour"),
    Indie(63,"Indie"),
    Industrial(64,"Industrial"),
    Instrumental(65,"Instrumental"),
    InstrumentalPop(66,"Instrumental Pop"),
    InstrumentalRock(67,"Instrumental Rock"),
    Jazz(68,"Jazz"),
    JazzFunk(69,"Jazz+Funk"),
    Jungle(70,"Jungle"),
    Latin(70,"Latin"),
    LoFi(72,"Lo-Fi"),
    Meditative(73,"Meditative"),
    Metal(74,"Metal"),
    Musical(75,"Musical"),
    NationalFolk(76,"National Folk"),
    NativeAmerican(77,"Native American"),
    NewAge(78,"New Age"),
    NewWave(79,"New Wave"),
    Noise(80,"Noise"),
    Oldies(81,"Oldies"),
    Opera(82,"Opera"),
    Other(83,"Other"),
    Polka(84,"Polka"),
    Pop(85,"Pop"),
    PopFolk(86,"Pop-Folk"),
    PopFunk(87,"Pop/Funk"),
    PornGroove(88,"Porn Groove"),
    PowerBallad(89,"Power Ballad"),
    Pranks(90,"Pranks"),
    Primus(91,"Primus"),
    ProgressiveRock(92,"Progressive Rock"),
    Psychedelic(93,"Psychedelic"),
    PsychedelicRock(94,"Psychedelic Rock"),
    Punk(95,"Punk"),
    PunkRock(96,"Punk Rock"),
    RnB(97,"R&B"),
    Rap(98,"Rap"),
    Rave(99,"Rave"),
    Reggae(100,"Reggae"),
    Retro(101,"Retro"),
    Revival(102,"Revival"),
    Rock(103,"Rock"),
    RockAndRoll(104,"Rock & Roll"),
    RhythmicSoul(105,"Rhythmic Soul"),
    Samba(106,"Samba"),
    Satire(107,"Satire"),
    Showtunes(108,"Showtunes"),
    Ska(109,"Ska"),
    SlowJam(110,"Slow Jam"),
    SlowRock(111,"Slow Rock"),
    Sonata(112,"Sonata"),
    Soul(113,"Soul"),
    SoundClip(114,"Sound Clip"),
    Soundtrack(115,"Soundtrack"),
    SouthernRock(116,"Southern Rock"),
    Space(117,"Space"),
    Speech(118,"Speech"),
    Swing(119,"Swing"),
    Symphony(120,"Symphony"),
    SymphonicRock(121,"Symphonic Rock"),
    Tango(122,"Tango"),
    Techno(123,"Techno"),
    TechnoIndustrial(124,"Techno-Industrial"),
    Terror(125,"Terror"),
    Top40(126,"Top 40"),
    Trailer(127,"Trailer"),
    Trance(128,"Trance"),
    Tribal(129,"Tribal"),
    TripHop(130,"Trip-Hop"),
    Vocal(131,"Vocal");

    private int id;
    private String name;

    Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the genre name
     * @return  the genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the genre ID
     * @return  the genre ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the genre by ID
     * @param id    the genre ID
     * @return      the genre
     */
    public static Genre getGenreById(int id) {
        if (id < 0 || id >= values().length) {
            return null;
        } else {
            return values()[id];
        }
    }

}
