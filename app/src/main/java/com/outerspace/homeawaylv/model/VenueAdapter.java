package com.outerspace.homeawaylv.model;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.view.RxViewGroup;
import com.outerspace.homeawaylv.databinding.VenueHolderBinding;
import com.outerspace.homeawaylv.view.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder> {
    public static final int MAX_CATEGORY = 4; // Max index for category icon in the venue_holder.xml layout
    private static final int ICON_SIZE = 32;

    private Context fragmentContext;
    private ArrayList<VenueItem> items;
    public VenueAdapter(Context fragmentContext, ArrayList<VenueItem> items) {
        this.fragmentContext = fragmentContext;
        this.items = items;
    }

    CompositeDisposable composite = new CompositeDisposable();

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        VenueHolderBinding binding = VenueHolderBinding.inflate(inflater, parent, false);
        return new VenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        VenueItem item = items.get(position);
        holder.binding.setItemHolder(item);
        for(int i = 0; i < item.categoryList.size(); i++) {
            holder.categoryTxt[i].setVisibility(View.VISIBLE);
            holder.categoryTxt[i].setText(item.categoryList.get(i).name);
            holder.categoryImg[i].setVisibility(View.VISIBLE);
            Glide.with(fragmentContext)
                    .load(item.categoryList.get(i).iconUrl)
                    .into(holder.categoryImg[i]);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VenueViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        composite.add( holder.disposable );
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VenueViewHolder holder) {
        composite.remove( holder.disposable );
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        composite.clear();
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {
        public VenueHolderBinding binding;
        public Disposable disposable;
        public ImageView[] categoryImg = new ImageView[MAX_CATEGORY+1];
        public TextView[]  categoryTxt = new TextView[MAX_CATEGORY+1];

        public VenueViewHolder(VenueHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            categoryImg[0] = binding.category0;
            categoryImg[1] = binding.category1;
            categoryImg[2] = binding.category2;
            categoryImg[3] = binding.category3;
            categoryImg[4] = binding.category4;
            categoryTxt[0] = binding.caption0;
            categoryTxt[1] = binding.caption1;
            categoryTxt[2] = binding.caption2;
            categoryTxt[3] = binding.caption3;
            categoryTxt[4] = binding.caption4;

            disposable = RxView.clicks(binding.itemHolder)
                    .subscribe( (Object v) -> {
                        Intent intent = new Intent(MainActivity.START_DETAIL_MESSAGE);
                        String venueId = binding.getItemHolder().id;
                        intent.putExtra(MainActivity.ID_FOR_DETAIL, venueId);
                        LocalBroadcastManager
                                .getInstance(binding.getRoot().getContext())
                                .sendBroadcast(intent);
                    });
        }
    }
}
