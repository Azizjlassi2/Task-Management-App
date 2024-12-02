package models;

import java.util.Date;

public class Task {

    private String title;
    private String description;
    private Date last_update;
    private Date date_created;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.date_created = new Date();
        this.last_update = this.date_created;

    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getTitle() + " - " + this.getDescription();
    }

}