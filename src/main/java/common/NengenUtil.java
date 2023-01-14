package common;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static nengen.EngineConfiguration.DEBUG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import text.CharacterData;
import text.GameFont;
import visuals.lwjgl.render.Texture;

public class NengenUtil {

	private NengenUtil() {
	}

	public static String readFileAsString(String path) {
		try {
			return new String(readAllBytes(get(path)));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readFileAsString(File file) {
		try {
			return new String(readAllBytes(get(file.getAbsolutePath())));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static GameFont loadFont(File source, Texture texture) throws IOException {
		try (FileInputStream fis = new FileInputStream(source)) {
			// Read header
			int nameLength = fis.read();
			byte[] nameBytes = new byte[nameLength];
			for (int i = 0; i < nameBytes.length; i++) {
				nameBytes[i] = (byte) fis.read();
			}
			String name = new String(nameBytes, UTF_8);
			short fontSize = readShort(fis);
			short pages = readShort(fis);
			int numCharacters = readShort(fis);
			short kernings = readShort(fis);
			DEBUG("Font name: " + name);
			DEBUG("Pages: " + pages);
			DEBUG("Font size: " + fontSize);
			DEBUG("Characters: " + numCharacters);
			DEBUG("Kernings: " + kernings);

			GameFont gameFont = new GameFont(name, fontSize, texture);

			// Read characters
			CharacterData[] characters = gameFont.getCharacterDatas();
			DEBUG(" Char | X | Y | Width | Height | X Offset | Y Offset | X Advance | Page ");
			for (int i = 0; i < numCharacters; i++) {
				short c = readShort(fis);
				short x = readShort(fis);
				short y = readShort(fis);
				short width = readShort(fis);
				short height = readShort(fis);
				short xOffset = readShort(fis);
				short yOffset = readShort(fis);
				short xAdvance = readShort(fis);
				short page = (short) fis.read();
				CharacterData charData = new CharacterData(x, y, width, height, xOffset, yOffset, xAdvance, page);
				DEBUG(c + " " + x + " " + y + " " + width + " " + height + " " + xOffset + " " + yOffset + " " + xAdvance + " " + page);
				characters[c] = charData;
			}
			CharacterData space = characters[' '];
			characters['\t'] = new CharacterData(space.x(), space.y(), space.width(), space.height(), space.xOffset(), space.yOffset(), (short) (space.xAdvance() * 4), space.getPage());

			return gameFont;
		}
	}

	private static short readShort(FileInputStream fis) throws IOException {
		byte b1 = (byte) fis.read();
		byte b2 = (byte) fis.read();
		return convertBytesToShort(b1, b2);
	}

	private static short convertBytesToShort(byte b1, byte b2) {
		return (short) ((b1 << 8) | (b2 & 0xFF));
	}

}
