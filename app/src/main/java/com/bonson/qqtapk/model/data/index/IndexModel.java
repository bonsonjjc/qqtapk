package com.bonson.qqtapk.model.data.index;

import com.bonson.qqtapk.model.data.ApiServer;

import javax.inject.Inject;

public class IndexModel {
    private ApiServer apiServer;

    @Inject
    public IndexModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }
}
