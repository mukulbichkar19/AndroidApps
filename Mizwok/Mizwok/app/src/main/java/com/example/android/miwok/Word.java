package com.example.android.miwok;

import android.media.Image;

/**
 * Created by mukul on 7/15/2016.
 */
public class Word {

    // English Translation
    private String defaultTranslation;

    // Miwok Translation
    private String miwokTranslation;

    // Address of Image Associated
    private int imageResourceId;

    // A boolean indicating image association
    private boolean hasImageAssociated = false;

    // An int for storing reference for sudio file
    private int soundResourceId;



    public Word(String defaultTranslation,String miwokTranslation,int soundResourceId){

        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.soundResourceId = soundResourceId;

    }

    public Word(String defaultTranslation,String miwokTranslation,int imageResourceId,int soundResourceId){

        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId=imageResourceId;
        this.soundResourceId = soundResourceId;
        this.hasImageAssociated = true;

    }

    public String getDefaultTranslation(){
        return this.defaultTranslation;
    }

    public String getMiwokTranslation(){
        return this.miwokTranslation;
    }
    public int getImageResourceId(){
        return this.imageResourceId;
    }
    public boolean checkifImageAssociated(){
        return this.hasImageAssociated;
    }

    public int getSounddResourceId(){
        return this.soundResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "defaultTranslation='" + defaultTranslation + '\'' +
                ", miwokTranslation='" + miwokTranslation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", hasImageAssociated=" + hasImageAssociated +
                ", soundResourceId=" + soundResourceId +
                '}';
    }
}
