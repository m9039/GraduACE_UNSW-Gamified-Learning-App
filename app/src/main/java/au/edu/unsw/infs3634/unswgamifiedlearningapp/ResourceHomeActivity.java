package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResourceHomeActivity extends AppCompatActivity implements VideoAdapter.ClickListener {
    public ArrayList<Video> mVideo = Video.getVideo();
    public RecyclerView mRecyclerView;
    public VideoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resourcehome);

        //Instantiate RecyclerView
        mRecyclerView = findViewById(R.id.jobRecyclerView);
        //Set setHasFixedSize true if contents of the adapter does not change in its height or width
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Specify grid layout manager for RecyclerView
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mAdapter = new VideoAdapter(this, mVideo, this);
        mRecyclerView.setAdapter(mAdapter);

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
}

