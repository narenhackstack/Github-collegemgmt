package hackstack.collegemgmt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    EditText get_admin_code,get_admin_mail,get_admin_pwd;
    Button verify,admin_login;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(AdminLogin.this);
        get_admin_code=(EditText)findViewById(R.id.edit_admin_code);
        get_admin_mail=(EditText)findViewById(R.id.edit_admin_mail);
        get_admin_pwd=(EditText)findViewById(R.id.edit_admin_pwd);
        verify=(Button)findViewById(R.id.verify_admin);
        admin_login=(Button)findViewById(R.id.admin_login);
        verify.setOnClickListener(this);
        admin_login.setOnClickListener(this);
        linearLayout=(LinearLayout)findViewById(R.id.lin1);
    }

    @Override
    public void onClick(View v)
    {
        String admin_auth_code="123456";

        if(v==verify)
        {
            if(get_admin_code.getText().toString().equals(admin_auth_code))
            {
                linearLayout.setVisibility(View.VISIBLE);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Enter Admin Code correctly", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==admin_login)
        {
            String admin_mail = "narain2829@gmail.com";
            String Email = get_admin_mail.getText().toString();
            String password = get_admin_pwd.getText().toString();
            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                return;

            }

            progressDialog.setMessage("Logging Admin");
            progressDialog.show();
            if (Email.equals(admin_mail)) {


                firebaseAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Admin_profile.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Kindly the Check the mail and pwd", Toast.LENGTH_LONG).show();

                        }



                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "the entered Email is not an Admin Email", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        }

        progressDialog.dismiss();

    }
    }

