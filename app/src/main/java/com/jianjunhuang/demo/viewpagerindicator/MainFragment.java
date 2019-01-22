package com.jianjunhuang.demo.viewpagerindicator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

  public static MainFragment newInstance(int number) {
    Bundle args = new Bundle();
    args.putInt("key", number);
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    TextView textView = view.findViewById(R.id.tv_number);
    Bundle bundle = getArguments();
    textView.setText(String.valueOf(bundle.getInt("key")));
    return view;
  }
}
