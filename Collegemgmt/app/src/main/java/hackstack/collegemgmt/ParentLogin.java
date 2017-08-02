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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    EditText email,pwd;
    Button login;
    TextView forgot;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ParentProfile.class));
        }
        user=firebaseAuth.getCurrentUser();

        progressDialog=new ProgressDialog(ParentLogin.this);
        email=(EditText)findViewById(R.id.editText_parent_mail);
        pwd=(EditText)findViewById(R.id.editText_parent_pwd);
        login=(Button)findViewById(R.id.parent_login);

                
      
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v==login)
        {
            String mail=email.getText().toString();
            String password=pwd.getText().toString();

            if(TextUtils.isEmpty(mail))
            {
                Toast.makeText(getApplicationContext(), "Enter Mail ID", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }
            progressDialog.setMessage("Logging in");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(ParentLogin.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        finish();
                        startActivity(new Intent(getApplicationContext(),ParentProfile.class));
                    }
                    else
                    {
                        Toast.makeText(ParentLogin.this, "Check the Mail id and Password", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });  
        }

        


    }
}
