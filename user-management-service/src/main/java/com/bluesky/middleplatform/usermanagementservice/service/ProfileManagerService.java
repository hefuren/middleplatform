package com.bluesky.middleplatform.usermanagementservice.service;

import com.bluesky.middleplatform.commons.db.PageInfo;
import com.bluesky.middleplatform.usermanagementservice.dao.TenantDAO;
import com.bluesky.middleplatform.usermanagementservice.dao.UserDAO;
import com.bluesky.middleplatform.usermanagementservice.dao.UserProfileDAO;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import com.bluesky.middleplatform.usermanagementservice.model.UserProfile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ElwinHe
 */
@Service(value = "ProfileManagerService")
public class ProfileManagerService implements ProfileManager {

//    @Resource(name="TenantDAOImpl")

    @Resource(name = "UserDAOImpl")
    private UserDAO userDAO;

    @Resource(name="UserProfileDAOImpl")
    private UserProfileDAO userProfileDAO;

    @Resource(name = "TenantDAOImpl")
    private TenantDAO tenantDAO;

    @Override
    public void newTenant(Tenant tenant) {
        try {
            this.tenantDAO.newMode(tenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateTenant(Tenant tenant) {
        try {
            this.tenantDAO.updateMode(tenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Tenant getTenant(int tenantId) {
        Tenant tenant = null;
        try {
            tenant = (Tenant) tenantDAO.getMode(tenantId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tenant;
    }

    @Override
    public void deleteTenant(int tenantId) {
        try {
            this.tenantDAO.deleteMode(tenantId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Tenant getTenant(String tenantName) {
        try {
            return (Tenant) this.tenantDAO.getModesByProperty("name", tenantName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void activateTenant(Tenant tenant) {
        try {
            this.tenantDAO.activateTenant(tenant);;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void expireTenant(Tenant tenant) {
        try {
            this.tenantDAO.expireTenant(tenant);;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        try {
            return (UserProfile)
                    this.userProfileDAO.getModesByProperty("userId", userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void newUserProfile(UserProfile userProfile) {
        try {
            this.userProfileDAO.newMode(userProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void batchNewUserProfiles(List<UserProfile> modes) {
        try {
            this.userProfileDAO.batchNewModes(modes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        try {
            this.userProfileDAO.updateMode(userProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void batchUpdateUserProfiles(List<UserProfile> modes) {
        try {
            this.userProfileDAO.batchUpdateModes(modes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUserProfile(UserProfile userProfile) {
        try {
            this.userProfileDAO.deleteMode(userProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
