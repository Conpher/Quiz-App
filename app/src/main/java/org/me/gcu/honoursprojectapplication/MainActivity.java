package org.me.gcu.honoursprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputMail, inputPwd;
    private Button btnLogin;
    private TextView txtViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMail = (EditText) findViewById(R.id.email);
        inputPwd = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.sign_up_btn);
        txtViewSignUp = (TextView) findViewById(R.id.sign_up_txt);

        //This on click method is called whenever the txtViewSignUp has been selected
        txtViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });



    }
}
