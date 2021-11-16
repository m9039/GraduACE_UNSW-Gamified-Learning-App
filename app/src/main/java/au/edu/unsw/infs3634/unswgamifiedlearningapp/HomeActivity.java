package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void goToPersonalityHome(View view){
        Intent intent = new Intent (this, PersonalityHomeActivity.class);
        startActivity(intent);
    }

    public void goToMockAssessments(View view){
        Intent intent = new Intent (this, MockAssessHomeActivity.class);
        startActivity(intent);
    }

    //NEED TO CHANGE THIS TO THE CORRECT CLASS
    public void goToJobSuggestions(View view){
        Intent intent = new Intent (this, PersonalityHomeActivity.class);
        startActivity(intent);
    }

    public void goToResourceVideos(View view){
        Intent intent = new Intent (this, ResourceHomeActivity.class);
        startActivity(intent);
    }
}