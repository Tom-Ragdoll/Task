package sample.model;

import java.sql.Timestamp;

public class task {
    private int userId;
    private int taskId;
    private Timestamp dateCreated;
    private int description;
    private String content;

    public task() {
    }

    public task(int userId, int description, String task, Timestamp dateCreated) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.content = task;
        this.userId = userId;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getDescription() {
        return description;
    }

    public String getDescriptionString(){
        if(description==0){
            return "Normal";
        }
        return "Important";
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String task) {
        this.content = task;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() { return taskId; }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
