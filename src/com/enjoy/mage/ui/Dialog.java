package com.enjoy.mage.ui;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;

import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.graphics.DlgGrh;
import com.enjoy.mage.graphics.TextGrh;

import android.graphics.Point;

/**
 * @author shaoleibo
 * ����ؼ��Ļ���
*/
public class Dialog {
	protected Point mPos=new Point(0,0);
	protected Sprite mDlgSprite;
	protected Rectangle mCloseRect=new Rectangle(277,0,51,38);
	protected TextGrh mTextGrh=new TextGrh();  //���ƿؼ��ϵ����ֻ���
	protected Scene mScene;
	
	public TextGrh getTextGrh() {
		return mTextGrh;
	}

	public Dialog(Point pPos)
	{
		mPos=pPos;					
	}
	
	public void onLoadScene(Scene scene)
	{
		mScene=scene;
		mTextGrh.onLoadResource();		
		mDlgSprite=new Sprite(mPos.x,mPos.y,DlgGrh.getmDlgRegion());//! �ڻ���ע�� �����¼���������	
		
		//keepInCenter();//����
		
		scene.attachChild(mDlgSprite);
		mDlgSprite.setZIndex(UIConfig.UI_LEVEL);
		mDlgSprite.setAlpha(0.8f);	
		onClose();
	}
		
	public void onClose()
	{
		mDlgSprite.setVisible(false);
	}
	
	public void show()
	{
		mDlgSprite.setVisible(true);
	}
	
	protected boolean bInCloseRect(float x,float y)
	{
		Rectangle localRect=new Rectangle(mPos.x+mCloseRect.getX(),mPos.y+mCloseRect.getY(),mCloseRect.getWidth(),mCloseRect.getHeight());		
		return localRect.contains(x, y);		
	}
	
	
//	//ÿ��DIALOG����ʾ��� ���ܹ���ͬ   �����޷������رմ��ڵĴ����¼�   ԭ���в����
//	private void keepInCenter()
//	{
//		mPos.x=(SceneConfig.CAMERA_WIDTH-(int)(mDlgSprite.getWidth()))/2;
//		mPos.y=(SceneConfig.CAMERA_HEIGHT-(int)(mDlgSprite.getHeight()))/2;
//		mDlgSprite.setPosition(mPos.x,mPos.y);
//	}
}
