package prop.domain;

import java.util.HashMap;

/**
 * The genre list as defined in http://www.id3.org/id3v2.3.0
 * @author oscar.manas
 */
public enum Genre {

    ACapella(1,"A capella"),
    Acid(2,"Acid"),
    AcidPunk(3,"Acid Punk"),
    AcidJazz(4,"Acid Jazz"),
    Acoustic(5,"Acoustic"),
    Alternative(6,"Alternative"),
    AlternativeRock(7,"Alternative Rock"),
    Ambient(8,"Ambient"),
    Avantgarde(9,"Avantgarde"),
    Ballad(10,"Ballad"),
    Bass(11,"Bass"),
    Bebop(12,"Bebop"),
    BigBand(13,"Big Band"),
    Bluegrass(14,"Bluegrass"),
    Blues(15,"Blues"),
    BootyBass(16,"Booty Bass"),
    Cabaret(17,"Cabaret"),
    Celtic(18,"Celtic"),
    ChamberMusic(19,"Chamber Music"),
    Chanson(20,"Chanson"),
    Chorus(21,"Chorus"),
    ChristianRap(22,"Christian Rap"),
    Classical(23,"Classical"),
    ClassicRock(24,"Classic Rock"),
    Club(25,"Club"),
    ClubHouse(26,"Club-House"),
    Comedy(27,"Comedy"),
    Country(28,"Country"),
    Cult(29,"Cult"),
    Dance(30,"Dance"),
    DanceHall(31,"Dance Hall"),
    Darkwave(32,"Darkwave"),
    DeathMetal(33,"Death Metal"),
    Disco(34,"Disco"),
    Dream(35,"Dream"),
    DrumAndBass(36,"Drum & Bass"),
    DrumSolo(37,"Drum Solo"),
    Duet(38,"Duet"),
    EasyListening(39,"Easy Listening"),
    Electronic(40,"Electronic"),
    Ethnic(41,"Ethnic"),
    Eurodance(42,"Eurodance"),
    EuroHouse(43,"Euro-House"),
    EuroTechno(44,"Euro-Techno"),
    FastFusion(45,"Fast Fusion"),
    Folk(46,"Folk"),
    Folklore(47,"Folklore"),
    FolkRock(48,"Folk-Rock"),
    Freestyle(49,"Freestyle"),
    Funk(50,"Funk"),
    Fusion(51,"Fusion"),
    Game(52,"Game"),
    Gangsta(53,"Gangsta"),
    GoaTrance(54,"Goa Trance"),
    Gospel(55,"Gospel"),
    Gothic(56,"Gothic"),
    GothicRock(57,"Gothic Rock"),
    Grunge(58,"Grunge"),
    HardcoreTechno(59,"Hardcore Techno"),
    HardRock(60,"Hard Rock"),
    HipHop(61,"Hip-Hop"),
    House(62,"House"),
    Humour(63,"Humour"),
    Indie(64,"Indie"),
    Industrial(65,"Industrial"),
    Instrumental(66,"Instrumental"),
    InstrumentalPop(67,"Instrumental Pop"),
    InstrumentalRock(68,"Instrumental Rock"),
    Jazz(69,"Jazz"),
    JazzFunk(70,"Jazz+Funk"),
    Jungle(71,"Jungle"),
    Latin(72,"Latin"),
    LoFi(73,"Lo-Fi"),
    Meditative(74,"Meditative"),
    Metal(75,"Metal"),
    Musical(76,"Musical"),
    NationalFolk(77,"National Folk"),
    NativeAmerican(78,"Native American"),
    NewAge(79,"New Age"),
    NewWave(80,"New Wave"),
    Noise(81,"Noise"),
    Oldies(82,"Oldies"),
    Opera(83,"Opera"),
    Other(84,"Other"),
    Polka(85,"Polka"),
    Pop(86,"Pop"),
    PopFolk(87,"Pop-Folk"),
    PopFunk(88,"Pop/Funk"),
    PornGroove(89,"Porn Groove"),
    PowerBallad(90,"Power Ballad"),
    Pranks(91,"Pranks"),
    Primus(92,"Primus"),
    ProgressiveRock(93,"Progressive Rock"),
    Psychedelic(94,"Psychedelic"),
    PsychedelicRock(95,"Psychedelic Rock"),
    Punk(96,"Punk"),
    PunkRock(97,"Punk Rock"),
    RnB(98,"R&B"),
    Rap(99,"Rap"),
    Rave(100,"Rave"),
    Reggae(101,"Reggae"),
    Retro(102,"Retro"),
    Revival(103,"Revival"),
    Rock(104,"Rock"),
    RockAndRoll(105,"Rock & Roll"),
    RhythmicSoul(106,"Rhythmic Soul"),
    Samba(107,"Samba"),
    Satire(108,"Satire"),
    Showtunes(109,"Showtunes"),
    Ska(110,"Ska"),
    SlowJam(111,"Slow Jam"),
    SlowRock(112,"Slow Rock"),
    Sonata(113,"Sonata"),
    Soul(114,"Soul"),
    SoundClip(115,"Sound Clip"),
    Soundtrack(116,"Soundtrack"),
    SouthernRock(117,"Southern Rock"),
    Space(118,"Space"),
    Speech(119,"Speech"),
    Swing(120,"Swing"),
    Symphony(121,"Symphony"),
    SymphonicRock(122,"Symphonic Rock"),
    Tango(123,"Tango"),
    Techno(124,"Techno"),
    TechnoIndustrial(125,"Techno-Industrial"),
    Terror(126,"Terror"),
    Top40(127,"Top 40"),
    Trailer(128,"Trailer"),
    Trance(129,"Trance"),
    Tribal(130,"Tribal"),
    TripHop(131,"Trip-Hop"),
    Vocal(132,"Vocal");

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
