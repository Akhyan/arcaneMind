package navigateMenu;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.akhyanvaidya.secusafe.R;

import static controller.SessionControl.HeaderUsername;
import static controller.SessionControl.SessionEnd;

public class LoginHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView inUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View navHeader=navigationView.getHeaderView(0);
        inUser=(TextView) navHeader.findViewById(R.id.head_user);

        //get Username and setText to header
        HeaderUsername(inUser, getApplicationContext());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();//change this
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SessionEnd(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    //check what this method does
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getFragmentManager();
        if (id == R.id.nav_home){///may not be needed


        } else if (id == R.id.nav_recorder) {


            fragmentManager.beginTransaction().replace(R.id.content_frame, new RecorderFragment()).commit();

        }else if (id == R.id.nav_cctv) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new CctvFragment()).commit();

        }else if (id == R.id.nav_intercom) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new IntercomFragment()).commit();


        }else if (id == R.id.nav_logout) {



        }else if (id == R.id.nav_account) {

        }else if (id == R.id.nav_ecart) {

        }else if (id == R.id.nav_contactus) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new ContactUsFragment()).commit();

        } else if (id == R.id.nav_aboutus) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
