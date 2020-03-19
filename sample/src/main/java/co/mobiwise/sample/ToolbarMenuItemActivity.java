package co.mobiwise.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.fragment.FocusFragment;
import co.mobiwise.sample.fragment.GravityFragment;
import co.mobiwise.sample.fragment.MainFragment;
import co.mobiwise.sample.fragment.RecyclerviewFragment;

/**
 * This activity demonstrates how to implement Material introView on ToolBar MenuItems
 *
 * @author Thomas Kioko
 */
public class ToolbarMenuItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MaterialIntroListener {

    private static final String MENU_SHARED_ID_TAG = "menuSharedIdTag";
    private static final String MENU_ABOUT_ID_TAG = "menuAboutIdTag";
    private static final String MENU_SEARCH_ID_TAG = "menuSearchIdTag";
    private ImageView mIvShare, mIvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //User toolbar to access the views
        ImageView ivSearch = (ImageView) toolbar.findViewById(R.id.ivToolbarSearch);
        mIvShare = (ImageView) toolbar.findViewById(R.id.ivToolbarShare);
        mIvAbout = (ImageView) toolbar.findViewById(R.id.ivToolbarAbout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //show the intro view
        showIntro(ivSearch, MENU_SEARCH_ID_TAG, getString(R.string.guide_setup_profile), FocusGravity.CENTER);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_demo:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                break;
            case R.id.nav_gravity:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new GravityFragment()).commit();
                break;
            case R.id.nav_focus:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FocusFragment()).commit();
                break;
            case R.id.nav_recyclerview:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new RecyclerviewFragment()).commit();
                break;
            case R.id.nav_toolbar:
                startActivity(new Intent(getApplicationContext(), ToolbarMenuItemActivity.class));
                break;
            case R.id.nav_tab:
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Method that handles display of intro screens
     *
     * @param view         View to show guide
     * @param id           Unique ID
     * @param text         Display message
     * @param focusGravity Focus Gravity of the display
     */
    public void showIntro(View view, String id, String text, FocusGravity focusGravity) {
        new MaterialIntroView.Builder(ToolbarMenuItemActivity.this)
                .enableDotAnimation(true)
                .setFocusGravity(focusGravity)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(100)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setListener(this)
                .setUsageId(id)
                .show();
    }

    @Override
    public void onUserClicked(String materialIntroViewId) {
        switch (materialIntroViewId) {
            case MENU_SEARCH_ID_TAG:
                showIntro(mIvAbout, MENU_ABOUT_ID_TAG, getString(R.string.guide_setup_profile), FocusGravity.LEFT);
                break;
            case MENU_ABOUT_ID_TAG:
                showIntro(mIvShare, MENU_SHARED_ID_TAG, getString(R.string.guide_setup_profile), FocusGravity.LEFT);
                break;
            case MENU_SHARED_ID_TAG:
                Toast.makeText(ToolbarMenuItemActivity.this, "Complete!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSkipClicked() {

    }
}
