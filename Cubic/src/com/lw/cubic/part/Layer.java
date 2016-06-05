package com.lw.cubic.part;

import java.util.HashMap;
import java.util.Map;

import com.lw.cubic.Vector3D;

public abstract class Layer {

	protected Map<Vector3D, Block> blocks = new HashMap<>();
	protected String direction;

	public Layer(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setBlocks(Map<Vector3D, Block> blocks) {
		this.blocks = blocks;
	}

	public Map<Vector3D, Block> getBlocks() {
		return blocks;
	}

	public void addBlocks(Vector3D v3d , Block block) {
		blocks.put(v3d, block);
	}

	public abstract void print();
	
	/**
	 * CLW 顺时针
	 * CCW 逆时针
	 * @param way
	 */
	public abstract void turn(String way);

}
