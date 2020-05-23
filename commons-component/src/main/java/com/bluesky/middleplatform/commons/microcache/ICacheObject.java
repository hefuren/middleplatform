package com.bluesky.middleplatform.commons.microcache;

/**
 * 缓存对象接口
 * （需要缓存的对象，需要实现该接口）
 */
public interface ICacheObject extends Cloneable {

    public Object clone();

//    public boolean isDeleted();
//
//    public void setDeleted(boolean paramBoolean);
//
//    public boolean isModified();
//
//    public void setModified(boolean paramBoolean);
//
//    public boolean isNew();
//
//    public void setNew(boolean paramBoolean);
}
