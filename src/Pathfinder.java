package net.tofweb.starlite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Finds a path through the specified BlockManager controlled CellSpace.
 * 
 * @version .9
 * @since .9
 */
public class Pathfinder  {

	private Path path = new Path();
	private BlockManager blockManager;
	private int fuel ;
	private int initial_fuel_amount ; 
	public static HashMap<Triple, Boolean> busy = new HashMap<Triple, Boolean>();
	public static double FUEL_FOR_BEING_IN_SAME_POSITION =0.1;
	private long car_id ;
	public static int total_cars = 0; 
	public static int total_cars_arrived = 0; 
	public double total_distance = 0.0;
	private double time_taken=0.0;
	public static double LITER_PER_KILO = 0.2 ; 
	
	
	

	
	


	public long getId() {
		return car_id;
	}

	public void setId(int id) {
		this.car_id = id;
	}

	/**
	 * Creates a Pathfinder with the specified BlockManager.
	 * 
	 * @param blockManager
	 */
	public Pathfinder(BlockManager blockManager) {
		super();
		this.blockManager = blockManager;
	}

	/**
	 * Find and returns a path to the goal.
	 * 
	 * The returned path may not be complete, meaning that it was not able to
	 * find a path to the goal. The Path should be checked for completion.
	 * 
	 * @return Path The discovered Path
	 * @throws InterruptedException 
	 */
	public Path findPath() throws InterruptedException {
		total_cars++;
		path.clear();
		System.out.println("Car ["+car_id+"] starting trip...");

		CellSpace space = blockManager.getSpace();
		LinkedList<Cell> potentialNextCells = new LinkedList<Cell>();
		Cell currentCell = space.getStartCell();
		Cell previous = currentCell;


		if (space.getG(space.getStartCell()) == Double.POSITIVE_INFINITY) {
			return path;
		}
		boolean isTrapped = false;
		long expected = Math.abs(space.getGoalCell().getX()- space.getStartCell().getX()) + Math.abs(space.getGoalCell().getY()- space.getStartCell().getY()) +Math.abs(space.getGoalCell().getZ()- space.getStartCell().getZ());
		int k = 0; 
		while (!currentCell.equals(space.getGoalCell()) && !isTrapped) {
			// fuel 
			if(fuel < 10) {
				System.out.println("Car ["+car_id+"] Failed Fule Tank is Empty");

				return null ;
			}
			
			// too many moves 
			if(k++ - expected > 10) {
				System.out.println("Car ["+car_id+"] Failed many moves");

				return null;
			}

			isTrapped = true;
			path.add(currentCell);
			
			System.out.println("Car ["+car_id+"] is in..."+currentCell);
			Triple tuple = new Triple(currentCell.getX(),currentCell.getY(),currentCell.getZ()) ;
			busy.put(tuple, true);
			Thread.sleep(1000);// take some time in the position
			time_taken+=1000;
			busy.put(tuple, false);

			
			potentialNextCells = space.getSuccessors(currentCell);
			if (potentialNextCells.isEmpty()) {
				return path;
			}


			double minimumCost = Double.POSITIVE_INFINITY;
			Cell minimumCell = new Cell();

			for (Cell potentialNextCell : potentialNextCells) {
				Triple currentTuple = new Triple(potentialNextCell.getX(),potentialNextCell.getY(),potentialNextCell.getZ()) ;
				if (blockManager.isBlocked(potentialNextCell) || (( busy.containsKey(currentTuple)) == true) && busy.get(currentTuple)==true) {
					continue;
				} else {
					isTrapped = false;
				}

				double costToMove = Geometry.euclideanDistance(currentCell, potentialNextCell);
				double euclideanDistance = Geometry.euclideanDistance(potentialNextCell, space.getGoalCell())
						+ Geometry.euclideanDistance(space.getStartCell(), potentialNextCell);
				costToMove += space.getG(potentialNextCell);

				// If the cost to move is essentially zero ...
				if (space.isClose(costToMove, minimumCost)) {
					if (0 > euclideanDistance) {
						minimumCost = costToMove;
						minimumCell = potentialNextCell;
					}
				} else if (costToMove < minimumCost) {
					minimumCost = costToMove;
					minimumCell = potentialNextCell;
				}
			}
			
			
			double distance = Geometry.euclideanDistance(currentCell,previous);
			
			//scale the distance 
			distance*=10;

			if (!isTrapped) {
				potentialNextCells.clear();
				previous=currentCell;
				currentCell = new Cell(minimumCell);
			}
			
			if(distance == 0) {
				fuel -= FUEL_FOR_BEING_IN_SAME_POSITION;
				System.out.println("Car ["+car_id+"] still in..."+currentCell);


			}else {
				fuel-=distance*LITER_PER_KILO ;
				System.out.println("Car ["+car_id+"] moving to..."+currentCell);

			}
			
			
			total_distance+=distance;
			
			// time for moving 
			Thread.sleep((long) (distance));
			time_taken+=((distance)/60)*3600L;

			
		}

		if (!isTrapped) {
			path.add(space.getGoalCell());
		}

		path.setComplete(blockManager.getSpace().getGoalCell().equals(path.getLast()));
		if(path.isComplete()) {
			total_cars_arrived++;
			System.out.println("Car ["+car_id+"] Trip Ended Successfully with fuel"+" "+fuel +", "+Math.round(((initial_fuel_amount-fuel)*1.0/initial_fuel_amount)*100.0)+"% of fuel ran out"+ " and distance"+" "+total_distance+", Time Taken "+time_taken+" s");
		}else {
			System.out.println("Car ["+car_id+"] TRip Failed couldn't reach goal.");

		}

		return path;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
		this.initial_fuel_amount= fuel;

	}

}
