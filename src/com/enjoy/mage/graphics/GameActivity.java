package com.enjoy.mage.graphics;
import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSCounter;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.SceneType;
import com.enjoy.mage.data.TasksReader;
import com.enjoy.mage.entity.MYScene;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.manager.SceneManager;
import com.enjoy.mage.manager.UIManager;
import com.enjoy.mage.process.DataAccessor;
import com.enjoy.mage.ui.GameMenu;
import com.enjoy.mage.ui.IClickListener;
import com.enjoy.mage.ui.ModelDlg;
import com.kuguo.pushads.PushAdsManager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.KeyEvent;


public class GameActivity extends BaseGameActivity implements IOnMenuItemClickListener {
    	 
    private static final int CAMERA_WIDTH = SceneConfig.CAMERA_WIDTH;
    private static final int CAMERA_HEIGHT = SceneConfig.CAMERA_HEIGHT;
	private Camera mCamera;
	
	//--------------------------------------------------------------------------------------------------
    protected MenuScene mMenuScene;
    private Font mFont;
	private BitmapTextureAtlas mFontTexture;
	
	ModelDlg mExitDlg=new ModelDlg();

    @Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub		
		mCamera=new Camera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
		EngineOptions options=new EngineOptions(true,ScreenOrientation.LANDSCAPE,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);				
		options.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(options);
	}
	
	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		Global.Init(mEngine); //��������	
        this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);       
        FontFactory.setAssetBasePath("font/");
        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),32, true,Color.GREEN);
        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
        this.mEngine.getFontManager().loadFont(this.mFont);
		
        MultipleManager.onLoadResource();
		SceneManager.onLoadResources(); //������
		RoleManager.onLoadResources();  //��ɫ��
		UIManager.onLoadResources();    //UI��
	
		TasksReader.onLoad();		
		mExitDlg.onLoadResources();
	}
	
    int x=0,y=0;
    Text text;
    Scene mScene;
    FPSCounter mFpsCounter = new FPSCounter();
	@Override
	public Scene onLoadScene() {	
		
		PushAdsManager.getInstance().receivePushMessage(this);
		// TODO Auto-generated method stub
		this.mEngine.registerUpdateHandler(mFpsCounter);
		this.mEngine.registerUpdateHandler(new FPSLogger());		
		this.mMenuScene = this.createMenuScene();
        
		Scene scene=SceneManager.startGameMenu();
		
		//�˵��˳��¼�����
		((GameMenu)scene).setmClick(new IClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
			    GameActivity.this.finish();
			    System.exit(0);
			}
		});
	    mExitDlg.onLoadScene("ȷ���˳���Ϸ��",new IClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				mExitDlg.back();
			}
		}, new IClickListener() {
			
			@Override
			public void onClick() {
				GameActivity.this.finish();
				System.exit(0);
				
			}
		});	    
	    return scene;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
        MYScene scene=(MYScene)(this.mEngine.getScene());
        if(scene.getSceneInfo().getType()!=SceneType.SCENE_TYPE_MENU)
	       scene.setChildScene(mExitDlg,false, true, true);

	}
		
	//�浵�¼��Ĵ���
    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
            
    	   MYScene curScene=(MYScene)(this.mEngine.getScene());
    	   if(curScene.getSceneInfo().getType()!=SceneType.SCENE_TYPE_MENU)
    	   {
    	   if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN)
    	   {
                   if(!curScene.hasChildScene())
                   {
    		          this.mEngine.getScene().setChildScene(this.mMenuScene, false, true, true);
                   }
                   else
                   {
                	   curScene.back();
                   }
                   return true;
            } else {
                    return super.onKeyDown(pKeyCode, pEvent);
            }
    	   }
    	   else
    	   {
    		   return super.onKeyDown(pKeyCode, pEvent);
    	   }
    	

    }

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		BufferObjectManager.getActiveInstance().clear();		
		super.onDestroy();
		mEngine=null;
	}
	
    protected MenuScene createMenuScene() {
        final MenuScene menuScene = new MenuScene(this.mCamera);
        final IMenuItem saveMenuItem = new ColorMenuItemDecorator(new TextMenuItem(0, this.mFont, "�������"), 1.0f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        saveMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(saveMenuItem);
        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);
        menuScene.setOnMenuItemClickListener(this);
        return menuScene;
     }

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		  switch(pMenuItem.getID()) {
		    case 0:
		    	DataAccessor.saveUser();
		    	this.mEngine.getScene().back();
		    	return true;
		    default:
		    	return false;
		  }
	}
}