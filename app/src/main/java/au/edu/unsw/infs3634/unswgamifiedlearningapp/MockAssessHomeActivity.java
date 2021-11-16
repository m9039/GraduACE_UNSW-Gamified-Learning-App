package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MockAssessHomeActivity extends AppCompatActivity {

    TextView tvNumericalMock, tvVerbalMock, tvLogicalMock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_assess_home);
        tvNumericalMock = findViewById(R.id.tvNumericalMock);
        tvVerbalMock = findViewById(R.id.tvVerbalMock);
        tvLogicalMock = findViewById(R.id.tvLogicalMock);
    }

    public void fromNumericalToMockQuestions(View view){
        String quizType = "Numerical";
        Intent intent = new Intent (this, MockAssessmentQActivity.class);
        intent.putExtra("receiveQuiz", quizType);
        startActivity(intent);
    }

    public void fromVerbalToMockQuestions(View view){
        String quizType = "Verbal";
        Intent intent = new Intent (this, MockAssessmentQActivity.class);
        intent.putExtra("receiveQuiz", quizType);
        startActivity(intent);
    }

    public void fromLogicalToMockQuestions(View view){
        String quizType = "Logical";
        Intent intent = new Intent (this, MockAssessmentQActivity.class);
        intent.putExtra("receiveQuiz", quizType);
        startActivity(intent);
    }

}