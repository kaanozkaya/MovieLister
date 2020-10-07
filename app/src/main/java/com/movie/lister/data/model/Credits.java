
package com.movie.lister.data.model;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Credits {
    @Expose
    private List<Cast> cast;
    @Expose
    private List<Crew> crew;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
