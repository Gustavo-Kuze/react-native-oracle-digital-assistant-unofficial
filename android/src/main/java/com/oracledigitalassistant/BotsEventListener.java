package com.oracledigitalassistant;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.ReactApplicationContext;

import java.util.ArrayList;

import oracle.cloud.bots.mobile.core.ConnectionStatus;
import oracle.cloud.bots.mobile.core.EventListener;
import oracle.cloud.bots.mobile.core.SupportedLanguage;
import oracle.cloud.bots.mobile.core.payload.Message;

public class BotsEventListener implements EventListener {
    Callbacks callbacks;

    BotsEventListener(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void onStatusChange(ConnectionStatus connectionStatus) {
        this.callbacks.onStatusChange(connectionStatus.name().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMessageReceived(Message message) {
        ArrayList<String> actions = new ArrayList<>();

        message.getPayload().getActions().forEach(a -> {
            actions.add(a.getLabel());
        });

        this.callbacks.onMessage(new MessageDTO(
                message.getPayload().getHeaderText().toString(),
                message.getPayload().getFooterText().toString(),
                message.getCreatedDate(),
                message.isRead(),
                actions
        ));
    }

    @Override
    public void onAttachmentComplete() {
    }

    @Override
    public void onMessageSent(Message message) {
    }

    @Override
    public void onChatLanguageChange(SupportedLanguage supportedLanguage) {
    }
}