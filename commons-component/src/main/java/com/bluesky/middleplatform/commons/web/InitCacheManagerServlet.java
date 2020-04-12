package com.bluesky.middleplatform.commons.web;

import com.bluesky.middleplatform.commons.db.SequenceUtils;
import com.bluesky.middleplatform.commons.microcache.EhcacheUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * @author ElwinHe
 */
public class InitCacheManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 5936195460241311008L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {

            EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
            //初始化序列緩存
            SequenceUtils.initSequence(cacheUtils);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
