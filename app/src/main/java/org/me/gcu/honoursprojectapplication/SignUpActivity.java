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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {

   private EditText inputMail, inputPwd;
   private Button btnSignUp;
   private TextView txtViewLogin;
   private ProgressBar progressBar;
   private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputMail = (EditText) findViewById(R.id.email);
        inputPwd = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.sign_up_btn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtViewLogin = (TextView) findViewById(R.id.login_txt);

        //Create an instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();


        //Set OnClickListener for when user selects "already has an account, login".
        txtViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        //set OnClickListener for when user hits "Sign-Up" button.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
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

                //Progress bar should become visible when user has clicked the "Sign-Up" button.
                progressBar.setVisibility(View.VISIBLE);

                //Create a new user method which will authenticate and confirm email and pwd details.
                mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Hide visibility of progress bar once event has been completed.
                        progressBar.setVisibility(View.GONE);

                        //If account is created successfully display message to the user and bring to LoginActivity.
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            //Drop previous activities so user cannot go back to Sign-Up screen.
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            //Else check if email has already been registered.
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                                //Else display error msg and ask user to try again.
                            } else {
                                Toast.makeText(getApplicationContext(), "Error, try again...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}

