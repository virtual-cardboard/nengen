package visuals.rendering.text;

public final class CharacterData {

	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final int xOffset;
	private final int yOffset;
	private final int xAdvance;
	private final int page;

	public CharacterData(int x, int y, int width, int height, int xOffset, int yOffset, int xAdvance, int page) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.xAdvance = xAdvance;
		this.page = page;
	}

	public CharacterData(short x, short y, short width, short height, short xOffset, short yOffset, short xAdvance, short page) {
		this(x, y, width, height, xOffset, yOffset, xAdvance, (int) page);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public int xOffset() {
		return xOffset;
	}

	public int yOffset() {
		return yOffset;
	}

	public int xAdvance() {
		return xAdvance;
	}

	public int getPage() {
		return page;
	}

}
