package SpaceGame.Audio;


import javafx.scene.media.*;

public class Audio {

    public static final AudioClip shot = new AudioClip(Audio.class.getResource("/res/sound.wav").toString());
    public static final AudioClip explosion = new AudioClip(Audio.class.getResource("/res/explosion.wav").toString());
    public static final AudioClip menu = new AudioClip(Audio.class.getResource("/res/menu.wav").toString());

}
