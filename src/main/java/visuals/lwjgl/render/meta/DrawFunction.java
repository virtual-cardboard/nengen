package visuals.lwjgl.render.meta;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.Collections;

import visuals.lwjgl.GLContext;
import visuals.lwjgl.render.Texture;
import visuals.lwjgl.render.VertexArrayObject;

public class DrawFunction {

	private ArrayList<Texture> textures = new ArrayList<>();
	private VertexArrayObject vao;
	private GLContext glContext;

	public DrawFunction(){
	}

	public DrawFunction vao(VertexArrayObject vao){
		this.vao = vao;
		return this;
	}

	public DrawFunction textures(Texture... textures){
		addAll(this.textures, textures);
		return this;
	}

	public DrawFunction glContext(GLContext glContext) {
		this.glContext = glContext;
		return this;
	}

	public void draw(){
		for (Texture texture : textures) {
			texture.bind();
		}
		vao.draw(glContext);
	}

}
