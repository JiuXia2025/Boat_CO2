package cosine.boat;


import android.app.NativeActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.lwjgl.input.Keyboard;
import android.view.View.OnClickListener;


public class BoatClientActivity extends NativeActivity implements View.OnClickListener, View.OnTouchListener, TextWatcher, TextView.OnEditorActionListener
{

	Button Button_Y,Button_A,Button_B,Button_P,Button_R,Hide,G,Q;
    boolean ButtonHide=false;
	public BoatInputEventSender mInputEventSender;
	
	Button controlr;

	//private View Button_A;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		popupWindow = new PopupWindow();
		popupWindow.setWidth(LayoutParams.FILL_PARENT);
		popupWindow.setHeight(LayoutParams.FILL_PARENT);
		popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		popupWindow.setFocusable(true);
		base = (RelativeLayout)LayoutInflater.from(BoatClientActivity.this).inflate(R.layout.overlay,null);
		mouseCursor = (ImageView)base.findViewById(R.id.mouse_cursor);
		touchPad = this.findButton(R.id.touch_pad);
		controlUp = this.findButton(R.id.control_up);
		controlDown = this.findButton(R.id.control_down);
		controlLeft = this.findButton(R.id.control_left);
		controlRight = this.findButton(R.id.control_right);
		controlJump = this.findButton(R.id.control_jump);
		controlLshift2 = this.findButton(R.id.control_lshift2);
		controlInv = this.findButton(R.id.control_inventory);
		controlLshift = this.findButton(R.id.control_lshift);
		control1 = this.findButton(R.id.control_1);
		control2 = this.findButton(R.id.control_2);
		control3 = this.findButton(R.id.control_3);
		control4 = this.findButton(R.id.control_4);
		control5 = this.findButton(R.id.control_5);
		control6 = this.findButton(R.id.control_6);
		control7 = this.findButton(R.id.control_7);
		control8 = this.findButton(R.id.control_8);
		control9 = this.findButton(R.id.control_9);
        Button_Y=this.findButton(R.id.key_y);
        Button_P=this.findButton(R.id.key_p);
        Button_R=this.findButton(R.id.key_r);
        final Button Button_A=this.findButton(R.id.key_a);
		final Button Button_B=this.findButton(R.id.key_b);
        Q = this.findButton(R.id.btn_q);
        Hide = this.findButton(R.id.btn_yc);
        Hide.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1) {
                    if(!ButtonHide)
                    {
                        Button_Y.setVisibility(View.INVISIBLE);
                        Button_R.setVisibility(View.INVISIBLE);
                        Button_P.setVisibility(View.INVISIBLE);
                        controlUp.setVisibility(View.INVISIBLE);
                        controlCommand.setVisibility(View.INVISIBLE);
                        control3rd.setVisibility(View.INVISIBLE);
                        controlDown.setVisibility(View.INVISIBLE);
                        controlLeft.setVisibility(View.INVISIBLE);
                        controlRight.setVisibility(View.INVISIBLE);
                        controlJump.setVisibility(View.INVISIBLE);
                        controlInv.setVisibility(View.INVISIBLE);
                        controlChat.setVisibility(View.INVISIBLE);
                        Q.setVisibility(View.INVISIBLE);
						Button_A.setVisibility(View.INVISIBLE);
						Button_B.setVisibility(View.INVISIBLE);
                        controlLshift.setVisibility(View.INVISIBLE);
					    controlLshift2.setVisibility(View.INVISIBLE);
                        ButtonHide=true;
                    }
                    else
                    {
                        Button_Y.setVisibility(View.VISIBLE);
                        Button_R.setVisibility(View.VISIBLE);
                        Button_P.setVisibility(View.VISIBLE);
                        controlUp.setVisibility(View.VISIBLE);
                        controlCommand.setVisibility(View.VISIBLE);
                        control3rd.setVisibility(View.VISIBLE);
                        controlDown.setVisibility(View.VISIBLE);
                        controlLeft.setVisibility(View.VISIBLE);
                        controlRight.setVisibility(View.VISIBLE);
                        controlJump.setVisibility(View.VISIBLE);
                        controlInv.setVisibility(View.VISIBLE);
						controlLshift.setVisibility(View.VISIBLE);
						controlLshift2.setVisibility(View.VISIBLE);
                        controlChat.setVisibility(View.VISIBLE);
                        Q.setVisibility(View.VISIBLE);
						Button_A.setVisibility(View.VISIBLE);
						Button_B.setVisibility(View.VISIBLE);
                        ButtonHide=false;
                    }
                }
            });
		itemBar = (LinearLayout)base.findViewById(R.id.item_bar);
		mousePrimary = this.findButton(R.id.mouse_primary);
		mouseSecondary = this.findButton(R.id.mouse_secondary);
		esc = this.findButton(R.id.esc);
		controlChat = this.findButton(R.id.control_chat);
		controlCommand = this.findButton(R.id.control_command);
		control3rd = this.findButton(R.id.control_3rd);
		inputScanner = (EditText)base.findViewById(R.id.input_scanner);
		inputScanner.setFocusable(true);
		inputScanner.addTextChangedListener(this);
		inputScanner.setOnEditorActionListener(this);
		inputScanner.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI|EditorInfo.IME_FLAG_NO_FULLSCREEN|EditorInfo.IME_ACTION_DONE);
		inputScanner.setSelection(1);
		popupWindow.setContentView(base);
		mHandler = new MyHandler();
		new Thread(){
			@Override
			public void run(){
				try
				{
					System.out.println("Trying to create BoatInputEventSender");
					mInputEventSender = new BoatInputEventSender();
					mInputEventSender.startServer(BoatClientActivity.this);
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("Failed to create BoatInputEventSender");
				}

			}
		}.start();
		
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		popupWindow.dismiss();
	}
	
	


	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		// TODO: Implement this method
		
		super.surfaceCreated(holder);
		System.out.println("Surface is created!");
		
		
		new Thread(){
			@Override
			public void run(){
				LauncherConfig config = LauncherConfig.fromFile(getIntent().getExtras().getString("config"));
				LoadMe.exec(config, BoatClientActivity.this);		
				Message msg=new Message();
				msg.what = -1;
				mHandler.sendMessage(msg);
				
			}
		}.start();
		
		
	}
	
	

	//private boolean overlayCreated = false;
	private PopupWindow popupWindow;
	private RelativeLayout base;
	private Button touchPad;
	private Button controlUp;
	private Button controlDown;
	private Button controlLeft;
	private Button controlRight;
	private Button controlJump;
	private Button controlInv;
	private Button controlLshift;
	private Button controlLshift2;
	private Button control1;
	private Button control2;
	private Button control3;
	private Button control4;
	private Button control5;
	private Button control6;
	private Button control7;
	private Button control8;
	private Button control9;
	private LinearLayout itemBar;
	private Button mousePrimary;
	private Button mouseSecondary;
	private Button controlChat;
	private Button controlCommand;
	private Button control3rd;
	private ImageView mouseCursor;
	private Button esc;
	
	private EditText inputScanner;
	public boolean mode = false;
	
	private class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg)
		{

			switch (msg.what)
			{
				case 1:
					BoatClientActivity.this.mouseCursor.setVisibility(View.INVISIBLE);
					BoatClientActivity.this.itemBar.setVisibility(View.VISIBLE);
					BoatClientActivity.this.mode = true;
					break;
				case 0:
					BoatClientActivity.this.mouseCursor.setVisibility(View.VISIBLE);
					BoatClientActivity.this.itemBar.setVisibility(View.INVISIBLE);
					BoatClientActivity.this.mode = false;
					break;
				default:
				    BoatClientActivity.this.finish();
				    break;
			}
		}
	}
	private Button findButton(int id){
		Button b = (Button)base.findViewById(id);
		b.setOnTouchListener(this);
		return b;
	}
	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
		if (p1 == inputScanner){
			inputScanner.setSelection(1);
		}
	}
	private MyHandler mHandler;
	public void changeGrab(byte g){
		Message msg=new Message();
		msg.what = g;
		mHandler.sendMessage(msg);
	}
	private int initialX;
	private int initialY;
	private int baseX;
	private int baseY;
	@Override
	public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
	}

	@Override
	public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
	}

	@Override
	public void afterTextChanged(Editable p1)
	{
		// TODO: Implement this method
		String newText = p1.toString();
		if (newText.length() < 1){
			
			mInputEventSender.setKey(Keyboard.KEY_BACK, true,0);
			mInputEventSender.setKey(Keyboard.KEY_BACK, false,0);
			inputScanner.setText(">");
			inputScanner.setSelection(1);
		}
		if (newText.length() > 1){
			for(int i = 1; i < newText.length(); i++){
				mInputEventSender.setKey(0, true, newText.charAt(i));
				mInputEventSender.setKey(0, false, newText.charAt(i));
			}
			
			inputScanner.setText(">");
			inputScanner.setSelection(1);
		}
	}
	
	@Override
	public boolean onEditorAction(TextView p1, int p2, KeyEvent p3)
	{
		// TODO: Implement this method
		
		mInputEventSender.setKey(Keyboard.KEY_RETURN, true, '\n');
		mInputEventSender.setKey(Keyboard.KEY_RETURN, false, '\n');
        return false;  
	}

	@Override
	public boolean onTouch(View p1, MotionEvent p2)
	{
		if (p1 == inputScanner){
			inputScanner.setSelection(1);
			return false;

		}
		
		if (p1 == mousePrimary){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setMouseButton((byte)1, true);

			}
			if (p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setMouseButton((byte)1, false);

			}
			return false;
			
		}
		if (p1 == mouseSecondary){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setMouseButton((byte)2, true);

			}
			if (p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setMouseButton((byte)2, false);

			}
			return false;
		}
		if (p1 == controlChat){
			
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_T, true,0);

			}
			if (p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_T, false,0);

			}
			
			return false;
		}
		if (p1 == controlCommand){

			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_SLASH, true,0);

			}
			if (p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_SLASH, false,0);

			}

			return false;
		}
		if (p1 == control3rd){

			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_F5, true,0);

			}
			if (p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_F5, false,0);

			}

			return false;
		}
		if (p1 == control1){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_1, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_1, false, 0);

			}
			return false;
		}
		if (p1 == control2){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_2, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_2, false, 0);

			}
			return false;
		}
		if (p1 == control3){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_3, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_3, false, 0);

			}
			return false;
		}
		if (p1 == control4){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_4, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_4, false, 0);

			}
			return false;
		}
		if (p1 == control5){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_5, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_5, false, 0);

			}
			return false;
		}
		if (p1 == control6){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_6, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_6, false, 0);

			}
			return false;
		}
		if (p1 == control7){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_7, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_7, false, 0);

			}
			return false;
		}
		if (p1 == control8){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_8, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_8, false, 0);

			}
			return false;
		}
		if (p1 == control9){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_9, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_9, false, 0);

			}
			return false;
		}
		if (p1 == controlUp){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_W, true, 0);
				
			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_W, false, 0);
				
			}
			return false;
		}
		if (p1 == controlInv){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_E, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_E, false, 0);

			}
			return false;
		}
		if (p1 == controlLshift){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_LSHIFT, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_LSHIFT, false, 0);

			}
			return false;
		}
		if (p1 == controlLshift2){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_LSHIFT, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_LSHIFT, false, 0);

			}
			return false;
		}
		if (p1 == controlDown){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_S, true, 0);
				
			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_S, false, 0);
				
			}
			return false;
		}
		if (p1 == controlLeft){
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_A, true, 0);
				
			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_A, false, 0);
				
			}
			return false;
		}
		if (p1 == controlRight){
			
			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_D, true, 0);
				
			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_D, false, 0);
				
			}
			return false;
		}
		if (p1 == controlJump){

			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_SPACE, true, 0);
				
			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_SPACE, false, 0);
				
			}
			return false;
		}
		if (p1 == esc){

			if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
				mInputEventSender.setKey(Keyboard.KEY_ESCAPE, true, 0);

			}
			else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
				mInputEventSender.setKey(Keyboard.KEY_ESCAPE, false, 0);

			}
			return false;
		}
        if (p1 == Button_Y){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_Y, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_Y, false, 0);

            }
            return false;
		}
        if (p1 == Button_P){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_P, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_P, false, 0);

            }
            return false;
		}
        if (p1 == Button_R){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_R, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_R, false, 0);

            }
            return false;
		}
        if (p1 == Button_A){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_A, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_A, false, 0);

            }
            return false;
		}
        if (p1 == Q){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_Q, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_Q, false, 0);

            }
            return false;
		}
		if (p1 == Button_A){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_A, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_A, false, 0);

            }
            return false;
		}
		if (p1 == Button_B){

            if (p2.getActionMasked() == MotionEvent.ACTION_DOWN){
                mInputEventSender.setKey(Keyboard.KEY_B, true, 0);

            }
            else if(p2.getActionMasked() == MotionEvent.ACTION_UP){
                mInputEventSender.setKey(Keyboard.KEY_B, false, 0);

            }
            return false;
		}
		//by.JiuXia2025 QQ2025226181
		// TODO: Implement this method
		if (p1 == touchPad){
			if (mode){
				switch(p2.getActionMasked()){
					case MotionEvent.ACTION_DOWN:
						initialX = (int)p2.getX();
						initialY = (int)p2.getY();
					case MotionEvent.ACTION_MOVE:
						mInputEventSender.setPointer(baseX + (int)p2.getX() -initialX, baseY + (int)p2.getY() - initialY);
						break;
					case MotionEvent.ACTION_UP:
						baseX += ((int)p2.getX() - initialX);
						baseY += ((int)p2.getY() - initialY);
						
						mInputEventSender.setPointer(baseX, baseY);
						break;
					default:
						break;
				}
			}
			else{
				baseX = (int)p2.getX();
				baseY = (int)p2.getY();
				mInputEventSender.setPointer(baseX, baseY);
				
				
			}
			
			mouseCursor.setX(p2.getX());
			mouseCursor.setY(p2.getY());
			return true;
		}
		return false;
		
	}

	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		// TODO: Implement this method
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus){
			popupWindow.showAtLocation(BoatClientActivity.this.getWindow().getDecorView(),Gravity.TOP|Gravity.LEFT,0,0);	
			
		}
		
	}
	
	
	
	
}


//Boat Co2
