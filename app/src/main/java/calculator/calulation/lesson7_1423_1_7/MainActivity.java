package calculator.calulation.lesson7_1423_1_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        readSettings();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main:
                showFragment(MainFragment.newInstance());
                break;
            case R.id.action_favorite:
                showFragment(FavoriteFragment.newInstance());
                break;
            case R.id.action_settings:
                showFragment(SettingsFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void readSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        Settings.isDeleteFragment = sharedPreferences.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
        Settings.isBackIsRemove = sharedPreferences.getBoolean(Settings.IS_BACK_IS_REMOVE_FRAGMENT, false);
        Settings.isBackStack = sharedPreferences.getBoolean(Settings.IS_BACK_STACK_USED, false);
        Settings.isReplaceFragment = sharedPreferences.getBoolean(Settings.IS_REPLACE_FRAGMENT_USED, false);
        Settings.isAddFragment = sharedPreferences.getBoolean(Settings.IS_ADD_FRAGMENT_USED, false);
    }

    private void initView() {
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonMain = findViewById(R.id.buttonMain);
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        Button buttonSettings = findViewById(R.id.buttonSettings);

        buttonBack.setOnClickListener(this);
        buttonMain.setOnClickListener(this);
        buttonFavorite.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if(fragmentManager.getFragments().size()<=1) break;
                if( Settings.isBackIsRemove){
                    Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
                    if(fragmentForDelete!=null){
                        fragmentManager.beginTransaction().remove(fragmentForDelete).commit();
                    }
                }else {
                    fragmentManager.popBackStack();
                }
                break;
            case R.id.buttonMain:
                showFragment(MainFragment.newInstance());
                break;
            case R.id.buttonFavorite:
                showFragment(FavoriteFragment.newInstance());
                break;
            case R.id.buttonSettings:
                showFragment(SettingsFragment.newInstance());
                break;

        }
    }

    Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fList = fragmentManager.getFragments();
        for (int i = 0; i < fList.size(); i++) {
            Fragment fragment = fList.get(i);
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    public void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (Settings.isDeleteFragment) {
            Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
            if (fragmentForDelete != null) {
                fragmentTransaction.remove(fragmentForDelete);
            }
        }

        if (Settings.isAddFragment) {
            fragmentTransaction
                    .add(R.id.fragment_container, fragment);
        } else if (Settings.isReplaceFragment) {
            fragmentTransaction
                    .replace(R.id.fragment_container, fragment);
        }

        if(Settings.isBackStack){
            fragmentTransaction.addToBackStack("");
        }

        fragmentTransaction.commit();

    }
}