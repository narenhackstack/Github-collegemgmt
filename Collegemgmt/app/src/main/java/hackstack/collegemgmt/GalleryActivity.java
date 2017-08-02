package hackstack.collegemgmt;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class GalleryActivity extends AppCompatActivity
{
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        viewPager=(ViewPager)findViewById(R.id.viewpagerid);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);


    }
    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run()
        {
            GalleryActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    if(viewPager.getCurrentItem()==0)
                    {
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem()==1)
                    {
                        viewPager.setCurrentItem(2);
                    }
                    else if(viewPager.getCurrentItem()==2)
                    {
                        viewPager.setCurrentItem(3);
                    }
                    else
                    {
                        viewPager.setCurrentItem(0);

                    }}
            });

        }
    }
}
