package com.example.imagematch;

import org.opencv.android;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	// statics
	private static ImageView	view1;
	private static ImageView	view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		view1 = (ImageView)findViewById(R.id.view1);
		view2 = (ImageView)findViewById(R.id.view2);

		//���͉摜
		Bitmap src = BitmapFactory.decodeFile("/mnt/sdcard/Android/FtrScanDemo/fp2.bmp");
		Bitmap src1 = src.copy(Bitmap.Config.ARGB_8888, true);
		Mat image = android.BitmapToMat(src1);
		//�e���v���[�g�摜
		src = BitmapFactory.decodeFile("/mnt/sdcard/Android/FtrScanDemo/fp1.bmp");
		Bitmap src2 = src.copy(Bitmap.Config.ARGB_8888, true);
		Mat templ = android.BitmapToMat(src2);
		//�e���v���[�g�}�b�`���O
		Mat result = new Mat();
		Imgproc.matchTemplate(image, templ, result, Imgproc.TM_CCOEFF_NORMED);
		Core.MinMaxLocResult maxr = Core.minMaxLoc(result);
		//�}�b�`���O���ʂ̕\��
		org.opencv.core.Point maxp = maxr.maxLoc;
		org.opencv.core.Point pt2 = new Point((int)(maxp.x + templ.width()), (int)(maxp.y + templ.height()));
		Mat dst = image.clone();
		Core.rectangle(dst, maxp, pt2, new Scalar(255,0,0), 2);
		
		view1.setImageBitmap(src1);
		view2.setImageBitmap(src2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
