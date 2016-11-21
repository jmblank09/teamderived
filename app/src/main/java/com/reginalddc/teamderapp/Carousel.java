package com.reginalddc.teamderapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Carousel extends Fragment {

    public static final String ARG_PAGE = "page";
    private static final String TAG = "IntroFragment";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static Carousel create(int pageNumber) {
        Carousel fragment = new Carousel();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Carousel() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Select a layout based on the current page
        int layoutResId;
        switch (mPageNumber) {
            case 0:
                layoutResId = R.layout.fragment_carousel1;
                break;
            case 1:
                layoutResId = R.layout.fragment_carousel2;
                break;
            case 2:
                layoutResId = R.layout.fragment_carousel3;
                break;
            case 3:
                layoutResId = R.layout.fragment_carousel4;
                break;
            default:
                layoutResId = R.layout.fragment_carousel1;
        }

        // Inflate the layout resource file
        View view = getActivity().getLayoutInflater().inflate(layoutResId, container, false);

        // Set the current page index as the View's tag (useful in the PageTransformer)
        view.setTag(mPageNumber);

        return view;
    }
}
