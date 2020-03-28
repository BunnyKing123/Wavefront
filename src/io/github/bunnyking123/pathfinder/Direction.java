package io.github.bunnyking123.pathfinder;

public enum Direction {
	UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right");
	
	private String name;

	Direction(String string) {
		// TODO Auto-generated constructor stub
		name = string;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
