package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersTab#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("unchecked")

  public class  UsersTab extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
       private ListView listView;
       private ArrayList arrayList;
       private ArrayAdapter arrayAdapter;
    public UsersTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersTab.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersTab newInstance(String param1, String param2) {
        UsersTab fragment = new UsersTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_users_tab, container, false);
        listView=view.findViewById(R.id.ListView);
        TextView txtLoadingUsers=view.findViewById(R.id.txtLoadingUsers);
        arrayList=new ArrayList();
        arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1,arrayList);

        listView.setOnItemClickListener(UsersTab.this);
        listView.setOnItemLongClickListener(UsersTab.this);

        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e==null)
                {
                    if(users.size()>0)
                    {
                        for(ParseUser user: users)
                        {
                            arrayList.add(user.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
                        txtLoadingUsers.animate().alpha(0).setDuration(2000);
                        listView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        return  view;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent=new Intent(getContext(),UsersPost.class);
        intent.putExtra("username",arrayList.get(position).toString());
        startActivity(intent);

    }
      // to get details when long pressed on user
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
         ParseQuery<ParseUser> parseUserParseQuery=ParseUser.getQuery();
         parseUserParseQuery.whereEqualTo("username",arrayList.get(position));
         parseUserParseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
             @Override
             public void done(ParseUser user, ParseException e) {
                 if(e==null&&user!=null)
                 {
                    // FancyToast.makeText(getContext(),user.get("profileProfession") + "", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                 }
             }
         });


        return true;
    }


}