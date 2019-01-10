package dhbw.engine.impl.display.render;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import lombok.Getter;

public class Material {

	@Getter
	private int textureID;
	private String file;

	public Material(String file) {
		try {
			Texture texture = TextureLoader.getTexture("png", new FileInputStream(file));
			this.textureID = texture.getTextureID();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
