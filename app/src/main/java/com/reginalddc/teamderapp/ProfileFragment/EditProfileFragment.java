package com.reginalddc.teamderapp.ProfileFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    View fragmentView;
    EditText name, email, phone, birthday, school, course,
        yearLevel, mainRole, otherRole, achievements, extraCo;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        name = (EditText) fragmentView.findViewById(R.id.editText_name);
        email = (EditText) fragmentView.findViewById(R.id.editText_email);
        phone = (EditText) fragmentView.findViewById(R.id.editText_mobileNum);
        birthday = (EditText) fragmentView.findViewById(R.id.editText_birthday);
        school = (EditText) fragmentView.findViewById(R.id.editText_school);
        course = (EditText) fragmentView.findViewById(R.id.editText_course);
        yearLevel = (EditText) fragmentView.findViewById(R.id.editText_yearLevel);
        mainRole = (EditText) fragmentView.findViewById(R.id.editText_mainRole);
        otherRole = (EditText) fragmentView.findViewById(R.id.editText_preferredRole);
        achievements = (EditText) fragmentView.findViewById(R.id.editText_achievements);
        extraCo = (EditText) fragmentView.findViewById(R.id.editText_seminars);

        RequestParams params = new RequestParams();
        params.put("email", name.getText().toString());
        return fragmentView;
    }

}
