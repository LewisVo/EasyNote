package luongvo.com.madara;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import luongvo.com.madara.fragments.AllNotesFragment;
import luongvo.com.madara.fragments.NotebooksFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NotebooksFragment.OnFragmentInteractionListener,
        AllNotesFragment.OnFragmentInteractionListener{

    private int currentFragmentId;
    private FloatingActionMenu menuMain;
    private FloatingActionButton menuAddTextNote, menuAddAudioNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        initFragment();

        addControls();
        //addEvents();
    }


    private void addControls() {
        menuMain = (FloatingActionMenu) findViewById(R.id.menuMain);
        menuAddTextNote = (FloatingActionButton) findViewById(R.id.menuAddTextNote);
        menuAddAudioNote = (FloatingActionButton) findViewById(R.id.menuAddAudioNote);
    }


    private void addEvents() {
        menuAddTextNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuMain.close(true);
                // TODO: action add new Text Note
                // Do anything after here
            }
        });
        menuAddAudioNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuMain.close(true);
                // TODO: action add new Audio Note
                // Do anything after here
            }
        });
    }


    private void initLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }


    private void initFragment() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_notebooks);

        loadFragmentToMainFrame(NotebooksFragment.newInstance("test_param1", "test_param2"));
    }


    private void loadFragmentToMainFrame(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main_frame, fragment);
            fragmentTransaction.commit();
        }
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

        switch (currentFragmentId) {
            case R.id.nav_allnotes:
                getMenuInflater().inflate(R.menu.main_allnotes,menu);
                break;
            default:
                getMenuInflater().inflate(R.menu.main_notebooks, menu);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // TODO: manage action menu
        switch (id) {
            case R.id.actionNewNotebook:

                break;
            case R.id.actionSearchNotebook:

                break;
            case R.id.actionSearchNote:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        currentFragmentId = id;

        Fragment choseFragment = null;

        switch (id) {
            case R.id.nav_notebooks:
                choseFragment = NotebooksFragment.newInstance("test_param1", "test_param2");
                break;
            case R.id.nav_allnotes:
                choseFragment = AllNotesFragment.newInstance("test_param1", "test_param2");
                break;
            case R.id.nav_tags:
                break;
        }

        MainActivity.this.invalidateOptionsMenu();

        // Make content_main_frame contains choseFragment
        loadFragmentToMainFrame(choseFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
