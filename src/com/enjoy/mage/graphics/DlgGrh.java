package com.enjoy.mage.graphics;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.UIConfig;

/**
 * @author shaoleibo
 * 
 */
public class DlgGrh {
    
	public static TextureRegion getmDlgRegion() {
		return mDlgRegion;
	}
	public static TextureRegion getmPlayerRegion() {
		return mPlayerRegion;
	}
	public static TextureRegion getmBagRegion() {
		return mBagRegion;
	}
	public static TextureRegion getmRoleInfoIconRegion() {
		return mRoleInfoIconRegion;
	}
	public static TextureRegion getmBagIconRegion() {
		return mBagIconRegion;
	}
	public static TextureRegion getmTaskIconRegion() {
		return mTaskIconRegion;
	}
	public static TextureRegion getmRingIconRegion() {
		return mRingIconRegion;
	}
	static TextureRegion mDlgRegion,mPlayerRegion,mBagRegion,mRoleInfoIconRegion,mBagIconRegion,mTaskIconRegion,mRingIconRegion;

	static Sprite mDlgSprite,mPlayerSprite,mBagSprite;
	static private BitmapTextureAtlas mAtlas;

	private static TextureRegion mLineRegion;
	static public void onLoadResources()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("textures/ui/");
		TextureManager tm = Global.GetEngine().getTextureManager();
		mAtlas=new BitmapTextureAtlas(tm, 1024,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		mDlgRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.DLG_FILE),0,0);
		mPlayerRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.DLG_PALYER),330,0);
		mBagRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.DLG_BAGTILE),330,374);
		
		mRoleInfoIconRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.ROLE_ICON),570,0);
		mBagIconRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.BAG_ICON),618,0);
		mTaskIconRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.TASK_CION),661,0);
		mRingIconRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.RING_ICON),703,0);
		
		mLineRegion=Tools.CreateTextureRegion(mAtlas,Tools.GetUIFile(UIConfig.LINE),767,0);
		
		Tools.LoadAtlas(mAtlas);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("textures/");
	}
	
	
	public static TextureRegion getLineRegion() {
		return mLineRegion;
	}	
	
	public static void unload()
	{
		mAtlas.unload();
		//Global.GetEngine().getTextureManager().unloadTextures(mAtlas);
		mRingIconRegion.getTexture().unload();
		
	}
}