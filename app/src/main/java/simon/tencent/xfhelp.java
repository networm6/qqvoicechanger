package simon.tencent;

import android.view.WindowManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public enum xfhelp
{
	INTERFACE;
    private    Context incon;
    private    WindowManager mWindowManager;
    private    ViewGroup toucherLayout;
    public boolean ishow=false;
    public    WindowManager.LayoutParams mParams;
    public xfhelp incon(Context th){
        incon=th;
        return this;
    }
    public ViewGroup getlayout(){
        return toucherLayout;
    }
    public xfhelp show(){
		ishow=true;
        mWindowManager.addView(toucherLayout, mParams);     
        return this;
    }
    public xfhelp reset(){
		ishow=true;
		hide();
		mWindowManager.addView(toucherLayout, mParams);     
        return this;
	}

    public xfhelp hide(){
        ishow=false;
        mWindowManager.removeView(toucherLayout);
        return this;
    }
    public xfhelp in(int layoutid){
        mWindowManager = (WindowManager) incon.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();

        this.mParams.type = 2003;

        this.mParams.flags = 296;
        this.mParams.format = -2;
		mParams.gravity = Gravity.TOP|Gravity.LEFT; // 调整悬浮窗口至左上角
        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height = LayoutParams.WRAP_CONTENT;
		//  mParams.height=500;
		//   mParams.x=-100;
		//mParams.y=-100;
        LayoutInflater inflater = LayoutInflater.from(incon);
        //获取浮动窗口视图所在布局.
        toucherLayout = (ViewGroup) inflater.inflate(layoutid, null);

        return this;
    }

}



