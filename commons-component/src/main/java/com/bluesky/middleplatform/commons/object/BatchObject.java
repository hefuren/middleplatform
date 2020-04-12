package com.bluesky.middleplatform.commons.object;

import com.bluesky.middleplatform.commons.microcache.CacheKey;
import com.bluesky.middleplatform.commons.microcache.CacheObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;


import javax.persistence.Transient;


/**
 * 批量操作BaseObject
 * 具备批量操作的对象，继承该类
 */
public class BatchObject implements Serializable, CacheObject {

    private static final long serialVersionUID = -4601357344844634511L;

    @Transient
    private boolean isNew;

    @Transient
    private boolean deleted;

    @Transient
    private boolean modified;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Integer id;

    @Column(name = "companyid", nullable = false)
    public Integer companyID;

    public CacheKey getKey() {
        return new CacheKey(getId(), getCompanyID());
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isModified() {
        return this.modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyID() {
        return this.companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (Exception e) {
        }
        return o;
    }

    public static List getNewList(List list) {
        List newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BatchObject object = (BatchObject) list.get(i);
            if (object.isNew()) {
                newList.add(object);
            }
        }
        return newList;
    }

    public static List getDeleteList(List list) {
        List newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BatchObject object = (BatchObject) list.get(i);
            if (object.isDeleted()) {
                newList.add(object);
            }
        }
        return newList;
    }

    public static List getNormalList(List list) {
        List newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BatchObject object = (BatchObject) list.get(i);
            if ((!object.isNew()) && (!object.isDeleted())) {
                newList.add(object);
            }
        }
        return newList;
    }

}
