package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {
    private User user;
    private TextView userName_userLastname_View;
    private EditText editName;
    private EditText editLastname;
    private Button updateBtn;
    private Button deleteBtn;
    private UserList userList;
    //запускается при создании фрагмента
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        // принимаем объект user
        user = (User) bundle.getSerializable("user");
    }
    // создаем компоненты внутри фрагментов и передаем их в Активность
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // шаблон-фрагмент кладем в контейнер и расширяем его, не присоединяя объекты к корневому элементу
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userList = UserList.get(getActivity());
        //  на фрагменте (View) ищем наш элемент
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        editName = view.findViewById(R.id.editName);
        editLastname = view.findViewById(R.id.editLastname);
        updateBtn = view.findViewById(R.id.updateBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);

        // соединяем фамилию и имя пользователя
        String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName); // выводим на экран нашу строку

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserName = editName.getText().toString();
                String newUserLastname = editLastname.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                userList.updateUser(user);
                Toast.makeText(getActivity(),"Данные изменены", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.deleteUser(user);
                Toast.makeText(getActivity(),"Данные удалены", Toast.LENGTH_SHORT).show();
            }
        });

        return view; // возвращает отображение
    }
}
