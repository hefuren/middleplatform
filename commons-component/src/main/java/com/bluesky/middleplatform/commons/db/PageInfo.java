package com.bluesky.middleplatform.commons.db;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(value = "PageInfo")
@Scope(value = "prototype")
public class PageInfo implements Serializable {

    private static final long serialVersionUID = -5058557639699621587L;
    private final int DEFAULT_PAGESIZE = 20;

    /**
     * id
     * 分页支持
     */
    private String id;
    private boolean paged = false;


    /**
     * pageSize
     * 每页显示多少数
     */
    private int pageSize = 0;

    /**
     * pageCount
     * 一共有多少页；
     */
    private int pageCount = 1;
    private int currentPage = 1;
    private int totalRows = 0;
    private int rowCount = 0;
    private int columnCount = 0;

    // 查询条件 Map<String,Object> key 为参数, value 为参数值(只支持属性相等的查询)
    private Map<String, Object> conditions = new HashMap<String, Object>();

    //查询条件，根据Example查询，支持equal,in,not in 等查询
    private Example example;

    // 数据排序
    private boolean ordered = false;
    private String orderType = "";
    private String orderBy = "";

    // 当前页内容
    private List<Object> items;

    public PageInfo() {
        super();
    }

    public PageInfo(String id, int currentPage, String orderType,
                    String orderBy, Map conditions) {
        super();
        this.id = id;
        this.currentPage = currentPage;
        this.conditions = conditions;
        this.orderType = orderType;
        this.orderBy = orderBy;
    }

    public String getOrderBySQL() {
        if (orderBy != null && orderBy.length() > 0) {
            return " order by " + orderBy + " " + orderType;
        }
        return "";
    }

    /**
     * get PageInfo
     */
    public void setRowSet(DBRowSet rowSet) {
        // pageInfo.setPageSize(this.rowCount);
        this.setPageCount(rowSet.getPageCount());
        this.setCurrentPage(rowSet.getCurrentPage());
        this.setTotalRows(rowSet.getTotalRows());
        this.setRowCount(rowSet.getRowCount());
        this.setColumnCount(rowSet.getColumnCount());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the paged
     */
    public boolean isPaged() {
        return paged;
    }

    /**
     * @param paged the paged to set
     */
    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    /**
     * @return the ordered
     */
    public boolean isOrdered() {
        return ordered;
    }

    /**
     * @param ordered the ordered to set
     */
    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGESIZE;
        }

        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGESIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalRows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * @param totalRows the totalRows to set
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the columnCount
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * @param columnCount the columnCount to set
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * @return the conditions
     */
    public Map<String, Object> getConditions() {
        return conditions;
    }

    /**
     * @param conditions the conditions to set
     */
    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}
