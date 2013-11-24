package com.enjoy.mage.graphics;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.enjoy.mage.common.Tools;


//�����߽�ʱ��NPC ����
public class BubleGrh {
	private BitmapTextureAtlas mAtlas;
	private TiledTextureRegion  mRegion;
    private AnimatedSprite mAniSprite;
	
	public void onLoadResource()
	{
		Tools.setAssetsPath("textures/");
		mAtlas=new BitmapTextureAtlas(128,32,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRegion=Tools.CreateTiledRegion(mAtlas,"buble.png",0,0,3,1); 
		Tools.LoadAtlas(mAtlas);
	}
	
	public void onLoadScene(Scene scene)
	{
		mAniSprite=new AnimatedSprite(0,0,34,32,mRegion);
		mAniSprite.animate(100);
		if(!mAniSprite.hasParent())
		scene.attachChild(mAniSprite);
		mAniSprite.setVisible(false);
	}
	
	public void Show(int x,int y,int z)
	{
		mAniSprite.setVisible(true);
		mAniSprite.setPosition(x, y);
		mAniSprite.setZIndex(z);		
	}
	
	public void hide()
	{
		mAniSprite.setVisible(false);
	}
}
