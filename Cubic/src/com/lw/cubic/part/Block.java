package com.lw.cubic.part;

import java.util.ArrayList;
import java.util.List;

public class Block {
	
	private List<Surface> surfaces;
	
	public List<Surface> getSurfaces() {
		return surfaces;
	}
	public void setSurfaces(List<Surface> surfaces) {
		this.surfaces = surfaces;
	}
	
	public void print(String direction){
		surfaces.forEach(c -> {
			if(c.getDirection().equals(direction)){
				c.print();
				return;
			}
		});
	}
	
	public Block turn90(String way, String direction) {
		Block b = new Block();
		List<Surface> sList = new ArrayList<>();
		surfaces.forEach(c -> sList.add(c.turn90(way, direction)));
		b.setSurfaces(sList);
		return b;
	}
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		surfaces.forEach(c -> sb.append(c.getDirection()+"面："+c.getColour()+"|"));
//		return sb.toString();
//	}
	
	

}
