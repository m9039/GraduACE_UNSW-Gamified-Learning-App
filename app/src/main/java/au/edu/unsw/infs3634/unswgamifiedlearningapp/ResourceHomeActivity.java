package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ResourceHomeActivity extends AppCompatActivity implements VideoAdapter.ClickListener {
    public ArrayList<Video> mVideo = Video.getVideo();
    public RecyclerView mRecyclerView;
    public VideoAdapter mAdapter;
    Button ibMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resourcehome);

        //Instantiate RecyclerView
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VideoAdapter(this, mVideo, this);
        mRecyclerView.setAdapter(mAdapter);
        ibMenu = findViewById(R.id.ibMenu);

        ibMenuClicked();

    }

    public void onClick(int position) {
        String msg = mVideo.get(position).getId();
        Intent intent = new Intent(this, ResourceDetailActivity.class);
        intent.putExtra("transferMsg", msg);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resource, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortName:
                //sort by new cases
                mAdapter.sort(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ResourceHomeActivity.this);
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
                Intent intent = new Intent(ResourceHomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceHomeActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceHomeActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceHomeActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceHomeActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}

