package com.husrev.satellites;

public class Satellite {
		
	private String name;
	private double x;
	private double y;
	private double z;
	private double velocity;
	private String time;
	
	
	public Satellite() {}
	
	
	public Satellite(String name, double x, double y, double z, double velocity, String time) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.velocity = velocity;
		this.time = time;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
}
