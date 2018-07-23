package com.me.yzx.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Table;

@Entity
@Table(name = "usedcar")
public class Usedcar extends Car {

	@Column(name = "usedcarid", unique = true, nullable = false)
	int usedcarid;
	@Column(name = "miles")
	int miles;
	@Column(name = "year")
	int year;

	public Usedcar() {

	}

	public Usedcar(int carid, int id, int year, int miles) {
		this.carid = carid;
		this.usedcarid = id;
		this.year = year;
		this.miles = miles;

	}

	public int getUsedcarid() {
		return usedcarid;
	}

	public void setUsedcarid(int usedcarid) {
		this.usedcarid = usedcarid;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
