package com.hptsec.vulnlab.View.M3SubView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hptsec.vulnlab.R;

/**
 * Created by baolq on 9/14/2015.
 * M3 Introduce Fragment
 */
public class M3IntroduceFragment extends SherlockFragment {
    private static final String ARG_POSITION = "position";

    @SuppressWarnings("unused")
    private int position;

    public static M3IntroduceFragment newInstance(int position) {
        M3IntroduceFragment f = new M3IntroduceFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_m3introduce, container,
                false);

        TextView txtM3Introduce = (TextView) view
                .findViewById(R.id.txtM3IntroduceData);

        String vulnDescription = "When designing a mobile application, data is commonly exchanged in a client-server fashion. When the solution transmits its data, it must traverse the mobile device's carrier network and the internet. Threat agents might exploit vulnerabilities to intercept sensitive data while it's traveling across the wire. ";

        txtM3Introduce.setText(vulnDescription);

        return view;
    }
}
