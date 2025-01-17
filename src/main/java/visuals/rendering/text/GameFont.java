package visuals.rendering.text;

import visuals.lwjgl.render.Texture;

public final class GameFont {

	private final String name;
	private final int fontSize;
	private final CharacterData[] characterDatas;
	private final Texture texture;

	public GameFont(String name, int fontSize, Texture texture) {
		this.name = name;
		this.fontSize = fontSize;
		characterDatas = new CharacterData[128];
		this.texture = texture;
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

	public Texture texture() {
		return texture;
	}

	public void delete() {
		if (texture != null) {
			texture.delete();
		}
	}

}
