package com.enjoy.mage.graphics;
import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSCounter;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.enjoy.mage.common.Global;
import com.enjoy.mage.config.SceneConfig;
import com.enjoy.mage.data.SceneType;
import com.enjoy.mage.data.TasksReader;
import com.enjoy.mage.entity.MageScene;
import com.enjoy.mage.manager.MultipleManager;
import com.enjoy.mage.manager.RoleManager;
import com.enjoy.mage.manager.GameWord;
import com.enjoy.mage.manager.UIManager;
import com.enjoy.mage.process.DataAccessor;
import com.enjoy.mage.ui.GameMenu;
import com.enjoy.mage.ui.IClickListener;
import com.enjoy.mage.ui.ModelDlg;
import com.kuguo.pushads.PushAdsManager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.KeyEvent;


public class GameActivity extends SimpleBaseGameActivity implements IOnMenuItemClickListener {
    	 
    private static final int CAMERA_WIDTH = SceneConfig.CAMERA_WIDTH;
    private static final int CAMERA_HEIGHT = SceneConfig.CAMERA_HEIGHT;
	private Camera mCamera;
	
	//--------------------------------------------------------------------------------------------------
    protected MenuScene mMenuScene;
    private Font mFont;
	private BitmapTextureAtlas mFontTexture;
	
	ModelDlg mExitDlg=new ModelDlg();
	GameWord mGameWord;

    @Override
	public EngineOptions onCreateEngineOptions(){
		// TODO Auto-generated method stub		
		mCamera=new Camera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
		EngineOptions options=new EngineOptions(true,ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);				
		
		mGameWord = GameWord.getInstance();
		return options;
	}
	
	@Override
	public void onCreateResources(){
		Global.Init(mEngine); 
		TextureManager tm = mEngine.getTextureManager();
        this.mFontTexture = new BitmapTextureAtlas(tm, 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);       
        FontFactory.setAssetBasePath("font/");
        this.mFont = new Font(mEngine.getFontManager(), this.mFontTexture, Typeface.create(Typeface.DEFAULT, 
        		Typeface.BOLD),32, true,Color.GREEN);
        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
        this.mEngine.getFontManager().loadFont(this.mFont);
		
        MultipleManager.onLoadResource();
        mGameWord.loadResources(); 
		RoleManager.onLoadResources();  
		UIManager.onLoadResources();    
	
		TasksReader.onLoad();		
		mExitDlg.onLoadResources();
	}
	
    int x=0,y=0;
    Text text;
    Scene mScene;
    FPSCounter mFpsCounter = new FPSCounter();
    
	@Override
	public Scene onCreateScene(){	
		
		PushAdsManager.getInstance().receivePushMessage(this);
		this.mEngine.registerUpdateHandler(mFpsCounter);
		this.mEngine.registerUpdateHandler(new FPSLogger());		
		this.mMenuScene = this.createMenuScene();
        
		Scene scene=mGameWord.startGameMenu();
		
		((GameMenu)scene).setmClick(new IClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
			    GameActivity.this.finish();
			    System.exit(0);
			}
		});
	    mExitDlg.onLoadScene("确定要退出？",new IClickListener() {
			
			@Override
			public void onClick() {
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
        MageScene scene=(MageScene)(this.mEngine.getScene());
        if(scene.getSceneInfo().getType()!=SceneType.SCENE_TYPE_MENU)
	       scene.setChildScene(mExitDlg,false, true, true);

	}
		
    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
            
    	   MageScene curScene=(MageScene)(this.mEngine.getScene());
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

	
    protected MenuScene createMenuScene() {
        final MenuScene menuScene = new MenuScene(this.mCamera);
        VertexBufferObjectManager vm = mEngine.getVertexBufferObjectManager();
        final IMenuItem saveMenuItem = new ColorMenuItemDecorator(new TextMenuItem(0, this.mFont, "是否需要保存？", vm), org.andengine.util.color.Color.WHITE,
        		org.andengine.util.color.Color.BLACK);
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