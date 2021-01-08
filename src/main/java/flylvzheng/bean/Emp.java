package flylvzheng.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.JoinType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * @author LvZheng 创建时间：2018年1月19日 下午3:32:06 项目名称：demo-1
 * @version 1.0
 * @since JDK 1.8 文件名称：cccc.java
 */
@Data
@Entity
public class Emp {
    /**
     * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO. 
     * TABLE：使用一个特定的数据库表格来保存主键。 
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。 
     * IDENTITY：主键由数据库自动生成（主要是自动增长型） 
     * AUTO：主键由程序控制。 
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    private String name;
    private BigDecimal money;
    private String sect;
    private String effort;
    private String age;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date date1;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date date2;
    // private int gid;


//	CascadeType.PERSIST：级联新增（又称级联保存）：对order对象保存时也对items里的对象也会保存。对应EntityManager的presist方法。
//
//	例子：只有A类新增时，会级联B对象新增。若B对象在数据库存（跟新）在则抛异常（让B变为持久态）
//
//	CascadeType.MERGE：级联合并（级联更新）：若items属性修改了那么order对象保存时同时修改items里的对象。对应EntityManager的merge方法 。
//	例子：指A类新增或者变化，会级联B对象（新增或者变化）
//
//	CascadeType.REMOVE：级联删除：对order对象删除也对items里的对象也会删除。对应EntityManager的remove方法。
//
//	例子：REMOVE只有A类删除时，会级联删除B类；
//
//	CascadeType.REFRESH：级联刷新：获取order对象里也同时也重新获取最新的items时的对象。对应EntityManager的refresh(object)方法有效。即会重新查询数据库里的最新数据。
//
//	CascadeType.ALL：以上四种都是。

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
//	@JoinColumn(name = "ttt")
//	@JsonBackReference
//	private User user;

/**
 *
 *
 * jpa 多对一 模糊查询
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", referencedColumnName = "accNbr", insertable = false, updatable = false)
    private BaseAccounts baseAccounts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BizMoneyRecords> bizMoneyRecordsList;

    if(StringUtils.isNotBlank(bankmoneyrecordsForm.getSoName()))
    {
        Join<BizMoneyRecords, BaseAccounts> join = root.join("baseAccounts", JoinType.LEFT);
        list.add(criteriaBuilder.equal(join.<String>get("soName"), bankmoneyrecordsForm.getSoName()));
    }
*/

    public Emp(String name, String sect, String effort, Date date1, Date date2) {
        this.name = name;
        this.sect = sect;
        this.effort = effort;
        this.date1 = date1;
        this.date2 = date2;
    }

    public Emp() {
    }
}
