package uiza.v4.categories;

/**
 * Created by www.muathu@gmail.com on 12/8/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.uiza.core.api.response.video.VideoData;
import io.uiza.core.util.UzDisplayUtil;
import io.uiza.core.util.UzImageUtil;
import java.util.List;
import uiza.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.DataHolder> {
    private int lastPosition = -1;
    private Context context;
    private Callback callback;
    private List<VideoData> dataList;
    private int sizeH;

    public CategoriesAdapter(Context context, List<VideoData> dataList, Callback callback) {
        this.context = context;
        this.dataList = dataList;
        this.callback = callback;
        this.sizeH = UzDisplayUtil.getScreenWidth() / 3;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.v4_item_categories, parent, false);
        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {
        final VideoData data = dataList.get(position);

        holder.cardView.getLayoutParams().height = sizeH;
        holder.cardView.requestLayout();

        holder.tvTitle.setText(data.getName());
        UzDisplayUtil.setTextShadow(holder.tvTitle);
        UzImageUtil.load(context, data.getThumbnail(), holder.ivThumnail);

        holder.ivPlaylistFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickPlaylistFolder(data, position);
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClick(data, position);
                }
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (callback != null) {
                    callback.onLongClick(data, position);
                }
                return true;
            }
        });
        if (position == dataList.size() - 1) {
            if (callback != null) {
                callback.onLoadMore();
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface Callback {
        public void onClick(VideoData data, int position);

        public void onClickPlaylistFolder(VideoData data, int position);

        public void onLongClick(VideoData data, int position);

        public void onLoadMore();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivThumnail;
        public ImageView ivPlaylistFolder;
        public CardView cardView;

        public DataHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            ivThumnail = (ImageView) view.findViewById(R.id.iv_thumnail);
            ivPlaylistFolder = (ImageView) view.findViewById(R.id.iv_playlist_folder);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}