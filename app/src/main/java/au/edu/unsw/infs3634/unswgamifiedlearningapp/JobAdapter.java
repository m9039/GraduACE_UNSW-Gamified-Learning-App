package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> implements Filterable {
    public ArrayList<Job> mJobs;
    public ArrayList<Job> mJobsFiltered;
    public JobAdapter.ClickListener listener;

    public JobAdapter(Context context, ArrayList<Job> mJobs, JobAdapter.ClickListener listener) {
        this.mJobs = mJobs;
        this.mJobsFiltered = mJobs;
        this.listener = listener;
    }
    //filters list by name
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //if empty returns the original list
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    mJobsFiltered = mJobs;
                } else {
                    ArrayList<Job> filteredList = new ArrayList<>();
                    for (Job jobs : mJobs) {
                        //compares search bar characters against details of songs
                        if(jobs.getJobName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(jobs);
                        }
                    }
                    mJobsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mJobsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mJobsFiltered = (ArrayList<Job>) results.values;
                notifyDataSetChanged();
            }
        };
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

    //sort method
    public void sort(final int sortMethod) {
        if(mJobsFiltered.size() > 0) {
            Collections.sort(mJobsFiltered, new Comparator<Job>() {
                @Override
                public int compare(Job o1, Job o2) {
                    if (sortMethod == 1) {
                        return o1.getJobName().compareTo(o2.getJobName()); //sorts by job name
                    }
                    // if they did not put anything it will sort by job name
                    return o1.getJobName().compareTo(o2.getJobName());
                }
            });
        }
        notifyDataSetChanged();
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
        Job jobs = mJobsFiltered.get(position);
        holder.category.setText(jobs.getCategory());
        holder.icon.setImageResource(jobs.getIcon());


    }

    @Override
    public int getItemCount() {
        return mJobsFiltered.size();
    }

}



