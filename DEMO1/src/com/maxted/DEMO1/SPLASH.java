package com.maxted.DEMO1;
import flashbox.ANDROID.MANAGER;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SPLASH extends Activity{
	RelativeLayout view;
    ImageView image;
	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
		
		Rect rectgle= new Rect(); 
		Window window= getWindow(); window.getDecorView().getWindowVisibleDisplayFrame(rectgle); 
		int StatusBarHeight= rectgle.top; 
		int contentViewTop= window.findViewById(Window.ID_ANDROID_CONTENT).getTop(); 
		int TitleBarHeight= contentViewTop - StatusBarHeight; 

		System.out.println("modelName is :"+Build.MODEL);
		System.out.println("device is :"+Build.DEVICE);
		System.out.println("ProductName is :"+Build.PRODUCT);		
		
		Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		MANAGER.vWidth=320;
		MANAGER.vHeight=480-38;
		MANAGER.log("Size :: StatusBar Height= " + StatusBarHeight + " TitleBar Height = " + TitleBarHeight);
		MANAGER.log("Size :: Device Height= " + display.getHeight() + " Device Width = " + display.getWidth());
		if(Build.DEVICE.equals("SHW-M380S") ){
			StatusBarHeight=48;		
			int realwidth = display.getWidth();
			//int height = display.getHeight()-StatusBarHeight;
			int height = display.getHeight();
			int width=(int)(height*(float)MANAGER.vWidth/MANAGER.vHeight);
			int leftMargin=(realwidth-width)/2;
			MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
			MANAGER.frameMarginLeft=8; 		
			MANAGER.deviceWidth=width;
			MANAGER.deviceHeight=height;	
		}else if(display.getHeight()==800&&display.getWidth()==480){
			StatusBarHeight=38;	
			int realwidth = display.getWidth();
			int height = display.getHeight()-StatusBarHeight;
			int width=(int)(height*(float)MANAGER.vWidth/MANAGER.vHeight);
			int leftMargin=(realwidth-width)/2;
			MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
			MANAGER.frameMarginLeft=0; 		
			MANAGER.deviceWidth=MANAGER.vWidth;
			MANAGER.deviceHeight=MANAGER.vHeight;	
		}else if(StatusBarHeight>0){
			int realwidth = display.getWidth();
			int realheight = display.getHeight();
			int height = display.getHeight()-StatusBarHeight;
			int width=(int)(height*(float)MANAGER.vWidth/MANAGER.vHeight);
			int leftMargin=(realwidth-width)/2;
			if(leftMargin<0){
				width=realwidth;
				height=(int)(width*(float)MANAGER.vHeight/MANAGER.vWidth);
				int topMargin=(realheight-height)/2;
				MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
				MANAGER.frameMarginTop=0; 		
				MANAGER.deviceWidth=width;
				MANAGER.deviceHeight=height;	
			}else{
				MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
				MANAGER.frameMarginLeft=leftMargin; 		
				MANAGER.deviceWidth=width;
				MANAGER.deviceHeight=height;	
			}
		}else{
			StatusBarHeight=38;	
			int realwidth = display.getWidth();
			int realheight = display.getHeight();
			int height = display.getHeight()-StatusBarHeight;
			int width=(int)(height*(float)MANAGER.vWidth/MANAGER.vHeight);
			int leftMargin=(realwidth-width)/2;
			if(leftMargin<0){
				width=realwidth;
				height=(int)(width*(float)MANAGER.vHeight/MANAGER.vWidth);
				int topMargin=(realheight-height)/2;
				MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
				MANAGER.frameMarginTop=topMargin; 		
				MANAGER.deviceWidth=width;
				MANAGER.deviceHeight=height;	
			}else{
				MANAGER.log("left margin= " + width+":"+height+":"+leftMargin);
				MANAGER.frameMarginLeft=leftMargin; 		
				MANAGER.deviceWidth=width;
				MANAGER.deviceHeight=height;	
			}			
		}
		if(image!=null){
			RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(MANAGER.deviceWidth,MANAGER.deviceHeight);		
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

			params.setMargins(MANAGER.frameMarginLeft,MANAGER.frameMarginTop,MANAGER.frameMarginRight,MANAGER.frameMarginBottom);
			view.updateViewLayout(image,params);			
		}
	}
	public Drawable getImage(String opath){
		try{
			String path=opath.toLowerCase();
			if(path.indexOf(".")>0){
				path=path.substring(0,path.indexOf("."));
			}
			Class R=Class.forName("com.maxted.DEMO1.R$drawable");
			if(R.getField(path)!=null){
				return (getResources().getDrawable(R.getField(path).getInt(R)));	
			}else{
				return null;
			}
		}catch(Exception t){
			return null;
		}
	}	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new RelativeLayout(this);
        image=new ImageView(this);
        image.setImageDrawable(getImage("intro.png"));
        view.addView(image,new RelativeLayout.LayoutParams(-1, -1));
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	
        setContentView(view);
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(MANAGER.deviceWidth,MANAGER.deviceHeight);		
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		params.setMargins(MANAGER.frameMarginLeft,MANAGER.frameMarginTop,MANAGER.frameMarginRight,MANAGER.frameMarginBottom);
		view.updateViewLayout(image,params);
			
			
        Handler aHandler = new Handler();
        aHandler.postDelayed(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg=Message.obtain();
                msg.what=0;
                mHandler.sendMessage(msg);
            }
        },500);
    }
    
    Handler mHandler=new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                try{
                    Intent aIntent = new Intent(SPLASH.this, MAIN.class);
                    startActivity(aIntent);
                    finish();
                    overridePendingTransition(com.maxted.DEMO1.R.anim.fadein,
                            com.maxted.DEMO1.R.anim.fadeout);                    
                }catch(Exception t){
                    System.out.println("Handle Error :"+t.toString());
                }
            }
        }
    };
    
    @Override
    public void onBackPressed() {
    }
}