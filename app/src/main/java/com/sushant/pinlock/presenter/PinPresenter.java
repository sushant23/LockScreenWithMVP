package com.sushant.pinlock.presenter;

import com.sushant.pinlock.Injection;
import com.sushant.pinlock.data.PinRepository;
import com.sushant.pinlock.exception.ErrorBundle;
import com.sushant.pinlock.exception.ErrorMessageFactory;
import com.sushant.pinlock.service.LockerService;
import com.sushant.pinlock.util.Callback;

/**
 * Created by braindigit on 3/10/16.
 */
public class PinPresenter implements Presenter, PinContract.UserActionsListener {

    private PinRepository repository;
    private PinContract.View view;

    public PinPresenter(PinContract.View view, PinRepository pinRepository) {
        this.view = view;
        this.repository = pinRepository;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void tryUnlock(final String pin) {
        repository.getSavedPin(new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                if(pin.equals(result)){
                    view.correctPinEntered();
                    if(LockerService.getInstance() != null){
                        LockerService.getInstance().removeView();
                    }
                } else {
                    view.showPinMismatchError("Pin Mismatch");
                }
            }

            @Override
            public void onError(ErrorBundle e) {
                view.showPinMismatchError(ErrorMessageFactory.create(view.getContext(), e.getException()));
            }
        });
    }
}
