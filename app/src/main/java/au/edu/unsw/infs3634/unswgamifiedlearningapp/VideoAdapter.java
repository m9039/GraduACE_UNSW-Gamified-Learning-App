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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import au.edu.unsw.infs3634.unswgamifiedlearningapp.Video;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> implements Filterable {
    public ArrayList<Video> mVideo;
    public ArrayList<Video> mVideoFiltered;
    public ClickListener listener;

    public VideoAdapter(Context context, ArrayList<Video> mVideo, ClickListener listener) {
        this.mVideo = mVideo;
        this.mVideoFiltered = mVideo;
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
                    mVideoFiltered = mVideo;
                } else {
                    ArrayList<Video> filteredList = new ArrayList<>();
                    for (Video video : mVideo) {
                        //compares search bar characters against details of songs
                        if(video.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(video);
                        }
                    }
                    mVideoFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mVideoFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mVideoFiltered = (ArrayList<Video>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView picture;
        ClickListener listener;
        final VideoAdapter mAdapter;

        public MyViewHolder(@NonNull View itemView, VideoAdapter mAdapter, ClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.pName);
            picture = itemView.findViewById(R.id.pResourcePic);
            this.mAdapter = mAdapter;
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {listener.onClick(getAdapterPosition()); }
    }

    //sort method
    public void sort(final int sortMethod) {
        if(mVideoFiltered.size() > 0) {
            Collections.sort(mVideoFiltered, new Comparator<Video>() {
                @Override
                public int compare(Video o1, Video o2) {
                    if (sortMethod == 1) {
                        return o1.getName().compareTo(o2.getName()); //sorts by rating
                    }
                    // if they did not put anything it will sort by video name
                    return o1.getName().compareTo(o2.getName());
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
    public VideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_item_row, parent, false);
        return new MyViewHolder(view, this, listener);
    }

    //binds the data to the TextView Elements in each row
    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyViewHolder holder, int position) {
        Video video = mVideoFiltered.get(position);
        holder.name.setText(video.getName());
        holder.picture.setImageResource(video.getPicture());


    }

    @Override
    public int getItemCount() {
        return mVideoFiltered.size();
    }

}
