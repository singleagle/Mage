package com.enjoy.mage.graphics;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.entity.ItemEntity;
import com.enjoy.mage.entity.MageScene;
import com.enjoy.mage.entity.NPC;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.manager.UIManager;
import com.enjoy.mage.process.GoodsDropProcessor;
public class Controller {
	BitmapTextureAtlas mControllerAtlas;
	TextureRegion mUp;
	TextureRegion mDown;
	TextureRegion mLeft;
	TextureRegion mRight;	
	TextureRegion mAttack;
	
	int WIDTH=720;
	int HEIGHT=480;
	
	int mOffsetX=3;
	int mOffsetY=280;

	public void onLoadResources()
	{
		TextureManager tm = Global.GetEngine().getTextureManager();
		this.mControllerAtlas=new BitmapTextureAtlas(tm, 128,256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mLeft=GetRegion("left.png",0,0);
		mDown=GetRegion("down.png",0,64);
		mRight=GetRegion("right.png",64,0);
		mUp=GetRegion("up.png",64,64);
		mAttack=GetRegion("attack.png",0,128);
		Tools.LoadAtlas(mControllerAtlas);
	}
	
	public void onLoadInScene(Scene scene)
	{
	    Sprite left=getLeftSprite(this.mOffsetX+20,this.mOffsetY+67,scene);
	    scene.attachChild(left);
	    scene.registerTouchArea(left);
	    left.setZIndex(UIConfig.CONTROLLER_LEVEL);
	    
	    Sprite down=getDownSprite(this.mOffsetX+92,this.mOffsetY+108,scene);
	    scene.attachChild(down);
	    scene.registerTouchArea(down);
	    down.setZIndex(UIConfig.CONTROLLER_LEVEL);
	    
	    Sprite right=getRightSprite(this.mOffsetX+160,this.mOffsetY+66,scene);
	    scene.attachChild(right);
	    scene.registerTouchArea(right);
	    right.setZIndex(UIConfig.CONTROLLER_LEVEL);
	    
	    Sprite up=getUpSprite(this.mOffsetX+92,this.mOffsetY+12,scene);
	    scene.attachChild(up);
	    scene.registerTouchArea(up);	
	    up.setZIndex(UIConfig.CONTROLLER_LEVEL);
	    
	    Sprite mAttack=getAttackSprite(700,330,scene);
	    
	    if(mAttack.getParent() != scene)
	    {
	      scene.attachChild(mAttack);
	    }
	    scene.registerTouchArea(mAttack);
	    

	    
	    mAttack.setZIndex(UIConfig.CONTROLLER_LEVEL);
	    mAttack.registerUpdateHandler(new Update(scene));
	}
	
	
	
	class Update implements IUpdateHandler
	{
		Scene mScene;
		public Update(Scene scene)
		{
			mScene=scene;
		}
		
	    @Override
		public void onUpdate(float pSecondsElapsed) {
         	if(mUpTime-mDownTime>100) //��������
        	{
         		if(((MageScene)mScene).getSceneInfo().isCanPK())  //�ж��Ƿ�ΪPK����
         		{
         			if(RoleManager.getHero().getTarget()!=null)
         			 RoleManager.getHero().attackTarget(); 

         		}
         		else
         			 UIManager.showTaskDlg((NPC)(RoleManager.getHero().getTarget()));
             	                             
        		mUpTime=0;
        		mDownTime=0;
        	}
         }

		@Override
		public void reset() {
			// TODO Auto-generated method stub			
		}
		
	}
	
	public BitmapTextureAtlas getmControllerAtlas() {
		return mControllerAtlas;
	}

	public void setmControllerAtlas(BitmapTextureAtlas mControllerAtlas) {
		this.mControllerAtlas = mControllerAtlas;
	}

	public TextureRegion getmUp() {
		return mUp;
	}

	public void setmUp(TextureRegion mUp) {
		this.mUp = mUp;
	}

	public TextureRegion getmDown() {
		return mDown;
	}

	public void setmDown(TextureRegion mDown) {
		this.mDown = mDown;
	}

	public TextureRegion getmLeft() {
		return mLeft;
	}

	public void setmLeft(TextureRegion mLeft) {
		this.mLeft = mLeft;
	}

	public TextureRegion getmRight() {
		return mRight;
	}

	public void setmRight(TextureRegion mRight) {
		this.mRight = mRight;
	}

	private TextureRegion GetRegion(String path,int x,int y)
	{
		return Tools.CreateTextureRegion(mControllerAtlas,path,x,y);
	}
	
	
	public Sprite getUpSprite(int x,int y,Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(x,y, mUp, vm)
		{
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    //this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                
            	if(pSceneTouchEvent.isActionDown())
                {
            	   this.setScale(1.5f);   

                }
                else if(pSceneTouchEvent.isActionUp())
                {
              
                	this.setScale(1.0f);
                	RoleManager.getHero().getGrh().toStand(0,0);
                }
                else if(pSceneTouchEvent.isActionMove())
                {
                	RoleManager.getHero().getGrh().toWaking(2);
        
                }
                
                return true;

		   }
		};
	}
	
	public Sprite getDownSprite(int x,int y,Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(x,y, mDown,vm)
		{
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    //this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                
            	if(pSceneTouchEvent.isActionDown())
                {
            	   this.setScale(1.5f);   

                }
                else if(pSceneTouchEvent.isActionUp())
                {
              
                	this.setScale(1.0f);
                	RoleManager.getHero().getGrh().toStand(0,0);//����ƫ��
                }
                else if(pSceneTouchEvent.isActionMove())
                {
                	RoleManager.getHero().getGrh().toWaking(-2);
        
                }
                
                return true;

		   }
		};
	}
	int mPosX=100,mPosY=0;
	
	int tPos=0;
	public Sprite getRightSprite(int x,int y,final Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(x,y, mRight,vm)
		{
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    //this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if(pSceneTouchEvent.isActionDown())
                {
            	   this.setScale(1.5f);   
            	}
                else if(pSceneTouchEvent.isActionUp())
                {        
                	this.setScale(1.0f);
                	RoleManager.getHero().getGrh().toStand(0,0);
                }
                else if(pSceneTouchEvent.isActionMove())
                {
                	RoleManager.getHero().getGrh().toWaking(1);
                }
                return true;
		   }
		};
	}
	
	public Sprite getLeftSprite(int x,int y,Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(x,y,mLeft, vm)
		{
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    //this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if(pSceneTouchEvent.isActionDown())
                {
            	   this.setScale(1.5f);   

                }
                else if(pSceneTouchEvent.isActionUp())
                {
              
                	this.setScale(1.0f);
                	RoleManager.getHero().getGrh().toStand(0,0);
                }
                else if(pSceneTouchEvent.isActionMove())
                {
                	RoleManager.getHero().getGrh().toWaking(-1);       
                }
                
                return true;

		   }
		};
	}	
	long end=0;
	
	long mDownTime=0;
	long mUpTime=0;
	
	//��ť�Ӵ��ص�����Ϊһ�غ�
	private boolean bFirstDown=true;//һ���غ��ڵ�һ�ΰ���  ������֤���ذ�ť��ʱ��
	public Sprite getAttackSprite(int x,int y,final Scene scene)
	{
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		return new Sprite(x,y,mAttack, vm)
		{
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                //this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
            	            	
            	if(bFirstDown)
            	{
            	  mDownTime=System.currentTimeMillis();
            	  bFirstDown=false;
            	}
   
            	if(pSceneTouchEvent.isActionDown())
                {
            	   this.setScale(1.3f);   
            	   
            	   //////////////////ʰȡ��Ʒ///////////////////////
        			ItemEntity item=GoodsDropProcessor.getHeroOn();
        			if(item!=null)
        			{
        				RoleManager.getHero().pickUp(item);
        				GoodsDropProcessor.removeFromList(item);
        			}
        			///////////////////////////////////////
            	   
                }
                else if(pSceneTouchEvent.isActionUp())
                {   
                	mUpTime=System.currentTimeMillis();
                	bFirstDown=true;
                	this.setScale(1.0f);                 	
                }
            	

            	if(pSceneTouchEvent.isActionUp())
            	{


            	}
            	//SceneManager.gotoScene(1000);
                return true;
		   }
		};
	}
}
