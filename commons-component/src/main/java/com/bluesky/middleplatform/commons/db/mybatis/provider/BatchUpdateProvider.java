package com.bluesky.middleplatform.commons.db.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class BatchUpdateProvider extends MapperTemplate {

    public BatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量更新
     *
     * @param ms
     * @return
     */
    public String updateList(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        // 开始拼sql
        StringBuilder sql = new StringBuilder();

        sql.append("<foreach collection=\"list\" item=\"record\" index=\"index\" open=\"\" close=\"\" separator=\";\" >");
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        //<foreach>语句中，直接将item 的名称 “record” 作为entityName 参数
        sql.append(SqlHelper.updateSetColumns(entityClass, null, false, false));
        sql.append(SqlHelper.wherePKColumns(entityClass));

//		sql.append(SqlHelper.updateSetColumns(entityClass, "record", false, false));
//		sql.append(SqlHelper.wherePKColumns(entityClass,"record"));
        sql.append("</foreach>");

        System.out.println("tableName : " + tableName(entityClass));
        System.out.println("updateSQL : " + sql.toString());
        System.out.println();
        return sql.toString();
    }
}
