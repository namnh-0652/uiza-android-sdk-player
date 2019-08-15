package uiza.v4;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import io.uiza.core.api.response.linkplay.LinkPlay;
import io.uiza.core.api.response.video.VideoData;
import io.uiza.core.exception.UzException;
import io.uiza.core.util.UzDisplayUtil;
import io.uiza.player.interfaces.UZCallback;
import io.uiza.player.interfaces.UZItemClick;
import io.uiza.player.util.UZUtil;
import io.uiza.player.view.UzPlayerView;
import io.uiza.player.view.rl.video.UzVideo;
import uiza.R;

public class FrmVideoTop extends Fragment implements UZCallback, UZItemClick {
    private final String TAG = getClass().getSimpleName();
    private UzVideo uzVideo;

    public UzVideo getUZVideo() {
        return uzVideo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uzVideo = view.findViewById(R.id.uiza_video);
        uzVideo.addUZCallback(this);
        uzVideo.addItemClick(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.v4_frm_top, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uzVideo.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        uzVideo.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uzVideo.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        uzVideo.onActivityResult(resultCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setListener() {
        if (uzVideo == null || uzVideo.getPlayer() == null) {
            return;
        }
        uzVideo.addControllerStateCallback(new UzPlayerView.ControllerStateCallback() {
            @Override
            public void onVisibilityChange(boolean isShow) {
                if (((HomeV4CanSlideActivity) getActivity()).getDraggablePanel() != null
                        && !((HomeV4CanSlideActivity) getActivity()).isLandscapeScreen()) {
                    if (((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().isMaximized()) {
                        if (isShow) {
                            ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().setEnableSlide(false);
                        } else {
                            ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().setEnableSlide(true);
                        }
                    } else {
                        ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().setEnableSlide(true);
                    }
                }
            }
        });
    }

    @Override
    public void isInitResult(boolean isInitSuccess, boolean isGetDataSuccess, LinkPlay linkPlay, VideoData data) {
        ((HomeV4CanSlideActivity) getActivity()).isInitResult(isGetDataSuccess, linkPlay, data);
        if (isInitSuccess) {
            setListener();
            //uzVideo.setEventBusMsgFromActivityIsInitSuccess();
        }
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.exo_back_screen:
                if (!uzVideo.isLandscape()) {
                    ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().minimize();
                }
                break;
        }
    }

    @Override
    public void onStateMiniPlayer(boolean isInitMiniPlayerSuccess) {
        if (isInitMiniPlayerSuccess) {
            uzVideo.pauseVideo();
            ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().minimize();
            UzDisplayUtil.setDelay(500, new UzDisplayUtil.DelayCallback() {
                @Override
                public void doAfter(int mls) {
                    ((HomeV4CanSlideActivity) getActivity()).getDraggablePanel().closeToRight();
                }
            });
        }
    }

    @Override
    public void onSkinChange() {

    }

    @Override
    public void onScreenRotate(boolean isLandscape) {
    }

    @Override
    public void onError(UzException e) {
        if (e == null) {
            return;
        }
        Toast.makeText(getContext(), "Error while playing this video !", Toast.LENGTH_LONG).show();
    }

    public void initEntity(String entityId) {
        UZUtil.initEntity(getActivity(), uzVideo, entityId);
    }

    public void initPlaylistFolder(String metadataId) {
        UZUtil.initPlaylistFolder(getActivity(), uzVideo, metadataId);
    }
}
