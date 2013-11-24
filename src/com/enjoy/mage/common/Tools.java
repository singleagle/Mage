package com.enjoy.mage.common;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.xmlpull.v1.XmlPullParser;

import com.enjoy.mage.config.UIConfig;

import android.content.Context;
import android.graphics.Point;

import java.util.*;
/**
 * @author shaoleibo
 * ��һЩ����Ĳ���
 */
public class Tools {
	public static void setAssetsPath(String path)
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(path);
	}
	
	public static String GetUIFile(String file)
	{
		return file;
	}
	
	public static void LoadAtlas(BitmapTextureAtlas pAtlas)
	{
		Global.GetEngine().getTextureManager().loadTexture(pAtlas);
	}
	
	public static void LoadAtlas(final ITexture ... pTextures)
	{
	  Global.GetEngine().getTextureManager().loadTextures(pTextures);
	}
	
	public static void LoadFont(Font pFont)
	{
		Global.GetEngine().getFontManager().loadFont(pFont);
	}
	
	public static TextureRegion CreateTextureRegion(Context context,BitmapTextureAtlas atlas,String fileName,int x,int y)
	{
		return BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, context, fileName,x,y);
	}
	
	public static TiledTextureRegion CreateTiledRegion(Context context,BitmapTextureAtlas atlas,String fileName,int x,int y,int col,int row)
	{
		return BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(atlas, context, fileName, x, y,col,row);
	}
	
	public static TextureRegion CreateTextureRegion(BitmapTextureAtlas atlas,String fileName,int x,int y)
	{
		return BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas,Global.GetContext(), fileName,x,y);
	}
	
	public static TiledTextureRegion CreateTiledRegion(BitmapTextureAtlas atlas,String fileName,int x,int y,int col,int row)
	{
		return BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(atlas,Global.GetContext(), fileName, x, y,col,row);
	}
	
	///�ַ�ת����ԭ���INT�� eg:'1'=1
	public static int CharToInt(char c)
	{
	    String str=String.valueOf(c);
	    return Integer.parseInt(str);	    
	}
	
	// ��ȡָ����ʼֵ������
	public static long [] LongArray(int count,long val)
	{
		long []array=new long[count];
		for(int i=0;i<count;i++)
		{
			array[i]=val;
		}
		return array;
	}
	
	public static double Distance(Point start,Point end)
	{
		double disPow=Math.pow(end.x-start.x,2)+Math.pow(end.y-start.y,2);
		return Math.floor(Math.sqrt(disPow));
	}
	
	//���Ƿ��ھ�����
	public static boolean BInRect(Point pos,int x,int y,int w,int h)
	{
		if((pos.x>=x)&&(pos.y>=y)&&(pos.x<=x+w)&&(pos.y<=y+h))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	static Random mRand=new Random();
	public static int Random(int min,int max)
	{		
		return (min+mRand.nextInt(max-min));
	}
	
	//��ȡָ��XML�ڵ������ֵ  PULL��ʽʹ��
	static public int GetIntVal(XmlPullParser xrp,String attr)
	{
		String val=xrp.getAttributeValue(null,attr);
		if(val.isEmpty())
		  return 0;
		else
		  return Integer.parseInt(val);
	}
	
    static public boolean StringToBool(String str)
    {
    	return str.equals("true")?true:false;
    }
    
    //�����ֵĴ���  �Զ����д��� 
    static public String ProcessText(String str)
    {
    	int length=str.length();
    	int segment=length/UIConfig.LINE_TEXT_NUM;
    	StringBuilder bs=new StringBuilder();
    	for(int i=0;i<segment;i++)
    	{
    		String lcoalStr=str.substring(i*UIConfig.LINE_TEXT_NUM,UIConfig.LINE_TEXT_NUM*(i+1));
    		bs.append(lcoalStr);
    		bs.append("\n");
    	}
    	String endstr=str.substring(segment*UIConfig.LINE_TEXT_NUM);
    	bs.append(endstr);
    	return bs.toString();   	
    }
    
    
	//��4�ֽڵ�byte����ת��Ϊint��  ��˷�
	public static int ConvertByteArrayToInt(byte []pb)
	{
		if(pb.length>4)
			return 0;		
		int result=0;
		for(int i=0;i<4;i++)
		{
			int val=pb[i];
			if(val<0) //byte ֵ��-128��127֮��  ���޷����0-255֮��
			{
				val+=256;
			}
			result+=val*Math.pow(16,2*i);
		}
		return result;		
	}
	
	
	//Int��ת��ΪBYTE����
	public static byte[] ConvertIntToByteArray(int val)
	{
        byte[] b=new byte[4];
        for(int i=0;i<4;i++){
            b[i]=(byte)(val>>>(i*8));
        }
        return b; 
	}
	
	//��ȡ���ڻ����val����С��������
	public static int GetNearBinary(int val)
	{
		String str=Integer.toBinaryString(val);	
		int size=str.toCharArray().length;//��λ����
		if((val&(1<<(size-1)))==val)
		{
			return val;
		}
		else
		{
		    return val&(1<<size)|(1<<size);
		}		
	}
}
