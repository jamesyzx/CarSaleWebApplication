package com.me.yzx.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modelid",unique = true, nullable = false)
	int modelid;
	@Column(name = "model")
	String model;
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER) 
    @JoinColumn(name="markid")  
	Mark mark;
	
    
    public Model() {
    	
    }
	public Model(String model, Mark mark) {
		this.model=model;
		this.mark=mark;
	}


	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
}
