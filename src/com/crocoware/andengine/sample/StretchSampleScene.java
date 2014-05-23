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

		test1 = new PerspectiveSprite(100, 100, 100, 100,
				textureRegion.getTextureRegion(0),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test2 = new StretchSprite(100, 400, 100, 100,
				textureRegion.getTextureRegion(8),
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test3 = new StretchSprite(700, 400, 100, 100, textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		//test 4 and 5 uses the setPosition function, in order to accurately use the X and Y
		//you need to set the Width and Height to 1. 
		test4 = new StretchSprite(700, 100, 1, 1, textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test5 = new StretchSprite(0, 0, 1, 1, textureRegion2,
				engine.getVertexBufferObjectManager(), DrawType.STATIC);
		test5.setPosition(ax1 + test5X, ay1 + test5Y, ax2 + test5X, ay2+ test5Y, ax3 + test5X, ay3+ test5Y, ax4 + test5X, ay4+ test5Y);
		
		attachChild(test1);
		attachChild(test2);
		attachChild(test3);
		attachChild(test4);
		attachChild(test5);
	}

	PerspectiveSprite test1;
	StretchSprite test2;
	StretchSprite test3;
	StretchSprite test4;
	StretchSprite test5;
	float test5X = 400;
	float test5Y = 200;
	float t; //time

	//test 5 default set to Rhombus
	float ax1 = -100; float ay1 = -100;
	float ax2 = -60; float ay2 = 100;
	float ax3 = 60; float ay3 = 100;
	float ax4 = 100; float ay4 = -100;
	
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
		
		// Sprite 4 will turn slowly - Currently Buggy and needs work
		double a1 = t/5;//Math.PI/4;//3*Math.PI/4;//t/5;
		double a2 = a1 + Math.PI / 2;
		double a3 = a2 + Math.PI / 2;
		double a4 = a3 + Math.PI / 2;
		a2 += Math.PI / 6 * Math.sin(t);
		a3 += Math.PI / 6 * Math.cos(t);
		float xc = 700;
		float yc = 100;
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
		
		/*
		 * Test 5 - editing each vertex in the sprite
		 *
		 * Vertex 1 is the bottom left point, from there on vertex2 etc is clockwise
		 * v2		v3
		 * v1		v4
		/*test5X and test5Y change the position of the sprite
		 * alternatively you can set them to 0 or remove them to use the vertexes
		 * 
		 * Can distort if you try to make an hourglass or weird shapes 
		 */

		if(ax2 >= ax3){
			//reset to square
			ax2 = ax1;
			ax3 = ax4;
		}
		else
		{
			ax2 += pSecondsElapsed * 20;
			ax3 -= pSecondsElapsed * 20;
		}
		
		test5.setPosition(ax1 + test5X, ay1 + test5Y, ax2 + test5X, ay2+ test5Y, ax3 + test5X, ay3+ test5Y, ax4 + test5X, ay4+ test5Y);
	}
}