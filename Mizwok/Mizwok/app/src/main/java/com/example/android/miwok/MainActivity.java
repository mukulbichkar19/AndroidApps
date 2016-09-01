/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);






        // 1. Find numbers View
        TextView numbersView = (TextView)findViewById(R.id.numbers);
        // 2. Set a ClickListener on that numbers View
       numbersView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Toast.makeText(v.getContext(),"Open the list of numbers",Toast.LENGTH_SHORT).show();
               Intent i = new Intent(MainActivity.this,NumbersActivity.class);
               startActivity(i);
           }
       });


        // 1. Find TextView for Colors
        TextView colors = (TextView)findViewById(R.id.colors);
        // 2. Set an onclickListener on that
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent c = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(c);
            }

        });

        // 1. Find view for Phrases
        TextView phrases = (TextView) findViewById(R.id.phrases);
        // 2. Set an OnClickListener on it
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(p);
            }
        });

        // 1. Find View
        TextView family = (TextView)findViewById(R.id.family);
        // 2. setOnClickListener on that view
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(MainActivity.this,FamilyMembersActivity.class);
                startActivity(f);
            }
        });






    }




}
