package com.bluesky.middleplatform.usermanagementservice.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bluesky.middleplatform.commons.component.utils.ComponentFactory;
import com.bluesky.middleplatform.commons.db.PageInfo;
import com.bluesky.middleplatform.commons.db.SequenceUtils;
import com.bluesky.middleplatform.commons.utils.BaseContext;
import com.bluesky.middleplatform.commons.utils.BaseController;
import com.bluesky.middleplatform.commons.utils.CalendarUtils;
import com.bluesky.middleplatform.commons.utils.TypeUtils;
import com.bluesky.middleplatform.commons.utils.password.PBKDF2Utils;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import com.bluesky.middleplatform.usermanagementservice.service.DepartmentManager;
import com.bluesky.middleplatform.usermanagementservice.service.ProfileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User控制器
 * RESTful 接口实现
 *
 * @author ElwinHe
 */


@RestController(value = "UserController")
@RequestMapping(value = "/UserAction.html")
public class UserController extends BaseController {

//    @Override
//    public String setForwardPath(){
//        this.forwardPath = "profile/";
//        return forwardPath;
//    }

    /**
     * 用户注册
     * @param userJson (User对象JSON字符串)
     * @return userJson （注册成功，用户ID不为0）
     */
    @PostMapping(value = "/registerUser")
    public String registerUser(String userJson) throws Exception{
        //注册用户，即为新建用户
        userJson = newUser(userJson);
        return userJson;
    }


    /**
     * 新建用户
     * 使用场景：系统管理员后台创建用户
     * @param userJson (User对象JSON字符串)
     * @return userJson
     */
    @PostMapping(value = "/newUser")
    public String newUser(String userJson) throws Exception{
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        JSONObject jsonObject = JSONObject.parseObject(userJson);
        User mode = JSON.parseObject(userJson, User.class);
        //设置新用户ID
        Long seqID = SequenceUtils.getSequence("userManagerService_user");
        mode.setId(TypeUtils.nullToInt(seqID));
        //设置用户密码(明文密码加密后存储)
        String password = jsonObject.getString("password");
        String salt = PBKDF2Utils.getSalt();
        String cryptographPassword = PBKDF2Utils.getPBKDF2Cryptograph(password,salt,PBKDF2Utils.PBKDF2_DEFAULT_ITERCOUNT);
        mode.setPassword(cryptographPassword);
        mode.setSalt(salt);
//        mode.setCreateBy(user.getId());
        mode.setCreateTime(CalendarUtils.getCurrentDate());
//        mode.setLastUpdateBy(user.getId());
        mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
        manager.newUser(mode);

        userJson = JSONObject.toJSONString(mode);
        return userJson;
    }

    /**
     * 通过用户ID获取用户对象
     * @param userId 用户ID
     * @return 用户JSON字符
     */
    @GetMapping(value = "/getUser")
    public String getUser(int userId){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        User user = manager.getUser(userId);
        String userJson = JSON.toJSONString(user);
        return userJson;

    }

    /**
     * 根据查询条件获取用户列表
     * @param paramJson
     * @return userList （返回用户列表的JSON字符串）
     */
    @GetMapping(value = "/userList")
    public String userList(String paramJson) {
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        DepartmentManager departmentManager = (DepartmentManager) ComponentFactory.getManager("DepartmentManager");
        ApplicationContext ctx = BaseContext.getApplicationContext();

        JSONObject jsonObject = JSONObject.parseObject(paramJson);
        int currentPage = TypeUtils.nullToInt(jsonObject.getString("currentPage"));
        currentPage = (currentPage == 0) ? 1 : currentPage;
        int tenantId =  TypeUtils.nullToInt(jsonObject.getString("tenantId"));

//        Hierarchy obsHierarchy = departmentManager.getDepartmentTree(tenantId);
        PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("tenantId", new Integer(tenantId));
        pageInfo.setConditions(conditions);
        pageInfo.setCurrentPage(currentPage);
        pageInfo.setPaged(true);

        pageInfo = manager.getUsers(pageInfo);
        List<User> userList = pageInfo.getItems();
        String userListJson = JSON.toJSONString(userList);

        return userListJson;
    }


    /**
     * 更新用户信息
     * @param userJson
     * @return
     */
    public String updateUser(String userJson){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        JSONObject jsonObject = JSONObject.parseObject(userJson);
        String displayName = jsonObject.getString("displayName");
        String email = jsonObject.getString("email");
        String lastName = jsonObject.getString("lastName");
        String firstName = jsonObject.getString("firstName");
        String tel =jsonObject.getString("tel");
        String mobile = jsonObject.getString("mobile");
        int curDeptID = TypeUtils.nullToInt(jsonObject.getString("curDeptID"));
        String jobnumber = jsonObject.getString("jobnumber");

        String username = jsonObject.getString("username");
        int status = TypeUtils.nullToInt(jsonObject.getString("status"));


        int userID = TypeUtils.nullToInt(jsonObject.getString("userId"));
        User mode = manager.getUser(userID);
        mode.setDisplayname(displayName);
        mode.setEmail(email);
        mode.setLastname(lastName);
        mode.setFirstname(firstName);
        mode.setTel(tel);
        mode.setMobile(mobile);
//        mode.setd(curDeptID);
        mode.setJobnumber(jobnumber);
        mode.setName(username);
        mode.setStatus(status);
//        mode.setTenantId(tenantId);
//        mode.setLastUpdateBy(user.getId());
        mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
        manager.updateUser(mode);

        userJson = JSONObject.toJSONString(mode);

        return  userJson;
    }

    /**
     * 注销用户（注销用户，同时删除用户在系统中所有数据）
     * @param userId
     */
    public void unregisterUser(int userId){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        User mode = manager.getUser(userId);
        manager.deleteUser(mode);

    }

    /**
     * 用户登录校验
     * @param username 用户名
     * @param password 密码
     * @param tenantId 租户ID
     */
    public boolean login(String username, String password, int tenantId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        boolean flag = false;
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        User user = manager.getUser(username,tenantId);

        if(user == null){
            //用户不存在；
            return false;
        }

        flag = PBKDF2Utils.verify(password,user.getSalt(), PBKDF2Utils.PBKDF2_DEFAULT_ITERCOUNT,user.getPassword());

        return flag;

    }




}
