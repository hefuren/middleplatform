package com.bluesky.middleplatform.commons.hierarchy;

import java.io.Serializable;

import lombok.Data;

@Data
public class TreeNode implements Hierarchyable, Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片 imgcss
     */
    private String imgcss;

    /**
     * 链接
     */
    private String url;

    /**
     * js事件函数
     */
    private String function;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 序号
     */
    private Integer seqno;

    /**
     * TreeNode 实际对象
     */
    private Object actualObject;
}
