package a123.diplom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import a123.diplom.Interface.API;
import a123.diplom.Model.Login.SessionReg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a123.diplom.Login.SAVED_ID;
import static a123.diplom.Login.SAVED_KEY;
import static a123.diplom.Login.SAVED_ROLE;
import static a123.diplom.Login.sPref;
import static a123.diplom.Login.savedKey;


public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent intent = new Intent(this, Group.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }else if (id == R.id.nav_exit) {
            new AlertDialog.Builder(this)
                    .setTitle("Выход")
                    .setMessage("Вы действительно хотите выйти?")
                    .setNegativeButton("Нет", null)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            API.Converter.getConvert().getDeleteKey(savedKey).enqueue(new Callback<SessionReg>() {
                                @Override
                                public void onResponse(Call<SessionReg> call, Response<SessionReg> response) {

                                }
                                @Override
                                public void onFailure(Call<SessionReg> call, Throwable t) {
                                }
                            });
                            sPref.edit().remove(SAVED_KEY).apply();
                            sPref.edit().remove(SAVED_ID).apply();
                            sPref.edit().remove(SAVED_ROLE).apply();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                    }).create().show();

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
