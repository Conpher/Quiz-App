package org.me.gcu.honoursprojectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //Declare instance of FirebaseAuth
    private FirebaseAuth mAuth;
    //Declare instance of type objects
    private EditText inputmail, inputPwd;
    private Button btnSignUp, btnSignIn, btnResetPwd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialise Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        //Link objects to XML data
        inputmail = (EditText) findViewById(R.id.mailCreate);
        inputPwd = (EditText) findViewById(R.id.pwdCreate);
        btnSignUp = (Button) findViewById(R.id.createBtn);
        btnSignIn = (Button) findViewById(R.id.loginLinkBtn);
        btnResetPwd = (Button) findViewById(R.id.resetPwdLinkBtn);
        progressBar = (ProgressBar) findViewById(R.id.barProgress);

        //Set On Click Listeners for each event
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            //Then create onClick Methods for each event
            @Override
            public void onClick(View v) {

                //Parse email and password to a String
                String mail = inputmail.getText().toString().trim();
                String pwd = inputPwd.getText().toString().trim();

                //Make if statements to set conditions for email and password
                //If email is empty
                if (TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(), "Enter E-mail Address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //If password is empty
                if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //If password length is less than 6
                if (pwd.length()<6){
                    Toast.makeText(getApplicationContext(), "Password Too Short, Must Be At Least 6 Digits",Toast.LENGTH_SHORT).show();
                    return;

                }

                btnSignIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                btnResetPwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RegisterActivity.this, ResetPasswordActivity.class));
                    }
                });

                //Once user has hit the create account button the progress bar will and authentication will occur
                progressBar.setVisibility(View.VISIBLE);
                //Authenticate details and create the user using email and password
                mAuth.createUserWithEmailAndPassword(mail, pwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "Create User With E-Mail: OnComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                //If sign in is successful display message to the user. Else display error message
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Error Occured... Authentication Failed!" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }


                            }
                        });

            }

        });

    }

    //Once event has completed hide the progress bar until next event
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
