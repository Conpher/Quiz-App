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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText inputMail, inputPwd;
    private Button btnLogin, btnRegister, btnForgotPwd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Authenticate if user has signed into the system
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        //Once Authenticated set view of login page
        setContentView(R.layout.activity_login);

        inputMail = (EditText) findViewById(R.id.mailEdit);
        inputPwd = (EditText) findViewById(R.id.pwdEdit);
        btnLogin = (Button) findViewById(R.id.loginBtn);
        btnRegister = (Button) findViewById(R.id.newAccountLink);
        btnForgotPwd = (Button) findViewById(R.id.resetPwdLinkBtn);

        //Get instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //OnClickListener for link to Register Activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //OnClickListener for link to Reset Password Activity
        btnForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        //OnClickListener and Authentication once user selects Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = inputMail.getText().toString();
                final String pwd = inputPwd.getText().toString();

                if (TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Enter E-Mail Address!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
                }

                //Once Login Button has been pressed show Progress Bar
                progressBar.setVisibility(View.VISIBLE);

                //Authenticate details in the background
                mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Hide Progress Bar once Authentication has been completed and display message if error occurs
                        progressBar.setVisibility(View.GONE);
                        //If task is successful, error has occured display msg
                        if (!task.isSuccessful()) {
                            if (pwd.length() < 6) {
                                inputPwd.setError(getString(R.string.min_pwd_msg));
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.auth_fail_msg), Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });
    }
}

