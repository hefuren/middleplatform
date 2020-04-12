package com.bluesky.middleplatform.commons.object;

import java.io.Serializable;
import java.util.Date;


import com.bluesky.middleplatform.commons.microcache.CacheObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;


@Data
public class BaseObject extends BatchObject implements Serializable,
        CacheObject {

    private static final long serialVersionUID = 1946457095889908885L;

    @Column(name = "id")
    protected Integer id;

    @Column(name = "createby")
    protected Integer createBy;

    @Column(name = "lastupdateby")
    protected Integer lastUpdateBy;

    @Column(name = "createtime")
    protected Date createTime;

    @Column(name = "lastupdatetime")
    protected Date lastUpdateTime;

    @Column(name = "tenantId", nullable = false)
    protected Integer tenantId;

    @Transient
    protected Integer objectID;

    @Transient
    protected Integer objectType;

    // Property accessors

}
