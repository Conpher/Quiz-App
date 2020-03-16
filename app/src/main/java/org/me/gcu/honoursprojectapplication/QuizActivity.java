package org.me.gcu.honoursprojectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {


    Button btnOne, btnTwo, btnThree, btnFour;
    TextView txtViewTimer, txtViewQuestion;
    DatabaseReference reference;
    int total = 1;
    int correct = 0;
    int incorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Initialise each variable.
        btnOne = findViewById(R.id.answer_btn_one);
        btnTwo = findViewById(R.id.answer_btn_two);
        btnThree = findViewById(R.id.answer_btn_three);
        btnFour = findViewById(R.id.answer_btn_four);

        txtViewTimer = findViewById(R.id.timer_txt_view);
        txtViewQuestion = findViewById(R.id.question_txt_view);

        //Method called updateQuestion.
        updateQuestion();

    }

    private void updateQuestion() {

        //If less than set amount of questions go straight to the result activity.
        total++;
        if (total > 30){
        //Else read questions from the database and start the quiz.
        }else {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //Get details from the Question class.
                    final Question question = dataSnapshot.getValue(Question.class);

                    //Set and get the question. Then set and get answer options for each button.
                    txtViewQuestion.setText(question.getQuestion());
                    btnOne.setText(question.getOption1());
                    btnTwo.setText(question.getOption2());
                    btnThree.setText(question.getOption3());
                    btnFour.setText(question.getOption4());

                    //Check output of each option to see if it is correct
                    btnOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnOne.getText().toString().equals(question.getAnswer())){
                                //Display msg telling user answer is correct.
                                Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
                                btnOne.setBackgroundColor(Color.parseColor("#1B998B"));
                                //Use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //If correct load next question with new answers. Set btn colour back to blue.
                                        correct++;
                                        btnOne.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
                                Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                incorrect++;
                                btnOne.setBackgroundColor(Color.parseColor("#EC0B43"));

                                if (btnTwo.getText().toString().equals(question.getAnswer()))
                                {
                                    btnTwo.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnThree.getText().toString().equals(question.getAnswer()))
                                {
                                    btnThree.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnFour.getText().toString().equals(question.getAnswer()))
                                {
                                    btnFour.setBackgroundColor(Color.parseColor("#1B998B"));
                                }

                                //Create new handler which updates the question and rests buttons back to normal colour
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnOne.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnTwo.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnThree.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnFour.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    btnTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnTwo.getText().toString().equals(question.getAnswer())){
                                //Display msg telling user answer is correct.
                                Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
                                btnTwo.setBackgroundColor(Color.parseColor("#1B998B"));
                                //Use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //If correct load next question with new answers. Set btn colour back to blue.
                                        correct++;
                                        btnTwo.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
                                Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                incorrect++;
                                btnTwo.setBackgroundColor(Color.parseColor("#EC0B43"));

                                if (btnOne.getText().toString().equals(question.getAnswer()))
                                {
                                    btnOne.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnThree.getText().toString().equals(question.getAnswer()))
                                {
                                    btnThree.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnFour.getText().toString().equals(question.getAnswer()))
                                {
                                    btnFour.setBackgroundColor(Color.parseColor("#1B998B"));
                                }

                                //Create new handler which updates the question and rests buttons back to normal colour
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnOne.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnTwo.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnThree.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnFour.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    btnThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnThree.getText().toString().equals(question.getAnswer())){
                                //Display msg telling user answer is correct.
                                Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
                                btnThree.setBackgroundColor(Color.parseColor("#1B998B"));
                                //Use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //If correct load next question with new answers. Set btn colour back to blue.
                                        correct++;
                                        btnThree.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
                                //Create message for incorrect an
                                Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                incorrect++;
                                btnThree.setBackgroundColor(Color.parseColor("#EC0B43"));

                                if (btnOne.getText().toString().equals(question.getAnswer()))
                                {
                                    btnOne.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnTwo.getText().toString().equals(question.getAnswer()))
                                {
                                    btnTwo.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnFour.getText().toString().equals(question.getAnswer()))
                                {
                                    btnFour.setBackgroundColor(Color.parseColor("#1B998B"));
                                }

                                //Create new handler which updates the question and rests buttons back to normal colour
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnOne.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnTwo.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnThree.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnFour.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    btnFour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnFour.getText().toString().equals(question.getAnswer())){
                                //Display msg telling user answer is correct.
                                Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
                                btnFour.setBackgroundColor(Color.parseColor("#1B998B"));
                                //Use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //If correct load next question with new answers. Set btn colour back to blue.
                                        correct++;
                                        btnFour.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
                                Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                incorrect++;
                                btnFour.setBackgroundColor(Color.parseColor("#EC0B43"));

                                if (btnOne.getText().toString().equals(question.getAnswer()))
                                {
                                    btnOne.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnTwo.getText().toString().equals(question.getAnswer()))
                                {
                                    btnTwo.setBackgroundColor(Color.parseColor("#1B998B"));
                                }
                                else if (btnThree.getText().toString().equals(question.getAnswer()))
                                {
                                    btnThree.setBackgroundColor(Color.parseColor("#1B998B"));
                                }

                                //Create new handler which updates the question and rests buttons back to normal colour
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnOne.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnTwo.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnThree.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        btnFour.setBackgroundColor(Color.parseColor("#35A7FF"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    //Create timer method this will be used to create the 30 second timer in the quiz.
    public void timeLimiter(int seconds, final TextView txtTimer)
    {
        new CountDownTimer(seconds * 1000 + 1000, 1000)
        {
            //Use method onTick to calculate the milliseconds into seconds and minutes.
            @Override
            public void onTick(long millisUntilFinished)
            {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txtTimer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            //Use method quizComplete to display total number of questions answered and how many they got correct and incorrect.
            @Override
            public void onFinish()
            {
                txtTimer.setText("Quiz Finished!");
                Intent intent = new Intent(QuizActivity.this, ScoreboardActivity.class);
                intent.putExtra("Total", String.valueOf(total));
                intent.putExtra("Correct Answers", String.valueOf(correct));
                intent.putExtra("Incorrect Answers", String.valueOf(incorrect));
                startActivity(intent);
            }

            //Use method eachTick to calculate the milliseconds into seconds and minutes.
            public void eachTick (long milliSecFin)
            {
                int seconds = (int) (milliSecFin / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txtTimer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            //Use method quizComplete to display total number of questions answered and how many they got correct and incorrect.
            public void quizComplete()
            {
                txtTimer.setText("Quiz Finished!");
                Intent intent = new Intent(QuizActivity.this, ScoreboardActivity.class);
                intent.putExtra("Total", String.valueOf(total));
                intent.putExtra("Correct Answers", String.valueOf(correct));
                intent.putExtra("Incorrect Answers", String.valueOf(incorrect));
                startActivity(intent);

            }

        }.start();

    }
}
