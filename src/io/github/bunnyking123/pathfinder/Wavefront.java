package io.github.bunnyking123.pathfinder;

import java.util.ArrayList;

public class Wavefront {
	
	//99 = Player
	//100 = Obstacle
	//101 = This enemy
	public int[][] map = {{99, 0, 0, 0, 0},
						  {0, 0, 0, 0, 0},
						  {0, 0, 0, 0, 0},
						  {0, 0, 0, 0, 0},
						  {0, 0, 0, 0, 101}};
	public ArrayList<Direction> path = new ArrayList<Direction>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wavefront finder = new Wavefront();
		finder.findPath();
	}
	
	public void findPath() {
		int coordX = 0;
		int coordY = 0;
		for (int i = 0; i < map.length; i++) {
			boolean foundSelf = false;
			int[] row = map[i];
			for (int j = 0; j < row.length; j++) {
				if (row[j] == 101) {
					foundSelf = true;
					coordX = j;
					break;
				}
			}
			if (foundSelf == true) {
				coordY = i;
				break; 
			}
		}
		System.out.println(coordX);
		System.out.println(coordY);
		
		int index = 0;
		while (true) {
			for  (int i = 0; i < map.length; i++) {
				int[] row = map[i];
				for (int j = 0; j < row.length; j++) {
					int selection = 0;
					if (index == 0) {
						selection = 101;
					} else {
						selection = index;
					}
					if (row[j] == selection) {
						checkMap(j, i, index);
					}
				}
			}
			if (allFilled()) {
				break;
			}
			index++;
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + ",");
			}
			System.out.println();
		}
		
		for (int i = 0; i < map.length; i++) {
			boolean foundSelf = false;
			int[] row = map[i];
			for (int j = 0; j < row.length; j++) {
				if (row[j] == 99) {
					foundSelf = true;
					coordX = j;
					break;
				}
			}
			if (foundSelf == true) {
				coordY = i;
				break; 
			}
		}
		System.out.println(coordX);
		System.out.println(coordY);
		
		while (true) {
			if (pathComplete(coordX, coordY)) {
				break;
			}
			
			Direction direction = Direction.DOWN;
			int targetNum = 0;
			int coordXTemp = 0;
			int coordYTemp = 0;
			if (coordY != 0) {
				if (map[coordY - 1][coordX] < targetNum || targetNum == 0) {
					targetNum = map[coordY - 1][coordX];
					coordXTemp = coordX;
					coordYTemp = coordY - 1;
					direction = Direction.DOWN;
				}
			}
			if (coordX != 0) {
				if (map[coordY][coordX - 1] < targetNum || targetNum == 0) {
					targetNum = map[coordY][coordX - 1];
					coordXTemp = coordX - 1;
					coordYTemp = coordY;
					direction = Direction.RIGHT;
				}
			}
			if (coordY != map.length - 1) {
				if (map[coordY + 1][coordX] < targetNum || targetNum == 0) {
					targetNum = map[coordY + 1][coordX];
					coordXTemp = coordX;
					coordYTemp = coordY + 1;
					direction = Direction.UP;
				}
			}
			if (coordX != map[coordY].length - 1) {
				if (map[coordY][coordX + 1] < targetNum || targetNum == 0) {
					targetNum = map[coordY][coordX + 1];
					coordXTemp = coordX + 1;
					coordYTemp = coordY;
					direction = Direction.LEFT;
				}
			}
			path.add(direction);
			//System.out.println(direction);
			coordX = coordXTemp;
			coordY = coordYTemp;
		}
		for(Direction d:path) {
			System.out.println(d);
		}
	}
	
	private boolean pathComplete(int x, int y) {
		boolean found = false;
		if (y != 0) {
			if (map[y - 1][x] == 101) {
				found = true;
				path.add(Direction.DOWN);
			}
		}
		if (x != 0) {
			if (map[y][x - 1] == 101) {
				found = true;
				path.add(Direction.RIGHT);
			}
		}
		if (y != map.length - 1) {
			if (map[y + 1][x] == 101) {
				found = true;
				path.add(Direction.UP);
			}
		}
		if (x != map[y].length - 1) {
			if (map[y][x + 1] == 101) {
				found = true;
				path.add(Direction.LEFT);
			}
		}
		return found;
	}
	
	private void checkMap(int x, int y, int index) {
		if (y != 0) {
			if (map[y - 1][x] == 0) {
				map[y - 1][x] = index + 1;
			}
		}
		if (x != 0) {
			if (map[y][x - 1] == 0) {
				map[y][x - 1] = index + 1;
			}
		}
		if (y != map.length - 1) {
			if (map[y + 1][x] == 0) {
				map[y + 1][x] = index + 1;
			}
		}
		if (x != map[y].length - 1) {
			if (map[y][x + 1] == 0) {
				map[y][x + 1] = index + 1;
			}
		}
	}
	
	private boolean allFilled() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
