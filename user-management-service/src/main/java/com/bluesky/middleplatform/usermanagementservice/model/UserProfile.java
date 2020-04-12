package com.bluesky.middleplatform.usermanagementservice.model;

import com.bluesky.middleplatform.commons.object.BaseObject;

import javax.persistence.*;
import java.util.Date;

public class UserProfile extends BaseObject implements java.io.Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -754787446191246802L;

    /**
     * 可用，激活的
     */
    public static final int STATUS_ACTIVE = 1;

    /**
     * 被删除的
     */
    public static final int STATUS_DELETED = -1;

    // Fields

    private Integer id;
    private Integer userId;
    private Integer position;
    private Integer status;
    private Date joinTime;
    private Date leaveTime;

    @Transient
    private User user;
    // Constructors

    /**
     * default constructor
     */
    public UserProfile() {
    }

    /**
     * minimal constructor
     */
    public UserProfile(Integer id, User user, Integer companyID) {
        this.id = id;
        this.user = user;
        this.companyID = companyID;
    }

    /**
     * full constructor
     */
    public UserProfile(Integer id, User user, Integer companyID,
                       Integer position, Date joinTime, Date leaveTime,
                       Integer createby, Date createtime, Integer lastupdateby,
                       Date lastupdatetime) {
        this.id = id;
        this.user = user;
        this.companyID = companyID;
        this.position = position;
        this.joinTime = joinTime;
        this.leaveTime = leaveTime;
        this.createBy = createby;
        this.createTime = createtime;
        this.lastUpdateBy = lastupdateby;
        this.lastUpdateTime = lastupdatetime;
    }

    // Property accessors
    @Override
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Transient
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "position")
    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Column(name = "jointime")
    public Date getJointime() {
        return this.joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    @Column(name = "leavetime")
    public Date getLeaveTime() {
        return this.leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
