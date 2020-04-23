package org.me.gcu.honoursprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PlayActivity extends AppCompatActivity {

    Button btnPlay, btnLogout;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        btnPlay = findViewById(R.id.play_btn);
        btnLogout = findViewById(R.id.sign_out_btn);

        mAuth = FirebaseAuth.getInstance();


        //Play button.
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, QuizActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(PlayActivity.this, MainActivity.class));
            }
        });
    }
}
