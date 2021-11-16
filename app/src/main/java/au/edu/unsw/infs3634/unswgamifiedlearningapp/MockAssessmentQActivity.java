package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MockAssessmentQActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNumber, tvQuizTitle;
    private String quizType;
    private Button btnNext, btnResults, btnSolution;
    private ImageView ivImage;
    private ArrayList<MockAssessment> numericalQuizArrayList;
    private ArrayList<MockAssessment> verbalQuizArrayList;
    private ArrayList<MockAssessment> logicalQuizArrayList;
    int currentScore = 0, currentQuestionPosition = 0, currentArrayPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_assessment_activity);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        btnNext = findViewById(R.id.btnNextQuestion);
        btnResults = findViewById(R.id.btnViewResults);
        btnSolution = findViewById(R.id.btnSolution);
        ivImage = findViewById(R.id.ivImage);
        numericalQuizArrayList = new ArrayList<>();
        verbalQuizArrayList = new ArrayList<>();
        logicalQuizArrayList = new ArrayList<>();

        Intent intent = getIntent();
        quizType = intent.getStringExtra("receiveQuiz");
    }
}