package net.tofweb.starlite;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws InterruptedException {


		// fuel range 30-60
		//speed 60KM/H
		for(int i =1;i<=10000;i++) {
			int x = (int) (Math.random()*10+1);
			int y = (int) (Math.random()*10+1);
			int z = (int) (Math.random()*10+1);
			int xx ;
			int yy ;
			int zz ;
			
			int fuel = (int) (Math.random()*30+30) ;
			
			if(i<=3333) {
				 xx = (int) (Math.random()*10+1);
				 yy = (int) (Math.random()*10+1);
				 zz = (int) (Math.random()*10+1);
				// group1 short
			}
			if(i>3333 && i <= 6666) {
				//group2 medium 
				 xx = (int) (Math.random()*30+1);
				 yy = (int) (Math.random()*30+1);
				 zz = (int) (Math.random()*30+1);
			}
			
			if(i>6666 && i <= 10000) {
				 xx = (int) (Math.random()*50+1);
				 yy = (int) (Math.random()*50+1);
				 zz = (int) (Math.random()*50+1);
				//group3 long 
			}
			
			FlyingCar f = new FlyingCar(x,y,z,5,5,5,i,fuel);
			f.start();
			

		}
		
		Thread.sleep(100000);
		System.out.println("Total Cars "+ Pathfinder.total_cars+" Cars Arrived "+Pathfinder.total_cars_arrived +" Failed Cars "+(Pathfinder.total_cars-Pathfinder.total_cars_arrived));
		
		

		
		
		
	}
	
}
