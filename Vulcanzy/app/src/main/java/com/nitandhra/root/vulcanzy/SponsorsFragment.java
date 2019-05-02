package com.nitandhra.root.vulcanzy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SponsorsFragment extends Fragment {


 @Nullable
 @Override
 public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState )
 {

  View vi=inflater.inflate(R.layout.fragment_sponsors,container,false );
  AboutVulcanzy.state="closed";
  String[] titles = new String[]{};
  Integer[] images = new Integer[]{};
  ListView listView;
  List<RowItem> rowItems;

  AboutVulcanzy.state="closed";
  rowItems = new ArrayList<RowItem>();
  for (int i = 0; i < titles.length; i++) {
   RowItem item = new RowItem(images[i], titles[i]);
   rowItems.add(item);
  }

  listView = (ListView) vi.findViewById(R.id.sponsor_list);
  Sponsorslist adapter = new Sponsorslist(vi.getContext(),
          R.layout.sponsors_listview, rowItems);
  listView.setAdapter(adapter);
  return vi;
 }

}
