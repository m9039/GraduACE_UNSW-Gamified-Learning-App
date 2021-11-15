package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonalityQuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNumber;
    private Button btnNext, btnResults;
    private SeekBar seekbar;
    private ArrayList<PersonalityQuiz> opennessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> conscientiousnessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> extraversionPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> agreeablenessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> neuroticismPersonalityQuizArrayList;
    int currentScore = 0, currentQuestionPosition = 0, seekBarProgress = 3, currentArrayPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalityquiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        btnNext = findViewById(R.id.btnNextQuestion);
        btnResults = findViewById(R.id.btnViewResults);
        seekbar = findViewById(R.id.seekBar);
        opennessPersonalityQuizArrayList = new ArrayList<>();
        conscientiousnessPersonalityQuizArrayList = new ArrayList<>();
        extraversionPersonalityQuizArrayList = new ArrayList<>();
        agreeablenessPersonalityQuizArrayList = new ArrayList<>();
        neuroticismPersonalityQuizArrayList = new ArrayList<>();


//        if statement
        getOpennessQuizQuestions(opennessPersonalityQuizArrayList);
        setDataToViews(currentQuestionPosition,currentArrayPosition);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;
                currentScore = currentScore + progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentArrayPosition == 9){
                    btnNext.setVisibility(View.GONE);
                    btnResults.setVisibility(View.VISIBLE);
                } else {
                    currentQuestionPosition++;
                    currentArrayPosition++;
                    setDataToViews(currentArrayPosition, currentArrayPosition);
                }
            }
        });
    }

    private void setDataToViews(int currentQuestionPosition, int currentArrayPosition){
        currentQuestionPosition++;
        tvQuestionNumber.setText(currentQuestionPosition + "/10");
        tvQuestion.setText(opennessPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());

    }

    private void getOpennessQuizQuestions(ArrayList<PersonalityQuiz> opennessPersonalityQuizArrayList) {
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I'm interested in learning about the history and politics of other countries."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I would spend my time reading a book of poetry."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I enjoy looking at maps of different places."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I would enjoy creating a work of art, such as a novel, a song, or a painting."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I like people who have unconventional views."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("If I had the opportunity, I would like to attend a classical music concert."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I wouldn't be very bored by a book about the history of science and technology. "));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("People have often told me that I have a good imagination."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I think of myself as a somewhat eccentric person."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("Sometimes I like to just watch the wind as it blows through the trees."));

    }
}