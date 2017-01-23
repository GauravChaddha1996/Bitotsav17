package com.bitmesra.bitotsav.features.start;

import com.bitmesra.bitotsav.database.DataManager;

public class StartPresenterImpl implements StartPresenterInterface {
    private DataManager manager;
    private StartViewInterface viewInterface;

    public StartPresenterImpl(StartViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        manager = DataManager.getDataManager();
    }
}
