package com.bluesky.middleplatform.commons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bluesky.middleplatform.commons.web.SessionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 定义BaseController
 * 抽象控制器通用功能
 *
 * @author ElwinHe
 */
@Controller
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected SessionManager webMgr;
    protected ModelAndView mv;
    protected String forwardPath;

    protected String baseXml = "<?xml version='1.0' encoding='UTF-8'?>";

    protected static String correctJson = "{\"success\" : true}";
    protected static String errorJson = "{\"success\" : false}";

    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();

        this.webMgr = new SessionManager();
        webMgr.init(session);
        ApplicationContext ctx = BaseContext.getApplicationContext();
//		user = ctx.getBean("User", User.class);
//		user.setCompanyID(1000);
//		user.setId(1000);
//		webMgr.setCurrentUser(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postRequest() {
        return execute();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRequest() {
        return execute();
    }

    /**
     * @param forward
     * @return
     */
    public String forward(String forward) {
        String result = forwardPath + forward;
        return result;
    }

    /**
     * @return
     */
    public String execute() {
        return null;
    }


    public String setForwardPath() {
        return null;
    }

    /**
     * 获取SessionManager对象（WebMgr）
     * @return
     */
    public SessionManager getWebMgr(){
        return this.webMgr;
    }


}
