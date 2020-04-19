package org.me.gcu.honoursprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PlayActivity extends AppCompatActivity {

    Button btnPlay, btnSignOut;
    TextView textViewForgotPwd;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        btnPlay = findViewById(R.id.play_btn);
        btnSignOut = findViewById(R.id.exit_quiz_btn);


        //Play button.
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, QuizActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
