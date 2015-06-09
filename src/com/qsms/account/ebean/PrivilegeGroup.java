package com.qsms.account.ebean;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_privilegegroup")
public class PrivilegeGroup {
	@Id
	@Column
	private String id;

	@Column(unique = true)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_pri_group", joinColumns = @JoinColumn(name = "g_id"), inverseJoinColumns = @JoinColumn(name = "p_id"))
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();

	@ManyToMany(mappedBy = "groups", cascade = CascadeType.REFRESH)
	private Set<Account> accounts = new LinkedHashSet<Account>();

	/**
	 * ���Ϊtrue��Ȩ�ޱ�ʾΪϵͳ��ʼȨ�ޣ����ܱ�ɾ��
	 */
	private Boolean initSign = false;

	// ��Ȩ�������Ȩ��
	public void addPrivilege(SystemPrivilege p) {
		this.privileges.add(p);
	}

	public Boolean getInitSign() {
		return initSign;
	}

	public void setInitSign(Boolean initSign) {
		this.initSign = initSign;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
