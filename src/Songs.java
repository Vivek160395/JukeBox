public class Songs {

    private String songName;
    private String songAlbum;
    private String songDuration;
    private String songGenre;
    private String songArtist;

    public Songs(String songName, String songAlbum, String songDuration, String songGenre, String songArtist) {

        this.songName = songName;
        this.songAlbum = songAlbum;
        this.songDuration = songDuration;
        this.songGenre = songGenre;
        this.songArtist = songArtist;
    }

    public Songs() {

    }
    public Songs(String songName, String songDuration) {
        this.songName = songName;
        this.songDuration = songDuration;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    @Override
    public String toString() {
        return String.format("%15s\t %15s\t %15s\t %15s\t %15s\n",songName,songAlbum,songDuration,songGenre,songArtist);
    }
}
