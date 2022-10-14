import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PodcastListOperations {

    List<Podcast> search = new ArrayList<>();

    public List<Podcast> searchByCelebrityName(List<Podcast> podcastList, String celebrityName)
    {
        Optional<Podcast> checkSearchByCelebrityName = podcastList.stream().filter(celebrity -> celebrity.getPodcastCelebrityName().
                equalsIgnoreCase(celebrityName)).findAny();

        if (checkSearchByCelebrityName.isPresent())
        {
            search = podcastList.stream().filter(celebrity -> celebrity.getPodcastCelebrityName().equalsIgnoreCase(celebrityName)).
                    collect(Collectors.toList());
        }
        else
        {
            System.out.println("Your Podcast is not Available");
        }
        return  search;
    }
}
