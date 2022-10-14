import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayer
{

    Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;

    public void audioStream(String pathName)
    {
        try{
             audioInputStream= AudioSystem.getAudioInputStream(new File(pathName).getAbsoluteFile());

            clip=AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        clip.start();
        status ="play";

    }
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("Audio track is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio(String filePath)
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        audioStream(filePath);
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void stop()
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void toParticularSecond()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the seconds ");
        long second = scanner.nextInt();

        long microSecond = 1000000;
        clip.setMicrosecondPosition((second) * (microSecond));
    }

    public void next(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }
}

