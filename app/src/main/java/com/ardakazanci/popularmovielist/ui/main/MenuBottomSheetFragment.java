package com.ardakazanci.popularmovielist.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.ardakazanci.popularmovielist.Interface.BottomSheetListener;
import com.ardakazanci.popularmovielist.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        Button button1 = v.findViewById(R.id.button1);
        Button button2 = v.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 1 Tıklandı");
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 2 Tıklandı");
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