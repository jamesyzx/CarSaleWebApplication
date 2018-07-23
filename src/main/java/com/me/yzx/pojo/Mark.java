package com.me.yzx.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mark")
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "markid", unique = true, nullable = false)
	int markid;
	@Column(name = "mark")
	String mark;
	@OneToMany(mappedBy = "mark")
	Set<Model> marks;

	public Mark() {

	}

	public Mark(String mark) {
		this.mark = mark;
	}

	public int getMarkid() {
		return markid;
	}

	public void setMarkid(int markid) {
		this.markid = markid;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		for (Model m : marks) {
			bf.append("{\"model\":\"" + m.model + "\"}");
		}
		String ms = bf.toString().replaceAll("\\}\\{", "\\}\\,\\{");
		return "{\"mark\":\"" + mark + "\",\"model\":[" + ms + "]}";
	}

}
