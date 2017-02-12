package net.rcelma.feb9_17_camerarecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 2/9/2017.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerHolder> {
	List<ImageData> images;

	public ImageRecyclerAdapter(List<ImageData> images) {

		this.images = images;
	}

	public void setImages(List<ImageData> images) {

		this.images = images;
	}

	@Override
	public ImageRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holder2, parent, false);
		return new ImageRecyclerHolder(view);
	}

	@Override
	public void onBindViewHolder(ImageRecyclerHolder holder, int position) {

		ImageData data = images.get(position);
		Glide.with(holder.itemView.getContext()).load(data.getUrl()).into(holder.iv);
		holder.tv1.setText(data.getName());
		holder.tv2.setText(data.getDateCreated());
		holder.tv3.setText(data.getUrl());
	}

	@Override
	public int getItemCount() {

		return images.size();
	}
}