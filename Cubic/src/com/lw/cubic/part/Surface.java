package com.lw.cubic.part;

import java.util.HashMap;
import java.util.Map;

public class Surface {
	private static Map<String,Map<String,String[]>> turnDir = new HashMap<>();
	
	static{
		//x轴，顺时针 z -> -y ; 逆时针 z -> y .....
		Map<String,String[]> m = new HashMap<>();
		m.put("z", new String[]{"-y","y"});
		m.put("-z", new String[]{"y","-y"});
		m.put("y", new String[]{"z","-z"});
		m.put("-y", new String[]{"-z","z"});
		turnDir.put("x", m);
		
		Map<String,String[]> m1 = new HashMap<>();
		m1.put("z", new String[]{"x","-x"});
		m1.put("-z", new String[]{"-x","x"});
		m1.put("x", new String[]{"-z","z"});
		m1.put("-x", new String[]{"z","-z"});
		turnDir.put("y", m1);
		
		Map<String,String[]> m2 = new HashMap<>();
		m2.put("y", new String[]{"-x","x"});
		m2.put("-y", new String[]{"x","-x"});
		m2.put("x", new String[]{"y","-y"});
		m2.put("-x", new String[]{"-y","y"});
		turnDir.put("z", m2);
	}
	
	private String colour;
	private String direction;
	
	public Surface(String colour, String direction) {
		this.colour = colour;
		this.direction = direction;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void print(){
		System.out.print("["+colour+"]");
	}
	
	public Surface turn90(String way,String dir){
		int index = 0;
		String dirKey = dir;
		if(dir.startsWith("-")){
			if(way.equals("CLW")){
				index = 1;
			}
			dirKey = dirKey.substring(1);
		}else{
			if(way.equals("CCW")){
				index = 1;
			}
		}
		String newdir = direction;
		if(!direction.equals(dir)){
			newdir = turnDir.get(dirKey).get(direction)[index];
		}
		return new Surface(colour, newdir);
	}
	
	

}
