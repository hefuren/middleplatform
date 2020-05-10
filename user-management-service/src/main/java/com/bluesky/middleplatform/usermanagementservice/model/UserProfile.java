package com.bluesky.middleplatform.usermanagementservice.model;

import com.bluesky.middleplatform.commons.object.BaseObject;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Component(value = "UserProfile")
@Scope(value = "prototype")
@Entity
@Table(name = "userManagerService_userProfile", schema = "public")
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

    @Id
    @OrderBy
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 用户Id
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Integer userId;

    /**
     * 职位
     */
    @Column(name = "position", length = 50)
    private Integer position;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 入职时间
     */
    @Column(name = "jointime")
    private Date joinTime;

    /**
     * 离职时间
     */
    @Column(name = "leavetime")
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
    public UserProfile(Integer id, User user, Integer tenantId) {
        this.id = id;
        this.user = user;
        this.tenantId = tenantId;
    }

    /**
     * full constructor
     */
    public UserProfile(Integer id, User user, Integer tenantId,
                       Integer position, Date joinTime, Date leaveTime,
                       Integer createby, Date createtime, Integer lastupdateby,
                       Date lastupdatetime) {
        this.id = id;
        this.user = user;
        this.tenantId = tenantId;
        this.position = position;
        this.joinTime = joinTime;
        this.leaveTime = leaveTime;
        this.createBy = createby;
        this.createTime = createtime;
        this.lastUpdateBy = lastupdateby;
        this.lastUpdateTime = lastupdatetime;
    }


}
