import java.sql.Date;

public class Podcast {

    private String podcastName;
    private Date publishedDate;
    private String Duration;
    private String podcastCelebrityName;


    public Podcast(String podcastName, Date publishedDate, String duration, String podcastCelebrityName) {
        this.podcastName = podcastName;
        this.publishedDate = publishedDate;
        Duration = duration;
        this.podcastCelebrityName = podcastCelebrityName;
    }

    public Podcast() {

    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getPodcastCelebrityName() {
        return podcastCelebrityName;
    }

    public void setPodcastCelebrityName(String podcastCelebrityName) {
        this.podcastCelebrityName = podcastCelebrityName;
    }

    @Override
    public String toString() {
        return String.format("%15s\t %15s\t %15s\t %15s\n",podcastName,publishedDate,Duration,podcastCelebrityName);
    }
}
