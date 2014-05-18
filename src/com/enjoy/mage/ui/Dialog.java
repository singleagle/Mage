package com.enjoy.mage.ui;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
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
	protected Rectangle mCloseRect;
	protected TextGrh mTextGrh=new TextGrh();  //���ƿؼ��ϵ����ֻ���
	protected Scene mScene;
	
	public TextGrh getTextGrh() {
		return mTextGrh;
	}

	public Dialog(Point pPos)
	{
		mPos=pPos;	
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mCloseRect=new Rectangle(277,0,51,38, vm);
	}
	
	public void onLoadScene(Scene scene)
	{
		mScene=scene;
		mTextGrh.onLoadResource();		
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mDlgSprite=new Sprite(mPos.x,mPos.y,DlgGrh.getmDlgRegion(), vm);//! �ڻ���ע�� �����¼���������	
		
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
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		Rectangle localRect=new Rectangle(mPos.x+mCloseRect.getX(),mPos.y+mCloseRect.getY(),
				mCloseRect.getWidth(),mCloseRect.getHeight(), vm);		
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
