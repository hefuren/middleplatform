package com.bluesky.middleplatform.commons.hierarchy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "Hierarchy")
@Scope(value = "prototype")
public class Hierarchy implements Serializable {

    private static final long serialVersionUID = -7234475649410507403L;

    private Comparator<Hierarchyable> comparator = null;

    private boolean sort = false;

    private Composite rootNode = null;

    private HashMap<Integer, Composite> items = new HashMap();

    public Hierarchy() {
    }

    public Hierarchy(boolean sort) {
        this.sort = sort;
    }

    public Hierarchy(Comparator comparator) {
        this.comparator = comparator;
    }

    public Hierarchy(Comparator<Hierarchyable> comparator, boolean sort) {
        this.comparator = comparator;
        this.sort = sort;
    }

    public boolean isSort() {
        return this.sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public Comparator<Hierarchyable> getComparator() {
        return this.comparator;
    }

    public void setComparator(Comparator<Hierarchyable> comparator) {
        this.comparator = comparator;
    }

    public Hierarchyable getRootNode() {
        if (this.rootNode != null) {
            return this.rootNode.getNode();
        }
        return null;
    }

    public void setRootNode(Hierarchyable rootNode) {
        this.rootNode = new Composite(rootNode);
        this.items.put(Integer.valueOf(rootNode.getId()), this.rootNode);
    }

    public synchronized void addObject(Hierarchyable object) {
        addNode(object);
    }

    public synchronized void addObject(Hierarchyable[] objectArray) {
        for (Hierarchyable object : objectArray) {
            addNode(object);
        }
    }

    public synchronized void addObject(List<Hierarchyable> objectArray) {
        for (Hierarchyable object : objectArray) {
            addNode(object);
        }
    }

    private void addNode(Hierarchyable object) {
        Composite item = (Composite) this.items.get(Integer.valueOf(object
                .getId()));
        if (item == null) {
            item = new Composite(object);
        } else {
            item.setNode(object);
        }
        this.items.put(Integer.valueOf(object.getId()), item);
        Composite parent = (Composite) this.items.get(Integer.valueOf(object
                .getParentId()));
        if ((parent == null) && (object.getParentId() != this.rootNode.getId())) {
            parent = new Composite(object);
        }
        if (parent != null) {
            if (!parent.getChilds().contains(item)) {
                parent.getChilds().add(item);
            }
            this.items.put(Integer.valueOf(object.getParentId()), parent);
        }
    }

    public synchronized boolean move(Hierarchyable movedNode,
                                     Hierarchyable toNode) {
        boolean result = false;
        if (this.rootNode != null) {
            Composite movedObj = (Composite) this.items.get(Integer
                    .valueOf(movedNode.getId()));
            Composite toObj = (Composite) this.items.get(Integer.valueOf(toNode.getId()));
            if ((movedObj != null) && (toObj != null)) {
                movedObj.setParentId(toNode.getId());
                toObj.getChilds().add(movedObj);
                Composite parent = (Composite) this.items.get(Integer.valueOf(movedObj.getParentId()));
                if (parent != null) {
                    parent.getChilds().remove(movedObj);
                }
                result = true;
            }
        }
        return result;
    }

    public synchronized void removeObject(Hierarchyable object) {
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            Composite parent = (Composite) this.items.get(Integer
                    .valueOf(object.getParentId()));
            if (parent != null) {
                parent.getChilds().remove(item);
            }
            this.items.remove(Integer.valueOf(object.getId()));
        }
    }

    @Deprecated
    public Hierarchyable[] getAllHierarchy() {
        List list = getAllTreeNodes();
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getAllTreeNodes() {
        ArrayList list = new ArrayList();
        if (this.rootNode != null) {
            list.add(this.rootNode.getNode());
            list.addAll(getTreeNodes());
        }
        return list;
    }

    @Deprecated
    public Hierarchyable[] getHierarchy() {
        List list = getTreeNodes();
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getTreeNodes() {
        if (this.rootNode != null) {
            return getAllChildren(this.rootNode.getNode());
        }
        return null;
    }

    @Deprecated
    public Hierarchyable[] getChilds(Hierarchyable object) {
        List list = getAllChildren(object);
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getAllChildren(Hierarchyable object) {
        ArrayList list = new ArrayList();
        if (this.rootNode != null) {
            getChilds(list, object);
        }
        return list;
    }

    private void getChilds(List<Hierarchyable> list, Hierarchyable item) {
        List<Hierarchyable> childs = getDirectChildren(item);
        for (Hierarchyable child : childs) {
            list.add(child);
            if (getChildCount(child) > 0) {
                getChilds(list, child);
            }
        }
    }

    @Deprecated
    public Hierarchyable[] getDirectChilds(Hierarchyable object) {
        List list = getDirectChildren(object);
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getDirectChildren(Hierarchyable object) {
        ArrayList list = new ArrayList();
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                List<Composite> childs = item.getChilds();
                for (Composite child : childs) {
                    list.add(child.getNode());
                }
                if (this.comparator != null) {
                    Collections.sort(list, this.comparator);
                }
            }
        }
        return list;
    }

    @Deprecated
    public Hierarchyable[] getDirectChilds(Hierarchyable object,
                                           Comparator comparator) {
        List list = getDirectChildren(object, comparator);
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getDirectChildren(Hierarchyable object,
                                                 Comparator<Hierarchyable> comparator) {
        ArrayList list = new ArrayList();
        if (this.rootNode != null) {
            Composite item = null;
            item = (Composite) this.items.get(Integer.valueOf(object.getId()));
            if (item != null) {
                List<Composite> childs = item.getChilds();
                for (Composite child : childs) {
                    list.add(child.getNode());
                }
                if (comparator != null) {
                    Collections.sort(list, comparator);
                }
            }
        }
        return list;
    }

    public Hierarchyable getLastChild(Hierarchyable object) {
        List children = getAllChildren(object);
        if (children.size() == 0) {
            return object;
        }
        return (Hierarchyable) children.get(children.size() - 1);
    }

    public int getChildCount(Hierarchyable object) {
        int result = 0;
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                result = item.getChildCount();
            }
        }
        return result;
    }

    public boolean isFirstChild(Hierarchyable object) {
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                Composite parent = (Composite) this.items.get(Integer
                        .valueOf(item.getParentId()));
                if ((parent != null) && (parent.getChildCount() > 0)) {
                    Composite child = (Composite) parent.getChilds().get(0);
                    if ((child != null) && (child.getId() == item.getId())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isLastChild(Hierarchyable object) {
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                Composite parent = (Composite) this.items.get(Integer
                        .valueOf(item.getParentId()));
                if ((parent != null) && (parent.getChildCount() > 0)) {
                    Composite child = (Composite) parent.getChilds().get(
                            parent.getChildCount() - 1);
                    if ((child != null) && (child.getId() == item.getId())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Deprecated
    public Hierarchyable[] getParents(Hierarchyable object) {
        List list = getAllParent(object);
        Hierarchyable[] resultArry = new Hierarchyable[list.size()];
        list.toArray(resultArry);
        return resultArry;
    }

    public List<Hierarchyable> getAllParent(Hierarchyable object) {
        ArrayList list = new ArrayList();
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                Composite parent = (Composite) this.items.get(Integer
                        .valueOf(item.getParentId()));
                while (parent != null) {
                    list.add(parent.getNode());
                    if (parent.getId() == this.rootNode.getId()) {
                        break;
                    }
                    parent = (Composite) this.items.get(Integer.valueOf(parent
                            .getParentId()));
                }
            }
        }
        return list;
    }

    public Hierarchyable getParents(Hierarchyable object, int level) {
        Hierarchyable node = null;
        int levels = 0;
        if (level == 0) {
            return object;
        }
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                Composite parent = (Composite) this.items.get(Integer
                        .valueOf(item.getParentId()));
                while (parent != null) {
                    levels++;
                    if (levels == level) {
                        return parent.getNode();
                    }
                    if (parent.getId() == this.rootNode.getId()) {
                        break;
                    }
                    parent = (Composite) this.items.get(Integer.valueOf(parent
                            .getParentId()));
                }
            }
        }
        return node;
    }

    public int getLevel(Hierarchyable object) {
        int result = 0;
        if (this.rootNode != null) {
            Composite item = (Composite) this.items.get(Integer.valueOf(object
                    .getId()));
            if (item != null) {
                Composite parent = (Composite) this.items.get(Integer
                        .valueOf(item.getParentId()));
                while ((parent != null)
                        && (parent.getId() != this.rootNode.getId())) {
                    result++;
                    parent = (Composite) this.items.get(Integer.valueOf(parent
                            .getParentId()));
                }
            }
        }
        return result;
    }

    public boolean contains(Hierarchyable object) {
        boolean result = false;
        Composite item = (Composite) this.items.get(Integer.valueOf(object
                .getId()));
        if (item != null) {
            result = true;
        }
        return result;
    }

    public void clear() {
        this.rootNode = null;
        this.items.clear();
    }

    public Hierarchyable getObject(Hierarchyable object) {
        return getObject(object.getId());
    }

    public Hierarchyable getObject(int id) {
        Hierarchyable result = null;
        Composite item = (Composite) this.items.get(Integer.valueOf(id));
        if (item != null) {
            result = item.getNode();
        }
        return result;
    }
}
