package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {


    AudioManager mAudioManager;

    MediaPlayer familySound;

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        public void onAudioFocusChange(int focusChange){
            if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                familySound.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                familySound.pause();
            }
        }
    };







    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> family = new ArrayList<Word>();
        family.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        family.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter familyMembers = new WordAdapter(this,family,R.color.category_family);
        ListView listItemView = (ListView)findViewById(R.id.list);
        listItemView.setAdapter(familyMembers);

        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                familySound = MediaPlayer.create(FamilyMembersActivity.this,family.get(position).getSounddResourceId());

                int result = mAudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    familySound.start();
                    familySound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }




            }
        });




    }

    private void releaseMediaPlayer(){

        if( familySound != null){
            // System.out.println("Inside release method");
            familySound.release();
            familySound = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
