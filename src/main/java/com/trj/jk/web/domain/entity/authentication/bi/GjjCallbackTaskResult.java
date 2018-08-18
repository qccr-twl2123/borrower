package com.trj.jk.web.domain.entity.authentication.bi;

public class GjjCallbackTaskResult {
    private String open_id;
    private String task_id;
    private String organization_type;
    private String status;
    private String progress;
    private String progress_message;
    private String error_message;

//    "open_id":"898acc1c6***741218cc22","task_id":"b5F***0100","organization_type":"TELECOM","status":"ACCEPT","progress":0,"progress_message":"","error_message":""


    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getOrganization_type() {
        return organization_type;
    }

    public void setOrganization_type(String organization_type) {
        this.organization_type = organization_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgress_message() {
        return progress_message;
    }

    public void setProgress_message(String progress_message) {
        this.progress_message = progress_message;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "BiResult [open_id=" + open_id + ", task_id=" + task_id + ", status=" + status + "]";
    }

}
