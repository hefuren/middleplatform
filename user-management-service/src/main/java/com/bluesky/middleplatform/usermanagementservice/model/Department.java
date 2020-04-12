package com.bluesky.middleplatform.usermanagementservice.model;

import com.bluesky.middleplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.middleplatform.commons.hierarchy.SerializableComparator;
import com.bluesky.middleplatform.commons.object.BaseObject;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ElwinHe
 */

@Data
@Component(value = "Department")
@Scope(value = "prototype")
@Entity
@Table(name = "st_department", schema = "public")
public class Department extends BaseObject implements Hierarchyable, Serializable {

    /**
     * 部门根节点 （公司节点）
     */
    public static final int ROOT_NODE = 100;

    // Fields
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "parentid")
    private Integer parentId;

    @Column(name = "level")
    private Integer level;

    @OrderBy
    @Column(name = "seqno")
    private Integer seqno;

    @Column(name = "tenantId", nullable = false)
    private Integer tenantId;

    /**
     * 部门经理IDs
     * （如果一个部门有多个部门经理时，显示：1000,1001,...）
     */
    @Column(name = "managerids", length = 200)
    private String managerids;

    @Column(name = "sourcetype")
    private Integer sourcetype;

    @Column(name = "sourcename", length = 50)
    private String sourcename;

    @Column(name = "sourceid")
    private Integer sourceid;

    // Constructors

    /**
     * default constructor
     */
    public Department() {
    }

    /**
     * minimal constructor
     */
    public Department(Integer id, Integer tenantId) {
        this.id = id;
        this.tenantId = tenantId;
    }

    /**
     * full constructor
     */
    public Department(Integer id, String name, String description,
                      Integer parentId, Integer level, Integer seqno, Integer tenantId,
                      String managerids, Integer sourcetype, String sourcename,
                      Integer sourceid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.level = level;
        this.seqno = seqno;
        this.tenantId = tenantId;
        this.managerids = managerids;
        this.sourcetype = sourcetype;
        this.sourcename = sourcename;
        this.sourceid = sourceid;
    }

    public static SerializableComparator SeqComparator = new SerializableComparator() {
        private static final long serialVersionUID = 1163795653927990202L;

        @Override
        public int compare(Object obj1, Object obj2) {
            Department dept1 = (Department) obj1;
            Department dept2 = (Department) obj2;
            return dept1.getSeqno() - dept2.getSeqno();
        }
    };

}
