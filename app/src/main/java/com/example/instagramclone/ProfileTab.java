package com.example.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtBio,edtProfession,edtHobbies,edtFavSport;
    Button btnUpdate;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTab newInstance(String param1, String param2) {
        ProfileTab fragment = new ProfileTab();
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
        View view=inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName=view.findViewById(R.id.edtProfileName);
        edtBio=view.findViewById(R.id.edtBio);
        edtProfession=view.findViewById(R.id.edtProfession);
        edtHobbies=view.findViewById(R.id.edtHobbies);
        edtFavSport=view.findViewById(R.id.edtFavSport);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        final ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser.get("profileName")==null)
        {
            edtProfileName.setText("");
        }
        else {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }
        if (parseUser.get("profileBio")==null)
        {
            edtBio.setText("");
        }
        else {
            edtBio.setText(parseUser.get("profileBio").toString());
        }
        if (parseUser.get("profileProfession")==null)
        {
            edtProfession.setText("");
        }
        else {
            edtProfession.setText(parseUser.get("profileProfession" +
                    "").toString());
        }
        if (parseUser.get("profileHobbies")==null)
        {
            edtHobbies.setText("");
        }
        else {
            edtHobbies.setText(parseUser.get("profileHobbies").toString());
        }
        if (parseUser.get("profileFavSport")==null)
        {
            edtFavSport.setText("");
        }
        else {
            edtFavSport.setText(parseUser.get("profileFavSport").toString());
        }








        // edtProfileName.setText(parseUser.get("profileName")+ "");
       // edtBio.setText(parseUser.get("profileBio")+ "");
     //   edtProfession.setText(parseUser.get("profileProfession")+ "");
    //    edtHobbies.setText(parseUser.get("profileHobbies")+ "");
        //edtFavSport.setText(parseUser.get("profileFavSport")+ "");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtBio.getText().toString());
                parseUser.put("profileProfession",edtProfession.getText().toString());
                parseUser.put("profileHobbies",edtHobbies.getText().toString());
                parseUser.put("profileFavSport",edtFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            FancyToast.makeText(getContext(),"Info Updated!!", Toast.LENGTH_SHORT,FancyToast.INFO,true).show();
                        }
                        else
                        {
                            FancyToast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                        }
                    }
                });

            }
        });
        return  view;
    }
}