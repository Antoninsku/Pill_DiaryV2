package fi.antonina.pilldiary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {
    //initialize variable
    ImageView avatar;
    TextView title, userName, ages, male;
    ImageButton logOut;
    AlertDialog dialog;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

        //Intitialize and Assign Variable
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        title = findViewById(R.id.title);
        avatar = findViewById(R.id.avatar);
        userName = findViewById(R.id.userName);
        ages = findViewById(R.id.ages);
        male = findViewById(R.id.male);

        logOut = findViewById(R.id.logOut);
//        Base_Theme_AppCompat_Dialog_MinWidth
        dialog = new AlertDialog.Builder(this, com.google.android.material.R.style.Base_V21_Theme_AppCompat_Dialog).create();
        editText = new EditText(this);

        dialog.setView(editText);
        //edit user name
        userName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.setTitle("Edit text:");
                editText.setText(userName.getText());
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userName.setText(String.valueOf(editText.getText()));
                        Toast.makeText(PersonActivity.this, "changes saved successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setTitle("Edit the text:");
                dialog.show();
                userName.setText(editText.getText().toString());
            }
        });
        //edit user age
        ages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editText.setText(ages.getText());
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ages.setText(String.valueOf(editText.getText()));
                        Toast.makeText(PersonActivity.this, "changes saved", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setTitle("Edit the text:");
                dialog.show();
                ages.setText(editText.getText().toString());
            }
        });
        Switch sw = (Switch) findViewById(R.id.switch1);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sw.setText("Male");
                } else {
                    sw.setText("Female");
                }
                Toast.makeText(PersonActivity.this, "changes saved", Toast.LENGTH_SHORT).show();
            }
        });
        //log out click
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
                builder.setMessage("do you want to log out ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                onDestroy();
                                Intent intent = new Intent(PersonActivity.this, MainActivity.class);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Please Confirm");
                alert.show();
            }
        });

        //Set icon selected
        navigationView.setSelectedItemId(R.id.person);

        //Perform ItemSelectedListener
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.my_Medicine) {
                    startActivity(new Intent(getApplicationContext()
                            , MedicineActivity.class));
                    PersonActivity.this.finish();
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.person) {
                    return true;
                } else if (itemId == R.id.calendar) {
                    startActivity(new Intent(getApplicationContext()
                            , CalendarActivity.class));
                    PersonActivity.this.finish();
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

}
