package com.util.db.entity;

/**
 * 部门
 * 
 * @author wulang
 *
 */
public class Department {
	/**
	 * 部门名字
	 */
	private String deptName;
	/**
	 * 部门id
	 */
	private Integer deptId;
	/**
	 * 部门所在位置ID
	 */
	private Location location;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Location getLocation() {
		System.out.println("who"+Thread.currentThread());
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", deptId=" + deptId +"]";
	}

}
