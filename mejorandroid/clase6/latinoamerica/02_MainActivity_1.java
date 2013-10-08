package com.mejorandroid.pictures;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.mejorandroid.pictures.utils.AlbumStorageDirFactory;
import com.mejorandroid.pictures.utils.BitmapManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private static final int TAKE_PICTURE = 1;
	private static final int SELECT_PICTURE = 2;
	private static final String ALBUM_NAME = "Mejorandroid";
	
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private Bitmap mImageBitmap;
	
	private ImageView pictureTaken;
	private String currentPhotoPath;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageBitmap = null;
		pictureTaken = (ImageView) findViewById(R.id.picture_taken);
	}
	
	public void takePictureFromCamera(View v){
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File f = null;
			try {
				f = AlbumStorageDirFactory.setUpPhotoFile(ALBUM_NAME);
				currentPhotoPath = f.getAbsolutePath();
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				currentPhotoPath = null;
			}
			startActivityForResult(intent, TAKE_PICTURE);
		}
	}
	
	public void takePictureFromGallery(View v){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
	}
	
	private void addPictureToGallery() {
		Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(currentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		intent.setData(contentUri);
		this.sendBroadcast(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case TAKE_PICTURE: {
					if (currentPhotoPath != null) {
							mImageBitmap = BitmapManager.setPic(pictureTaken, currentPhotoPath);
							pictureTaken.setImageBitmap(mImageBitmap);
							addPictureToGallery();
							currentPhotoPath = null;
					}
					break;
				}
				case SELECT_PICTURE: {
					Uri selectedImageUri = data.getData();
					currentPhotoPath = AlbumStorageDirFactory.getImageFromGalleryPath(this, selectedImageUri);
					pictureTaken.setImageURI(selectedImageUri);
					break;
				}
			} 
		}
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
	}
	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
	 *
	 * @param context The application's environment.
	 * @param action The Intent action to check for availability.
	 *
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list =
			packageManager.queryIntentActivities(intent,
					PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

}