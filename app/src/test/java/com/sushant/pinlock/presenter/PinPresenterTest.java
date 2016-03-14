package com.sushant.pinlock.presenter;

import com.sushant.pinlock.data.PinRepository;
import com.sushant.pinlock.util.Callback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by braindigit on 3/10/16.
 */
public class PinPresenterTest {

    @Mock
    private PinRepository pinRepository;

    @Mock
    private PinContract.View pinView;

    private PinPresenter pinPresenter;

    @Before
    public void setupPinPresenter() {
        MockitoAnnotations.initMocks(this);

        pinPresenter = new PinPresenter(pinView, pinRepository);
    }

    @Captor
    ArgumentCaptor<Callback<String>> argumentCaptor;

    @Test
    public void getSavedPin_ShowSuccessMessageUI(){
        pinPresenter.tryUnlock("1234");
        verify(pinRepository).getSavedPin(argumentCaptor.capture());
        argumentCaptor.getValue().onSuccess("1234");
        verify(pinView).correctPinEntered();
    }

    @Test
    public void getSavedPin_ShowErrorMessageUI(){
        pinPresenter.tryUnlock("1234");
        verify(pinRepository).getSavedPin(argumentCaptor.capture());
        argumentCaptor.getValue().onSuccess("1111");
        verify(pinView).showPinMismatchError("Pin Mismatch");
    }
}
