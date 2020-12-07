package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddUserFragment extends Fragment {
    private Button addUserBtn;
    private EditText nameEditText;
    private EditText lastnameEditText;
    private UserList userAddList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        userAddList = UserList.get(getActivity());
        nameEditText = view.findViewById(R.id.nameEditText);
        lastnameEditText = view.findViewById(R.id.lastnameEditText);
        addUserBtn = view.findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString();
                String userLastname = lastnameEditText.getText().toString();
                User user = new User();
                user.setUserName(userName);
                user.setUserLastName(userLastname);
                UserList userAddList = UserList.get(getActivity());
                userAddList.addUser(user);
                requireActivity().onBackPressed();
            }
        });
        return view; // возвращает отображение
    }
}

