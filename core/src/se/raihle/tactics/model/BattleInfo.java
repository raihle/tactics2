package se.raihle.tactics.model;

/**
 * Created by Jacob on 2014-07-22.
 */
public class BattleInfo {


	private State state;

	public BattleInfo(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State newState) {
		this.state = newState;
	}

	public enum State {
		BATTLE_START,
		PLAYER_SELECT_UNIT_TO_COMMAND
	}
}
