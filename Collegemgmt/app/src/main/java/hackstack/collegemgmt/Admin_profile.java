package hackstack.collegemgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_profile extends AppCompatActivity implements View.OnClickListener {
    Button logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        logout=(Button)findViewById(R.id.button2);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),AdminLogin.class));

    }
}
