package com.husrev.satellites;

import scala.Serializable;

public class PVT implements Serializable {

	private double x;
	private double y;
	private double z;
	
	private double velocity;
	private String time;
	
	
	
	public PVT(double x, double y, double z, double velocity, String time) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.velocity = velocity;
		this.time = time;
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
