package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public int teamAScore=0;
    public int teamBScore=0;




    public void score3Points(View view){

        int threePoints = 3;
        teamAScore+=threePoints;
        displayForTeamA(teamAScore);

    }

    public void score2Points(View view){

        int twoPoints = 2;
        teamAScore+=twoPoints;
        displayForTeamA(teamAScore);

    }

    public void score1Points(View view) {

        int onePoint = 1;
        teamAScore+=onePoint;
        displayForTeamA(teamAScore);
    }


    public void score3PointsteamB(View view){

        int threePoints = 3;
        teamBScore+=threePoints;
        displayForTeamB(teamBScore);

    }

    public void score2PointsteamB(View view){

        int twoPoints = 2;
        teamBScore+=twoPoints;
        displayForTeamB(teamBScore);

    }

    public void score1PointsteamB(View view){

        int onePoint = 1;
        teamBScore+=onePoint;
        displayForTeamB(teamBScore);

    }



    public void reset(View view){
        teamAScore = 0;
        teamBScore=0;
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);
    }


    public void displayForTeamA(int score){

        TextView teamAScore = (TextView)findViewById(R.id.teamAScore_text_view);
        //teamAScore.setText(String.valueOf(score));
        teamAScore.setText(String.valueOf(score));

    }

    public void displayForTeamB(int score){

        TextView teamAScore = (TextView)findViewById(R.id.teamBScore_text_view);
        //teamAScore.setText(String.valueOf(score));
        teamAScore.setText(String.valueOf(score));

    }
}
