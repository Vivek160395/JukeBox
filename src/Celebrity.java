public class Celebrity {

    private int celebrityId;
    private String celebrityName;

    public Celebrity(int celebrityId, String celebrityName) {
        this.celebrityId = celebrityId;
        this.celebrityName = celebrityName;
    }

    public int getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(int celebrityId) {
        this.celebrityId = celebrityId;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "celebrityId=" + celebrityId +
                ", celebrityName='" + celebrityName + '\'' +
                '}';
    }
}
