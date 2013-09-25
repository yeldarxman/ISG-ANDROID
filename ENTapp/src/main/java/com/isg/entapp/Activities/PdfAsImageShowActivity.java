package com.isg.entapp.Activities;

//import java.nio.ByteBuffer;



//import android.support.v4.app.FragmentManager;
//import android.app.FragmentManager;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.isg.entapp.Fragments.Read.PdfAsImageFragment;
import com.isg.entapp.R;
import com.qoppa.android.pdfProcess.PDFDocument;
import com.qoppa.android.pdfViewer.fonts.StandardFontTF;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import net.sf.andpdf.nio.ByteBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by 1 on 9/9/13.
 */
public class PdfAsImageShowActivity extends FragmentActivity {
        ImageView im ;
    private static int NUM_PAGES = 5;
    public static ArrayList<Bitmap> images = new ArrayList<Bitmap>();

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_to_image_view);
        readFromAssets("test2.pdf");

        // Instantiate a ViewPager and a PagerAdapter.

        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        //mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.app.Fragment getItem(int position) {

            //PdfAsImageFragment sl = images.get(position);
              // images.get(position);
            PdfAsImageFragment yerbolat = PdfAsImageFragment.create(position, images.get(position));
            return yerbolat;
            //PdfAsImageFragment sl= PdfAsImageFragment.

        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }



    private void readFromAssets(String filename)
    {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), filename);
        System.out.print("Writing file to00000: " + file.getAbsolutePath());
        try{

            in = assetManager.open(filename);
            out = openFileOutput(file.getName(), Context.MODE_MULTI_PROCESS);

            System.out.print("Writing file to1111: " + file.getAbsolutePath());
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            System.out.print("Writing file to2222: " + file.getAbsolutePath());
        }
        catch (Exception e)
        {
            System.out.print("Writing Exception : " + e.getMessage());
            Toast.makeText(PdfAsImageShowActivity.this,
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        //Converting PDF to Bitmap Image...
        StandardFontTF.mAssetMgr = getAssets();
        //Load document to get the first page

        byte[] bytes;
        try {

            FileInputStream is = new FileInputStream(file);
            PDFDocument pdf = new PDFDocument(file.getAbsolutePath(),null);
            NUM_PAGES = pdf.getPageCount();
            // Get the size of the file
            long length = file.length();
            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;

            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }


            ByteBuffer buffer = ByteBuffer.NEW(bytes);
            String data = Base64.encodeToString(bytes, Base64.DEFAULT);
            PDFFile pdf_file = new PDFFile(buffer);

            for(int i=0; i<NUM_PAGES; i++){

                PDFPage page = pdf_file.getPage(i,true);

                RectF rect = new RectF(0, 0, (int) page.getBBox().width(),
                        (int) page.getBBox().height());
                //  Bitmap bufferedImage = Bitmap.createBitmap((int)rect.width(), (int)rect.height(),
                //        Bitmap.Config.ARGB_8888);

                Bitmap image = page.getImage((int)rect.width(), (int)rect.height(), rect);
                FileOutputStream os = new FileOutputStream(getFilesDir().getAbsolutePath()+"pdf.jpg");
                image.compress(Bitmap.CompressFormat.JPEG,80, os);
                images.add(image);
                os.close();
            }
                //mPager.addView(im);
                //im.setImageBitmap(bm);
                //Saving the Bitmap
                //OutputStream os = new FileOutputStream(file.getAbsolutePath());
                //bm.compress(Bitmap.CompressFormat.PNG, 100, os);
                //os.close();

        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

}
