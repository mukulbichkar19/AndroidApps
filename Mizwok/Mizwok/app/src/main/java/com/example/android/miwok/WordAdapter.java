package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mukul on 7/16/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    private int mColor;


    public WordAdapter(Activity context, ArrayList<Word> words,int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mColor = color;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listviewitem = convertView;
        if(listviewitem==null){
            listviewitem= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word word = getItem(position);
        /*boolean check = word.checkifImageAssociated();
        Log.v("Check",String.valueOf(check));*/

        TextView defaultTranslation = (TextView)listviewitem.findViewById(R.id.defaulttranslation);
        defaultTranslation.setText(word.getDefaultTranslation());

        TextView miwokTranslation = (TextView)listviewitem.findViewById(R.id.miwok);
        miwokTranslation.setText(word.getMiwokTranslation());

        ImageView associatedImage = (ImageView)listviewitem.findViewById(R.id.image);


        // Set the theme color for the list item
        View textContainer = listviewitem.findViewById(R.id.listlinearView);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(),mColor);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        if(word.checkifImageAssociated()){
            associatedImage.setImageResource(word.getImageResourceId());
            associatedImage.setVisibility(View.VISIBLE);
        }else{
            associatedImage.setVisibility(View.GONE);
        }


        return listviewitem;


    }
}
