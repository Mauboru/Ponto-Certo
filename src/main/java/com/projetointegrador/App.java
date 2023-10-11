package com.projetointegrador;

import io.github.hugoperlin.navigatorfx.*;

public class App extends BaseAppNavigator {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getAppTitle() {
        return null;
    }

    @Override
    public String getHome() {
        return null;
    }

    @Override
    public void registrarTelas() {
        throw new UnsupportedOperationException("Unimplemented method 'registrarTelas'");
    }
}