import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastOperations {

    Connection connection ;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public PodcastOperations()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "1Vivekkutty7");
            statement = connection.createStatement();

        }catch (Exception e){
            System.out.println("Exception " + e);
        }
    }

    public void insertIntoPodcast(Podcast podcast)
    {
        int celebrityId = checkAndInsertCelebrity(podcast.getPodcastCelebrityName());

        try{
            preparedStatement = connection.prepareStatement("insert into podcast(podcastName,podcastDuration,podcastCelebrityId) values(?,?,?)");

            preparedStatement.setString(1,podcast.getPodcastName());
            preparedStatement.setString(2, podcast.getDuration());
            preparedStatement.setInt(3,celebrityId);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0)
                System.out.println("Your Podcast has been Added Successfully");
            else
                System.out.println("Failed to Add your Podcast");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int checkAndInsertCelebrity(String celebrityName)
    {

        int celebrityId = 0;

        try
        {
            preparedStatement = connection.prepareStatement("select celebrityId from celebrity where celebrityName = ?");

            preparedStatement.setString(1,celebrityName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                celebrityId = resultSet.getInt(1);
            }

            else
            {
                System.out.println("Celebrity details Not Present in the Table");
                celebrityId = insertIntoCelebrity(celebrityName);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return celebrityId;
    }

    public int insertIntoCelebrity(String celebrityName)
    {
        int celebrityId = 0;

        try
        {
            preparedStatement = connection.prepareStatement("insert into celebrity(celebrityName) values(?)",Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,celebrityName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Celebrity Details has been successfully Added");

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    celebrityId = resultSet.getInt(1);
                }
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return celebrityId;
    }


    public List<Podcast> displayPodcast()
    {
        List<Podcast> listOfPodcast = new ArrayList<>();

        try
        {
            preparedStatement = connection.prepareStatement("select * from podcastDetails");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                listOfPodcast.add(new Podcast(resultSet.getString(2),resultSet.getDate(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return listOfPodcast;
    }

}
