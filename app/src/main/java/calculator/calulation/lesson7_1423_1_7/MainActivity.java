package calculator.calulation.lesson7_1423_1_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        SharedPreferences sharedPreferences = getSharedPreferences(Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);

        Settings.isDeleteFragment = sharedPreferences.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD,false);
        Settings.isBackIsRemove = sharedPreferences.getBoolean(Settings.IS_BACK_IS_REMOVE_FRAGMENT,false);
        Settings.isBackStack = sharedPreferences.getBoolean(Settings.IS_BACK_STACK_USED,false);
        Settings.isReplaceFragment = sharedPreferences.getBoolean(Settings.IS_REPLACE_FRAGMENT_USED,false);
        Settings.isAddFragment = sharedPreferences.getBoolean(Settings.IS_ADD_FRAGMENT_USED,false);

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

    private void showFragment(Fragment fragment) {
        
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}