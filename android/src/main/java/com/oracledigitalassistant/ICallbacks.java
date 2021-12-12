package com.oracledigitalassistant;

public interface ICallbacks {
    public void onMessage(MessageDTO message);
    public void onStatusChange(String status);
}
