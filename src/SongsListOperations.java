import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongsListOperations {

    List<Songs> search = new ArrayList<>();

    public List<Songs> searchBySongName(List<Songs> songsList, String songName)
    {
        Optional<Songs> checkSearchByName = songsList.stream().filter(song -> song.getSongName().equalsIgnoreCase(songName)).findAny();

        if (checkSearchByName.isPresent())
        {
            search = songsList.stream().filter(songs -> songs.getSongName().equalsIgnoreCase(songName)).collect(Collectors.toList());
        }
        else
        {
            System.out.println("Your Song is not Available");
        }
        return  search;
    }

    public List<Songs> searchByGenreName(List<Songs> songsList, String genreName)
    {
        Optional<Songs> checkSearchByGenre = songsList.stream().filter(genre -> genre.getSongGenre().equalsIgnoreCase(genreName)).findAny();

        if (checkSearchByGenre.isPresent())
        {
            search = songsList.stream().filter(genre -> genre.getSongGenre().equalsIgnoreCase(genreName)).collect(Collectors.toList());
        }
        else
        {
            System.out.println("Your Song By Genre Name Not Found");
        }
        return search;
    }

    public List<Songs> searchByAlbumName(List<Songs> songsList, String albumName)
    {
        Optional<Songs> checkSearchByAlbum = songsList.stream().filter(album -> album.getSongAlbum().equalsIgnoreCase(albumName)).findAny();

        if (checkSearchByAlbum.isPresent())
        {
            search = songsList.stream().filter(genre -> genre.getSongGenre().equalsIgnoreCase(albumName)).collect(Collectors.toList());
        }
        else
        {
            System.out.println("Your Song By Album Name Not Found");
        }
        return search;
    }

    public List<Songs> searchByArtistName(List<Songs> songsList, String artistName)
    {
        Optional<Songs> checkSearchByArtist = songsList.stream().filter(artist -> artist.getSongArtist().equalsIgnoreCase(artistName)).findAny();

        if (checkSearchByArtist.isPresent())
        {
            search = songsList.stream().filter(artist -> artist.getSongArtist().equalsIgnoreCase(artistName)).collect(Collectors.toList());
        }
        else
        {
            System.out.println("Your Song By Artist Name Not Found");
        }
        return search;
    }
}
