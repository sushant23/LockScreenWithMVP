package com.sushant.pinlock;

import com.sushant.pinlock.data.FakePinServiceApiImpl;
import com.sushant.pinlock.data.PinRepositories;
import com.sushant.pinlock.data.PinRepository;

/**
 * Created by braindigit on 3/10/16.
 */
public class Injection {

    public static PinRepository providePinRepository(){
        return PinRepositories.getPinRepository(new FakePinServiceApiImpl());
    }
}
