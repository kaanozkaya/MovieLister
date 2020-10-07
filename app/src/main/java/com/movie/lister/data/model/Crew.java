
package com.movie.lister.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Crew {

    @SerializedName("credit_id")
    private String creditId;
    @Expose
    private String department;
    @Expose
    private Long gender;
    @Expose
    private Long id;
    @Expose
    private String job;
    @Expose
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

}
