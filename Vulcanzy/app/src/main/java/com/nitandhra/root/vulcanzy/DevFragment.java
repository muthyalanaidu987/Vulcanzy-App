package com.nitandhra.root.vulcanzy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DevFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        AboutVulcanzy.state="closed";
        return inflater.inflate(R.layout.fragment_dev,container,false );

    }

}
