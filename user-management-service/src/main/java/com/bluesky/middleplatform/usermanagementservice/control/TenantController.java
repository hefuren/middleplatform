package com.bluesky.middleplatform.usermanagementservice.control;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bluesky.middleplatform.commons.component.utils.ComponentFactory;
import com.bluesky.middleplatform.commons.db.SequenceUtils;
import com.bluesky.middleplatform.commons.utils.CalendarUtils;
import com.bluesky.middleplatform.commons.utils.TypeUtils;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;
import com.bluesky.middleplatform.usermanagementservice.service.ProfileManager;
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
     * @param tenantJSON
     * @return
     */
    public String openTenantAccount(String tenantJSON){
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        Tenant mode = JSON.parseObject(tenantJSON, Tenant.class);
        //设置公司ID
        Long seqID = SequenceUtils.getSequence("userManagerService_tenant");
        mode.setId(TypeUtils.nullToInt(seqID));
        //        mode.setCreateBy(user.getId());
        mode.setCreateTime(CalendarUtils.getCurrentDate());
//        mode.setLastUpdateBy(user.getId());
        mode.setLastUpdateTime(CalendarUtils.getCurrentDate());

        manager.newTenant(mode);
        tenantJSON = JSONObject.toJSONString(mode);
        return tenantJSON;

    }

}
