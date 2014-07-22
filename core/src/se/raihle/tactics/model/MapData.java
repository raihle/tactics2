package se.raihle.tactics.model;

/**
 * Created by Jacob on 2014-07-22.
 */
public class MapData {
	private Tile[][] tiles;

	public MapData() {
		tiles = new Tile[12][9];
		/*for (Tile[] column : tiles) {
			column = new Tile[9];
		}*/
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getHeight() {
		return 9;
	}

	public int getWidth() {
		return 12;
	}

	public class Tile {

	}
}


