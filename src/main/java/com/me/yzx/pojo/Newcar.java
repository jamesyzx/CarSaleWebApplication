package com.me.yzx.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "newcar")
public class Newcar extends Car {

	@Column(name = "newcarid", unique = true, nullable = false)
	int newcarid;

	public Newcar() {

	}

	public Newcar(int carid, int id) {
		this.carid = carid;
		this.newcarid = id;
	}

	public int getNewcarid() {
		return newcarid;
	}

	public void setNewcarid(int newcarid) {
		this.newcarid = newcarid;
	}

	@Override
	public String toString() {
		return "Newcar []";
	}

}
