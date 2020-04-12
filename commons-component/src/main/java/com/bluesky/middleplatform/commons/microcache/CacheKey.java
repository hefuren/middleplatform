package com.bluesky.middleplatform.commons.microcache;

import java.io.Serializable;

public class CacheKey implements Serializable {

    private static final long serialVersionUID = -4806856496647139230L;

    private int id;
    private int id1;
    private int id2;
    private int companyID;

    public CacheKey(int id, int companyID) {
        this.id = id;
        this.companyID = companyID;
        this.id1 = 0;
        this.id2 = 0;
    }

    public CacheKey(int id, int id1, int companyID) {
        this.id = id;
        this.id1 = id1;
        this.id2 = 0;
        this.companyID = companyID;
    }

    public CacheKey(int id, int id1, int id2, int companyID) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.companyID = companyID;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId1() {
        return this.id1;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getId2() {
        return this.id2;
    }

    public void setId1(int id2) {
        this.id2 = id2;
    }

    public int getCompanyID() {
        return this.companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public boolean equals(Object obj) {
        CacheKey o = (CacheKey) obj;
        return (o.getId() == this.id) && (o.getCompanyID() == this.companyID)
                && (o.getId1() == this.id1) && (o.getId2() == this.id2);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + new Integer(this.id).hashCode();
        hash = hash * 31 + new Integer(this.id1).hashCode();
        hash = hash * 31 + new Integer(this.id2).hashCode();
        hash = hash * 31 + new Integer(this.companyID).hashCode();
        return hash;
    }
}
