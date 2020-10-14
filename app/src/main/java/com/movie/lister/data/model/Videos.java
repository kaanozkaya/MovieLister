
package com.movie.lister.data.model;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Videos {

    @Expose
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
