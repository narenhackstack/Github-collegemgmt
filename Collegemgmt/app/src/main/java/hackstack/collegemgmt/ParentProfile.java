package hackstack.collegemgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ParentProfile extends AppCompatActivity implements View.OnClickListener {
Button logout,reset;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        logout=(Button)findViewById(R.id.button_logout);
        reset=(Button)findViewById(R.id.button3);
        reset.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) 
    {
        if(v==logout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),ParentLogin.class));
            
        }
        if(v==reset)
        {
            firebaseAuth.sendPasswordResetEmail(user.getEmail());
            Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
        }
       

    }
}
