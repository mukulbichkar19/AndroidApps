package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;




public class NumbersActivity extends AppCompatActivity {

    private AudioManager mAudioManager;

    MediaPlayer numbersSound;


    // Audio Management
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





























    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        //Adding Navigation Options

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);






        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Creating an ArrayList for English Words
        final ArrayList<Word> englishWords = new ArrayList<Word>();
        englishWords.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        englishWords.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        englishWords.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        englishWords.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        englishWords.add(new Word("five","oyyisa",R.drawable.number_five,R.raw.number_five));
        englishWords.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        englishWords.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        englishWords.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        englishWords.add(new Word("nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        englishWords.add(new Word("ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));




        //LinearLayout root_view = (LinearLayout)findViewById(R.id.root_view);
       /* TextView oneView = new TextView(this);
        oneView.setText(englishWords.get(0));
        root_view.addView(oneView);

        TextView twoView = new TextView(this);
        twoView.setText(englishWords.get(1));
        root_view.addView(twoView);

        TextView threeView = new TextView(this);
        threeView.setText(englishWords.get(2));
        root_view.addView(threeView);*/

       /* for(int i=0;i<englishWords.size();i++){
            TextView viewer = new TextView(this);
            viewer.setText(englishWords.get(i));
            root_view.addView(viewer);
        }*/

        WordAdapter itemsAdapter = new WordAdapter
                                            (this,englishWords, R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        //GridView gridView = (GridView)findViewById(R.id.list);
        //gridView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                //Toast.makeText(NumbersActivity.this,"List Item Clicked",Toast.LENGTH_SHORT).show();

                                                //Log.v("Number Info",englishWords.get(position).toString());
                                                releaseMediaPlayer();


                                                int result = mAudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                                                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                                                    numbersSound = MediaPlayer.create(NumbersActivity.this,englishWords.get(position).getSounddResourceId());
                                                    numbersSound.start();
                                                    numbersSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {

                                                            //Toast.makeText(NumbersActivity.this,"I am done",Toast.LENGTH_SHORT).show();
                                                            releaseMediaPlayer();
                                                        }
                                                    });






                                                }



                                            }
                                        }



        );







    }
    private void releaseMediaPlayer(){

        if(numbersSound != null){
           // System.out.println("Inside release method");
            numbersSound.release();
            numbersSound = null;
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
