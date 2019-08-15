package io.uiza.player.analytic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import io.uiza.core.util.SharedPreferenceUtil;

public final class UzTrackingHelper {

    private static final String PREFERENCES_FILE_NAME = "UzTrackingHelper";

    private static final String E_DISPLAY = "E_DISPLAY";
    private static final String E_PLAYS_REQUESTED = "E_PLAYS_REQUESTED";
    private static final String E_VIDEO_STARTS = "E_VIDEO_STARTS";
    private static final String E_VIEW = "E_VIEW";
    private static final String E_REPLAY = "E_REPLAY";
    private static final String E_PLAY_THROUGHT_25 = "PLAY_THROUGHT_25";
    private static final String E_PLAY_THROUGHT_50 = "PLAY_THROUGHT_50";
    private static final String E_PLAY_THROUGHT_75 = "PLAY_THROUGHT_75";
    private static final String E_PLAY_THROUGHT_100 = "PLAY_THROUGHT_100";

    @StringDef({E_DISPLAY, E_PLAYS_REQUESTED, E_VIDEO_STARTS, E_VIEW, E_REPLAY, E_PLAY_THROUGHT_25,
            E_PLAY_THROUGHT_50, E_PLAY_THROUGHT_75, E_PLAY_THROUGHT_100})
    @interface TrackingEvent {

    }

    private UzTrackingHelper() {
    }

    /**
     * Reset the local tracking progress.
     *
     * @param context the context
     */
    public static void resetTracking(Context context) {
        setTrackingDoneWithEventTypeDisplay(context, false);
        setTrackingDoneWithEventTypePlaysRequested(context, false);
        setTrackingDoneWithEventTypeVideoStarts(context, false);
        setTrackingDoneWithEventTypeView(context, false);
        setTrackingDoneWithEventTypeReplay(context, false);
        setTrackingDoneWithEventTypePlayThrought25(context, false);
        setTrackingDoneWithEventTypePlayThrought50(context, false);
        setTrackingDoneWithEventTypePlayThrought75(context, false);
        setTrackingDoneWithEventTypePlayThrough100(context, false);
    }

    public static void setEventTrackingProgress(Context context, @TrackingEvent String event,
            boolean hasDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), event, hasDone);
    }

    public static boolean isEventTracked(Context context, @TrackingEvent String event) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil.get(getPrivatePreference(context), event, false);
    }

    //Event type display
    public static boolean isTrackedEventTypeDisplay(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil.get(getPrivatePreference(context), E_DISPLAY, false);
    }

    public static void setTrackingDoneWithEventTypeDisplay(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_DISPLAY, isDone);
    }

    public static boolean isTrackedEventTypePlaysRequested(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_PLAYS_REQUESTED, false);
    }

    public static void setTrackingDoneWithEventTypePlaysRequested(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_PLAYS_REQUESTED, isDone);
    }

    public static boolean isTrackedEventTypeVideoStarts(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_VIDEO_STARTS, false);
    }

    public static void setTrackingDoneWithEventTypeVideoStarts(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_VIDEO_STARTS, isDone);
    }

    public static boolean isTrackedEventTypeView(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil.get(getPrivatePreference(context), E_VIEW, false);
    }

    public static void setTrackingDoneWithEventTypeView(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_VIEW, isDone);
    }

    public static boolean isTrackedEventTypeReplay(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil.get(getPrivatePreference(context), E_REPLAY, false);
    }

    public static void setTrackingDoneWithEventTypeReplay(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_REPLAY, isDone);
    }

    public static boolean isTrackedEventTypePlayThrought25(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_PLAY_THROUGHT_25, false);
    }

    public static void setTrackingDoneWithEventTypePlayThrought25(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_PLAY_THROUGHT_25, isDone);
    }

    public static boolean isTrackedEventTypePlayThrought50(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_PLAY_THROUGHT_50, false);
    }

    public static void setTrackingDoneWithEventTypePlayThrought50(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_PLAY_THROUGHT_50, isDone);
    }

    public static boolean isTrackedEventTypePlayThrought75(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_PLAY_THROUGHT_75, false);
    }

    public static void setTrackingDoneWithEventTypePlayThrought75(Context context, boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_PLAY_THROUGHT_75, isDone);
    }

    public static boolean isTrackedEventTypePlayThrough100(Context context) {
        if (context == null) {
            return false;
        }
        return (boolean) SharedPreferenceUtil
                .get(getPrivatePreference(context), E_PLAY_THROUGHT_100, false);
    }

    public static void setTrackingDoneWithEventTypePlayThrough100(Context context,
            boolean isDone) {
        if (context == null) {
            return;
        }
        SharedPreferenceUtil.put(getPrivatePreference(context), E_PLAY_THROUGHT_100, isDone);
    }

    private static SharedPreferences getPrivatePreference(Context context) {
        return context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
    }

    public interface UizaTrackingCallback {

        void onTrackingSuccess();
    }
}
