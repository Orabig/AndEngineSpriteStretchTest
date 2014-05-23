package com.crocoware.andengine.sample;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity {
	private Camera mCamera;

	@Override
	public EngineOptions onCreateEngineOptions() {

		int cameraWidth = 800;
		int cameraHeight = 480;

		mCamera = new Camera(0, 0, cameraWidth, cameraHeight);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(cameraWidth, cameraHeight), mCamera);
	}

	private TiledTextureRegion textureRegion;
	private TextureRegion textureRegionFull;

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlas mBitmapTextureAtlas;
		BitmapTextureAtlas mBitmapTextureAtlas2;
		/* Load all the textures this game needs. */
		mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),
				1024, 1024);
		textureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(mBitmapTextureAtlas, this,
						"sample_grid.png", 0, 0, 5, 5);

		mBitmapTextureAtlas.load();
		mBitmapTextureAtlas2 = new BitmapTextureAtlas(this.getTextureManager(),
				1024, 1024);
		textureRegionFull = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mBitmapTextureAtlas2, this, "sample_grid.png",
						0, 0);

		mBitmapTextureAtlas2.load();

	}

	@Override
	protected Scene onCreateScene() {
		return new StretchSampleScene(getEngine(), textureRegion,
				textureRegionFull);
	}

}
