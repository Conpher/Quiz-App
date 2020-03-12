package org.me.gcu.honoursprojectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                                //use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnOne.setBackgroundColor(Color.parseColor("#1B998B"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
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
                                //use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnTwo.setBackgroundColor(Color.parseColor("#1B998B"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
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
                                //use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnThree.setBackgroundColor(Color.parseColor("#1B998B"));
                                        updateQuestion();

                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
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
                                //use handler for changing colour of button if input is correct.
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnFour.setBackgroundColor(Color.parseColor("#1B998B"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            //Else if answer is incorrect change it to red #EC0B43 and find the correct answer and change it to green #1B998B
                            else {
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
}
