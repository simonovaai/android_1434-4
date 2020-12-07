package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    private Button addUserBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        // создаем компонент View
        View view = inflater.inflate(R.layout.fragment_user_list, viewGroup, false);
        userRecyclerView = view.findViewById(R.id.userRecyclerView); // находим его на экране
        //создаем макет области и получаем активность, там будут размещаться элементы списка
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addUserBtn = view.findViewById(R.id.openAddUserActivity);

        UserList userList = UserList.get(getActivity());
        List<User> users = userList.getUsers(); // получаем список пользователей
        // обращаемся к адаптеру
        userAdapter = new UserAdapter(users);
        userRecyclerView.setAdapter(userAdapter); // устанавливаем адаптер
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity activity = (FragmentActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                Fragment fragment = new AddUserFragment();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment,"add_user_fragment").addToBackStack("add_user_fragment").commit();
            }
        });
        return view;
    }
    // формирует элементы списка
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView userItemTextView;
        private User itemUser;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user, viewGroup, false));
            // itemView это элемент списка
            userItemTextView = itemView.findViewById(R.id.userItem); // у текущей View мы спрашиваем элемент с идентификатором
            itemView.setOnClickListener(this);
        }
        public void bind(User user){ // связываем текст из списка users с элементом userItem
            itemUser = user;
            String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n-----";
            userItemTextView.setText(userName);
        }
        @Override
        public void onClick(View view) {
            MainActivity.changeFragment(view, itemUser);
        }
    }
    // предоставляет элементы для RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> users;
        public UserAdapter(List<User> users){
            this.users = users;
        }

        @Override //  для создания следующего элемента списка
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater, viewGroup);
        } // при перелистывании берется каждый раз новый элемент list_item_user

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            // привязываем элемент к View
            User user = users.get(position);
            userHolder.bind(user);
        }

        @Override // возвращает кол-во элементов в списке
        public int getItemCount() {
            return users.size();
        }
    }
}
