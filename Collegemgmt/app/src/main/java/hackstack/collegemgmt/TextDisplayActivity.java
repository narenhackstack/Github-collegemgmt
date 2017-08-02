package hackstack.collegemgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextDisplayActivity extends AppCompatActivity {
TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);
        text=(TextView)findViewById(R.id.textid);
        Intent in=getIntent();
        String result=in.getStringExtra("result");
        text.setText(result);

    }
}
