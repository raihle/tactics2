package se.raihle.tactics.phases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import se.raihle.tactics.BattleScreen;
import se.raihle.tactics.game.GameObject;
import se.raihle.tactics.game.Text;

/**
 * Created by Jacob on 2014-07-22.
 */
public class PlayerTurn implements BattlePhase {

	private final BitmapFont font;
	Text text;
	private Array<GameObject> objects;
	private BattleScreen battle;
	private boolean drag;

	public PlayerTurn(BattleScreen battle) {
		this.battle = battle;
		objects = new Array<>();
		font = new BitmapFont();


		text = new Text(font, "X: , Y: ", 4, 24, 0, 20, Text.Alignment.LOWER_LEFT);
		objects.add(text);

	}

	@Override
	public void enter() {
	}

	@Override
	public void update(float delta) {
		Vector3 touchPos = new Vector3();
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		battle.gameCamera.unproject(touchPos);
		int tileX = MathUtils.floor(touchPos.x / BattleScreen.TILE_SIZE);
		int tileY = MathUtils.floor(touchPos.y / BattleScreen.TILE_SIZE);

		if (Gdx.input.isTouched()) {
			if (!drag) {
				//System.out.println("Down on X: " + tileX + ", Y: " + tileY);
				drag = true;
			}
		} else if (drag) {
			//System.out.println("Up on X: " + tileX + ", Y: " + tileY);
			drag = false;
		}
		text.setText("X: " + tileX + ", Y: " + tileY);
		//System.out.println(Gdx.input.getX() + "");
	}

	@Override
	public void exit() {
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Array<GameObject> getGameObjects() {
		return null;
	}

	@Override
	public Array<GameObject> getUiObjects() {
		return objects;
	}
}

