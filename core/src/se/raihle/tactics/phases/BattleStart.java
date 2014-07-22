package se.raihle.tactics.phases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import se.raihle.tactics.game.GameObject;
import se.raihle.tactics.game.Sprite;
import se.raihle.tactics.jobs.FadeInOut;

/**
 * Created by Jacob on 2014-07-22.
 */
public class BattleStart implements BattlePhase {

	private GameObject battleBeginsText;
	private FadeInOut textAnimationJob;
	private Texture bbImage;
	private Array<GameObject> objects;

	public BattleStart() {
		objects = new Array<>();
	}

	@Override
	public void enter() {
		bbImage = new Texture("the-battle-begins.png");
		battleBeginsText = new Sprite(bbImage);
		battleBeginsText.setOpacity(0);
		battleBeginsText.setX(Gdx.graphics.getWidth() / 2);
		battleBeginsText.setY(Gdx.graphics.getHeight() * 2 / 3);
		textAnimationJob = new FadeInOut(battleBeginsText, -30, 0, 20, 0, 0.2f, 1f, 0.1f);

		objects.add(battleBeginsText);
	}

	@Override
	public void update(float delta) {
		if (!textAnimationJob.isFinished()) {
			textAnimationJob.execute(delta);
		}
	}

	@Override
	public void exit() {
		bbImage.dispose();
	}

	@Override
	public boolean isFinished() {
		return textAnimationJob.isFinished();
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

