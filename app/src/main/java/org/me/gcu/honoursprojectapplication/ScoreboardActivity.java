package org.me.gcu.honoursprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreboardActivity extends AppCompatActivity {

    //Initialise TextView object.
    TextView txtViewTotalQues, txtViewCorrectAns, txtViewIncorrectAns;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        //Initialise each variable and link with XML.
        txtViewTotalQues = (TextView) findViewById(R.id.txt_total_questions_answered);
        txtViewCorrectAns = (TextView) findViewById(R.id.txt_total_correct_answers);
        txtViewIncorrectAns = (TextView) findViewById(R.id.txt_total_incorrect_answers);
        btnExit = (Button) findViewById(R.id.exit_scoreboard_btn);

        //Start new intent for getting scoreboardActivity.
        Intent newIntent = getIntent();

        //Set Strings to display when scoreboardActivity launches.
        String totalQuestions = newIntent.getStringExtra("Total");
        String correctAns = newIntent.getStringExtra("Correct Answers");
        String incorrectAns = newIntent.getStringExtra("Incorrect Answers");

        //Set Strings.
        txtViewTotalQues.setText(totalQuestions);
        txtViewCorrectAns.setText(correctAns);
        txtViewIncorrectAns.setText(incorrectAns);

        //Exit (go back) button.
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreboardActivity.this, PlayActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }
}
