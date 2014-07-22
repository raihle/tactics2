package se.raihle.tactics.game;

import com.badlogic.gdx.graphics.Texture;
import se.raihle.tactics.model.UnitData;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Unit extends Sprite {

	private static final int SIDE = 64;

	public Unit(UnitData data) {
		this(new Texture(data.getImageHandle()), data.getTileX(), data.getTileY());
	}

	public Unit(Texture image, int tileX, int tileY) {
		super(image, tileX * SIDE + SIDE / 2, tileY * SIDE, SIDE, SIDE);
	}

	public void dispose() {
		getImage().dispose();
	}

}
