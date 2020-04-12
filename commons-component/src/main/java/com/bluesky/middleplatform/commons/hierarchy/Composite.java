package com.bluesky.middleplatform.commons.hierarchy;

import java.io.Serializable;
import java.util.ArrayList;

public class Composite implements Serializable {

    private static final long serialVersionUID = 5248343046239365961L;

    private Hierarchyable node = null;

    private ArrayList<Composite> childs = new ArrayList();
    private int level;

    public Composite(Hierarchyable obj) {
        this.node = obj;
    }

    public int getId() {
        return this.node.getId();
    }

    public int getParentId() {
        return this.node.getParentId();
    }

    public void setParentId(int parentId) {
        this.node.setParentId(parentId);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean hasChild() {
        return this.childs.size() == 0;
    }

    public int getChildCount() {
        return this.childs.size();
    }

    public ArrayList<Composite> getChilds() {
        return this.childs;
    }

    public void setChilds(ArrayList<Composite> childs) {
        this.childs = childs;
    }

    public Hierarchyable getNode() {
        return this.node;
    }

    public void setNode(Hierarchyable node) {
        this.node = node;
    }

    public boolean equals(Object obj) {
        Composite o = (Composite) obj;
        return getId() == o.getId();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(":[");
        for (Composite c : this.childs) {
            sb.append(",");
            sb.append(c.getId());
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
