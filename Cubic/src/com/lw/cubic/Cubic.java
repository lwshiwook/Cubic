package com.lw.cubic;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lw.cubic.part.Block;
import com.lw.cubic.part.Layer;

/**
 * 魔方抽象类
 * 
 * @author Wayne
 *
 */
public abstract class Cubic {

	public final static Vector3D[] DIRS = new Vector3D[] { new Vector3D(1, 0, 0), new Vector3D(-1, 0, 0),
			new Vector3D(0, 1, 0), new Vector3D(0, -1, 0), new Vector3D(0, 0, 1), new Vector3D(0, 0, -1) }; // 6个面

	public final static String[] COLORS = new String[] { "红", "橙", "黄", "绿", "蓝", "紫" }; // 6个色

	protected Map<Vector3D,Block> axisBlocks = new HashMap<>(); // 轴块
	protected Map<Vector3D,Block> cornerBlocks = new HashMap<>(); // 角块
	protected Map<Vector3D,Block> edgeBlocks = new HashMap<>(); // 棱块

	public void assemble() {
		// 轴块
		assembleAxis();
		// 角块
		assembleCorner();
		// 棱块
		assembleEdge();
	}

	protected abstract void assembleCorner();

	protected abstract void assembleAxis();

	protected abstract void assembleEdge();

	/**
	 * 输出方法
	 */
	public abstract void print();
	
	public abstract Layer getLayer(String direction);
	
	public void turn(String way, String direction){
		Layer l = getLayer(direction);
		l.turn(way);
		l.getBlocks().entrySet().forEach(c -> {
			if(axisBlocks.containsKey(c.getKey())){
				axisBlocks.put(c.getKey(), c.getValue());
			}
			if(cornerBlocks.containsKey(c.getKey())){
				cornerBlocks.put(c.getKey(), c.getValue());
			}
			if(edgeBlocks.containsKey(c.getKey())){
				edgeBlocks.put(c.getKey(), c.getValue());
			}
		});
	}

	public abstract void recover(String way, String direction);
	
	public void save(){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();
		 System.out.println(gson.toJson(this));
	}
	

}
