
package com.igomall.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity - 会员扩张属性
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity
public class MemberExtra extends BaseEntity<Long> {

	private static final long serialVersionUID = 1671868028356504967L;
	
	/**
	 * 会员
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false)
	private Member member;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MemberExtra parent;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	private Set<MemberExtra> children = new HashSet<>();
	
	private String treePath;
	
	private Integer grade;
	
	/**
	 * 余额0
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance0;
	
	/**
	 * 余额1
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance1;
	
	/**
	 * 余额2
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance2;
	
	/**
	 * 余额3
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance3;
	
	/**
	 * 余额4
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance4;
	
	/**
	 * 余额5
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance5;
	
	/**
	 * 积分0
	 */
	@Column(nullable = false)
	private Long point0;
	
	/**
	 * 积分1
	 */
	@Column(nullable = false)
	private Long point1;
	
	/**
	 * 积分2
	 */
	@Column(nullable = false)
	private Long point2;
	
	/**
	 * 积分3
	 */
	@Column(nullable = false)
	private Long point3;
	
	/**
	 * 积分4
	 */
	@Column(nullable = false)
	private Long point4;
	
	/**
	 * 积分5
	 */
	@Column(nullable = false)
	private Long point5;
	

	public MemberExtra() {
		setPoint0(0L);
		setPoint1(0L);
		setPoint2(0L);
		setPoint3(0L);
		setPoint4(0L);
		setPoint5(0L);
		setBalance0(BigDecimal.ZERO);
		setBalance1(BigDecimal.ZERO);
		setBalance2(BigDecimal.ZERO);
		setBalance3(BigDecimal.ZERO);
		setBalance4(BigDecimal.ZERO);
		setBalance5(BigDecimal.ZERO);
		setParent(null);
		setChildren(new HashSet<>());
		setTreePath(",");
		setGrade(0);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BigDecimal getBalance0() {
		return balance0;
	}

	public void setBalance0(BigDecimal balance0) {
		this.balance0 = balance0;
	}

	public BigDecimal getBalance1() {
		return balance1;
	}

	public void setBalance1(BigDecimal balance1) {
		this.balance1 = balance1;
	}

	public BigDecimal getBalance2() {
		return balance2;
	}

	public void setBalance2(BigDecimal balance2) {
		this.balance2 = balance2;
	}

	public BigDecimal getBalance3() {
		return balance3;
	}

	public void setBalance3(BigDecimal balance3) {
		this.balance3 = balance3;
	}

	public BigDecimal getBalance4() {
		return balance4;
	}

	public void setBalance4(BigDecimal balance4) {
		this.balance4 = balance4;
	}

	public BigDecimal getBalance5() {
		return balance5;
	}

	public void setBalance5(BigDecimal balance5) {
		this.balance5 = balance5;
	}

	public Long getPoint0() {
		return point0;
	}

	public void setPoint0(Long point0) {
		this.point0 = point0;
	}

	public Long getPoint1() {
		return point1;
	}

	public void setPoint1(Long point1) {
		this.point1 = point1;
	}

	public Long getPoint2() {
		return point2;
	}

	public void setPoint2(Long point2) {
		this.point2 = point2;
	}

	public Long getPoint3() {
		return point3;
	}

	public void setPoint3(Long point3) {
		this.point3 = point3;
	}

	public Long getPoint4() {
		return point4;
	}

	public void setPoint4(Long point4) {
		this.point4 = point4;
	}

	public Long getPoint5() {
		return point5;
	}

	public void setPoint5(Long point5) {
		this.point5 = point5;
	}

	public MemberExtra getParent() {
		return parent;
	}

	public void setParent(MemberExtra parent) {
		this.parent = parent;
	}

	public Set<MemberExtra> getChildren() {
		return children;
	}

	public void setChildren(Set<MemberExtra> children) {
		this.children = children;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
}