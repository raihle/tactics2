package se.raihle.tactics.phases;

import com.badlogic.gdx.utils.Array;
import se.raihle.tactics.game.GameObject;

/**
 * Created by Jacob on 2014-07-22.
 */
public interface BattlePhase {
	public void enter();

	public void update(float delta);

	public void exit();

	public boolean isFinished();

	public Array<GameObject> getGameObjects();

	public Array<GameObject> getUiObjects();
}
