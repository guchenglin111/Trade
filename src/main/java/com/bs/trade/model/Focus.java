package com.bs.trade.model;

import javax.persistence.*;

/**
 * 关注
 * Created by wangyanan on 2016/5/13.
 */
@Table(name = "focus")
public class Focus {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *  外键，此用户
     */
    @Column(name = "this_id")
    private Integer thisId;
    /**
     * 外键， 好友
     */
    @Column(name = "friend_id")
    private Integer friendId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThisId() {
        return thisId;
    }

    public void setThisId(Integer thisId) {
        this.thisId = thisId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
