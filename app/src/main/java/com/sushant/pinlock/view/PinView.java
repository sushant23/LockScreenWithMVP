package com.sushant.pinlock.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sushant.pinlock.Injection;
import com.sushant.pinlock.R;
import com.sushant.pinlock.presenter.PinContract;
import com.sushant.pinlock.presenter.PinPresenter;

import net.frakbot.glowpadbackport.GlowPadView;

/**
 * Created by braindigit on 3/10/16.
 */
public class PinView extends RelativeLayout implements PinContract.View {

    private PinContract.UserActionsListener userActionListener;
    private EditText etPin;
    private Button btnUnlock;
//    private GlowPadView glowPadView;

    public PinView(Context context) {
        super(context);
        init();
    }

    public PinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PinView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY | SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        inflate(getContext(), R.layout.layout_pin, this);
        initUI();
        userActionListener = new PinPresenter(this, Injection.providePinRepository());
    }

    private void initUI() {
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.black));
        etPin = (EditText) findViewById(R.id.etPin);
        btnUnlock = (Button) findViewById(R.id.btnUnlock);

        btnUnlock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userActionListener.tryUnlock(etPin.getText().toString());
            }
        });



//        glowPadView = (GlowPadView) findViewById(R.id.incomingCallWidget);
//        glowPadView.setOnTriggerListener(new GlowPadView.OnTriggerListener() {
//            @Override
//            public void onGrabbed(View v, int handle) {
//
//            }
//
//            @Override
//            public void onReleased(View v, int handle) {
//
//            }
//
//            @Override
//            public void onTrigger(View v, int target) {
//                if (target == 0) {
//                    userActionListener.tryUnlock("1234");
//                }
//                glowPadView.reset(true);
//            }
//
//            @Override
//            public void onGrabbedStateChange(View v, int handle) {
//
//            }
//
//            @Override
//            public void onFinishFinalAnimation() {
//
//            }
//        });
    }

    @Override
    public void showPinMismatchError(String message) {
        etPin.setText("");
        etPin.setError(message);
    }

    @Override
    public void correctPinEntered() {
        etPin.setText("");
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (etPin.requestFocus()) {
//            getContext().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            InputMethodManager imm = (InputMethodManager)
                    getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm != null){
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }
}
