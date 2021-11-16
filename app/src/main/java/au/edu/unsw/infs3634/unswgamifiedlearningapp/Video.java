package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import java.util.ArrayList;

public class Video {
    public Video(String id, String name, String desc, String source, String videoId, int picture) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.source = source;
        this.videoId = videoId;
        this.picture = picture;
    }

    private String id, name, desc, source, videoId;
    private int picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public static ArrayList<Video> getVideo() {
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(new Video("1", "CV Best Practices", "A strong CV is crucial if you want to land interviews for the best jobs, so this video breaks down the CV writing process into simple steps.", "Source: StandOut CV", "_fP43gcBywU", R.drawable.reading));
        videos.add(new Video("2", "How to write a cover letter", "a", "Source: ZipJob", "jHg0b7Nai6c", R.drawable.mail));
        videos.add(new Video("3", "Recently graduated? Where to next?!", "a", "Source: Simple Victoria", "Has9xYpF4XQ", R.drawable.graduation_cap));
        videos.add(new Video("4", "How to write a professional email", "a", "Source: CTL", "SMnjShkHCug", R.drawable.send));
        videos.add(new Video("5", "Preparing for an interview", "a", "Source: StandOut CV", "HG68Ymazo18", R.drawable.newspaper));
        videos.add(new Video("6", "Video Interview tips", "a", "Source: StandOut CV", "Si4GLeQoqLA", R.drawable.laptop));
        videos.add(new Video("7", "Acing the Assessment Centre", "a", "Source: StandOut CV", "_mWqvsCC9kM", R.drawable.success));
        videos.add(new Video("8", "Networking 101: How to make connections", "a", "Source: StandOut CV", "OVf5c7NthSw", R.drawable.email));
        videos.add(new Video("9", "Persevering after professional rejection", "a", "Source: StandOut CV", "b3F5UATw_X8", R.drawable.target));
        videos.add(new Video("10", "Things to know before changing career paths", "a", "Source: StandOut CV", "MIjH8MCbONI", R.drawable.responsive));

        return videos;
    }
    //filters through every instance of the array list until it finds an ID that matches the input
    public static Video getName(String Id) {
        Video selectedName = null;
        for (Video v: getVideo()) {
            if (Id.equals(v.getId())) {
                selectedName = v;
            }
        }
        return selectedName;
    }
}
