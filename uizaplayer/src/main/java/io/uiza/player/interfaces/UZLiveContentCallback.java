package io.uiza.player.interfaces;

public interface UZLiveContentCallback {
    void onUpdateLiveInfoTimeStartLive(long duration, String hhmmss);

    void onUpdateLiveInfoCurrentView(long watchnow);

    void onLivestreamUnAvailable();
}
