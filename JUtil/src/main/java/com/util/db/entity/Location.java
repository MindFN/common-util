package com.util.db.entity;

import java.util.Set;

import org.hibernate.collection.PersistentSet;

/**
 * 工作地点
 * 
 * @author wulang
 *
 */

public class Location {
	/**
	 * 地点编号
	 */
	private Integer locationId;
	/**
	 * 详细地址
	 */
	private String locationDetail;
	/**
	 * 
	 */
	private PersistentSet depts;

	public String getLocationDetail() {
		System.out.println("get locationDetail");
		return locationDetail;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public Set<Department> getDepts() {
		System.out.println("----------");
		return depts;
	}

	public void setDepts(PersistentSet depts) {
		this.depts = depts;
	}

	@Override
	public String toString() {
		System.out.println("hehehe");
		return "Location [locationId=" + locationId + ", locationDetail=" + locationDetail + "]";
	}

}
