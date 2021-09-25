package com.example.myintentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvResult;
    // private int REQUEST_CODE = 100;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == MoveForResultActivity.RESULT_CODE && result.getData() != null) {
                    int selectedValue = result.getData().getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                    tvResult.setText(String.format("Hasil : %s", selectedValue));
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnMoveWithData = findViewById(R.id.btn_move_with_data);
        btnMoveWithData.setOnClickListener(this);

        Button btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_move_activity){
            Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        }
        else if (view.getId() == R.id.btn_move_with_data){
            Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Adi Munawartzy");
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20);
            startActivity(moveWithDataIntent);
        }
        else if (view.getId() == R.id.btn_move_activity_object){
            Person person = new Person();
            person.setName("Munawartzy");
            person.setAge(20);
            person.setEmail("munawar.adi31@gmail.com");
            person.setCity("Kuningan");

            Intent moveDataObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
            moveDataObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
            startActivity(moveDataObjectIntent);
        }
        else if (view.getId() == R.id.btn_dial_number){
            String phoneNumb = "081563754507";
            Intent moveDialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumb));
            startActivity(moveDialPhoneIntent);
        }
        else if (view.getId() == R.id.btn_move_for_result) {
            Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
            resultLauncher.launch(moveForResultIntent);
        }

    }
}
