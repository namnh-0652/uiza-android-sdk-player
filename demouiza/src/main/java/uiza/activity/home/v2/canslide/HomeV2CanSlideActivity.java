package uiza.activity.home.v2.canslide;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.android.exoplayer2.ui.PlayerControlView;

import uiza.R;
import uiza.app.LSApplication;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LScreenUtil;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.GetDetailEntity;
import vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.uizavideo.view.IOnBackPressed;
import vn.loitp.uizavideo.view.rl.videoinfo.ItemAdapterV2;
import vn.loitp.uizavideo.view.util.UizaData;
import vn.loitp.views.draggablepanel.DraggableListener;
import vn.loitp.views.draggablepanel.DraggablePanel;

public class HomeV2CanSlideActivity extends BaseActivity {
    private DraggablePanel draggablePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        draggablePanel = (DraggablePanel) findViewById(R.id.draggable_panel);
        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                //LLog.d(TAG, "onMaximized");
            }

            @Override
            public void onMinimized() {
                //LLog.d(TAG, "onMinimized");
                frmVideoTop.getUizaIMAVideo().getPlayerView().hideController();
            }

            @Override
            public void onClosedToLeft() {
                //LLog.d(TAG, "onClosedToLeft");
                frmVideoTop.getUizaIMAVideo().onDestroy();
            }

            @Override
            public void onClosedToRight() {
                //LLog.d(TAG, "onClosedToRight");
                frmVideoTop.getUizaIMAVideo().onDestroy();
            }

            @Override
            public void onDrag(int left, int top, int dx, int dy) {
                //LLog.d(TAG, "onDrag " + left + " - " + top + " - " + dx + " - " + dy);
                frmVideoTop.getUizaIMAVideo().getPlayerView().hideController();
            }
        });
        replaceFragment(new FrmHome());
    }

    public void replaceFragment(BaseFragment baseFragment) {
        if (baseFragment instanceof FrmHome) {
            LScreenUtil.replaceFragment(activity, R.id.fl_container, baseFragment, false);
        } else {
            LScreenUtil.replaceFragment(activity, R.id.fl_container, baseFragment, true);
        }
    }

    public void addFragment(BaseFragment baseFragment, boolean isAddToBackstack) {
        LScreenUtil.addFragment(activity, R.id.fl_container, baseFragment, isAddToBackstack);
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.test_uiza_ima_video_activity_rl_slide;
    }

    private FrmVideoTop frmVideoTop;
    private FrmVideoBottom frmVideoBottom;

    private void initializeDraggablePanel(final Item item, final int position) {
        if (frmVideoTop != null || frmVideoBottom != null) {
            LLog.d(TAG, "initializeDraggablePanel exist");
            LLog.d(TAG, "onClickItem FrmChannel " + item.getName());
            clearUIFrmBottom();
            initFrmTop(item, position);
            draggablePanel.maximize();
            return;
        }
        frmVideoTop = new FrmVideoTop();
        frmVideoTop.setFragmentCallback(new BaseFragment.FragmentCallback() {
            @Override
            public void onViewCreated() {
                LLog.d(TAG, "setFragmentCallback onViewCreated -> initFrmTop");
                initFrmTop(item, position);
            }
        });
        frmVideoBottom = new FrmVideoBottom();
        frmVideoBottom.setFragmentCallback(new BaseFragment.FragmentCallback() {
            @Override
            public void onViewCreated() {
                frmVideoBottom.init(new ItemAdapterV2.Callback() {
                    @Override
                    public void onClick(Item item, int position) {
                        LLog.d(TAG, "onClickItem frmVideoBottom " + item.getName());
                        clearUIFrmBottom();
                        initFrmTop(item, position);
                    }

                    @Override
                    public void onLoadMore() {
                        //do nothing
                    }
                });
            }
        });

        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(frmVideoTop);
        draggablePanel.setBottomFragment(frmVideoBottom);

        //draggablePanel.setXScaleFactor(xScaleFactor);
        //draggablePanel.setYScaleFactor(yScaleFactor);
        //draggablePanel.setTopViewHeight(800);
        //draggablePanel.setTopFragmentMarginRight(topViewMarginRight);
        //draggablePanel.setTopFragmentMarginBottom(topViewMargnBottom);
        draggablePanel.setClickToMaximizeEnabled(true);
        draggablePanel.setClickToMinimizeEnabled(false);
        draggablePanel.setEnableHorizontalAlphaEffect(false);
        setSizeFrmTop();
        draggablePanel.initializeView();

        frmVideoTop.setFrmTopCallback(new FrmVideoTop.FrmTopCallback() {
            @Override
            public void initDone(boolean isInitSuccess, GetLinkPlay getLinkPlay, GetDetailEntity getDetailEntity) {
                LLog.d(TAG, "setFrmTopCallback initDone");
                frmVideoTop.getUizaIMAVideo().getPlayerView().setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
                    @Override
                    public void onVisibilityChange(int visibility) {
                        if (draggablePanel != null && !isLandscape) {
                            if (draggablePanel.isMaximized()) {
                                if (visibility == View.VISIBLE) {
                                    //LLog.d(TAG, TAG + " onVisibilityChange visibility == View.VISIBLE");
                                    draggablePanel.setEnableSlide(false);
                                } else {
                                    //LLog.d(TAG, TAG + " onVisibilityChange visibility != View.VISIBLE");
                                    draggablePanel.setEnableSlide(true);
                                }
                            } else {
                                draggablePanel.setEnableSlide(true);
                            }
                        }
                    }
                });
                intFrmBottom(getDetailEntity);
            }

            @Override
            public void onClickListEntityRelation(Item item, int position) {
                LLog.d(TAG, "onClickItemListEntityRelation " + item.getName());
                clearUIFrmBottom();
                initFrmTop(item, position);
            }
        });
    }

    private void initFrmTop(Item item, int position) {
        String playerSkinId = UizaData.getInstance().getPlayerId();

        //String entityId = "88cdcd63-da16-4571-a8c4-ed7421865988";
        String entityId = item.getId();

        //String entityTitle = "Dummy title";
        String entityTitle = item.getName();

        //String videoCoverUrl = null;
        String videoCoverUrl = item.getThumbnail();

        //String urlIMAAd = activity.getString(loitp.core.R.string.ad_tag_url);
        String urlIMAAd = null;

        //String urlThumnailsPreviewSeekbar = activity.getString(loitp.core.R.string.url_thumbnails);
        String urlThumnailsPreviewSeekbar = null;

        frmVideoTop.setupVideo(playerSkinId, entityId, entityTitle, videoCoverUrl, urlIMAAd, urlThumnailsPreviewSeekbar);
    }

    private void intFrmBottom(GetDetailEntity getDetailEntity) {
        LLog.d(TAG, "intFrmBottom");
        frmVideoBottom.setup(getDetailEntity);
    }

    private void clearUIFrmBottom() {
        frmVideoBottom.clearAllViews();
    }

    public void play(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        initializeDraggablePanel(item, position);
    }

    private boolean isLandscape;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (activity != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                isLandscape = true;
                setSizeFrmTop();
                draggablePanel.setEnableSlide(false);
            } else {
                isLandscape = false;
                setSizeFrmTop();
                draggablePanel.setEnableSlide(true);
            }
        }
    }

    private void setSizeFrmTop() {
        if (isLandscape) {
            draggablePanel.setTopViewHeightApllyNow(LScreenUtil.getScreenHeight());
        } else {
            draggablePanel.setTopViewHeightApllyNow(LScreenUtil.getScreenWidth() * 9 / 16);
        }
    }

    @Override
    public void onBackPressed() {
        //LLog.d(TAG, "onBackPressed");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

    public DraggablePanel getDraggablePanel() {
        return draggablePanel;
    }
}