package com.bluesky.middleplatform.usermanagementservice.service;

import com.bluesky.middleplatform.commons.db.PageInfo;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import com.bluesky.middleplatform.usermanagementservice.model.UserProfile;

import java.util.List;

public interface ProfileManager {

    /**
     * 新建公司
     *
     * @param tenant
     * @
     */
    public void newTenant(Tenant tenant);

    /**
     * 更新公司
     *
     * @param tenant
     * @
     */
    public void updateTenant(Tenant tenant);

    /**
     * 通过ID获取公司
     *
     * @param tenantId
     * @return
     * @
     */
    public Tenant getTenant(int tenantId);

    /**
     * 删除公司
     * (删除公司时，需要删除该公司/租户所有的数据)
     *
     * @param tenantId
     * @
     */
    public void deleteTenant(int tenantId);

    /**
     * 通过名称获取公司
     *
     * @param tenantName
     * @return
     * @
     */
    public Tenant getTenant(String tenantName);

    /**
     * 激活公司/租户账号
     *
     * @param mode
     */
    public void activateTenant(Tenant mode);

    /**
     * 失效公司/租户账号
     *
     * @param mode
     */
    public void expireTenant(Tenant mode);

    /**
     * 新建用户
     *
     * @param user
     * @
     */
    public void newUser(User user);

    /**
     * 批量新增用户
     *
     * @param users
     * @
     */
    public void batchNewUsers(List<User> users);

    /**
     * 更新用户
     *
     * @param user
     * @
     */
    public void updateUser(User user);

    /**
     * 批量更新用户
     *
     * @param users
     * @
     */
    public void batchUpdateUsers(List<User> users);

    /**
     * 删除用户(标记删除，将用户status置为-1)
     *
     * @param user
     * @
     */
    public void deleteUser(User user);

    /**
     * 删除用户(标记删除，将用户status置为-1)
     *
     * @param ids
     * @
     */
    public void batchDeleteUsers(int[] ids);

    /**
     * 通过ID获取用户
     *
     * @param userID
     * @
     */
    public User getUser(int userID);

    /**
     * 从缓存中获取用户
     *
     * @param tenantID
     * @param userID
     * @return
     */
    public User getUserFormCache(int tenantID, int userID);

    /**
     * 通过用户名获取用户
     *
     * @param username 用户名
     * @param tenantID 公司ID（同一个公司/租户不允许用户名重复）
     * @return
     */
    public User getUser(String username, int tenantID);

    /**
     * 通过PageInfo获取用户
     *
     * @param pageInfo
     * @return
     * @
     */
    public PageInfo getUsers(PageInfo pageInfo);


    /**
     * 通过UserID获取 UserProfile信息
     *
     * @param userId
     * @
     */
    public UserProfile getUserProfile(int userId);

    /**
     * 新建 userProfile
     *
     * @param userProfile
     * @
     */
    public void newUserProfile(UserProfile userProfile);

    /**
     * 批量新增 userProfile
     *
     * @param modes
     * @
     */
    public void batchNewUserProfiles(List<UserProfile> modes);

    /**
     * 更新 userProfile
     *
     * @param userProfile
     * @
     */
    public void updateUserProfile(UserProfile userProfile);

    /**
     * 批量修改userProfile
     *
     * @param modes
     * @
     */
    public void batchUpdateUserProfiles(List<UserProfile> modes);

    /**
     * 删除UserProfile(标记删除)
     *
     * @param userProfile
     * @
     */
    public void deleteUserProfile(UserProfile userProfile);

    /**
     * 获取系统管理员账号
     *
     * @return
     */
    public User getAdminUser();
}
