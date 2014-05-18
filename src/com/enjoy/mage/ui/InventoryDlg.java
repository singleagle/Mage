package com.enjoy.mage.ui;




import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.data.Inventory;
import com.enjoy.mage.graphics.DlgGrh;






import android.graphics.Point;

public class InventoryDlg extends Dialog{
	
	Point mLeftUpPos=new Point(47,51);
	Inventory mInventory=new Inventory();
	
	public InventoryDlg(Point pPos,Inventory pInventory) {
		super(pPos);
		mInventory=pInventory;
		// TODO Auto-generated constructor stub
	}
	
	public InventoryDlg(int x,int y,Inventory pInventory)
	{
		this(new Point(x,y),pInventory);
	}
	
	public InventoryDlg(int x,int y)
	{
		super(new Point(x,y));
	}
	
	public void onLoadScene(Scene scene)
	{
		super.onLoadScene(scene);
		onCreateSprite(scene);
		mInventory.onLoadInDlg(mDlgSprite);//��ӵ���������
	}
		
	private void onCreateSprite(Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		for(int i=0;i<5;i++)
		{
			Sprite localSprite=new Sprite(mLeftUpPos.x,mLeftUpPos.y+45*i,DlgGrh.getmBagRegion(), vm)
			{
				
				///contains��ѭ���б����� ��pX,pYΪ ���ص���� ��ͨ���ж�containsȻ����ִ��onAreaTouched
				@Override
				public boolean contains(final float pX, final float pY) {
					if(bInCloseRect(pX,pY))
					    return true;
					else
						return false;
				}

				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY) {
					
		            onClose();
					return true;
				}
				
			};
			
			mDlgSprite.attachChild(localSprite);	
			scene.registerTouchArea(localSprite);
	  }
	}
}
