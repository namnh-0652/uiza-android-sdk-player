package io.uiza.player;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class UzPlayer {

    private final SimpleExoPlayer player;

    public UzPlayer(@NonNull SimpleExoPlayer player) {
        this.player = player;
    }
}
