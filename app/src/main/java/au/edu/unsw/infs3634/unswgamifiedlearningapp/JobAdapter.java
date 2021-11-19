package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder>{
    public ArrayList<Job> mJobs;
    public JobAdapter.ClickListener listener;

    public JobAdapter(Context context, ArrayList<Job> mJobs, JobAdapter.ClickListener listener) {
        this.mJobs = mJobs;
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView category;
        public ImageView icon;
        JobAdapter.ClickListener listener;
        final JobAdapter mAdapter;

        public MyViewHolder(@NonNull View itemView, JobAdapter mAdapter, JobAdapter.ClickListener listener) {
            super(itemView);
            icon = itemView.findViewById(R.id.pIcon);
            category = itemView.findViewById(R.id.pCategory);
            this.mAdapter = mAdapter;
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {listener.onClick(getAdapterPosition()); }
    }

    //Allows click events to be caught
    public interface ClickListener {
        void onClick(int position);
    }
    //inflate the row layout from xml when needed (just the view, no data)
    @NonNull
    @Override
    public JobAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_row, parent, false);
        return new JobAdapter.MyViewHolder(view, this, listener);
    }

    //binds the data to the TextView Elements in each row
    @Override
    public void onBindViewHolder(@NonNull JobAdapter.MyViewHolder holder, int position) {
        Job jobs = mJobs.get(position);
        holder.category.setText(jobs.getCategory());
        holder.icon.setImageResource(jobs.getIcon());

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

}



