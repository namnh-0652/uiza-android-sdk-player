package io.uiza.player.cast;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastState;
import com.google.android.gms.cast.framework.CastStateListener;
import io.uiza.core.exception.UzException;
import io.uiza.core.util.SentryUtil;
import io.uiza.core.util.UzCommonUtil;
import io.uiza.core.util.UzDisplayUtil;
import io.uiza.player.view.rl.video.UzMediaRouteButton;

public class UzChromeCast {

    protected static final String TAG = UzChromeCast.class.getSimpleName();
    private Casty casty;
    private UzMediaRouteButton uzMediaRouteButton;
    private CastListener listener;
    private long lastPlayedPosition;

    static {
        if (!UzCommonUtil.isCastDependencyAvailable()) {
            throw new NoClassDefFoundError(UzException.ERR_505);
        }
    }

//    public UzChromeCast(Activity activity) {
//        setCasty(activity);
//    }

    public void setCasty(Activity activity) {
        if (activity == null) {
            throw new NullPointerException(UzException.ERR_12);
        }
        if (!UzCommonUtil.isCastDependencyAvailable()) {
            throw new NoClassDefFoundError(UzException.ERR_505);
        }
        if (UzCommonUtil.isTV(activity)) {
            throw new UnsupportedOperationException(UzException.ERR_7);
        }
        casty = Casty.create(activity);
    }

    public void setUZChromeCastListener(CastListener listener) {
        this.listener = listener;
    }

    public void setupChromeCast(Context context) {
        uzMediaRouteButton = new UzMediaRouteButton(context);
        setUpMediaRouteButton();
        addUiChromeCastLayer(context);
    }

    @UiThread
    private void setUpMediaRouteButton() {
        if (casty == null) {
            throw new IllegalStateException("Casty is not initialized!");
        }
        casty.setUpMediaRouteButton(uzMediaRouteButton);
        casty.setOnConnectChangeListener(new Casty.OnConnectChangeListener() {
            @Override
            public void onConnected() {
                if (listener != null) {
                    listener.onConnected();
                }
            }

            @Override
            public void onDisconnected() {
                if (listener != null) {
                    listener.onDisconnected();
                }
            }
        });
    }

    private void updateMediaRouteButtonVisibility(int state) {
        if (state == CastState.NO_DEVICES_AVAILABLE) {
            UzDisplayUtil.goneViews(uzMediaRouteButton);
        } else {
            UzDisplayUtil.visibleViews(uzMediaRouteButton);
        }
    }

    //Gen layout ChromeCast with black background programmatically
    private void addUiChromeCastLayer(Context context) {
        //listener check state of chromecast
        CastContext castContext = null;
        try {
            castContext = CastContext.getSharedInstance(context);
        } catch (Exception e) {
            Log.e(TAG, "Error addUiChromeCastLayer: " + e.toString());
            SentryUtil.captureException(e);
        }
        if (castContext == null) {
            UzDisplayUtil.goneViews(uzMediaRouteButton);
            return;
        }
        updateMediaRouteButtonVisibility(castContext.getCastState());
        castContext.addCastStateListener(new CastStateListener() {
            @Override
            public void onCastStateChanged(int state) {
                updateMediaRouteButtonVisibility(state);
            }
        });
        if (listener != null) {
            listener.addUiChromeCast();
        }
    }

    public void setTintMediaRouteButton(final int color) {
        if (uzMediaRouteButton != null) {
            uzMediaRouteButton.post(new Runnable() {
                @Override
                public void run() {
                    uzMediaRouteButton.applyTint(color);
                }
            });
        }
    }

    public Casty getCasty() {
        return casty;
    }

    public UzMediaRouteButton getUzMediaRouteButton() {
        return uzMediaRouteButton;
    }

    public interface CastListener {

        void onConnected();

        void onDisconnected();

        void addUiChromeCast();
    }
}
