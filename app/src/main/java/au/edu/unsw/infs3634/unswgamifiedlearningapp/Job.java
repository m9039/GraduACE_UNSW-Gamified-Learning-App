package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import java.util.ArrayList;

public class Job {
    public Job(String id, String jobName, String jobDesc, String link, String category, int jobImage, int icon) {
        this.id = id;
        this.jobName = jobName;
        this.jobDesc = jobDesc;
        this.link = link;
        this.category = category;
        this.jobImage = jobImage;
        this.icon = icon;
    }

    private String id, jobName, jobDesc, link, category;
    private int jobImage, icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String name) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String desc) {
        this.jobDesc = jobDesc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getJobImage() {
        return jobImage;
    }

    public void setJobImage(int jobImage) {
        this.jobImage = jobImage;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public static ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("1", "Artist", "An artist uses their hands and mind to create new things. They appreciate beauty, unstructured activities and variety. They enjoy interesting and unusual people, sights, textures and sounds. These individuals prefer to work in unstructured situations and use their creativity and imagination.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=artist&industry=0&skilllevel=0", "High Openness", R.drawable.artist, R.drawable.compass));
        jobs.add(new Job("2", "Accountant", "Accountants are usually detail-oriented and organized, and like working in a structured environment. They also tend to be enterprising, which means that they are usually quite natural leaders who thrive at influencing and persuading others.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=accountant&industry=0&skilllevel=0", "Low Openness", R.drawable.accountants, R.drawable.intelligence));
        jobs.add(new Job("3", "Doctor", "Doctors are investigative individuals, where they are inquisitive and curious individuals who often like to spend time alone with their thoughts.They also tend to be social, meaning that they thrive in situations where they can interact with, persuade, or help people.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=doctor&industry=0&skilllevel=0", "High Conscientiousness", R.drawable.doctors, R.drawable.apple));
        jobs.add(new Job("4", "Technical Support", "IT support specialists are inquisitive and curious. They also tend to be conventional, meaning that they are usually detail-oriented and organized, and like working in a structured environment.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=ICT%20Support%20Technicians&industry=0&skilllevel=0", "Low Conscientiousness", R.drawable.tech_support, R.drawable.traffic_sign));
        jobs.add(new Job("5", "Event Planner", "As an event planner, everybody is relying on you for everything to go smoothly. So when you have the ability to notice the little things you are able to stop big disasters from happening and also make sure everything is well organized.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=event%20planner&industry=0&skilllevel=0", "High Extraversion", R.drawable.event_planner, R.drawable.chat));
        jobs.add(new Job("6", "Software Engineer", "The average Software Engineer tends to be confident and insightful, enjoying deep discussion to understand a particular issue. They may seem introverted in high-pressure environments, especially when faced with conflict and dissenting opinions. ",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=software%20engineer&industry=0&skilllevel=0", "Low Extraversion", R.drawable.software_engineer, R.drawable.mute));
        jobs.add(new Job("7", "Teacher", "A great teacher can get students reading, inspire a passion for languages, make math or science fun, and turn history lessons into fun and exciting stories. For many teachers, one of their simplest goals is to inspire their students to love learning.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=teacher&industry=0&skilllevel=0", "High Agreeableness", R.drawable.teacher, R.drawable.charity));
        jobs.add(new Job("8", "Scientist", "Scientists are much more open but less agreeable than people in other professions. On the plus side, they're more likely to be intellectually curious, idealistic, and passionate than non-scientists. But as a group, they also tend to be more rigid, cynical, and tactless.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=scientist&industry=0&skilllevel=0", "Low Agreeableness", R.drawable.scientist, R.drawable.search));
        jobs.add(new Job("9", "Massage Therapist", "Massage therapists are social individuals, meaning that they thrive in situations where they can interact with, persuade, or help people. They also tend to be realistic, which means that they often enjoy working outdoors or applying themselves to a hands-on project",
                "https://joboutlook.gov.au/occupations/massage-therapists?occupationCode=4116", "High Neuroticism", R.drawable.yoga, R.drawable.sad));
        jobs.add(new Job("10", "Lawyer", "The law is a thinking profession. Thinking lawyers are logical and detached, they stay away from having their personal preferences impact their decision and are by the book. Thinkers like to argue because they don't take conflict personally and view it objectively.",
                "https://joboutlook.gov.au/careers/explore-careers?keywords=lawyer&industry=0&skilllevel=0", "Low Neuroticism", R.drawable.lawyer, R.drawable.lotus_flower));

        return jobs;

    }

    public static Job getJobName(String Id) {
        Job selectedName = null;
        for (Job j : getJobs()) {
            if (Id.equals(j.getId())) {
                selectedName = j;
            }
        }
        return selectedName;
    }
}