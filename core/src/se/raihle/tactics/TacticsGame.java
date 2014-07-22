package se.raihle.tactics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import se.raihle.tactics.model.MapData;
import se.raihle.tactics.model.UnitData;


public class TacticsGame extends Game {

	private SpriteBatch batch;


	@Override
	public void create() {
		batch = new SpriteBatch();

		MapData map = new MapData();
		Array<UnitData> units = new Array<>();
		units.add(new UnitData(0, 0));
		units.add(new UnitData(1, 1));
		units.add(new UnitData(3, 2));

		setScreen(new BattleScreen(batch, map, units));

		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render() {
		if (Gdx.graphics.getDeltaTime() > .1f) {
			return;
		} else {
			super.render();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

