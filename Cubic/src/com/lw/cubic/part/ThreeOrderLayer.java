package com.lw.cubic.part;

import java.util.HashMap;
import java.util.Map;

import com.lw.cubic.Vector3D;

public class ThreeOrderLayer extends Layer {

	public ThreeOrderLayer(String direction) {
		super(direction);
	}

	@Override
	public void print() {
		int[] ii = new int[]{1,0,-1}; //从正到负
		int[] mii = new int[]{-1,0,1}; //从负到正
		//9个面打出来
		if(direction.equals("x")){
			for(int i:ii){
				for(int j:mii){
					blocks.get(new Vector3D(1,i,j)).print(direction);
				}
				System.out.print("\r\n");
			}
		}else if(direction.equals("-x")){
			for(int i:ii){
				for(int j:ii){
					blocks.get(new Vector3D(-1,i,j)).print(direction);
				}
				System.out.print("\r\n");
			}
		}else if(direction.equals("y")){
			for(int i:ii){
				for(int j:mii){
					blocks.get(new Vector3D(j,1,i)).print(direction);
				}
				System.out.print("\r\n");
			}
		}else if(direction.equals("-y")){
			for(int i:mii){
				for(int j:mii){
					blocks.get(new Vector3D(j,-1,i)).print(direction);
				}
				System.out.print("\r\n");
			}
		}else if(direction.equals("z")){
			for(int i:mii){
				for(int j:mii){
					blocks.get(new Vector3D(j,i,1)).print(direction);
				}
				System.out.print("\r\n");
			}
		}else if(direction.equals("-z")){
			for(int i:ii){
				for(int j:mii){
					blocks.get(new Vector3D(j,i,-1)).print(direction);
				}
				System.out.print("\r\n");
			}
		}
	}

	@Override
	public void turn(String way) {
		Map<Vector3D,Block> newblocks = new HashMap<>();
		this.blocks.entrySet().forEach(c -> {
			Block b = c.getValue();
			Vector3D tb = c.getKey();
			Vector3D ta = tb.turn90(way, direction);
//			System.err.println("坐标转换  "+tb + " "+ta);
//			System.err.println("block转换1  ["+way + "] ["+b+"] ");
			Block newB = b.turn90(way,direction);
//			System.err.println("block转换2  ["+way + "] ["+b+"]");
			
			newblocks.put(ta ,newB);
		});
		setBlocks(newblocks);
	}

}
