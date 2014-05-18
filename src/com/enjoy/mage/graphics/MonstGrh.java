package com.enjoy.mage.graphics;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.common.Tools;
import com.enjoy.mage.config.MultipleGrhCfg;
import com.enjoy.mage.config.UIConfig;
import com.enjoy.mage.entity.IMonstAttack;
import com.enjoy.mage.entity.Role;
import com.enjoy.mage.manager.MultipleManager;


public class MonstGrh {
	private String mRunFile,mAttackFile,mHPFile;	
	private Point mPos;
	
	//ͼ���ߴ�
    private BitmapTextureAtlas mRunAtlas;
	private TiledTextureRegion mRun;
	
	private BitmapTextureAtlas mAttackAtlas;
	private TiledTextureRegion mAttack;
	private Sprite mHPSprite;
		
	int mStep=1;//ÿ���ƶ�����
	private Point mRunOffset,mStandOffset,mAttackOffset;
	
	private int mStandImageId=0;
	
	//ͼ�β�������
	MonstPara mPara;
		
	public MonstGrh(int pStandImageId,String pRunFile,String pAttackFile,MonstPara pPara)
	{		
		mStandImageId=pStandImageId;
        mRunFile=pRunFile;	
		mAttackFile=pAttackFile;		
		mRunOffset=new Point(pPara.mRunOffsetX,pPara.mRunOffsetY);
		mStandOffset=new Point(pPara.mStandOffsetX,pPara.mStandOffsetY);
        mAttackOffset=new Point(pPara.mAttackOffsetX,pPara.mAttackOffsetY);
		mPara=pPara;		
		mPara.mAttackFrame=pPara.mAttackCol*pPara.mAttackRow;
		mPara.mRunFrame=pPara.mRunCol*pPara.mRunRow;
		mPara.mAttackAtlasSizeW=Tools.GetNearBinary(pPara.mAttackCol*pPara.mAttackTileWidth);
		mPara.mAttackAtlasSizeH=Tools.GetNearBinary(pPara.mAttackRow*pPara.mAttackTileHeight);
		mPara.mRunAtlasSizeW=Tools.GetNearBinary(pPara.mRunCol*pPara.mRunTileWidth);
		mPara.mRunAtlasSizeH=Tools.GetNearBinary(pPara.mRunRow*pPara.mRunTileHeight);
	}
	
	public void setPos(Point pPos)
	{
		mPos=pPos; //
	}

	public void onLoadResources()
	{
		Tools.setAssetsPath("textures/monst/");
		//mRunAtlas=new BitmapTextureAtlas(mPara.mRunAtlasSizeX,mPara.mRunAtlasSizeY,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//mRun=Tools.CreateTiledRegion(mRunAtlas,mRunFile,0,0,mPara.mRunCol,mPara.mRunRow);
		
		TextureManager tm = Global.GetEngine().getTextureManager();
		mAttackAtlas=new BitmapTextureAtlas(tm, mPara.mAttackAtlasSizeW,mPara.mAttackAtlasSizeH,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mAttack=Tools.CreateTiledRegion(mAttackAtlas,mAttackFile,0,0,mPara.mAttackCol,mPara.mAttackRow);
		
	    Tools.LoadAtlas(mAttackAtlas);
		Tools.setAssetsPath("textures/");		
	}
	AnimatedSprite mRunSprite,mAttackSprite;
	
	private Sprite mStandSprite,mBoardSprite;

	
	public void onLoadInScene(Scene scene)
	{
		//mRunSprite=new AnimatedSprite(mPos.x-mRunOffset.x,mPos.y-mRunOffset.y,mPara.mRunTileWidth,mPara.mRunTileHeight,mRun);		
		mStandSprite=MultipleManager.MSTAND.getSprite(mStandImageId);
		VertexBufferObjectManager vm = Global.GetEngine().getVertexBufferObjectManager();
		mAttackSprite=new AnimatedSprite(mPos.x-mAttackOffset.x,mPos.y-mAttackOffset.y,mPara.mAttackTileWidth,mPara.mAttackTileHeight,
				mAttack, vm);
		
		//Path path=new Path(5);//ÿ������
		//path.to(10,10).to(300,10);
		//mRunSprite.animate(Tools.LongArray(mPara.mRunFrame,300),0,mPara.mRunFrame-1,true);
		mStandSprite.setZIndex(mPos.y);
		mAttackSprite.setZIndex(mPos.y);
		//mRunSprite.setZIndex(mPos.y);
	
		mBoardSprite=MultipleManager.FACE.getSprite(MultipleGrhCfg.MONST_HPBOARD_ID); //Ѫ�ۿ�		
		mBoardSprite.setZIndex(UIConfig.UI_LEVEL-1);
		mHPSprite=MultipleManager.FACE.getSprite(MultipleGrhCfg.MONST_HP_ID);
		mBoardSprite.attachChild(mHPSprite);
		
		//mRunSprite.registerUpdateHandler(new MountUpdate());
		//scene.attachChild(mRunSprite);
	
		if(!mStandSprite.hasParent())
		{
			scene.attachChild(mStandSprite);
			
		}
	
		scene.attachChild(mAttackSprite);
		scene.attachChild(mBoardSprite);

		mBoardSprite.setPosition(380,5);
		mHPSprite.setPosition(35,22);
		stand(1);	
	}
	
	public void updateHPVal(double percent)
	{
		mHPSprite.setWidth((int)(percent*86));
	}
	
	public void hideAll()
	{
		//mBoardSprite.setVisible(false);
		mStandSprite.setVisible(false);
		mAttackSprite.setVisible(false);		
	}
	
	public void hideHPBar()
	{
		mBoardSprite.setVisible(false);
	}
	
	//
	public void walkTo(Point targetPos)
	{
		hideAll();
		mRunSprite.setVisible(true);
		int xDir=(targetPos.x-mPos.x)>=0?1:-1;
		int yDir=(targetPos.y-mPos.y)>=0?1:-1;
		//��X�������ƶ�
		if((targetPos.x==mPos.x)&&(targetPos.y==mPos.y))
		{
			stand(xDir);
			return;
		}		
		if(xDir>0)
		{
			if(mPos.x>=targetPos.x)
			{
				mPos.x=targetPos.x;
				mPos.y+=(mStep*yDir);
				if(yDir>0)
				{
					if(mPos.y>=targetPos.y)
					{
						mPos.y=targetPos.y;
						
					}
				}
			}
			
		}
		else
		{
			if(mPos.x<=targetPos.x)
			{
				mPos.x=targetPos.x;
				mPos.y+=(mStep*yDir);
				if(yDir<0)  //Y���� ����
				{
					if(mPos.y<=targetPos.y)
					{
						mPos.y=targetPos.y;
					}
				}
			}
		}
		
		mPos.x+=(mStep*xDir);
		this.mRunSprite.setPosition(mPos.x-mRunOffset.x,mPos.y-mRunOffset.y);
	}

	public void attackAni(IMonstAttack pAttack)
	{
		
	    Role localTarget=pAttack.getTarget();
	    if(localTarget.isDie())
	    	stand(1);
	    
	    if(localTarget!=null&&!localTarget.isDie())
	    {
		  hideAll();

		  mAttackSprite.setVisible(true);	
		
		  int xDir=(localTarget.getPos().x-mPos.x)>=0?1:-1;
	
		  if(System.currentTimeMillis()-mCurTime>300)
		  {
		    mCurTime=System.currentTimeMillis();
		    mCurFrame++;
		    if(mCurFrame==Integer.MAX_VALUE)
		    {
			   mCurFrame=0;
		    }	   
			if((mCurFrame%mPara.mAttackFrame==(mPara.mAttackFrame-1)/2)
					 ||(mCurFrame%mPara.mAttackFrame==(mPara.mAttackFrame-1)))
			 {
			    pAttack.onAttack(); //�Թ����˺�����
			 }
		 }
		
		if(xDir>0)
		{
			mAttackSprite.setFlippedHorizontal(false);
			mAttackSprite.setCurrentTileIndex(mCurFrame%mPara.mAttackFrame);
			mAttackSprite.setPosition(mPos.x-mAttackOffset.x,mPos.y-mAttackOffset.y);
			
		}
		else
		{                                                 
			mAttackSprite.setFlippedHorizontal(true);   //�����û��� ʵ��ˮƽ��ת
			mAttackSprite.setCurrentTileIndex(mCurFrame%mPara.mAttackFrame);
			
			float localOffsetx=mAttackSprite.getWidth()-2*mAttackOffset.x; //ˮƽ��ת������ƫ��
			mAttackSprite.setPosition(mPos.x-mAttackOffset.x-localOffsetx,mPos.y-mAttackOffset.y);

		}
	   }
	}
	
	public void stand(int pDir)
	{
		hideAll();
		if(pDir>0)
		{
			this.mStandSprite.setVisible(true);
			this.mStandSprite.setPosition(mPos.x-this.mStandOffset.x,mPos.y-this.mStandOffset.y);
		}
		else
		{
			
			this.mStandSprite.setVisible(true);
			this.mStandSprite.setPosition(mPos.x-this.mStandOffset.x,mPos.y-this.mStandOffset.y);
		}
				
	}
	
	long mCurTime=0;
	//
	boolean bInView=false;	
	int mCurFrame=0;//��ǰ֡
	private int mStandDir=1;
	

}
