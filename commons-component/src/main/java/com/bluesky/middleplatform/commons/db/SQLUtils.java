package com.bluesky.middleplatform.commons.db;

import com.bluesky.middleplatform.commons.utils.BaseContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "SQLUtils")
public class SQLUtils {

    public Map getTablesMaxID(List<String> tables, Map hashMap) throws Exception {
        ApplicationContext ctx = BaseContext.getApplicationContext();
//        SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
//        for (String table : tables) {
//            String sql = "select max(id) as maxid from " + table + " where 1=1";
//            Map<String,Object> result = sqlMapper.selectOne(sql);
//            long maxID = 1000;
//            if (result != null && result.size() == 1) {
//                Object object = result.get("maxid");
//                if (object instanceof Integer) {
//                    Integer temp = (Integer) object;
//                    maxID = (new Long(temp)).longValue();
//                } else if (object instanceof Long) {
//                    maxID = ((Long) object).longValue();
//                }
//                hashMap.put(table.toLowerCase(), new Long(maxID));
//            }
//        }
        return hashMap;
    }
}
