package com.sushant.pinlock.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.sushant.pinlock.Injection;
import com.sushant.pinlock.R;
import com.sushant.pinlock.presenter.PinContract;
import com.sushant.pinlock.presenter.PinPresenter;

/**
 * Created by braindigit on 3/10/16.
 */
public class PinView extends RelativeLayout implements PinContract.View {

    private PinContract.UserActionsListener userActionListener;
    private EditText etPin;
    private Button btnUnlock;

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
        etPin = (EditText) findViewById(R.id.etPin);
        btnUnlock = (Button) findViewById(R.id.btnUnlock);

        btnUnlock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userActionListener.tryUnlock(etPin.getText().toString());
            }
        });
    }

    @Override
    public void showPinMismatchError(String message) {
        etPin.setError(message);
    }

    @Override
    public void correctPinEntered() {

    }

}
