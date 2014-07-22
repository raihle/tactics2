package se.raihle.tactics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import se.raihle.tactics.model.MapData;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Map {

	private final ShapeRenderer sr;
	private final OrthographicCamera camera;
	private final Texture grass;
	private final MapData data;

	public Map(MapData data, BattleScreen battle) {
		this.data = data;
		this.camera = battle.gameCamera;
		sr = new ShapeRenderer();
		sr.setColor(Color.BLACK);
		sr.setProjectionMatrix(battle.gameCamera.combined);

		grass = new Texture(Gdx.files.internal("grass.png"));
	}

	public void renderTo(SpriteBatch batch) {
		batch.setColor(1, 1, 1, 1);

		MapData.Tile[][] tiles = data.getTiles();

		for (int x = 0; x < tiles.length; x++) {
			MapData.Tile[] column = tiles[x];
			for (int y = 0; y < column.length; y++) {
				MapData.Tile tile = column[y];
				Vector3 screenPos = new Vector3(x * BattleScreen.TILE_SIZE, y * BattleScreen.TILE_SIZE, 0);
				//camera.project(screenPos);
				batch.draw(grass, screenPos.x, screenPos.y);
			}
		}
		/*batch.end();
		Gdx.gl.glLineWidth(3);
		sr.begin(ShapeRenderer.ShapeType.Line);

		sr.line(64,0,64,600);
		sr.end();
		batch.begin();*/
	}

	public void dispose() {
		grass.dispose();
	}
}
