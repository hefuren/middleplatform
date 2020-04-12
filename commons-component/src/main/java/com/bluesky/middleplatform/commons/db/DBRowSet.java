package com.bluesky.middleplatform.commons.db;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component(value = "DBRowSet")
@Scope(value = "prototype")
public class DBRowSet implements Serializable, Cloneable {

    private static final long serialVersionUID = 2639489703423905959L;

    /**
     * The database connction.
     */
    private int totalRows = 0;
    private int rowCount = 0;
    private int columnCount = 0;
    private int pageCount = 1;
    private int currentPage = 1;
    private String[] columnNames = {};
    private String[] dbColumnNames = {};
    private String[] columnTypeNames = {};
    private int[] columnTypes = {};
    private int[] columnPrecisions;
    private int[] columnScales;
    private List rows;

    /**
     * default constructor
     */
    public DBRowSet() {
    }

    /**
     * Constructor.
     */
    public DBRowSet(int totalRows, int rowCount, int columnCount,
                    int currentPage, int pageCount, String[] columnNames,
                    String[] dbColumnNames, String[] columnTypeNames,
                    int[] columnTypes, List rows) {
        this.totalRows = totalRows;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.columnNames = columnNames;
        this.dbColumnNames = dbColumnNames;
        this.columnTypeNames = columnTypeNames;
        this.columnTypes = columnTypes;
        this.rows = rows;
    }

    public DBRowSet(int totalRows, int rowCount, int columnCount,
                    int currentPage, int pageCount, String[] columnNames,
                    String[] dbColumnNames, String[] columnTypeNames,
                    int[] columnTypes, List rows, int[] columnPrecisions,
                    int[] columnScales) {
        this.totalRows = totalRows;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.columnNames = columnNames;
        this.dbColumnNames = dbColumnNames;
        this.columnTypeNames = columnTypeNames;
        this.columnTypes = columnTypes;
        this.rows = rows;
        this.columnPrecisions = columnPrecisions;
        this.columnScales = columnScales;
    }

    /**
     * Get the page count.
     *
     * @return The page count
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * Set the page count.
     *
     * @param pageCount - the page count
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Get the current page number.
     *
     * @return The current page number
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     * Set the current page number.
     *
     * @param currentPage the current page number
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Get the count of rows.
     *
     * @return The count of rows
     */
    public int getTotalRows() {
        return this.totalRows;
    }

    /**
     * Get the count of rows.
     *
     * @return The count of rows
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Get the count of columns.
     *
     * @return The count of columns
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Get the column names from sql.
     *
     * @return The column names
     */
    public String[] getColumnNames() {
        return this.columnNames;
    }

    /**
     * Get the column names from database.
     *
     * @return The column names
     */
    public String[] getDBColumnNames() {
        return this.dbColumnNames;
    }

    /**
     * set the column names from database.
     *
     * @param dbColumnNames The column names
     */
    public void setDBColumnNames(String[] dbColumnNames) {
        this.dbColumnNames = dbColumnNames;
    }

    /**
     * Get the column type names.
     *
     * @return The column type names.
     */
    public String[] getColumnTypeNames() {
        return this.columnTypeNames;
    }

    /**
     * Get the column sql type.
     *
     * @return The column sql type(defined in java.sql.Types).
     */
    public int[] getColumnTypes() {
        return this.columnTypes;
    }

    /**
     * Get the column sql type according to the column name.
     *
     * @param columnName column name.
     * @return The column sql type(defined in java.sql.Types).
     */
    public int getColumnType(String columnName) {
        int idx = -1;
        for (int i = 0; i < columnCount; i++) {
            if (columnNames[i].equals(columnName.toUpperCase())
                    || (dbColumnNames[i].toUpperCase()).equals(columnName
                    .toUpperCase())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return -1;
        }
        return columnTypes[idx];
    }

    /**
     * Get the data of the specified row and column.
     *
     * @param row    the row number(0-based)
     * @param column the column index(0-based)
     * @return The data of the specified row
     */
    public Object getValueAt(int row, int column) {
        // System.out.println("row = " + row + " column = " + column);
        return getRow(row).get(column);
    }

    /**
     * Get the data of the specified row and column.
     *
     * @param row        the row number(0-based)
     * @param columnName the column name
     * @return The data of the specified row column
     */
    public Object getValueAt(int row, String columnName) {
        int idx = -1;
        for (int i = 0; i < columnCount; i++) {
            if (dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return null;
        }
        return getRow(row).get(idx);
    }

    /**
     * Get the data of the specified row.
     *
     * @param row the row number(0-based)
     * @return The data of the specified row
     */
    public List getRow(int row) {
        return (List) this.rows.get(row);
    }

    /**
     * Get all data retrieved from database.
     *
     * @return All data
     */
    public List getRows() {
        return this.rows;
    }

    /**
     * set the data of the specified column of all rows.
     *
     * @param columnName the column name
     * @param value      the column value
     */
    public void setValueAt(String columnName, Object value) {
        setValueAt(-1, columnName, value);
    }

    /**
     * set the data of the specified row column.
     *
     * @param row        the row number(0-based)
     * @param columnName the column name
     * @param value      the column value
     */
    public void setValueAt(int row, String columnName, Object value) {
        int idx = -1;
        for (int i = 0; i < columnCount; i++) {
            if ((columnNames[i].toUpperCase()).equals(columnName.toUpperCase())
                    || (dbColumnNames[i].toUpperCase()).equals(columnName
                    .toUpperCase())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return;
        }
        setValueAt(row, idx, value);
    }

    /**
     * set the data of the specified column of all rows.
     *
     * @param column the column index(0-based)
     * @param value  the column value
     */
    public void setValueAt(int column, Object value) {
        setValueAt(-1, column, value);
    }

    /**
     * set the data of the specified row column.
     *
     * @param row    the row number(0-based)
     * @param column the column index(0-based)
     * @param value  the column value
     */
    public void setValueAt(int row, int column, Object value) {
        // set all row's value of this column

        if (row == -1) {
            for (int i = 0; i < rowCount; i++) {
                getRow(i).set(column, value);
            }
        } else {
            getRow(row).set(column, value);
        }
    }

    /**
     * Get the data of the specified row.
     *
     * @param colName the row number(0-based)
     * @return The data of the specified row
     */
    public void addColumn(String colName, String colType, int colSqlType) {
        // update metadata information
        columnCount++;
        String[] tempColTypes = new String[columnCount];
        String[] tempColNames = new String[columnCount];
        String[] tempDBColNames = new String[columnCount];
        int[] tempColSqlTypes = new int[columnCount];
        for (int i = 0; i < columnCount - 1; i++) {
            tempColTypes[i] = columnTypeNames[i];
            tempColNames[i] = columnNames[i];
            tempDBColNames[i] = dbColumnNames[i];
            tempColSqlTypes[i] = columnTypes[i];
        }
        tempColTypes[columnCount - 1] = colType.toUpperCase();
        tempColNames[columnCount - 1] = colName.toUpperCase();
        tempDBColNames[columnCount - 1] = colName.toUpperCase();
        tempColSqlTypes[columnCount - 1] = colSqlType;
        columnTypeNames = tempColTypes;
        columnNames = tempColNames;
        dbColumnNames = tempDBColNames;
        columnTypes = tempColSqlTypes;

        // initialize column values
        for (int i = 0; i < rowCount; i++) {
            getRow(i).add(null);
        }
    }

    /**
     * Generate a copy of DBRowSet object.
     *
     * @return a copy of DBRowSet object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        DBRowSet dbRtn = (DBRowSet) super.clone();
        dbRtn.columnNames = (String[]) columnNames.clone();
        dbRtn.dbColumnNames = (String[]) dbColumnNames.clone();
        dbRtn.columnTypeNames = (String[]) columnTypeNames.clone();
        dbRtn.columnTypes = (int[]) columnTypes.clone();
        ArrayList al = new ArrayList();
        for (int i = 0; i < rowCount; i++) {
            al.add(((ArrayList) rows.get(i)).clone());
        }
        dbRtn.rows = al;
        return dbRtn;
    }

    /**
     * get a String value
     */
    public String getString(int row, int column) {
        Object ob = getValueAt(row, column);
        return ob == null ? "" : ob.toString();
    }

    public String getString(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        return ob == null ? "" : ob.toString();
    }

    /**
     * get a int value
     */
    public int getInt(int row, int column) {
        Object ob = getValueAt(row, column);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).intValue();
    }

    public int getInt(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).intValue();
    }

    // get boolean value
    public boolean getBoolean(int row, int column) {
        return getInt(row, column) != 0;
    }

    public boolean getBoolean(int row, String columnName) {
        return getInt(row, columnName) != 0;
    }

    /**
     * get a long value
     */
    public long getLong(int row, int column) {
        Object ob = getValueAt(row, column);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).longValue();
    }

    public long getLong(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).longValue();
    }

    /**
     * get a double value
     */
    public double getDouble(int row, int column) {
        Object ob = getValueAt(row, column);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).doubleValue();
    }

    public double getDouble(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).doubleValue();
    }

    /**
     * get a date value
     */
    public java.sql.Date getDate(int row, int column) {
        java.sql.Date oRet = null;
        Object ob = getValueAt(row, column);
        if (ob != null) {
            java.sql.Timestamp oTime = (java.sql.Timestamp) ob;
            oRet = new java.sql.Date(oTime.getTime());
        }
        return oRet;
    }

    public java.sql.Date getDate(int row, String columnName) {
        java.sql.Date oRet = null;
        Object ob = getValueAt(row, columnName);
        if (ob != null) {
            java.sql.Timestamp oTime = (java.sql.Timestamp) ob;
            oRet = new java.sql.Date(oTime.getTime());
        }
        return oRet;
    }

    /**
     * get a float value
     */
    public float getFloat(int row, int column) {
        Object ob = getValueAt(row, column);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).floatValue();
    }

    public float getFloat(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        return ob == null ? 0 : (new BigDecimal(ob.toString())).floatValue();
    }

    /**
     * Get the column precesion according to the column name.
     *
     * @param columnName column name.
     * @return The column sql type(defined in java.sql.Types).
     */
    public int getPrecision(String columnName) {
        int idx = -1;
        for (int i = 0; i < columnCount; i++) {
            if (columnNames[i].equals(columnName.toUpperCase())
                    || (dbColumnNames[i].toUpperCase()).equals(columnName
                    .toUpperCase())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return -1;
        }
        return columnPrecisions[idx];
    }

    /**
     * Get the column precesion according to the column name.
     *
     * @param columnName column name.
     * @return The column sql type(defined in java.sql.Types).
     */
    public int getScale(String columnName) {
        int idx = -1;
        for (int i = 0; i < columnCount; i++) {
            if (columnNames[i].equals(columnName.toUpperCase())
                    || (dbColumnNames[i].toUpperCase()).equals(columnName
                    .toUpperCase())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return -1;
        }
        return columnScales[idx];
    }

    /**
     * get a date value
     */
    public java.sql.Timestamp getTimestamp(int row, int column) {
        java.sql.Timestamp oRet = null;
        Object ob = getValueAt(row, column);
        if (ob != null) {
            java.sql.Date oTime = (java.sql.Date) ob;
            oRet = new java.sql.Timestamp(oTime.getTime());
        }
        return oRet;
    }

    public java.sql.Timestamp getTimestamp(int row, String columnName) {
        java.sql.Timestamp oRet = null;
        Object ob = getValueAt(row, columnName);
        if (ob != null) {
            java.sql.Date oTime = (java.sql.Date) ob;

            oRet = new java.sql.Timestamp(oTime.getTime());
        }
        return oRet;
    }

    public Integer getInteger(int row, int column) {
        Object ob = getValueAt(row, column);
        Integer result = null;
        if (null != ob) {
            result = new Integer(new BigDecimal(ob.toString()).intValue());
        }
        return result;

    }

    public Number getNumber(int row, int column) {
        Object ob = getValueAt(row, column);
        BigDecimal result = null;
        if (null != ob) {
            result = new BigDecimal(ob.toString());
        }
        return result;

    }

    public Integer getInteger(int row, String columnName) {
        Object ob = getValueAt(row, columnName);
        Integer result = null;
        if (null != ob) {
            result = new Integer(new BigDecimal(ob.toString()).intValue());
        }
        return result;
    }
}
