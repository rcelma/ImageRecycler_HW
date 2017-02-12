package net.rcelma.feb9_17_camerarecycler;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	//Photo take
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private String JPEG_FORMAT = ".jpg";
	private static final int REQUEST_TAKE_PHOTO = 1;
	private static final String TAG = "Camara2Rec TAG";

	//DataGral
	private List<ImageData> images;
	private RecyclerView recyclerView;
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

	//Photo view
	private ImageView mImageView;
	private FloatingActionButton fab;
	private String photoPath;
	private ImageData imageData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fab = (FloatingActionButton) findViewById(R.id.fab);
		mImageView = (ImageView) findViewById(R.id.imageView);

		//Files from the camera, loading
		File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		images = new ArrayList<>(directory.list().length);
		if (directory.isDirectory()) {
			for (File f : directory.listFiles()) {
				images.add(new ImageData(df.format(new Date(f.lastModified())), f.getName(), f.toURI().toString()));
			}
		}
		if (!images.isEmpty()) {
			Glide.with(this).load(images.get(0).getUrl()).into(mImageView);
		}

		//LayoutManager
		recyclerView = (RecyclerView) findViewById(R.id.rv);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		recyclerView.setLayoutManager(layoutManager);
		ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(images);
		recyclerView.setAdapter(adapter);
	}

	//--------------------------------------------
	//From here starts the Photo take methods
	public void camaraclick(View view) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = createImageFile();

			// Continue only if the File was successfully created
			if (photoFile != null) {
				photoPath = photoFile.toURI().toString();
				imageData = new ImageData(df.format(new Date(photoFile.lastModified())), photoFile.getName(), photoFile.toURI().toString());
				Uri photoURI = FileProvider.getUriForFile(this, "com.android.fileprovider2", photoFile);
				List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
				for (ResolveInfo resolveInfo : resInfoList) {
					grantUriPermission(resolveInfo.activityInfo.packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
				}
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}

	private File createImageFile() {

		File o = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "RCP_JPEG_".concat(timeStamp).concat(JPEG_FORMAT);
		File f = new File(o, imageFileName);
		return f;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		images.add(imageData);
		Glide.with(this).load(photoPath).into(mImageView);
	}
}