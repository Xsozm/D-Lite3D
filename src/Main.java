package net.tofweb.starlite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
	    //BufferedWriter writer = new BufferedWriter(new FileWriter("TestFile5", true));
		int total_cars = 0 ;
		Scanner sc = new Scanner("TestFile1");

		// fuel range 30-60
		// speed 60KM/H
		int i = 1;
		while (sc.br.readLine() != null) {
			total_cars++;
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			int xx = sc.nextInt();
			int yy = sc.nextInt();
			int zz = sc.nextInt();



			// random from 30-60
			int fuel = sc.nextInt();

			String str = x + " " + y + " " + z + " " + xx + " " + yy + " " + zz + " " + fuel + "\n";

			FlyingCar f = new FlyingCar(x, y, z, xx, yy, zz, i++, fuel);
			f.start();

		}

		Thread.sleep(100000);
		System.out.println("Total Cars " + total_cars + " Cars Arrived " + Pathfinder.total_cars_arrived
				+ " Failed Cars " + (total_cars - Pathfinder.total_cars_arrived));

	}

}
