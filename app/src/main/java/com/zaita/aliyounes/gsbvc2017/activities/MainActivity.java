package com.zaita.aliyounes.gsbvc2017.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.fragments.ActivitiesFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.AvailabilityFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.BranchesFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.BrandsFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.ClientsFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.ProductsFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.SuppliersFragment;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_fragment_container,
                        new BranchesFragment(),
                        BranchesFragment.TAG)
                .commit();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , AjouteBranchActivity.class));

            }
        });
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.branches);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        navigationView.setNavigationItemSelectedListener(this);
        ((TextView) headerView.findViewById(R.id.textView_fullName)).setText(PrefUtils.getUsername());
        ((TextView) headerView.findViewById(R.id.textView_emailUsername)).setText(PrefUtils.getUserEmail());
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
        if (id == R.id.action_logout) {
            PrefUtils.clearAll(MainActivity.this);
            Intent intent = new Intent(MainActivity.this , LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_activities : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new ActivitiesFragment(),
                                ActivitiesFragment.TAG)
                        .commit();
                fab.setVisibility(View.GONE);
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.activities);
            }
            break;
            case R.id.nav_availabilities : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new AvailabilityFragment(),
                                AvailabilityFragment.TAG)
                        .commit();

                fab.setVisibility(View.GONE);
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.availability);
            }
            break;
            case R.id.nav_branches : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new BranchesFragment(),
                                BranchesFragment.TAG)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , AjouteBranchActivity.class));
                    }
                });
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.branches);
            }
            break;
            case R.id.nav_brands : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new BrandsFragment(),
                                BrandsFragment.TAG)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , AjouteBbrandActivity.class));
                    }
                });
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.brands);
            }
            break;
            case R.id.nav_client : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new ClientsFragment(),
                                ClientsFragment.TAG)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , AjouteClientActivity.class));
                    }
                });
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.clients);
            }
            break;
            case R.id.nav_products : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new ProductsFragment(),
                                ProductsFragment.TAG)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , AjouteProductActivity.class));
                    }
                });
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.products);
            }
            break;
            case R.id.nav_suppliers : {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout_fragment_container,
                                new SuppliersFragment(),
                                SuppliersFragment.TAG)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this , AjouteSupplierActivity.class));
                    }
                });
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.suppliers);
            }
            break;
            case R.id.nav_about : {
                //TODO implement About Screen
                Toast.makeText(MainActivity.this , "Not Implemented Yet" , Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.nav_contact : {
                //TODO implement Contact Screen
                Toast.makeText(MainActivity.this , "Not Implemented Yet" , Toast.LENGTH_SHORT).show();
            }
            break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
