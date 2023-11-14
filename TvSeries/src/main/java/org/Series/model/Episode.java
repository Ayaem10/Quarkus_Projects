package org.Series.model;

public class Episode {
    private long id;
    private String name;
    private long season;
    private String summary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSeason() {
        return season;
    }

    public void setSeason(long season) {
        this.season = season;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
