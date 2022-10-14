import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class JukeboxImpl {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Songs songs = new Songs();
        SongsOperations songsOperations = new SongsOperations();
        SongsListOperations songsListOperations = new SongsListOperations();
        List<Songs> songsList = songsOperations.displaySongs();

        Podcast podcast = new Podcast();
        PodcastOperations podcastOperations = new PodcastOperations();
        PodcastListOperations podcastListOperations = new PodcastListOperations();
        List<Podcast> podcastList = podcastOperations.displayPodcast();

        PlaylistDAO playlistDAO = new PlaylistDAO();
        List<PlaylistContent> playlistContentList = playlistDAO.displayPlaylistContent();

        AudioPlayer audioPlayer = new AudioPlayer();


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Welcome to JUKEBOX Music Player~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        System.out.println(" 1: Add Songs \n 2: Add Podcast \n 3: Display Songs & For Searching \n 4: Display Podcast \n " +
                "5: Create Playlist & Adding Songs OR Podcast \n 6: Display Songs Or Podcast \n 7: Play a Song From Playlist");
        System.out.println();



        System.out.println("Enter Your Choice : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter the Song Name : ");
                String songName = scanner.next();
                songs.setSongName(songName);
                System.out.println("Enter the song Duration");
                String songDuration = scanner.next();
                songs.setSongDuration(songDuration);
                System.out.println("Enter the Album Name : ");
                String albumName = scanner.next();
                songs.setSongAlbum(albumName);
                System.out.println("Enter the Artist Name: ");
                String artistName = scanner.next();
                songs.setSongArtist(artistName);
                System.out.println("Enter the Genre Name: ");
                String genreName = scanner.next();
                songs.setSongGenre(genreName);
                songsOperations.insertSongs(songs);
                songsList = songsOperations.displaySongs();
                for (Songs sng : songsList) {
                    System.out.println(sng);
                }
            }
            case 2 -> {
                System.out.println("Enter the PodcastName : ");
                String podcastName = scanner.next();
                podcast.setPodcastName(podcastName);
                System.out.println("Enter the Podcast Duration : ");
                String podcastDuration = scanner.next();
                podcast.setDuration(podcastDuration);
                System.out.println("Enter the Podcast Celebrity Name : ");
                String celebrityName = scanner.next();
                podcast.setPodcastCelebrityName(celebrityName);
                podcastOperations.insertIntoPodcast(podcast);
                podcastList = podcastOperations.displayPodcast();
                for (Podcast pod : podcastList) {
                    System.out.println(pod);
                }
            }
            case 3 -> {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Displaying All Songs ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(songsOperations.displaySongs());
                System.out.println();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Searching ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" 1: Search BySong Name \n 2: Search ByGenre Name \n 3: Search ByAlbum Name \n 4: Search ByArtist Name");
                System.out.println();
                System.out.println("Enter your Search Operation");
                int searchOperations = scanner.nextInt();
                switch (searchOperations) {
                    case 1 -> {
                        System.out.println("Enter the Song Name to Search : ");
                        String nameOfSongToSearch = scanner.next();
                        System.out.println(songsListOperations.searchBySongName(songsList, nameOfSongToSearch));
                    }
                    case 2 -> {
                        System.out.println("Enter the Genre Name to Search : ");
                        String nameOfTheGenreToSearch = scanner.next();
                        System.out.println(songsListOperations.searchByGenreName(songsList, nameOfTheGenreToSearch));
                    }
                    case 3 -> {
                        System.out.println("Enter the Album Name to Search : ");
                        String nameOfTheAlbumToSearch = scanner.next();
                        System.out.println(songsListOperations.searchByAlbumName(songsList, nameOfTheAlbumToSearch));
                    }
                    case 4 -> {
                        System.out.println("Enter the Artist Name to Search");
                        String nameOfTheArtistToSearch = scanner.next();
                        System.out.println(songsListOperations.searchByArtistName(songsList, nameOfTheArtistToSearch));
                    }
                }
            }
            case 4 -> {
                System.out.println(podcastOperations.displayPodcast());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Press 1 for Search ByCelebrity Name ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                System.out.println("Enter your Choice :");
                int searchOperationsInPodcast = scanner.nextInt();
                if (searchOperationsInPodcast == 1) {
                    System.out.println("Enter the Celebrity Name to Search : ");

                    String nameOfCelebrityToSearch = scanner.next();
                    System.out.println(podcastListOperations.searchByCelebrityName(podcastList, nameOfCelebrityToSearch));
                } else {
                    System.out.println("Invalid Input");
                }
            }
            case 5 -> {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PLAYLIST ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                System.out.println(" 1: Create a New PlayList \n 2: Add a Songs OR Podcast In Playlist \n 3: Display Names Of Playlist");
                System.out.println();
                System.out.println("Enter the choice :");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1 -> {
                        System.out.println("Enter the Playlist Name :");
                        String playlistName = scanner.next();
                        playlistDAO.checkAndInsertPlaylist(playlistName);
                    }
                    case 2 -> {
                        System.out.println("Enter the PlaylistName :");
                        String playlistName1 = scanner.next();
                        System.out.println("Enter the Track Name :");
                        String trackName = scanner.next();
                        System.out.println("Please Select What You want to Add... \n 1.Songs \n 2.Podcast)");
                        int Options = scanner.nextInt();
                        playlistDAO.addPlayListContent(playlistName1, trackName, Options);
                    }
                    case 3 -> {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ List Of Playlist Names ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        playlistDAO.displayPlay_PlayList();
                        System.out.println();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Details of songs In Playlist ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        playlistDAO.displaySongsAndPlaylistDetails();
                    }
                }
            }
            case 6 -> {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Displaying The PlayList Details of Songs OR Podcast ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                System.out.println("Enter the Playlist Name :");
                String name = scanner.next();
                playlistDAO.displayPlaylistContent(name);
            }
            case 7 -> {
                System.out.println("Enter the Playlist Name :");
                String plName = scanner.next();

                int plID = playlistDAO.searchPlaylist(plName);
                List<Integer> allTrackId = playlistDAO.fetchTrackId(plID);

                System.out.println(allTrackId);
                String path = "D:\\proj\\JukeBox\\src\\";

                for (Integer trackId : allTrackId) {
                    String newPath = path + trackId + ".wav";
                    audioPlayer.audioStream(newPath);
                    audioPlayer.play();


                    while (true) {
                        System.out.println(" 1: Stop & Play Next Song");
                        System.out.println();

                        System.out.println("Enter the Choices :");
                        int choices = scanner.nextInt();


                            if (choices == 1) {
                                audioPlayer.pause();
                                break;
                            } else if (choices == 2) {
                                audioPlayer.resumeAudio(newPath);
                                break;
                            } else if (choices == 3) {
                                audioPlayer.toParticularSecond();
                                break;
                            } else if (choices == 4) {
                                audioPlayer.stop();
                                break;
                            } else if (choices == 5) {
                                try {
                                    audioPlayer.next(newPath);
                                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                                    e.printStackTrace();
                                }

                            }


                    }
                }
            }
        }
    }
}