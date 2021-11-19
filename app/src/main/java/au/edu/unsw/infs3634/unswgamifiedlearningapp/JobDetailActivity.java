package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;


public class JobDetailActivity extends AppCompatActivity {

    Button ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);

        ibMenu = findViewById(R.id.ibMenu);
        ibMenuClicked();

        Intent intent = getIntent();
        String msg = intent.getStringExtra("transferMsg");
        Job jobs = Job.getJobName(msg);

        TextView jobTitle = findViewById(R.id.tvJobTitle);
        jobTitle.setText(jobs.getJobName());

        TextView jobDesc = findViewById(R.id.tvJobDesc);
        jobDesc.setText(jobs.getJobDesc());

        TextView jobCategory = findViewById(R.id.tvCategoryDetail);
        jobCategory.setText(jobs.getCategory());

        ImageView jobImg = findViewById(R.id.ivJobImg);
        jobImg.setImageResource(jobs.getJobImage());

        //JOB OUTLOOK FUNCTION: OnClick, will send user to job outlook website
        Button readBtn = findViewById(R.id.btnRead);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jobs.getLink()));
                startActivity(intent);
            }
        });
    }

    //When menu hamburger button is clicked, launch method
    private void ibMenuClicked() {
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuScreen();
            }
        });
    }

    //Displays menu screen from xml file and attaches functionalities to each property
    private void showMenuScreen() {
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(JobDetailActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu,
                (LinearLayout)findViewById(R.id.llMenu));
        Button btnClose = bottomSheetView.findViewById(R.id.btnClose);
        Button btnHome = bottomSheetView.findViewById(R.id.btnHome);
        Button btnPersonalityQuizzes = bottomSheetView.findViewById(R.id.btnPersonalityQuizzes);
        Button btnMockAssessments = bottomSheetView.findViewById(R.id.btnMockAssessments);
        Button btnJobSuggestions = bottomSheetView.findViewById(R.id.btnJobSuggestions);
        Button btnResourceVideos = bottomSheetView.findViewById(R.id.btnResourceVideos);

        //When close button is clicked, the menu is hidden
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

}
