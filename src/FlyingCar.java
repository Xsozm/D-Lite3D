package net.tofweb.starlite;

import java.util.ArrayList;
import java.util.HashMap;

public class FlyingCar extends Thread{
	


	private int count = 0;
	private int fuel ; 
	int s_x,s_y,s_z,g_x,g_y,g_z ;
	private int maxZ = 100; 
	private int minZ = -100; 
	private CellSpace space = new CellSpace() ;
	private CostBlockManager blockManager = new CostBlockManager(space); 
	private Pathfinder pathfinder = new Pathfinder(blockManager) ; 
	public static ArrayList<FlyingCar> Cars = new ArrayList<FlyingCar>();

	
	
	@Override
	public void run() {
		try {
			fly();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FlyingCar(int s_x,int s_y,int s_z,int g_x,int g_y,int g_z,int count) {
		this.s_x=s_x;
		this.s_y=s_y;
		this.s_z=s_z;
		this.g_x=g_x;
		this.g_y=g_y;
		this.g_z=g_z;
		this.count=count;
		
		space.setGoalCell(g_x, g_y, g_z);
		space.setStartCell(s_x, s_y, s_z);
		Cars.add(this);
	}
	
	public FlyingCar(int s_x,int s_y,int s_z,int g_x,int g_y,int g_z ,int count,int fuel) {
		this.s_x=s_x;
		this.s_y=s_y;
		this.s_z=s_z;
		this.g_x=g_x;
		this.g_y=g_y;
		this.g_z=g_z;
		this.count=count;
		
		space.setGoalCell(g_x, g_y, g_z);
		space.setStartCell(s_x, s_y, s_z);
		this.fuel= fuel ;
		Cars.add(this);


	}
	
	public void block(int x , int y , int z) {
		blockManager.blockCell(space.makeNewCell(x, y, z));

	}
	
	public void fly() throws InterruptedException {
		pathfinder.setFuel(fuel);
		pathfinder.setId(count);
		Path path = pathfinder.findPath();
//		if(canFaly()) {
//			Path path = pathfinder.findPath();
//			// move on the path
//			TrackPath(path);
//			
//		}else {
//			System.out.println(": Can't Fly");
//		}
	}
	
	private void TrackPath(Path path) throws InterruptedException {
//		if(path==null)
//		{
//	        System.out.println("Trip["+count+"]"+ "Failed :the sky is suddenly over crowded ,or fuel is not enough ");
//	        return;
//
//		}
//		
//		for(Cell cell:path) {
//			Triple t = new Triple(cell.getX(),cell.getY(),cell.getZ()) ;
//			busy.put(t, true);
//			System.out.print("Car ["+count+"] moved to "+cell+"\n");
//			Thread.sleep(1000);
//			busy.put(t, false);
//
//
//		}
//		path.clear();
//		System.out.println("Car ["+count+"]"+" Finished the Trp Succesfully" );
		
	}


	

	public boolean canFaly() throws InterruptedException {
		System.out.println("Checking if you can do the trip now (Stability Check)");
		Thread.sleep(1000);

		boolean validRange = validRange(this.s_z, this.g_z);
		if(!validRange) {
			System.out.print("You can't Travel to This Range ");
			return false;
		}
		// test path 
		Path path = pathfinder.findPath();
				if(path==null) {
			        System.out.print("You Can't do your trip now , the sky is over crowded ,or fuel is not enough try to fly later ");
					return false;
				}
		return path.isComplete();		
		
	}
	
	public void updateGoal(int x,int y ,int z) {
		space.setGoalCell(x, y, z);
	}
	
	public void updateStart(int x,int y ,int z) {
		space.setStartCell(x, y, z);
	}
	
	private boolean validRange(int zstart, int zgoal) {
		return zstart >=  getMinZ() && zstart <= getMaxZ() && 
				zgoal >=  getMinZ() && zgoal <= getMaxZ(); 
	}
	public void setRange(int minZ,int maxZ) {
		this.minZ=minZ;
		this.maxZ= maxZ;
	}
	
	public int getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(int maxZ) {
		this.maxZ = maxZ;
	}

	public int getMinZ() {
		return minZ;
	}

	public void setMinZ(int minZ) {
		this.minZ = minZ;
	}

	public int getFuel() {
		return fuel;
	}
	
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	
	
	
	

}
