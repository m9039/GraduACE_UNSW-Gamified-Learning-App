package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MockAssessHomeActivity extends AppCompatActivity {

    TextView tvNumericalMock, tvVerbalMock, tvLogicalMock;
    Button ibMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_assess_home);
        tvNumericalMock = findViewById(R.id.tvNumericalMock);
        tvVerbalMock = findViewById(R.id.tvVerbalMock);
        tvLogicalMock = findViewById(R.id.tvLogicalMock);
        ibMenu = findViewById(R.id.ibMenu);

        ibMenuClicked();
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

    private void ibMenuClicked() {
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuScreen();
            }
        });
    }

    private void showMenuScreen() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MockAssessHomeActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu, (LinearLayout)findViewById(R.id.llMenu));
        Button btnClose = bottomSheetView.findViewById(R.id.btnClose);
        Button btnHome = bottomSheetView.findViewById(R.id.btnHome);
        Button btnPersonalityQuizzes = bottomSheetView.findViewById(R.id.btnPersonalityQuizzes);
        Button btnMockAssessments = bottomSheetView.findViewById(R.id.btnMockAssessments);
        Button btnJobSuggestions = bottomSheetView.findViewById(R.id.btnJobSuggestions);
        Button btnResourceVideos = bottomSheetView.findViewById(R.id.btnResourceVideos);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessHomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessHomeActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessHomeActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessHomeActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessHomeActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

}