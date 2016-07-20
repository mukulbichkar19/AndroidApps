/*
package com.example.android.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class NumbersFragment extends Fragment {

    private AudioManager mAudioManager;

    MediaPlayer numbersSound;

    private AudioManager.OnAudioFocusChangeListener afChangeListener= new AudioManager.OnAudioFocusChangeListener(){

        public void onAudioFocusChange(int focusChange){

            if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                numbersSound.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                numbersSound.pause();
                numbersSound.seekTo(0);
            }

        }


    };



    */
/**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     *//*

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        */
/** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call *//*


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){

        if(numbersSound != null){
            // System.out.println("Inside release method");
            numbersSound.release();
            numbersSound = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }

    }
}
*/
