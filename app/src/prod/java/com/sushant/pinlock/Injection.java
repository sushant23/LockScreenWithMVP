package com.sushant.pinlock;

import com.sushant.pinlock.data.PinRepositories;
import com.sushant.pinlock.data.PinRepository;
import com.sushant.pinlock.data.PinServiceApiImpl;

/**
 * Created by braindigit on 3/14/16.
 */
public class Injection {
    public static PinRepository providePinRepository(){
        return PinRepositories.getPinRepository(new PinServiceApiImpl());
    }

}
