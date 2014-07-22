package se.raihle.tactics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import se.raihle.tactics.game.GameObject;
import se.raihle.tactics.game.Unit;
import se.raihle.tactics.model.BattleInfo;
import se.raihle.tactics.model.MapData;
import se.raihle.tactics.model.UnitData;
import se.raihle.tactics.phases.BattlePhase;
import se.raihle.tactics.phases.BattleStart;
import se.raihle.tactics.phases.PlayerTurn;

import java.util.Iterator;

/**
 * Created by Jacob on 2014-07-22.
 */
public class BattleScreen implements Screen {
	public static final int TILE_SIZE = 64;
	public final OrthographicCamera gameCamera;
	private final OrthographicCamera uiCamera;
	private final Array<Unit> units;
	private SpriteBatch batch;
	private Map map;
	private BattleInfo battleInfo = new BattleInfo(BattleInfo.State.BATTLE_START);
	private BattlePhase currentPhase;


	public BattleScreen(SpriteBatch batch, MapData mapData, Array<UnitData> unitDatas) {
		int WIDTH = Gdx.graphics.getWidth() * 3 / 4;
		int HEIGHT = Gdx.graphics.getHeight();

		this.batch = batch;

		gameCamera = new OrthographicCamera(800, 600);
		gameCamera.setToOrtho(false);

		uiCamera = new OrthographicCamera(800, 600);
		uiCamera.setToOrtho(false);

		this.units = new Array<>();

		for (UnitData unitData : unitDatas) {
			units.add(new Unit(unitData));
		}

		this.map = new Map(mapData, this);
		gameCamera.translate(-(WIDTH - TILE_SIZE * mapData.getWidth()) / 2, -(HEIGHT - TILE_SIZE * mapData.getHeight()) / 2, 0);

		enterBattleStart();
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameCamera.update();
		batch.setProjectionMatrix(gameCamera.combined);
		batch.begin();

		map.renderTo(batch);

		for (Unit unit : units) {
			unit.renderTo(batch);
		}

		if (currentPhase != null) {
			Array<GameObject> gameObjects = currentPhase.getGameObjects();
			if (gameObjects != null) {
				for (GameObject object : gameObjects) {
					object.renderTo(batch);
				}
			}
			Array<GameObject> uiObjects = currentPhase.getUiObjects();
			if (uiObjects != null) {
				batch.setProjectionMatrix(uiCamera.combined);
				for (GameObject object : uiObjects) {
					object.renderTo(batch);
				}
			}
		}

		batch.end();

		if (currentPhase != null) {
			currentPhase.update(delta);
			if (currentPhase.isFinished()) {
				switch (battleInfo.getState()) {
					case BATTLE_START:
						enterPlayerSelectUnitForCommand();
						break;
					case PLAYER_SELECT_UNIT_TO_COMMAND:
						break;
					default:
						throw new IllegalStateException("Unknown battleInfo " + battleInfo);
				}
			}
		}
	}

	public void enterBattleStart() {
		if (currentPhase != null) {
			currentPhase.exit();
		}
		battleInfo.setState(BattleInfo.State.BATTLE_START);
		currentPhase = new BattleStart();
		currentPhase.enter();
	}

	public void enterPlayerSelectUnitForCommand() {
		if (currentPhase != null) {
			currentPhase.exit();
		}
		battleInfo.setState(BattleInfo.State.PLAYER_SELECT_UNIT_TO_COMMAND);
		currentPhase = new PlayerTurn(this);
	}

	@Override
	public void dispose() {
		Iterator<Unit> i_units = units.iterator();
		map.dispose();
		while (i_units.hasNext()) {
			Unit unit = i_units.next();
			unit.dispose();
		}
	}
}
