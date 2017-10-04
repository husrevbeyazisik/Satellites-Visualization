package com.husrev.satellites;

import java.util.ArrayList;
import java.util.List;

import scala.Serializable;

public class Positions implements Serializable {
	private String name;
	private List<PVT> pvt;

	
	
	public Positions(String name) {
		super();
		this.name = name;
		pvt = new ArrayList<PVT>();
	}
	
	
	public Positions() {
		pvt = new ArrayList<PVT>();
	}
	
	
	public void addPvt(double x,double y,double z,double velocity,String time)
	{
		this.pvt.add(new PVT(x,y,z,velocity,time));
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<PVT> getPvt() {
		return pvt;
	}


	public void setPvt(List<PVT> pvt) {
		this.pvt = pvt;
	}
	
	
	
	class PVT implements Serializable {

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
	
	


	
	
	
	
	
}
