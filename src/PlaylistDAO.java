import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    //Declaration
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    ResultSetMetaData resultSetMetaData;

    //Constructor
    public PlaylistDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "1Vivekkutty7");
            statement = connection.createStatement();

        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    //Insert Method for Playlist
    public void insertPlayList(String playlistName) {

        try {
            preparedStatement = connection.prepareStatement("insert into playlist (playlistName) values (?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, playlistName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    resultSet.getInt(1);
                    System.out.println("Playlist Name Inserted");
                } else {
                    System.out.println("Data Wrong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method for checking
    public void checkAndInsertPlaylist(String playlistName) {
        try {
            preparedStatement = connection.prepareStatement("select playlistId from playlist where playlistName = ?");

            preparedStatement.setString(1, playlistName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.getInt(1);
                System.out.println("PlayList Exist");
            } else {
                insertPlayList(playlistName);
                System.out.println("New PlayList Created...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Insert to Playlist Content Method
    public void addPlayListContent(String playlistName, String trackName, int choice) {
    try {
        int PlaylistId = searchPlaylist(playlistName);
        int trackId = 0;

        if (PlaylistId != 0) {
            switch (choice) {
                case 1 -> trackId = searchSongById(trackName);
                case 2 -> trackId = searchPodCastById(trackName);
            }
            if (trackId != 0) {
                preparedStatement = connection.prepareStatement("insert into playlistContent(playlistId,trackId) values (?, ?)");

                preparedStatement.setInt(1, PlaylistId);
                preparedStatement.setInt(2, trackId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Your Data inserted in PlaylistContent");

                }
            } else {
                System.out.println(" Track not Existed ");
            }
        } else {
            System.out.println("Please Create a Playlist");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    //Method for playlistId
    protected int searchPlaylist(String playlistName)  {
        int playlistId = 0;

        try {
            preparedStatement = connection.prepareStatement("select playlistId from playlist where playlistName = ?  ");

            preparedStatement.setString(1, playlistName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                playlistId = resultSet.getInt(1);
                System.out.println("Playlist Name Existed");
            } else {
                System.out.println("Playlist Not Existed");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return playlistId;
    }

    // Method for songId
    protected int searchSongById(String songName)  {
        int songId = 0;

        try {
            preparedStatement = connection.prepareStatement("select songId from songs where songName = ? ");

            preparedStatement.setString(1, songName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                songId = resultSet.getInt(1);
                System.out.println("Song Matched");
            } else {
                System.out.println("Please check your Input....");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return songId;
    }

    //Method for PodcastId
    protected int searchPodCastById(String podCastName) {

        int podCastId = 0;

        try {

            preparedStatement = connection.prepareStatement("select podcastId from podcast where podcastName = ? ");

            preparedStatement.setString(1, podCastName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                podCastId = resultSet.getInt(1);
                System.out.println("PodCast Matched");
            }
            else
            {
                System.out.println("Your Data is Incorrect");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return podCastId;
    }

    //Display Method for Playlist
    public void displayPlay_PlayList()
    {
        try
        {
            preparedStatement = connection.prepareStatement("Select playlistName from playlist");

            resultSetMetaData = preparedStatement.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-20s", resultSetMetaData.getColumnName(i));

            System.out.println();
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Display Method for Playlist Content Method
    public List<PlaylistContent> displayPlaylistContent()
    {
        List<PlaylistContent> listOfPlaylist = new ArrayList<>();

            try
            {
                preparedStatement = connection.prepareStatement("select * from playListContent");

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next())
                {
                    listOfPlaylist.add(new PlaylistContent
                            (resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
                }
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return listOfPlaylist;
    }

    //Method for fetching trackID
    public List<Integer> fetchTrackId(int  playlistId) {

        List<Integer> fetch = new ArrayList<>();

        try
        {
            preparedStatement = connection.prepareStatement("select trackId from playlistContent where playlistId = ?");

            preparedStatement.setInt(1, playlistId);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                fetch.add(resultSet.getInt(1));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return fetch;
    }

    //Display Method for Podcast OR Songs
    public void displayPlaylistContent(String playlistName)
    {
        try
        {
            preparedStatement = connection.prepareStatement
                        ("select trackId from playlistContent where playlistId = (select playlistId from playlist  where playlistName = ?)");

            preparedStatement.setString(1, playlistName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int id = resultSet.getInt(1);

                if (id >= 1000 && id < 2000)
                {
                    displaySongs(id);
                } else
                {
                    displayPodcast(id);
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Display Method for Songs
    public void displaySongs ( int trackId)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select songName, songDuration from songs where songId =  ? ");

            resultSetMetaData = preparedStatement.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++)
            {
                System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            }
            System.out.println();

            preparedStatement.setInt(1, trackId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-20s",resultSet.getString(i) );
                    System.out.println();
                }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Display method for Podcast
    public void displayPodcast( int trackId)
    {
        try {
            preparedStatement = connection.prepareStatement(" select podcastName, podcastDuration from podcast where podcastId = ? ");

            resultSetMetaData = preparedStatement.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            }
            System.out.println();

            preparedStatement.setInt(1, trackId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-20s",resultSet.getString(i));
                    System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display method for playlist songs
    public void displaySongsAndPlaylistDetails()
    {
        try
        {
            preparedStatement =connection.prepareStatement("select * from playlistDetails");

            resultSetMetaData = preparedStatement.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-20s", resultSetMetaData.getColumnName(i));

            System.out.println();

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-20s",resultSet.getString(i));
                    System.out.println();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

