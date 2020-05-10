package com.bluesky.middleplatform.usermanagementservice.control;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bluesky.middleplatform.commons.component.utils.ComponentFactory;
import com.bluesky.middleplatform.commons.db.SequenceUtils;
import com.bluesky.middleplatform.commons.utils.CalendarUtils;
import com.bluesky.middleplatform.commons.utils.TypeUtils;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;
import com.bluesky.middleplatform.usermanagementservice.service.ProfileManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户控制器
 * RESTful 接口实现
 *
 * @author ElwinHe
 */

@RestController(value = "TenantController")
@RequestMapping(value = "/TenantAction.html")
public class TenantController {

    /**
     * 租户/公司开户
     * 使用场景：系统管理员根据租户/公司信息开户,开户状态为激活；
     * @param tenantJSON 租户/公司基本信息JSON字符串
     * @return 租户信息JSON字符
     */
    @PostMapping(value = "/openTenantAccount")
    public String openTenantAccount(String tenantJSON){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        Tenant mode = JSON.parseObject(tenantJSON, Tenant.class);
        //设置公司ID
        Long seqID = SequenceUtils.getSequence("userManagerService_tenant");
        mode.setId(TypeUtils.nullToInt(seqID));

        //系统管理员开户，默认租户/公司状态为激活
        mode.setStatus(Tenant.STATUS_ACTIVATED);
        //        mode.setCreateBy(user.getId());
        mode.setCreateTime(CalendarUtils.getCurrentDate());
//        mode.setLastUpdateBy(user.getId());
        mode.setLastUpdateTime(CalendarUtils.getCurrentDate());

        manager.newTenant(mode);
        tenantJSON = JSONObject.toJSONString(mode);
        return tenantJSON;

    }

    /**
     * 注册租户/公司
     * 附注：通过网上注册的租户/公司，默认状态为“不可用”，待管理员激活。
     * @param tenantJSON 租户信息JSON字符串
     * @return reslut 注册成功/失败
     */
    @PostMapping(value = "/registerTenant")
    public boolean registerTenant(String tenantJSON){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        Tenant mode = JSON.parseObject(tenantJSON, Tenant.class);
        //设置公司ID
        Long seqID = SequenceUtils.getSequence("userManagerService_tenant");
        mode.setId(TypeUtils.nullToInt(seqID));

        //网上注册的租户/公司，默认租户/公司状态为不可用，待审批；
        mode.setStatus(Tenant.STATUS_UNACTIVATED);
        //        mode.setCreateBy(user.getId());
        mode.setCreateTime(CalendarUtils.getCurrentDate());
//        mode.setLastUpdateBy(user.getId());
        mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
        manager.newTenant(mode);

        mode = manager.getTenant(TypeUtils.nullToInt(seqID));
        if(mode != null){
            //注册成功；
            return true;
        }else{
            //注册失败
            return false;
        }

    }

    /**
     * 激活租户/公司账户
     * @param tenantId 租户/公司ID
     * @return 成功 or 失败
     */
    @PostMapping(value = "/registerTenant")
    public boolean activateTenant(int tenantId){
        boolean result = false;
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        Tenant mode = manager.getTenant(tenantId);
        if(mode != null){
//            mode.setLastUpdateBy();
            mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
            manager.activateTenant(mode);
            result = true;
        }

        return result;
    }

    /**
     * 将租户/公司失效
     * @param tenantId 租户/公司ID
     * @return 成功 or 失败
     */
    @PostMapping(value = "/registerTenant")
    public boolean expireTenant(int tenantId){
        boolean result = false;
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        Tenant mode = manager.getTenant(tenantId);
        if(mode != null){
//            mode.setLastUpdateBy();
            mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
            manager.expireTenant(mode);
            result = true;
        }

        return result;
    }



}
