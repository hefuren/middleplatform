package com.bluesky.middleplatform.commons.object;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;


@Data
public class BaseObject implements Serializable{

    private static final long serialVersionUID = 1946457095889908885L;

    @Transient
    protected Integer id;

    @Column(name = "createby")
    protected Integer createBy;

    @Column(name = "lastupdateby")
    protected Integer lastUpdateBy;

    @Column(name = "createtime")
    protected Date createTime;

    @Column(name = "lastupdatetime")
    protected Date lastUpdateTime;

    @Transient
    protected Integer tenantId;

    @Transient
    protected Integer objectID;

    @Transient
    protected Integer objectType;

    // Property accessors

}
