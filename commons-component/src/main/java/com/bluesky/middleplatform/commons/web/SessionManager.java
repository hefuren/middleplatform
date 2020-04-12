package com.bluesky.middleplatform.commons.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import com.bluesky.middleplatform.commons.db.PageInfo;

/**
 *
 */
public class SessionManager {
    private HttpSession session;

    private Stack backStack;

    private List backList;

    public SessionManager() {
    }

    public SessionManager(HttpSession session) {
        this.session = session;
        backStack = (Stack) session
                .getAttribute("com.bluesky.middleplatform.commons.web.BackStack");
        if (backStack == null) {
            backStack = new Stack();
            session.setAttribute("com.bluesky.middleplatform.commons.web.BackStack",
                    backStack);
        }
        backList = (List) session
                .getAttribute("com.bluesky.middleplatform.commons.web.BackList");
        if (backList == null) {
            backList = new ArrayList();
            session.setAttribute("com.bluesky.middleplatform.commons.web.BackList", backList);
        }
    }

    public void init(HttpSession session) {
        this.session = session;
        backStack = (Stack) session
                .getAttribute("com.bluesky.middleplatform.commons.web.BackStack");
        if (backStack == null) {
            backStack = new Stack();
            session.setAttribute("com.bluesky.middleplatform.commons.web.BackStack",
                    backStack);
        }
        backList = (List) session
                .getAttribute("com.bluesky.middleplatform.commons.web.BackList");
        if (backList == null) {
            backList = new ArrayList();
            session.setAttribute("com.bluesky.middleplatform.commons.web.BackList", backList);
        }
    }

    public PageInfo getCurrentPageInfo(boolean isPopup) {
        if (isPopup && backStack.size() > 1) {
            if (backList.size() > 0) {
                backList.remove(backList.size() - 1);
            }
            return (PageInfo) backStack.pop();
        }
        if (backStack.isEmpty()) {
            return null;
        }
        return (PageInfo) backStack.peek();

    }

    // 如果是第二级或更多返回,用此方法添加页面设置
    public void addPageInfo(PageInfo pageInfo) {
        backStack.push(pageInfo);
    }

    // 如果是第一级返回,用此方法重设页面设置
    public void setCurrentPageInfo(PageInfo pageInfo) {
        backStack.clear();
        backList.clear();
        backStack.push(pageInfo);
    }

    // 如果是第二级或更多返回,用此方法临时存储页面设置
    public void setTempPageInfo(PageInfo pageInfo) {
        backList.add(pageInfo);
        session.setAttribute("com.bluesky.middleplatform.commons.web.BackList", backList);
    }

    // 如果是确认第二级或更多返回,用此方法将临时存储的页面设置压入堆栈
    public void pushPageInfo() {
        if (backList.size() > 0) {
            PageInfo pageInfo = (PageInfo) backList.get(backList.size() - 1);
            backStack.push(pageInfo);
        }
    }

    public int getCurrentPage(String id) {
        PageInfo pageInfo = this.getCurrentPageInfo(false);
        if (pageInfo != null && pageInfo.getId().equals(id)) {
            return pageInfo.getCurrentPage();
        }
        return 1;
    }

    public Map<String, Object> getCurrentConditions(String id) {
        PageInfo pageInfo = this.getCurrentPageInfo(false);
        if (pageInfo != null && pageInfo.getId().equals(id)) {
            return pageInfo.getConditions();
        }
        return new HashMap<String, Object>();
    }

    public String getCurrentOrderBy(String id) {
        PageInfo pageInfo = this.getCurrentPageInfo(false);
        if (pageInfo != null && pageInfo.getId().equals(id)) {
            return pageInfo.getOrderBy();
        }
        return "";
    }

    public String getCurrentOrderType(String id) {
        PageInfo pageInfo = this.getCurrentPageInfo(false);
        if (pageInfo != null && pageInfo.getId().equals(id)) {
            return pageInfo.getOrderType();
        }
        return "";
    }

//	public boolean checkSession() {
//		try {
//			User user = getCurrentUser();
//			if (user == null) {
//				return false;
//			} else {
//				return true;
//			}
//		} catch (SessionTimeoutException e) {
//			return false;
//		}
//	}


//	public User getCurrentUser() throws SessionTimeoutException {
//		CacheKey key = (CacheKey) session
//				.getAttribute("com.bluesky.iplatform.component.profile.User");
//		User user = null;
//		if (key != null) {
//			try {
////				user = ComponentFactory.getProfileManager().getUser(
////						key.getCompanyID(), key.getId());
//				ApplicationContext ctx = BaseContext.getApplicationContext();
//				user = ctx.getBean("User", User.class);
//				user.setCompanyID(1000);
//				user.setId(1000);
//			} catch (Exception e) {
//			}
//		}
//		if (user == null) {
//			throw new SessionTimeoutException();
//		}
//		return user;
//	}
//
//	public void setCurrentUser(User user) {
//		session.setAttribute("com.bluesky.iplatform.component.profile.User",
//				user.getKey());
//	}


//	public void setSessionID(String id) {
//		session.setAttribute(
//				"com.bluesky.iplatform.component.profile.User.LoginSessionID", id);
//	}
//
//	public String getSessionID() {
//		return (String) session
//				.getAttribute("com.bluesky.iplatform.component.profile.User.LoginSessionID");
//	}
//
//	public void logout() {
//		session.removeAttribute("com.bluesky.iplatform.component.profile.User");
////		DocumentLock.removeUser(session);
//	}
//
//	public String getMenuID() {
//		Object ob = session.getAttribute("MenuID");
//		return (ob == null ? "1" : ob.toString());
//	}
//
//	public void setMenuID(String aMenuID) {
//		session.setAttribute("MenuID", aMenuID);
//	}

    public String getCurrentLanguage() {
        Object ob = session.getAttribute("CurrentLanguage");
        return (ob == null ? "GB" : ob.toString());
    }

    public void setCurrentLanguage(String aCurrentLanguage) {
        session.setAttribute("CurrentLanguage", aCurrentLanguage);
    }

    public Locale getCurrentLocale() {
        return (Locale) session.getAttribute("org.apache.struts.action.LOCALE");
    }
}
