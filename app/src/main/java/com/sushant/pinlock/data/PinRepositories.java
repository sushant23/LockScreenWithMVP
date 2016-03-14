package com.sushant.pinlock.data;

/**
 * Created by braindigit on 3/10/16.
 */

public class PinRepositories {

    private static PinRepository pinRepository = null;

    private PinRepositories(){

    }

    public synchronized static PinRepository getPinRepository(PinServiceApi pinServiceApi){
        if(pinRepository == null){
            pinRepository = new PinRepositoryImpl(pinServiceApi);
        }

        return pinRepository;
    }
}
