package com.lw.cubic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;

import com.lw.cubic.part.Block;
import com.lw.cubic.part.Layer;
import com.lw.cubic.part.Surface;
import com.lw.cubic.part.ThreeOrderLayer;

/**
 * 三阶魔方
 * 
 * @author Wayne
 *
 */
public class ThreeOrderCubic extends Cubic {

	@Override
	public void print() {
		for (int i = 0; i < Cubic.DIRS.length; i++) {
			System.out.print("这是" + Cubic.DIRS[i].getAxisCode() + "面 \r\n");
			getLayer(Cubic.DIRS[i].getAxisCode()).print();
		}
	}

	@Override
	protected void assembleAxis() {
		this.axisBlocks.clear();
		// 6个轴块
		for (int i = 0; i < Cubic.DIRS.length; i++) {
			Vector3D dir = Cubic.DIRS[i];
			this.axisBlocks.put(dir, (makeAxisBlock(dir, COLORS[i])));
		}
	}

	private Block makeAxisBlock(Vector3D v3d, String color) {
		Block b = new Block();
		List<Surface> s = new ArrayList<>();
		s.add(new Surface(color, v3d.getAxisCode()));
		b.setSurfaces(s);
		return b;
	}

	@Override
	protected void assembleCorner() {
		// 8个角块
		List<Vector3D> v3ds = new ArrayList<>();
		v3ds.add(new Vector3D(1, 1, 1));
		v3ds.add(new Vector3D(1, 1, -1));
		v3ds.add(new Vector3D(1, -1, 1));
		v3ds.add(new Vector3D(1, -1, -1));

		v3ds.add(new Vector3D(-1, 1, 1));
		v3ds.add(new Vector3D(-1, 1, -1));
		v3ds.add(new Vector3D(-1, -1, 1));
		v3ds.add(new Vector3D(-1, -1, -1));

		v3ds.forEach(c -> {
			Block b = new Block();
			setSurfaces(c, b);
			this.cornerBlocks.put(c, b);
		});

	}

	private void setSurfaces(Vector3D v3d, Block block) {
		// 添加面
		List<Surface> surfaces = new ArrayList<>();
		if (v3d.getX() != 0) {
			surfaces.add(this.axisBlocks.get(new Vector3D(v3d.getX(), 0, 0)).getSurfaces().get(0));
		}
		if (v3d.getY() != 0) {
			surfaces.add(this.axisBlocks.get(new Vector3D(0, v3d.getY(), 0)).getSurfaces().get(0));
		}
		if (v3d.getZ() != 0) {
			surfaces.add(this.axisBlocks.get(new Vector3D(0, 0, v3d.getZ())).getSurfaces().get(0));
		}
		block.setSurfaces(surfaces);
	}

	@Override
	protected void assembleEdge() {
		// 12个棱块
		List<Vector3D> v3ds = new ArrayList<>();
		// y==1
		v3ds.add(new Vector3D(1, 1, 0));
		v3ds.add(new Vector3D(0, 1, 1));
		v3ds.add(new Vector3D(-1, 1, 0));
		v3ds.add(new Vector3D(0, 1, -1));

		// y==0
		v3ds.add(new Vector3D(1, 0, 1));
		v3ds.add(new Vector3D(1, 0, -1));
		v3ds.add(new Vector3D(-1, 0, 1));
		v3ds.add(new Vector3D(-1, 0, -1));

		// y==-1
		v3ds.add(new Vector3D(1, -1, 0));
		v3ds.add(new Vector3D(0, -1, 1));
		v3ds.add(new Vector3D(-1, -1, 0));
		v3ds.add(new Vector3D(0, -1, -1));

		v3ds.forEach(c -> {
			Block b = new Block();
			setSurfaces(c, b);
			this.edgeBlocks.put(c, b);
		});
	}

	@Override
	public Layer getLayer(String dir) {
			//每面1个轴块，4个角块，4个棱块
			Layer layer = new ThreeOrderLayer(dir);
			
			Predicate<Entry<Vector3D, Block>> p = new Predicate<Entry<Vector3D,Block>>() {

				@Override
				public boolean test(Entry<Vector3D, Block> f) {
					if(dir.equals("x")){
						if(f.getKey().getX() > 0){
							return true;
						}
					}else if(dir.equals("-x")){
						if(f.getKey().getX() < 0){
							return true;
						}
					}else if(dir.equals("y")){
						if(f.getKey().getY() > 0){
							return true;
						}
					}else if(dir.equals("-y")){
						if(f.getKey().getY() < 0){
							return true;
						}
					}else if(dir.equals("z")){
						if(f.getKey().getZ() > 0){
							return true;
						}
					}else if(dir.equals("-z")){
						if(f.getKey().getZ() < 0){
							return true;
						}
					}
					return false;
				}
				
			};
			
			
			axisBlocks.entrySet().stream().filter(f -> f.getKey().getAxisCode().equals(dir))
					.forEach(c -> {
						layer.addBlocks(c.getKey(), c.getValue());
						return;
					});
			cornerBlocks.entrySet().stream().filter(p).forEach(c -> {
				layer.addBlocks(c.getKey(), c.getValue());
			});
			
			edgeBlocks.entrySet().stream().filter(p).forEach(c -> {
				layer.addBlocks(c.getKey(), c.getValue());
			});
			
			return layer;
	}

	@Override
	public void recover(String way, String direction) {
		// TODO 没有做，应该是有公式的，有待实现
	}

}
