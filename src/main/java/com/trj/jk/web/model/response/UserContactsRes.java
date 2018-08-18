package com.trj.jk.web.model.response;


/**
 * 联系人响应模型
 * Created by maievshabu on 2017/8/10.
 */
public class UserContactsRes {

    private RelationRes family;
    private RelationRes friend;
    private RelationRes colleague;

    public RelationRes getFamily() {
        return family;
    }

    public void setFamily(RelationRes family) {
        this.family = family;
    }

    public RelationRes getFriend() {
        return friend;
    }

    public void setFriend(RelationRes friend) {
        this.friend = friend;
    }

    public RelationRes getColleague() {
        return colleague;
    }

    public void setColleague(RelationRes colleague) {
        this.colleague = colleague;
    }
}
