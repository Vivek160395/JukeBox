import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongsOperations {

    Connection connection ;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public SongsOperations()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "1Vivekkutty7");
            statement = connection.createStatement();

        }
        catch (Exception e)
        {
            System.out.println("Exception " + e);
        }
    }

    public void insertSongs(Songs songs)
        {
            int artistId = checkAndInsertArtist(songs.getSongArtist());
            int genreId = checkAndInsertGenre(songs.getSongGenre());

            try{
                preparedStatement = connection.prepareStatement
                        ("insert into songs(songName,songAlbum,songDuration,songGenreID,songArtistID) values(?,?,?,?,?)");

                preparedStatement.setString(1,songs.getSongName());
                preparedStatement.setString(2,songs.getSongAlbum());
                preparedStatement.setString(3, songs.getSongDuration());
                preparedStatement.setInt(4,genreId);
                preparedStatement.setInt(5,artistId);

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected > 0)
                    System.out.println("Your Songs has been Added Successfully");
                else
                    System.out.println("Failed to Add your Songs");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        public int checkAndInsertArtist(String artistName)
        {
            int artist_Id = 0;

            try
            {
                preparedStatement = connection.prepareStatement("select artistId from artist where artistName = ?");

                preparedStatement.setString(1,artistName);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    artist_Id = resultSet.getInt(1);
                }
                else
                {
                    System.out.println("Artist Name is Not Present in the Table");
                    artist_Id = insertIntoArtist(artistName);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return artist_Id;
        }

        public int insertIntoArtist(String artistName)
        {
            int artist_Id = 0;

            try
            {
                preparedStatement = connection.prepareStatement("insert into artist(artistName) values(?)",Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1,artistName);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0)
                {
                    System.out.println("Artist Details has been successfully Added");
                    resultSet = preparedStatement.getGeneratedKeys();

                    if (resultSet.next()) {
                        artist_Id = resultSet.getInt(1);
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return artist_Id;
        }

    public int checkAndInsertGenre(String genreName)
    {
        int genreId = 0;

        try
        {
            preparedStatement = connection.prepareStatement("select genreId from genre where genreName = ?");

            preparedStatement.setString(1,genreName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                genreId = resultSet.getInt(1);
            }
            else
            {
                System.out.println("Genre is Not Present in the Table");
                System.out.println();
                genreId = insertIntoGenre(genreName);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return genreId;
    }

    public int insertIntoGenre(String genreName)
    {
        int genreId = 0;

        try
        {
            preparedStatement = connection.prepareStatement("insert into genre(genreName) values(?)",Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,genreName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Genre Details has been  Successfully Added");

                resultSet = preparedStatement.getGeneratedKeys();

                if(resultSet.next())
                {
                    genreId= resultSet.getInt(1);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return genreId;
    }

    public List<Songs> displaySongs()
    {
        List<Songs> listOfSongs = new ArrayList<>();
        try
        {
            preparedStatement = connection.prepareStatement("select * from songsView");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                listOfSongs.add(new Songs(resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return listOfSongs;
    }
}
