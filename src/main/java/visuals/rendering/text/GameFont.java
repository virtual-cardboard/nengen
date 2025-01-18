package visuals.rendering.text;

import visuals.lwjgl.render.Texture;

public final class GameFont {

	public static long FONT_INDEX = 0;

	/**
	 * The global index of the font.
	 */
	private final long fontIndex;

	private final String name;
	private final int fontSize;
	private CharacterData[] characterDatas;
	private final Texture texture;

	public GameFont(String name, int fontSize, Texture texture, long fontIndex) {
		this.name = name;
		this.fontSize = fontSize;
		characterDatas = new CharacterData[128];
		this.texture = texture;
		this.fontIndex = fontIndex;
	}

	public GameFont(String name, int fontSize, CharacterData[] characterDatas, long fontIndex) {
		this.name = name;
		this.fontSize = fontSize;
		this.characterDatas = characterDatas;
		this.texture = null;
		this.fontIndex = fontIndex;
	}

	public GameFont(String name, int fontSize, CharacterData[] characterDatas) {
		this.name = name;
		this.fontSize = fontSize;
		this.characterDatas = characterDatas;
		this.texture = null;
	}

	public String getName() {
		return name;
	}

	public int getFontSize() {
		return fontSize;
	}

	public CharacterData[] getCharacterDatas() {
		return characterDatas;
	}

	public void characterDatas(CharacterData[] characterDatas) {
		this.characterDatas = characterDatas;
	}

	public Texture texture() {
		return texture;
	}

	public void delete() {
		if (texture != null) {
			texture.delete();
		}
	}

	public static long getFontIndex() {
		return FONT_INDEX++;
	}

}
