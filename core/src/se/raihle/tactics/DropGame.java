package se.raihle.tactics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import se.raihle.tactics.game.GameObject;
import se.raihle.tactics.game.Sprite;

import java.util.Iterator;


public class DropGame extends ApplicationAdapter {
	private static final int BUCKET_SPEED = 300;
	private static final int DROP_SPEED = 100;
	private static final int DROP_INTERVAL = 1_300_000_000;
	private static final float DROP_RANDOM = 0.2f;
	private SpriteBatch batch;
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private GameObject bucket;
	private Array<GameObject> raindrops;
	private long nextDropTime;


	@Override
	public void create() {
		loadAssets();
		init();
	}

	private void init() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		//rainMusic.play();

		bucket = new Sprite(bucketImage, 0, 0, 128, 128);
		raindrops = new Array<>();
	}

	private void loadAssets() {
		dropImage = new Texture("drop.png");
		bucketImage = new Texture("bucket.png");

		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//camera.update() not necessary because camera is never moved
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		bucket.renderTo(batch);

		for (GameObject drop : raindrops) {
			drop.renderTo(batch);
			drop.renderTo(batch);
		}

		batch.end();

		update(Gdx.graphics.getDeltaTime());
	}

	private void update(float delta) {
		if (delta > .1f) {
			return;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			bucket.addX(-BUCKET_SPEED * delta);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			bucket.addX(BUCKET_SPEED * delta);
		}

		Iterator<GameObject> drops = raindrops.iterator();
		while (drops.hasNext()) {
			GameObject drop = drops.next();
			drop.addY(-DROP_SPEED * delta);
			if (drop.getTop() < 0) {
				drops.remove();
			}

			if (drop.getRect().overlaps(bucket.getRect())) {
				dropSound.play();
				drops.remove();
			}
		}

		if (TimeUtils.nanoTime() > nextDropTime) {
			spawnRaindrop();
			int dropDelay = (int) (DROP_INTERVAL * MathUtils.random(1f - DROP_RANDOM, 1f + DROP_RANDOM));
			nextDropTime = TimeUtils.nanoTime() + dropDelay;
		}
	}

	private void spawnRaindrop() {
		int x = MathUtils.random(32, 800 - 32);
		int y = 480;

		GameObject raindrop = new Sprite(dropImage, x, y, 64, 64);
		raindrops.add(raindrop);
	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	@Override
	public void pause() {
		super.pause();
		System.out.println("PAUSE");
	}
}