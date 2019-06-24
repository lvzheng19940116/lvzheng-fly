package flylvzheng.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private int eid;
	private String name;
	private String sect;
	private String effort;
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date date1;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date date2;
	// private int gid;

	// @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=false)
	// @JoinColumn(name="order_id")
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
//	@JoinColumn(name = "ttt")
//	@JsonBackReference
//	private User user;


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
