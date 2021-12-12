package com.oracledigitalassistant;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;

public class Callbacks implements ICallbacks {
    public ReactApplicationContext reactContext = null;

    Callbacks(ReactApplicationContext context){
        this.reactContext = context;
    }

    @Override
    public void onMessage(MessageDTO message) {
        WritableMap payload = Arguments.createMap();
        payload.putString("message", (new Gson()).toJson(message));

        this.reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("onMessage", payload);
    }

    @Override
    public void onStatusChange(String status) {
        WritableMap payload = Arguments.createMap();
        payload.putString("status", String.valueOf(status));
        this.reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("onStatusChange", payload);
    }
}
