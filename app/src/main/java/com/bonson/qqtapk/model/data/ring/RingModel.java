package com.bonson.qqtapk.model.data.ring;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingModel {
    private RingServer ringServer;

    @Inject
    public RingModel(RingServer ringServer) {
        this.ringServer = ringServer;
    }
}
