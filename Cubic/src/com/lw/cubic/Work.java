package com.lw.cubic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Work {

	private static Scanner s;
	private static List<String> comms1 = new ArrayList<>();
	private static List<String> comms2 = new ArrayList<>();
	private static List<String> comms3 = new ArrayList<>();
	
	static{
		comms1.add("CLW"); //顺时针旋转
		comms1.add("CCW"); //逆时针旋转
		comms1.add("RT");//还原
		comms1.add("READ");//读取
		
		comms2.add("x");
		comms2.add("-x");
		comms2.add("y");
		comms2.add("-y");
		comms2.add("z");
		comms2.add("-z");
		
		comms3.add("SAVE");//保存
	}

	public static void main(String[] args) {

		Cubic c = new ThreeOrderCubic();
		c.assemble();
		c.print();
		s = new Scanner(System.in);
		while (s.hasNextLine()) {
			String word = s.nextLine();
			String[] coms = word.split("\\|");
			if(coms.length == 2 && comms1.contains(coms[0]) && comms2.contains(coms[1])){
				if(coms[0].equals("RT")){
					c.recover(coms[0], coms[1]);
					c.print();
				}else if(coms[0].equals("CLW") ||coms[0].equals("CCW")){
					c.turn(coms[0], coms[1]);
					c.print();
				}
			}else if(coms.length == 2 && coms[0].equals("READ")){
				Gson g = new Gson();
				c = g.fromJson(coms[1], new TypeToken<ThreeOrderCubic>(){}.getType());
				System.out.println("读取成功");
			}else if(coms.length == 1 && comms3.contains(coms[0])){
				if(coms[0].equals("SAVE")){
					c.save();
				}
			}else{
				System.out.println("指令错误");
			}
		}
		
	}

}
