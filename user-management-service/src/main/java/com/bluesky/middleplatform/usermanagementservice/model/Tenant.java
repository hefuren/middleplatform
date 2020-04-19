package com.bluesky.middleplatform.usermanagementservice.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 租户（公司）对象
 * @author ElwinHe
 */
@Data
@Component(value = "Tenant")
@Scope(value = "prototype")
@Entity
@Table(name = "userManagerService_tenant", schema = "public")
public class Tenant implements java.io.Serializable {

    /**
     * 状态：激活的
     */
    public static final int STATUS_ACTIVATED = 1;

    /**
     * 状态：未激活的
     */
    public static final int STATUS_UNACTIVATED = 0;

    // Fields
    @Id
    @OrderBy
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    private Integer type;

    @Column(name = "contactname", length = 50)
    private String contactname;

    @Column(name = "contactemail", length = 50)
    private String contactemail;

    @Column(name = "contactphone", length = 50)
    private String contactphone;

    @Column(name = "contactfax", length = 50)
    private String contactfax;

    @Column(name = "zip", length = 20)
    private String zip;

    /**
     * 公司网址
     */
    @Column(name = "url", length = 100)
    private String url;

    /**
     * 雇员数量（后续扩展20,50,100,100~200,500,500+）
     */
    @Column(name = "employess")
    private Integer employess;

    @Column(name = "adress", length = 500)
    private String adress;

    // Constructors

    /** default constructor */
    public Tenant() {
    }

    /** minimal constructor */
    public Tenant(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public Tenant(Integer id, String name, String description,
                   Integer status, Integer type, String contactname,
                   String contactemail, String contactphone, String contactfax,
                   String zip, String url, Integer employess, String adress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
        this.contactname = contactname;
        this.contactemail = contactemail;
        this.contactphone = contactphone;
        this.contactfax = contactfax;
        this.zip = zip;
        this.url = url;
        this.employess = employess;
        this.adress = adress;
    }


}
