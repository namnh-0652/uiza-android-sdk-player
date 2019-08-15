package io.uiza.player.analytic;

import static io.uiza.player.analytic.VideoTrackingEvent.EVENT_TYPE_DISPLAY;
import static io.uiza.player.analytic.VideoTrackingEvent.EVENT_TYPE_PLAYS_REQUESTED;
import static io.uiza.player.analytic.VideoTrackingEvent.EVENT_TYPE_PLAY_THROUGH;
import static io.uiza.player.analytic.VideoTrackingEvent.EVENT_TYPE_VIDEO_STARTS;
import static io.uiza.player.analytic.VideoTrackingEvent.EVENT_TYPE_VIEW;

import android.support.annotation.StringDef;

@StringDef({EVENT_TYPE_DISPLAY, EVENT_TYPE_PLAYS_REQUESTED, EVENT_TYPE_VIDEO_STARTS,
        EVENT_TYPE_VIEW, EVENT_TYPE_PLAY_THROUGH})
public @interface VideoTrackingEvent {

    String EVENT_TYPE_DISPLAY = "display";
    String EVENT_TYPE_PLAYS_REQUESTED = "plays_requested";
    String EVENT_TYPE_VIDEO_STARTS = "video_starts";
    String EVENT_TYPE_VIEW = "view";
    String EVENT_TYPE_PLAY_THROUGH = "play_through";
}
