package hackstack.collegemgmt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, LocationListener {
    Button b;
    ImageButton bt1, bt2, bt3, bt4, bt5, bt6;
    Double latti, longi;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ui elements registration
        bt1 = (ImageButton) findViewById(R.id.imageButton);
        bt2 = (ImageButton) findViewById(R.id.imageButton1);
        bt3 = (ImageButton) findViewById(R.id.imageButton2);
        bt4 = (ImageButton) findViewById(R.id.imageButton3);
        bt5 = (ImageButton) findViewById(R.id.imageButton4);
        bt6 = (ImageButton) findViewById(R.id.imageButton5);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1,this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7867983739"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            startActivity(new Intent(getApplicationContext(),AdminLogin.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_college)
        {
            //String file="aboutcollege.txt";
            displaytext("aboutcollege.txt");

            //startActivity(new Intent(getApplicationContext(),TextDisplayActivity.class));
        } else if (id == R.id.nav_profile)
        {
            displaytext("profile.txt");

        } else if (id == R.id.nav_gallery)
        {
           startActivity(new Intent(getApplicationContext(),GalleryActivity.class));
        } else if (id == R.id.nav_departments)
        {
            displaytext("departments.txt");

        } else if (id == R.id.nav_academics)
        {
            displaytext("academics.txt");

        } else if (id == R.id.nav_placementdetails)
        {
            displaytext("placementdetails.txt");
        }
        else if (id == R.id.nav_facility)
        {
            displaytext("facilities.txt");
        }

        else if (id == R.id.nav_contact)
        {
            startActivity(new Intent(getApplicationContext(),ContactActivity.class));

        }
        else if (id == R.id.nav_website)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.hackstacktech.com"));
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void displaytext(String filename)
    {
        String text="";
        try {
            InputStream is=getAssets().open(filename);
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            text =new String(buffer);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Intent intent=new Intent(getApplicationContext(),TextDisplayActivity.class);
        intent.putExtra("result",text);
        startActivity(intent);

    }

    @Override
    public void onClick(View v)
    {
        if(v==bt3)
        {
            startActivity(new Intent(getApplicationContext(),ParentLogin.class));
        }
        if(v==bt4)
        {
            startActivity(new Intent(getApplicationContext(),GalleryActivity.class));
        }
        if(v==bt5)
        {
            Toast.makeText(getApplicationContext(), "Kindly Turn on the Location and Data Connection", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr="+latti+","+longi+"&daddr=11.0236,76.9426"));
            startActivity(intent);

        }
        if(v==bt6)
        {
            Toast.makeText(getApplicationContext(), "Kindly Turn on the Location and Data Connection", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/d/viewer?mid=1Tdib5Z4I9Sshw_ePdYxsViZpna4&ll=10.488491001784567%2C77.45395930000007&z=18"));
            startActivity(intent);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latti=location.getLatitude();
        longi=location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
