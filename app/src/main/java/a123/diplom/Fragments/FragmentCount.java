package a123.diplom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;

import a123.diplom.Interface.API;
import a123.diplom.Model.Count.CountMember;
import a123.diplom.Model.Count.CountRequest;
import a123.diplom.R;
import a123.diplom.utils.ProgressGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a123.diplom.CountSecretary.id;
import static a123.diplom.Login.savedId;
import static a123.diplom.Login.savedKey;


public class FragmentCount extends Fragment implements ProgressGenerator.OnCompleteListener{
    int[] ArrayItems = new int[14];
    int apply,i,pos;
    ActionProcessButton btn;
    ProgressGenerator progressGenerator;
    public EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        btn = (ActionProcessButton)view.findViewById(R.id.btn_count);
        btn.setOnClickListener(mybtn);
        editText = (EditText)view.findViewById(R.id.editText);

        initSpinner(view, R.array.spin_req_1, R.array.spin_1_criteria , R.id.spinner1,R.id.coins_1, 0);
        initSpinner(view, R.array.spin_req_2, R.array.spin_2_criteria , R.id.spinner2,R.id.coins_2, 1);
        initSpinner(view, R.array.spin_req_3, R.array.spin_3_criteria , R.id.spinner3,R.id.coins_3, 2);
        initSpinner(view, R.array.spin_req_4, R.array.spin_4_criteria , R.id.spinner4,R.id.coins_4, 3);
        initSpinner(view, R.array.spin_req_5, R.array.spin_5_criteria , R.id.spinner5,R.id.coins_5, 4);
        initSpinner(view, R.array.spin_req_6, R.array.spin_6_criteria , R.id.spinner6,R.id.coins_6, 5);
        initSpinner(view, R.array.spin_req_7, R.array.spin_7_criteria , R.id.spinner7,R.id.coins_7, 6);
        initSpinner(view, R.array.spin_req_8, R.array.spin_8_criteria , R.id.spinner8,R.id.coins_8, 7);
        initSpinner(view, R.array.spin_req_9, R.array.spin_9_criteria , R.id.spinner9,R.id.coins_9, 8);
        initSpinner(view, R.array.spin_req_10, R.array.spin_10_criteria , R.id.spinner10,R.id.coins_10, 9);
        initSpinner(view, R.array.spin_req_11, R.array.spin_11_criteria , R.id.spinner11,R.id.coins_11, 10);
        initSpinner(view, R.array.spin_req_12, R.array.spin_12_criteria , R.id.spinner12,R.id.coins_12, 11);
        initSpinner(view, R.array.spin_req_13, R.array.spin_13_criteria , R.id.spinner13,R.id.coins_13, 12);
        initEdit(view,R.id.coins_14, 13);

        return view;
    }

    private void initSpinner(View view, int spinReq, int spin_req, int spinner, int coins, final int index) {
        Spinner spin = (Spinner) view.findViewById(spinner);
        String[] sp = getResources().getStringArray(spinReq);
        final int[] intArray = getResources().getIntArray(spin_req);
        final TextView txt = (TextView) view.findViewById(coins);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,sp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt.setText(""+intArray[position]);
                pos=intArray[position];
                ArrayItems[index]=pos;
                Log.d("LoginActivity", "Array:" + ArrayItems[index]);
                apply=0;
                for(i=0;i<ArrayItems.length;i++) {
                    apply=apply+ArrayItems[i];
                }
                btn.setText("Отправить: "+apply);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void initEdit(View view, int coins, final int index) {
        final TextView txt = (TextView) view.findViewById(coins);
        editText.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                String critStr = editText.getText().toString();

                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    if(critStr.equals("")){
                        txt.setText("0");
                    }else {
                        txt.setText(critStr);
                        pos=Integer.parseInt(critStr);
                        ArrayItems[index]=pos;
                        Log.d("LoginActivity", "ss:" + ArrayItems[index]);
                        apply=0;
                        for(i=0;i<ArrayItems.length;i++) {
                            apply = apply + ArrayItems[i];
                        }
                        btn.setText("Отправить: "+apply);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    View.OnClickListener mybtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //progressGenerator.start(btn);
            if(apply>100){
               // btn.setProgress(-1);
                Toast.makeText(getContext(),
                        "Количество баллов привысило максимум!", Toast.LENGTH_LONG).show();
            }else {
                API.Converter.getConvert().getCount(savedKey,(new CountRequest(apply,savedId,id))).enqueue(new Callback<CountMember>() {
                    @Override
                    public void onResponse(Call<CountMember> call, Response<CountMember> response) {
                        Log.d("LoginActivity", "points:" + apply);
                        Log.d("LoginActivity", "user:" + savedId);
                        Log.d("LoginActivity", "student:" + id);
                        //btn.setProgress(1);
                        Toast.makeText(getContext(),
                                "Отправленно!", Toast.LENGTH_LONG).show();
                       // Intent intent = new Intent(getContext(), Group.class);
                       // startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<CountMember> call, Throwable t) {

                    }
                });
            }
        }
    };

    @Override
    public void onComplete() {

    }
}
