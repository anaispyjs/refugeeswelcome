package de.pajowu.donate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListTabFragment extends Fragment{
    public ArrayList<CategoryListItem> arrayList;
    public long primaryKeyValue;
    public String title;
    public CategoryListTabFragment(ArrayList<CategoryListItem> arli, String ttl) {
        this.arrayList = arli;
        Log.d("MainActivity cltf",arrayList.toString());
        title = ttl;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_list_tab, container, false);

        ListView listView = (ListView) viewRoot.findViewById(R.id.listView);
        View empty = viewRoot.findViewById(R.id.empty);
        listView.setEmptyView(empty);
        ScrollView scrollView = (ScrollView) viewRoot.findViewById(R.id.fragment_list_scrollView);
        Log.d("MainActivity","onCreateView");
        Log.d("MainActivity cltf",this.arrayList.toString());
        Log.d("MainActivity cltf",arrayList.toString());
        CategoryListAdapter listAdapter = new CategoryListAdapter(getActivity(), R.layout.list_layout, this.arrayList);

        Log.d("R.layout.list_layout = ", "" + R.layout.list_layout);
        Log.d("R.layout.list_layout = ", ""+R.layout.list_layout);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //primaryKeyValue = arrayList.get(position).primaryKey;
                //((MaterialNavigationDrawer) getActivity()).setFragment(new ProductFragment(getActivity().getApplicationContext(), arrayList.get(position).primaryKey),"Offer");
                Log.d("ListView clicked: ", ""+position);
                ((MaterialNavigationDrawer) getActivity()).setFragment(new CategoryProductsFragment(getActivity().getApplicationContext(), arrayList.get(position).id),"Category Products");
                //Log.d("ListView Pos: ", ""+primaryKeyValue);
                
            }
        });

        getTotalHeightofListView(listView, listAdapter);

        // Make List scroll by default to top
        scrollView.smoothScrollTo(0, 0);


        return viewRoot;
    }

    public static void getTotalHeightofListView(ListView listView, CategoryListAdapter listAdapter) {

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View mView = listAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
    public String getTitle() {
        return title;
    }
}
