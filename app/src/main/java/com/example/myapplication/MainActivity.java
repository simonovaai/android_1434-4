package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Активность", "Метод onCreate вызван");
        setContentView(R.layout.activity_main);
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new UserListFragment(), "main_fragment").addToBackStack("main_fragment").commit();
    }
    @Override
    public void onStart(){// заново рисуем фрагмент при каждом добавлении пользователя
        super.onStart();
        Log.d("Активность", "Метод onStart вызван");
        // Создаем фрагмент
        Fragment fragment = new UserListFragment();
        // помещает элементы фрагмента на экран и фиксируем их на экране
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
    }
    @Override
    public void onBackPressed(){
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        if(currentFragment != null && currentFragment.isVisible()){
            super.onBackPressed();
        }else {
            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
        }
    }
    public static void changeFragment(View view, User user){
        // получаем хостинговую активность (MainActivity)
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // создаем менеджер фрагментов
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // создаем фрагмент
        Fragment fragment = new UserFragment();
        // создаем bundle - коллекция
        Bundle bundle = new Bundle();
        // записываем user в bundle для передачи во фрагмент
        bundle.putSerializable("user", (Serializable) user);
        // устанавливаем аргументы во фрагмент (кладем bundle во фрагмент)
        fragment.setArguments(bundle);
        // заменяем фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}
