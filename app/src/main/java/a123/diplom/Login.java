package a123.diplom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;

import a123.diplom.Interface.API;
import a123.diplom.Model.Login.Registration;
import a123.diplom.Model.Login.Request;
import a123.diplom.utils.ProgressGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements ProgressGenerator.OnCompleteListener{
    final String TAG = "Логин:";
    EditText log, psd;
    String log_str;
    String psd_str;
    ActionProcessButton btn;
    ProgressGenerator progressGenerator;
    boolean cancel;
    View focusView;
    public static String savedRole;
    public static String savedKey;
    public static String savedId;
    public static SharedPreferences.Editor editor ;
    public static SharedPreferences sPref;
    public static final String SAVED_KEY = "saved_key";
    public static final String SAVED_ID = "saved_ID";
    public static final String SAVED_ROLE = "saved_role";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressGenerator = new ProgressGenerator(this);
        btn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        editor = sPref.edit();
        log = (EditText) findViewById(R.id.login);
        psd = (EditText) findViewById(R.id.password);
        Login();

    }
    public void Login() {

        cancel = false;
        focusView = null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_str = log.getText().toString();
                psd_str = psd.getText().toString();

                if (TextUtils.isEmpty(log_str)) {
                    log.setError("Введите логин!");
                    focusView = log;
                    cancel = true;

                }
                if (!(log_str.contains("@") && log_str.contains("."))){
                    log.setError("Некорректный логин!");
                    focusView = log;
                    cancel = true;

                }
                if (TextUtils.isEmpty(psd_str)) {
                    psd.setError("Введите пароль!");
                    focusView = psd;
                    cancel = true;

                }
                else {
                    progressGenerator.start(btn);
                    //btn.setEnabled(true);

                    API.Converter.getConvert().getReg(new Request(log_str,psd_str)).enqueue(new Callback<Registration>() {
                        @Override
                        public void onResponse(Call<Registration> call, Response<Registration> response) {
                            Registration reg = response.body();


                            if(reg.getConnect()){
                                //btn.setEnabled(false);
                                btn.setProgress(100);
                                savedRole = reg.getUser().getRole();
                                savedKey = reg.getSession_key();
                                savedId = reg.getUser().getId();
                                if(savedRole.equals("admin")){
                                    Toast.makeText(getApplicationContext(),
                                            "Неверный логин или пароль!", Toast.LENGTH_LONG).show();
                                }
                                editor.putString(SAVED_ID,savedId);
                                editor.putString(SAVED_KEY,savedKey);
                                editor.putString(SAVED_ROLE,savedRole);
                                editor.apply();
                                Log.d("LoginActivity", "role:" + savedRole);
                                Intent intent = new Intent(Login.this, Group.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                btn.setProgress(-1);
                                //btn.setEnabled(false);
                                Toast.makeText(getApplicationContext(),
                                        "Неверный логин или пароль!", Toast.LENGTH_LONG).show();
                            }

                            Log.d("LoginActivity", "connect:" + reg.getConnect());
                        }
                        @Override
                        public void onFailure(Call<Registration> call, Throwable t) {

                        }
                    });
                }
            }

        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onComplete() {

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Login: onStart()-1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Login: onResume()-1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Login: onPause()-1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Login: onStop()-1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Login: onDestroy()-1");
    }

}
