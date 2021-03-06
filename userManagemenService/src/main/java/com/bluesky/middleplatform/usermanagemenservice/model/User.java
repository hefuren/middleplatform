package com.bluesky.middleplatform.usermanagemenservice.model;

import com.bluesky.middleplatform.commons.object.BaseObject;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Data
@Component(value = "User")
@Scope(value = "prototype")
@Entity
@Table(name = "userManagerService_user", schema = "public")
public class User extends BaseObject implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6527492586997280301L;

    public static final int SYS_ADMIN_ID = 100;

    public static final int STATUS_ACTIVE = 1; // 可用，激活的
    public static final int STATUS_UNACTIVE = 0; // 不可用，激活的
    public static final int STATUS_DELETED = -1;//被删除的

    // Fields

    @Id
    @OrderBy
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "displayname", length = 50)
    private String displayname;

    @Column(name = "firstname", length = 20)
    private String firstname;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "seqno")
    private Integer seqno;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "mobile", length = 30)
    private String mobile;

    @Column(name = "tel", length = 30)
    private String tel;

    @Column(name = "zip", length = 20)
    private String zip;

    @Column(name = "jobnumber", length = 30)
    private String jobnumber;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "birthday")
    private Timestamp birthday;

    //用户来源（HR系统，社交账号等）

    @Column(name = "sourcetype")
    private Integer sourcetype;

    @Column(name = "sourcename", length = 50)
    private String sourcename;

    @Column(name = "sourceid")
    private Integer sourceid;

    @Transient
    private UserProfile userProfile;

    // Constructors

    /**
     * 默认构造器
     */
    public User() {
    }

    /**
     * minimal constructor
     */
    public User(Integer id, String name, Integer companyID) {
        this.id = id;
        this.name = name;
        this.companyID = companyID;
    }

    public User(Integer id, String name, String password, String displayname, String firstname, String lastname,
                Integer seqno, Integer type, Integer status, String email, String mobile, String tel, String zip,
                String jobnumber, Integer sex, Timestamp birthday, Integer sourcetype, String sourcename,
                Integer sourceid) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayname = displayname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.seqno = seqno;
        this.type = type;
        this.status = status;
        this.email = email;
        this.mobile = mobile;
        this.tel = tel;
        this.zip = zip;
        this.jobnumber = jobnumber;
        this.sex = sex;
        this.birthday = birthday;
        this.sourcetype = sourcetype;
        this.sourcename = sourcename;
        this.sourceid = sourceid;
    }
}
