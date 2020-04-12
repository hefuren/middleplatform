package com.bluesky.middleplatform.commons.hierarchy;

import java.io.Serializable;

public interface Hierarchyable extends Serializable {

    public Integer getId();

    public Integer getParentId();

    public void setParentId(Integer parentId);

    public String getName();
}
