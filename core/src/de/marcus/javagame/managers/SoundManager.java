package de.marcus.javagame.managers;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import javafx.util.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SoundManager {

    private static LinkedHashMap<SoundEffects,ArrayList<Long>> soundsPlayingOfType = new LinkedHashMap<>();
    private static LinkedHashMap<SoundEffects,Sound> sounds = new LinkedHashMap<>();

    public static boolean playSoundEffect(SoundEffects soundEffect, boolean loop) {
        if(!sounds.containsKey(soundEffect)) {
            if (Gdx.files.internal("sfx/" + soundEffect.getEffectName()).exists()) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("sfx/" + soundEffect.getEffectName()));
                sound.play();
                sounds.put(soundEffect, sound);
                return true;
            } else {
                return false;
            }
        } else {
            Sound sound = sounds.get(soundEffect);
            long play = sound.play();
            sound.setLooping(play,loop);
            return true;
        }

    }

    public static void stopSoundLoopForType() {

    }

    public static void stopAllSoundEffectsOfType(SoundEffects soundEffect) {
        for (Map.Entry<SoundEffects,Sound> pair : sounds.entrySet()) {
            if(pair.getKey().equals(soundEffect)) {
               pair.getValue().stop();
            }
        }
    }

    public static boolean playMusic(MusicTypes musicType, boolean loop) {
        if (Gdx.files.internal("sfx/" + musicType.getMusicName()).exists()) {
            Music music = Gdx.audio.newMusic(Gdx.files.internal("sfx/" + musicType.getMusicName()));
            return true;
        } else {
            return false;
        }
    }

    @Getter
    public enum SoundEffects {
        INVALID("invalid.wav"),
        NOTIFICATION("notification.wav");

        SoundEffects(String effectName) {
            this.effectName = effectName;
        }

        private final String effectName;
    }

    @Getter
    public enum MusicTypes {

        MAIN_MUSIC("main.wav");

        MusicTypes(String musicName) {
            this.musicName = musicName;
        }

        private final String musicName;
    }
}
