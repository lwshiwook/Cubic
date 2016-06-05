package com.lw.cubic;

public class Vector3D {
	
	private int x;
	private int y;
	private int z;
	
	private String axisCode = "notAxis";
	
	public String getAxisCode() {
		return axisCode;
	}

	public Vector3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		if(x == 0 && y == 0){
			axisCode = z > 0?"z":"-z";
		}else if(x == 0 && z == 0){
			axisCode = y > 0?"y":"-y";
		}else if(y == 0 && z == 0){
			axisCode = x > 0?"x":"-x";
		}
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3D other = (Vector3D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public Vector3D turn90(String way,String axis) {
		if(way.equals("CLW")){
			if(axis.equals("x")){
				return new Vector3D(x,-z,y);
			}else if(axis.equals("-x")){
				return new Vector3D(x,z,-y);
			}else if(axis.equals("y")){
				return new Vector3D(z,y,-x);
			}else if(axis.equals("-y")){
				return new Vector3D(-z,y,x);
			}else if(axis.equals("z")){
				return new Vector3D(y,-x,z);
			}else if(axis.equals("-z")){
				return new Vector3D(-y,x,z);
			}
		}else if(way.equals("CCW")){
			if(axis.equals("x")){
				return new Vector3D(x,z,-y);
			}else if(axis.equals("-x")){
				return new Vector3D(x,-z,y);
			}else if(axis.equals("y")){
				return new Vector3D(-z,y,x);
			}else if(axis.equals("-y")){
				return new Vector3D(z,y,-x);
			}else if(axis.equals("z")){
				return new Vector3D(-y,x,z);
			}else if(axis.equals("-z")){
				return new Vector3D(y,-x,z);
			}
		}
		return this;
	}
//
//	@Override
//	public String toString() {
//		return "["+x+","+y+","+z+"]";
//	}

	
	
	
	
	
	
	

}
