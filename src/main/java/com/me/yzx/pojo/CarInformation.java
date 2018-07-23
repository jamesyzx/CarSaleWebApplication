package com.me.yzx.pojo;

public class CarInformation {
	
	private int carid;
	private String model;
	private String mark;
	
    private double liter;
    private String color;
    private String bodytype;
    private int price;
    
    
    public CarInformation() {
    	
    }
    
    
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public double getLiter() {
		return liter;
	}
	public void setLiter(double liter) {
		this.liter = liter;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBodytype() {
		return bodytype;
	}
	public void setBodytype(String bodytype) {
		this.bodytype = bodytype;
	}
    
    
}
