package com.oracledigitalassistant;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;

import oracle.cloud.bots.mobile.core.Bots;
import oracle.cloud.bots.mobile.core.BotsCallback;
import oracle.cloud.bots.mobile.core.BotsConfiguration;
import oracle.cloud.bots.mobile.core.BotsSDKException;

public class OracleDigitalAssistantModule extends ReactContextBaseJavaModule {

    OracleDigitalAssistantModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "OracleDigitalAssistant";
    }

    @ReactMethod
    public void init(String userId, String channelId, String chatServer, Promise promise) {
        try {
            BotsConfiguration botsConfiguration = new BotsConfiguration.BotsConfigurationBuilder(chatServer, false, this.getReactApplicationContext()) // Configuration to initialize the SDK
                    .channelId(channelId)
                    .userId(userId)
                    .build();

            Bots.init(this.getCurrentActivity().getApplication(), botsConfiguration, new BotsCallback() {  // Initialize the SDK
                @Override
                public void onSuccess(Response paramResponse) {
                    promise.resolve(true);
                }

                @Override
                public void onFailure(Response paramResponse) {
                    promise.reject(new Exception(paramResponse.getErrorMessage()));
                }
            });
            
            // Bots.setEventListener(new BotsEventListener(new Callbacks(this.getReactApplicationContext())));

        //    Bots.setEventListener(new BotsEventListener((status) -> {
        //        WritableMap payload = Arguments.createMap();
        //        payload.putString("status", String.valueOf(status));
        //        this.getReactApplicationContext()
        //                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        //                .emit("onStatusChange", payload);
        //    }, (message) -> {
        //        WritableMap payload = Arguments.createMap();
        //        payload.putString("message", (new Gson()).toJson(message));

        //        this.getReactApplicationContext()
        //                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        //                .emit("onMessage", payload);
        //    }));

        } catch (BotsSDKException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void isInitialized(Promise promise) {
        try {
            promise.resolve(Bots.isInitialized());
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /*@ReactMethod
    public void setupChatListeners(Callback onStatusChanged, Callback onMessageReceived) {
        Bots.setEventListener(new BotsEventListener((status) -> {
            WritableMap payload = Arguments.createMap();
            payload.putString("status", String.valueOf(status));
            this.getReactApplicationContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onStatusChange", payload);
        }, (message) -> {
            WritableMap payload = Arguments.createMap();
            payload.putString("message", (new Gson()).toJson(message));

            this.getReactApplicationContext()
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onMessage", payload);
        }));
    }*/

    @ReactMethod
    public void setupChatListeners() {
        Bots.setEventListener(new BotsEventListener(new Callbacks(this.getReactApplicationContext())));
    }

    @ReactMethod
    public void sendMessage(String message, Promise promise) {
        try {
            Bots.sendMessage(message);

            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    private void sendReactEvent(String eventName,
                                @Nullable WritableMap params) {
        this.getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}
