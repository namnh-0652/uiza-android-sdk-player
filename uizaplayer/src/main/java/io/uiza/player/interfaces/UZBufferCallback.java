package io.uiza.player.interfaces;

public interface UZBufferCallback {
    void onBufferChanged(long bufferedDurationUs, float playbackSpeed);
}
