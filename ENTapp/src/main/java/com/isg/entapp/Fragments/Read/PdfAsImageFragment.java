package com.isg.entapp.Fragments.Read;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.isg.entapp.R;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 *
 */
public class PdfAsImageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    private ImageView mImg;
    private Bitmap bitmap;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static PdfAsImageFragment create(int pageNumber, Bitmap bm) {
        PdfAsImageFragment fragment = new PdfAsImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        fragment.setImage(bm);

        return fragment;
    }

    public PdfAsImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        System.out.println("Page number is: "+getArguments().getInt(ARG_PAGE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView =  (ViewGroup)inflater
                .inflate(R.layout.pdf_to_image_fragment, container, false);
        mImg = (ImageView)getActivity().findViewById(R.id.imagebitmap);

        return rootView;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if(mImg != null && bitmap != null){
            mImg.setImageBitmap(bitmap);
            System.out.println("The page number is: "+getPageNumber());
            mImg.setAdjustViewBounds(true);
        } else {
            System.out.println("Image View in PDF Fragment is null");
        }
    }

    public void setImage(Bitmap img){
        this.bitmap = img;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}