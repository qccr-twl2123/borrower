package com.trj.jk.web.domain.entity.authentication.bi;

public class GjjCallbackResult {
    private String callback_type;
    private GjjCallbackTaskResult task_info;
    private GjjCallbackTaskResult notice;

    public String getCallback_type() {
        return callback_type;
    }

    public void setCallback_type(String callback_type) {
        this.callback_type = callback_type;
    }

    public GjjCallbackTaskResult getTask_info() {
        return task_info;
    }

    public void setTask_info(GjjCallbackTaskResult task_info) {
        this.task_info = task_info;
    }

    public GjjCallbackTaskResult getNotice() {
        return notice;
    }

    public void setNotice(GjjCallbackTaskResult notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "GjjCallbackResult [callback_type=" + callback_type + ",  task_info=" + task_info + "]";
    }

}
