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


	public StretchSampleScene(Engine engine,
			TiledTextureRegion textureRegion,
			TextureRegion textureRegion2) {

		test1 = new PerspectiveSprite(200, 600, 128, 128,
				textureRegion.getTextureRegion(0),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test2 = new StretchSprite(400, 600, 128, 128,
				textureRegion.getTextureRegion(8),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test3 = new StretchSprite(200, 300, 128, 128,
				textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test4 = new StretchSprite(400, 300, 128, 128,
				textureRegion2,
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

		test4.setStretchX(pinchX);
	}

}
