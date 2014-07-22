package se.raihle.tactics.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Text extends GameObject {

	private Alignment alignment;
	private CharSequence text;
	private BitmapFont font;
	private int alignedX, alignedY;

	public Text(BitmapFont font, CharSequence text, int x, int y, int width, int height, Alignment alignment) {
		super(x, y, width, height);
		this.font = font;
		this.text = text;
		this.alignment = alignment;
		calculatePosition();
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
		calculatePosition();
	}

	private void calculatePosition() {

		switch (alignment) {
			case UPPER_LEFT:
			case UPPER_CENTER:
			case UPPER_RIGHT:
				alignedY = getY();
				break;
			case MIDDLE_CENTER:
			case MIDDLE_LEFT:
			case MIDDLE_RIGHT: {
				int textHeight = (int) font.getCapHeight();
				alignedY = getY() - (getHeight() - textHeight) / 2;
				break;
			}
			case LOWER_CENTER:
			case LOWER_LEFT:
			case LOWER_RIGHT: {
				int textHeight = (int) font.getCapHeight();
				alignedY = getY() - getHeight() + textHeight;
				break;
			}
		}

		switch (alignment) {
			case UPPER_LEFT:
			case MIDDLE_LEFT:
			case LOWER_LEFT: {
				alignedX = getX();
				break;
			}
			case UPPER_CENTER:
			case MIDDLE_CENTER:
			case LOWER_CENTER: {
				int textWidth = (int) font.getBounds(text).width;
				alignedX = (getX() + getWidth() - textWidth) / 2;
				break;
			}
			case UPPER_RIGHT:
			case MIDDLE_RIGHT:
			case LOWER_RIGHT: {
				int textWidth = (int) font.getBounds(text).width;
				alignedX = (getX() + getWidth() - textWidth);
				break;
			}
		}
	}

	public void setFont(BitmapFont font) {
		this.font = font;
		calculatePosition();
	}

	public void setText(CharSequence text) {
		this.text = text;
		calculatePosition();
	}

	@Override
	public void renderTo(SpriteBatch batch) {
		font.setColor(1, 1, 1, getOpacity());
		//font.setScale() if necessary to fit text in bounds
		font.draw(batch, text, alignedX, alignedY);
	}

	public enum Alignment {
		UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT,
		MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT,
		LOWER_LEFT, LOWER_CENTER, LOWER_RIGHT
	}
}
