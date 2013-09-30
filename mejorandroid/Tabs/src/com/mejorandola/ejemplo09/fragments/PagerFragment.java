package com.mejorandola.ejemplo09.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mejorandola.ejemplo09.R;

public class PagerFragment extends Fragment {
    public static final String CONTENT = "content";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_pager, container, false);
        Bundle args = getArguments();
        TextView content = ((TextView) rootView.findViewById(R.id.txt_pager_content));
        content.setText(args.getInt(CONTENT) + "");
        return rootView;
    }
}