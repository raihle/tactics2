package se.raihle.tactics.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Jacob on 2014-07-22.
 */
public class UnitData {

	private final int tileX;
	private final int tileY;

	public UnitData(int tileX, int tileY) {
		this.tileX = tileX;
		this.tileY = tileY;
	}

	public FileHandle getImageHandle() {
		return Gdx.files.internal("unit.png");
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}
}
