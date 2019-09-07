package com.ardakazanci.popularmovielist.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ardakazanci.popularmovielist.Interface.BottomSheetListener;
import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.common.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;
    private TextView tvPopular, tvTopRated, tvUpcoming;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);

        tvPopular = v.findViewById(R.id.textview_popular);
        tvTopRated = v.findViewById(R.id.textview_top_rated);
        tvUpcoming = v.findViewById(R.id.textview_upcoming);

        tvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTextViewMenuClicked(Constants.I_POPULAR, true);
                dismiss();
            }
        });
        tvTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTextViewMenuClicked(Constants.I_TOP_RATED, true);
                dismiss();
            }
        });

        tvUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTextViewMenuClicked(Constants.I_UPCOMING, true);
                dismiss();
            }
        });



        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " ButtomSheetListener Bağlanırken problem yaşandı.");
        }
    }
}