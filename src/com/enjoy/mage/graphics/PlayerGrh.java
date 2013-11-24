package com.enjoy.mage.graphics;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.BaseSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.manager.MultipleManager;

import android.graphics.Point;

public class PlayerGrh {
	public BitmapTextureAtlas getmAttackAtlas() {
		return mAttackAtlas;
	}

	public void setmAttackAtlas(BitmapTextureAtlas mAttackAtlas) {
		this.mAttackAtlas = mAttackAtlas;
	}

	TextureRegion mStandLeft,mStandRight,mHeroUI;
	TiledTextureRegion mRunLeft,mRunRight,mAttackLeft,mAttackRight;
	BitmapTextureAtlas mRunAtlas,mStandAtlas,mAttackAtlas,mHeroUIAtlas;
	
	///HP MP
	TiledTextureRegion mHP,mMP;
	BitmapTextureAtlas mHPMPAtlas;
	AnimatedSprite mHPSprite;
	AnimatedSprite mMPSprite;
	String mHPMPFileName;
	int mFaceImage=MultipleGrhCfg.FACE_ID;//
	public BitmapTextureAtlas getmRunAtlas() {
		return mRunAtlas;
	}

	public void setmRunAtlas(BitmapTextureAtlas mRunAtlas) {
		this.mRunAtlas = mRunAtlas;
	}

	public BitmapTextureAtlas getmStandAtlas() {
		return mStandAtlas;
	}

	public void setmStandAtlas(BitmapTextureAtlas mStandAtlas) {
		this.mStandAtlas = mStandAtlas;
	}

	public Point getmPos() {
		return mPos;
	}

	private Point mPos;//������ꣻ�������   ����ڻ�׼��
	private Point mStandOffset,mRunOffset,mAttackOffset,mDieOffset;//��׼��ƫ��
	
	AnimatedSprite mRunLeftSprite,mRunRightSprite,mAttackLeftSprite,mAttackRightSprite;
	Sprite mStandLeftSprite,mStandRightSprite,mHeroUISprite,mDieSprite;
	Scene mScene;
	long mAniTime=80;//������ʾʱ�� 
	long mAttackTime=70;
	int mDir=1;//�� ������¼������������
    float mVx=0.5f,mVy=0.5f;
    
    String mStandRFile,mStandLFile,mRunRFile,mRunLFile,mAttackRFile,mAttackLFile,mHeroUIFile;
    
    public PlayerGrh()
    {
       mPos=new Point(300,400);      
       mStandOffset=new Point(124,128);
       mRunOffset=new Point(124,128);
       mAttackOffset=new Point(155,115);
       mDieOffset=new Point(46,101);
       
       this.mStandRFile="role_r_stand.png";
       this.mStandLFile="role_l_stand.png";
       this.mRunRFile="role_r.png";
       this.mRunLFile="role_l.png";
       this.mAttackRFile="attack_r.png";
       this.mAttackLFile="attack_l.png";
       this.mHeroUIFile="hero_ui.png";
       this.mHPMPFileName="hero_ui1.png";
    }
	
    public void setPos(Point pPos)
    {
    	mPos=pPos;
    }
    
    public void setPos(int x,int y)
    {
    	mPos=new Point(x,y);
    }
    
	public void onLoadResource()
	{
		Tools.setAssetsPath("textures/");
		mStandAtlas=new BitmapTextureAtlas(512,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mStandRight=Tools.CreateTextureRegion(mStandAtlas,mStandRFile,0,0);	
		mStandLeft=Tools.CreateTextureRegion(mStandAtlas,mStandLFile,256,0);
		
		mRunAtlas=new BitmapTextureAtlas(4096,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mRunRight=Tools.CreateTiledRegion(mRunAtlas,mRunRFile,0,0,10,1);
		mRunLeft=Tools.CreateTiledRegion(mRunAtlas,mRunLFile,0,256,10,1);	
		
		mAttackAtlas=new BitmapTextureAtlas(4096,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mAttackRight=Tools.CreateTiledRegion(mAttackAtlas,mAttackRFile,0,0,11,1);
		mAttackLeft=Tools.CreateTiledRegion(mAttackAtlas,mAttackLFile,0,512,11,1);
		
	    mHeroUIAtlas=new BitmapTextureAtlas(256,128,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    mHeroUI=Tools.CreateTextureRegion(mHeroUIAtlas, mHeroUIFile, 0, 0);
	    
	    mHPMPAtlas=new BitmapTextureAtlas(128,32,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    mHP=Tools.CreateTiledRegion(mHPMPAtlas, mHPMPFileName, 0,0,1,2);
	    mMP=Tools.CreateTiledRegion(mHPMPAtlas, mHPMPFileName, 0,0,1,2);		
		Tools.LoadAtlas(mRunAtlas,mStandAtlas,mAttackAtlas,mHeroUIAtlas,mHPMPAtlas);
		
	}
	
	private void createSprite()
	{
		mDieSprite=MultipleManager.FACE.getSprite(MultipleGrhCfg.DIE_ID);
		
    	mStandLeftSprite=new Sprite(mPos.x-mStandOffset.x,mPos.y-mStandOffset.y,mStandLeft);
    	mStandRightSprite=new Sprite(mPos.x-mStandOffset.x,mPos.y-mStandOffset.y,mStandRight);
    	mRunLeftSprite=new AnimatedSprite(mPos.x-mRunOffset.x,mPos.y-mRunOffset.y,mRunLeft);
    	mRunRightSprite=new AnimatedSprite(mPos.x-mRunOffset.x,mPos.y-mRunOffset.y,mRunRight);
    	   	
    	mAttackLeftSprite=new AnimatedSprite(mPos.x-mAttackOffset.x,mPos.y-mAttackOffset.y,mAttackLeft);
    	mAttackRightSprite=new AnimatedSprite(mPos.x-mAttackOffset.x,mPos.y-mAttackOffset.y,mAttackRight);  
    	
    	mHeroUISprite=new Sprite(0,0,mHeroUI);
    	Sprite localFaceSprite=MultipleManager.FACE.getSprite(mFaceImage);
    	localFaceSprite.setPosition(2,4);
    	mHeroUISprite.attachChild(localFaceSprite);
    	
    	mHPSprite=new AnimatedSprite(65,22,97,9,mHP);
    	
    	mHPSprite.setCurrentTileIndex(1);
    	
    	mMPSprite=new AnimatedSprite(65,35,97,9,mMP);
    
	}
	
		
    public void onLoadInScene(Scene scene)	
    {
    	
    	createSprite();
    	
    	//��֤һ������ֻ���һ��
    	if(scene.getChildIndex(mStandRightSprite)==-1)
    	{
    	  scene.attachChild(mStandRightSprite);
    	  scene.attachChild(mStandLeftSprite);
    	  scene.attachChild(mRunLeftSprite);
    	  scene.attachChild(mRunRightSprite);
    	  scene.attachChild(mAttackLeftSprite);
    	  scene.attachChild(mAttackRightSprite);
    	  scene.attachChild(mHeroUISprite);
    	  scene.attachChild(mHPSprite);
    	  scene.attachChild(mMPSprite);
    	  scene.attachChild(mDieSprite);
    	}
    	mHeroUISprite.setZIndex(UIConfig.UI_LEVEL);
    	mHPSprite.setZIndex(UIConfig.UI_LEVEL);
    	mMPSprite.setZIndex(UIConfig.UI_LEVEL);
    		
    	mRunLeftSprite.animate(this.mAniTime);
    	mRunRightSprite.animate(this.mAniTime);
    	   
    	//mAttackLeftSprite.animate(this.mAttackTime);    	
    	//mAttackRightSprite.animate(this.mAttackTime);   	
    	toStand(0,0);
    }
    int testVal=0;
    public void update()
    {
    	updateZIndex(mPos.y);   	
    }
    
    public void updateZIndex(int index)
    {
    	mStandRightSprite.setZIndex(index);
    	mStandLeftSprite.setZIndex(index);
    	mRunLeftSprite.setZIndex(index);
    	mRunRightSprite.setZIndex(index);
    	mAttackLeftSprite.setZIndex(index);
    	mAttackRightSprite.setZIndex(index); 
    	mDieSprite.setZIndex(index);
    }
    
    private void hideAll()
    {
    	mStandLeftSprite.setVisible(false);
    	mStandRightSprite.setVisible(false);
    	mRunLeftSprite.setVisible(false);
    	mRunRightSprite.setVisible(false);
    	
        mAttackLeftSprite.setVisible(false);
    	mAttackRightSprite.setVisible(false);
    	mDieSprite.setVisible(false);
    	
    }
    
    public int getCurIndex()
    {
    	if(tempSprite!=null)
    		return tempSprite.getCurrentTileIndex();
    	else
    		return -1;
    }
    
    ///x,yΪУ�����
    public void toStand(int x,int y)
    {
    	
    	hideAll();
    	if(mDir==1)
    	{
    		mStandRightSprite.setVisible(true);
    		changePos(mStandRightSprite,new Point(mPos.x+x,mPos.y+y),mStandOffset);
    	}
    	else
    	{
    		mStandLeftSprite.setVisible(true);
    		changePos(mStandLeftSprite,new Point(mPos.x+x,mPos.y+y),mStandOffset);
    	}  	
    }
    
    public void toStand()
    {
    	toStand(0,0);
    }
    
    //��������
    public void toDie()
    {
    	hideAll();
    	mDieSprite.setPosition(mPos.x-mDieOffset.x,mPos.y-mDieOffset.y);
    	mDieSprite.setVisible(true);
    }
    
    //�ı�ͼ�����ʾ���
    private void changePos(BaseSprite sprite,Point newPos,Point offset)
    {
    	sprite.setPosition(newPos.x-offset.x,newPos.y-offset.y);
    	
    }
    private AnimatedSprite tempSprite;
    
    public void toAttacking(IAnimationListener listener)
    {
    	hideAll();

    	if(mDir==1)
    	{
    		tempSprite=mAttackRightSprite;
    		mAttackRightSprite.setVisible(true);    
    		mAttackRightSprite.animate(this.mAttackTime,0,listener);    		
    		changePos(mAttackRightSprite,new Point(mPos.x,mPos.y-70),mAttackOffset);
    		
    	}
    	else
    	{
    		tempSprite=mAttackLeftSprite;
    		mAttackLeftSprite.setVisible(true);
    		
    		mAttackLeftSprite.animate(this.mAttackTime,0,listener);
    		changePos(mAttackLeftSprite,new Point(mPos.x,mPos.y-70),mAttackOffset);
    	}
   
    }   
    public void toWaking(int dir)
    {
    	hideAll();
    	switch(dir)
    	{
    	 case 1:  //��   
    		mRunRightSprite.setVisible(true);
    		
    		mDir=1;
    		this.mPos.x+=mVx*4;
    		changePos(this.mRunRightSprite,new Point(this.mPos.x,this.mPos.y),mRunOffset);
    		break;
    	 case -1: //��
    		 mRunLeftSprite.setVisible(true);

    		 mDir=-1;
    		 this.mPos.x-=mVx*4;
    		 changePos(this.mRunLeftSprite,new Point(this.mPos.x,mPos.y),mRunOffset);
    	     break;
    	 case 2: //��   		 
    		 this.mPos.y-=mVy*4;
    		 doWakingAnimate();
    		 break;
    	 case -2:	 
    		 this.mPos.y+=mVy*4;
    		 doWakingAnimate();
    		 break;
    	}
    }   
    
    private void doWakingAnimate()
    {
    	hideAll();
    	if(mDir==1)
    	{
    		mRunRightSprite.setVisible(true);
    
    		changePos(this.mRunRightSprite,new Point(this.mPos.x,this.mPos.y),mRunOffset);
    	}
    	else if(mDir==-1)
    	{
       		mRunLeftSprite.setVisible(true);
    
    		changePos(this.mRunLeftSprite,new Point(this.mPos.x,this.mPos.y),mRunOffset);
    	}
    }
    
    //���ٷ�����ʾHP
    public void updateHPVal(float per)
    {
    	mHPSprite.setWidth(per*97);
    }
    
    public void updateMPVal(float per)
    {
    	mMPSprite.setWidth(per*97);
    }   
}
