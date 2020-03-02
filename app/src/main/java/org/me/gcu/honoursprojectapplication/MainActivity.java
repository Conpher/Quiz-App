package org.me.gcu.honoursprojectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText inputMail, inputPwd;
    private Button btnLogin;
    private TextView txtViewSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMail = (EditText) findViewById(R.id.email);
        inputPwd = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.sign_up_btn);
        txtViewSignUp = (TextView) findViewById(R.id.sign_up_txt);

        mAuth = FirebaseAuth.getInstance();

        //This on click method is called whenever the txtViewSignUp has been selected.
        txtViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        //Set up OnClickListener for when login button has been selected.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = inputMail.getText().toString().trim();
                String pwd = inputPwd.getText().toString().trim();

                //If email is empty display error message and return.
                if (mail.isEmpty()){
                    inputMail.setError("E-Mail is required!");
                    inputMail.requestFocus();
                    return;
                }

                //Check if user has entered a valid email, if not display error message and return.
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    inputMail.setError("Please enter a valid email!");
                    inputMail.requestFocus();
                    return;
                }

                //If user has not entered a password, display error message and return.
                if (pwd.isEmpty()){
                    inputPwd.setError("Password is required!");
                    inputPwd.requestFocus();
                    return;
                }

                //Ensure user has met the minimum password length of 6. If not display error msg.
                if (pwd.length()<6){
                    inputPwd.setError("Minimum length of password should be 6!");
                    inputPwd.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            finish();
                            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                            //Drop previous activities so user cannot go back to Sign-Up screen.
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
