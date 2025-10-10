package com.backend.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class Anime {

    public class MainPictures {
        private String medium;
        private String large;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "Large: " + this.large + " " + "Medium: " + medium + "\n";
        }
    }

    private String id;
    private String title;
    private MainPictures main_picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainPictures getmain_picture() {
        return main_picture;
    }

    public void setmain_picture(MainPictures main_picture) {
        this.main_picture = main_picture;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + main_picture;
    }
}
