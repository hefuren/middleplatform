package com.bluesky.middleplatform.usermanagemenservice.service;

import com.bluesky.middleplatform.commons.db.PageInfo;
import com.bluesky.middleplatform.usermanagemenservice.dao.UserDAO;
import com.bluesky.middleplatform.usermanagemenservice.model.Tenant;
import com.bluesky.middleplatform.usermanagemenservice.model.User;
import com.bluesky.middleplatform.usermanagemenservice.model.UserProfile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ElwinHe
 */
@Service(value = "ProfileManagerService")
public class ProfileManagerService implements ProfileManager {

//    @Resource(name="TenantDAOImpl")
//    private CompanyDAO companyDAO;

    @Resource(name = "UserDAOImpl")
    private UserDAO userDAO;

    @Override
    public void newTenant(Tenant Tenant) {

    }

    @Override
    public void updateTenant(Tenant Tenant) {

    }

    @Override
    public Tenant getTenant(int TenantId) {
        return null;
    }

    @Override
    public void deleteTenant(int TenantId) {

    }

    @Override
    public Tenant getTenant(String TenantName) {
        return null;
    }

    @Override
    public void activateTenant(Tenant mode) {

    }

    @Override
    public void expireTenant(Tenant mode) {

    }

    @Override
    public void newUser(User user) {
        try {
            this.userDAO.newMode(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void batchNewUsers(List<User> users) {
        try {
            this.userDAO.batchNewModes(users);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        try {
            this.userDAO.updateMode(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void batchUpdateUsers(List<User> users) {
        try {
            this.userDAO.batchUpdateModes(users);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            this.userDAO.deleteMode(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void batchDeleteUsers(int[] ids) {
        try {
            this.userDAO.batchDeleteModes(ids);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User getUser(int userID) {
        try {
            return (User) this.userDAO.getMode(userID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserFormCache(int tenantId, int userId) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String username, int tenantId) {
        try {
            User mode = (User) this.userDAO.getUser(username, tenantId);
            return mode;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public PageInfo getUsers(PageInfo pageInfo) {
        try {
            return this.userDAO.getByPageInfo(pageInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserProfile getUserProfile(int userId) {
        return null;
    }

    @Override
    public void newUserProfile(UserProfile userProfile) {

    }

    @Override
    public void batchNewUserProfiles(List<UserProfile> modes) {

    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {

    }

    @Override
    public void batchUpdateUserProfiles(List<UserProfile> modes) {

    }

    @Override
    public void deleteUserProfile(UserProfile userProfile) {

    }

    @Override
    public User getAdminUser() {
        try {
            return (User) this.userDAO.getAdminUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
