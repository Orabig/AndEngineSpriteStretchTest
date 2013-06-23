package com.crocoware.andengine.sample;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.DrawType;

import com.crocoware.andengine.entity.sprite.stretch.PerspectiveSprite;
import com.crocoware.andengine.entity.sprite.stretch.StretchSprite;

/**
 * This scene makes several usage of the strechable sprites
 * 
 * @author Benoit
 * 
 */
public class StretchSampleScene extends Scene {

	public StretchSampleScene(Engine engine, TiledTextureRegion textureRegion,
			TextureRegion textureRegion2) {

		test1 = new PerspectiveSprite(200, 600, 128, 128,
				textureRegion.getTextureRegion(0),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test2 = new StretchSprite(400, 600, 128, 128,
				textureRegion.getTextureRegion(8),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test3 = new StretchSprite(200, 300, 128, 128, textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test4 = new StretchSprite(400, 300, 128, 128, textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		attachChild(test1);
		attachChild(test2);
		attachChild(test3);
		attachChild(test4);
	}

	PerspectiveSprite test1;
	StretchSprite test2;
	StretchSprite test3;
	StretchSprite test4;
	float t;

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		t += pSecondsElapsed;
		float pinchX = (float) (Math.sin(t * 2)) * 0.9f;
		float pinchY = (float) (Math.cos(t * 2)) / 3;

		if (Math.sin(t) > 0)
			test1.setStretchX(pinchX);
		else
			test1.setStretchY(pinchX);

		if (Math.sin(t) > 0)
			test2.setStretchX(pinchX);
		else
			test2.setStretchY(pinchX);

		test3.setStretchX(pinchX);
		test3.setStretchY(pinchY);

		// Sprite 4 will turn slowly
		double a1 = t/5;//Math.PI/4;//3*Math.PI/4;//t/5;
		double a2 = a1 + Math.PI / 2;
		double a3 = a2 + Math.PI / 2;
		double a4 = a3 + Math.PI / 2;
		a2 += Math.PI / 6 * Math.sin(t);
		a3 += Math.PI / 6 * Math.cos(t);
		float xc = 400;
		float yc = 300;
		float R = 150;
		float x1 = xc + R * (float) Math.cos(a1);
		float y1 = yc + R * (float) Math.sin(a1);
		float x2 = xc + R * (float) Math.cos(a2);
		float y2 = yc + R * (float) Math.sin(a2);
		float x3 = xc + R * (float) Math.cos(a3);
		float y3 = yc + R * (float) Math.sin(a3);
		float x4 = xc + R * (float) Math.cos(a4);
		float y4 = yc + R * (float) Math.sin(a4);
		test4.setFlipped(false,true);
		test4.setPosition(x1, y1, x2, y2, x3, y3, x4, y4);
	}

}
