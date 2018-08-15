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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String name;
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





}
