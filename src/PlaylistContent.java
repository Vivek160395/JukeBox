public class PlaylistContent
{
    private int playListContentId;
    private String playListName;
    private int trackName;

    public PlaylistContent(int playListContentId, String plName, int trackName) {
        this.playListContentId = playListContentId;
        this.playListName = plName;
        this.trackName = trackName;
    }
    

    public int getPlayListContentId() {
        return playListContentId;
    }

    public void setPlayListContentId(int playListContentId) {
        this.playListContentId = playListContentId;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getTrackName() {
        return trackName;
    }

    public void setTrackName(int trackName) {
        this.trackName = trackName;
    }

    @Override
    public String toString() {
        return String.format("%15s\t %15s\t %15s\n",playListContentId,playListName,trackName);
    }
}
