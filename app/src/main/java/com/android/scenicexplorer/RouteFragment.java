package com.android.scenicexplorer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RouteFragment extends Fragment {
    private Button login;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View routeLayout =  inflater.inflate(R.layout.route_layout,container,false);

        return routeLayout;
    }
}
