package net.rcelma.feb9_17_camerarecycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ImageRecyclerHolder extends RecyclerView.ViewHolder {
	public ImageView iv;
	public TextView tv1;
	public TextView tv2;
	public TextView tv3;
	private Button b1;

	public ImageRecyclerHolder(final View itemView) {

		super(itemView);
		iv = (ImageView) itemView.findViewById(R.id.iv2);
		tv1 = (TextView) itemView.findViewById(R.id.tv1ih2);
		tv2 = (TextView) itemView.findViewById(R.id.tv2ih2);
		tv3 = (TextView) itemView.findViewById(R.id.imUriIh2);
		b1 = (Button) itemView.findViewById(R.id.b1ih2);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				TextView tvUri;
				ViewGroup viewGroup1 = (ViewGroup) view.getParent();
				String url = null;
				for(int i = 0; i < viewGroup1.getChildCount(); i++){
					View v = viewGroup1.getChildAt(i);
					Log.d("TAGGG", "" + v.getId());
					if(v.getId() == R.id.imUriIh2){

						url = ((TextView) v).getText().toString();
					}
				}
				ImageView iv = (ImageView) ((ViewGroup) viewGroup1.getParent().getParent()).findViewById(R.id.imageView);
				Glide.with(viewGroup1.getContext()).load(url).into(iv);
			}
		});
	}
}
