package com.enjoy.mage.graphics;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

import android.content.Context;

///
/// ��Ҫ���뵽��Ϸ�е�ͼ�ζ���
///
public interface IGraphicsObj {
	public void onLoadResource(Context context,Engine engine);
	public void onLoadInScene(Scene scene);
	public void update();
	
}
