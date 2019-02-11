package com.realfarmsystem.rfs.Activity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentAddFur;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentBuilderShelf;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentDashboard;
import com.realfarmsystem.rfs.Activity.DialogActivities.DialolgSorting;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentFurTracking;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentListOfChin;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentListOfShelf;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentProfile;
import com.realfarmsystem.rfs.Activity.Fragments.FragmentSettings;
import com.realfarmsystem.rfs.Entity.FurSkin;
import com.realfarmsystem.rfs.Entity.User;
import com.realfarmsystem.rfs.JsonConvertation.JsonToObj;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;
import com.realfarmsystem.rfs.SystemProperty.SetLanguage;


import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DialolgSorting.FragmentDialogSortingListener{

    private static final String MY_SETTINGS = "mySettings";
    FragmentFurTracking furTracking;
    FragmentAddFur addFur;
    FragmentDashboard dashboard;
    FragmentListOfChin listOfChin;
    FragmentListOfShelf listOfShelf;
    FragmentProfile fragmentProfile;
    FragmentBuilderShelf fragmentBuilderShelf;
    FragmentSettings fragmentSettings;


    FragmentTransaction transaction;
    boolean flagToShowSearchInMenu = false;
    boolean flagToShowFilterInMenu = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetLanguage.loadLocale(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        transaction = getSupportFragmentManager().beginTransaction();
        furTracking = new FragmentFurTracking();
        addFur = new FragmentAddFur();
        dashboard = new FragmentDashboard();
        listOfChin = new FragmentListOfChin();
        listOfShelf = new FragmentListOfShelf();
        fragmentProfile = new FragmentProfile();
        fragmentBuilderShelf = new FragmentBuilderShelf();
        fragmentSettings = new FragmentSettings();
        GetRequest userInfo = new GetRequest( UrlData.getUserInfo(),this, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println(new String(responseBody));
                JsonToObj<User> user = new JsonToObj<>();
                User userInfo = user.convert(responseBody,User.class);
                TextView username = findViewById(R.id.nav_bar_username);
                username.setText(userInfo.getName()+" "+userInfo.getSurname());
                TextView email = findViewById(R.id.nav_bar_email);
                email.setText(userInfo.getEmail());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("error user");
            }
        });

        userInfo.commit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.dashboard);
        transaction.add(R.id.fragment_container,dashboard);
        transaction.commit();
    }



    @Override
    public void onBackPressed() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else
            if (fragment!=null && getSupportFragmentManager().getBackStackEntryCount()>1){
            transaction.remove(fragment);
            transaction.commit();
        }else{
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(flagToShowSearchInMenu);
        menu.findItem(R.id.action_filter).setVisible(flagToShowFilterInMenu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment.getTag().equals("listOfChin"))
                {
                    listOfChin.getArrayAdapter().filter(s);
                }else  if(fragment.getTag().equals("listOfFur")){
                    furTracking.getArrayAdapter().filter(s);
                }else  if(fragment.getTag().equals("listOfShelf")){
                    listOfShelf.getArrayAdapter().filter(s);
                }
                return false;
            }
        });

        MenuItem filterItem = menu.findItem(R.id.action_filter);
        filterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment.getTag().equals("listOfChin"))
                {
//                    listOfChin.getArrayAdapter().filter(s);
                }else  if(fragment.getTag().equals("listOfFur")){
                    DialolgSorting dialolgSorting = new DialolgSorting();
                    dialolgSorting.show(fragmentManager,"sorting dialog");
                }else  if(fragment.getTag().equals("listOfShelf")){

                }
                return false;
            }
        });

        return super.onPrepareOptionsMenu(menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        flagToShowSearchInMenu = false;
        flagToShowFilterInMenu = false;
        // Handle navigation view item clicks here.
        ActionBar actionBar = getSupportActionBar();
        transaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        switch (id){
            case R.id.nav_profile:
                actionBar.setTitle(R.string.myProfile);
                transaction.replace(R.id.fragment_container,fragmentProfile,"myProfile");
                break;
            case R.id.nav_list_of_fur:
                actionBar.setTitle(R.string.listOfFur);
                flagToShowSearchInMenu = true;
                flagToShowFilterInMenu = true;
                transaction.replace(R.id.fragment_container,furTracking,"listOfFur");
                break;
            case R.id.nav_add_fur:
                actionBar.setTitle(R.string.addFurSkin);
                transaction.replace(R.id.fragment_container,addFur);
                break;
            case R.id.nav_dashboard:
                actionBar.setTitle(R.string.dashboard);
                transaction.replace(R.id.fragment_container,dashboard);
                break;
            case R.id.nav_list_of_chin:
                actionBar.setTitle(R.string.listOfChin);
                flagToShowSearchInMenu = true;
                flagToShowFilterInMenu = true;
                transaction.replace(R.id.fragment_container,listOfChin,"listOfChin");
                break;
            case R.id.nav_list_of_shelfs:
                actionBar.setTitle(R.string.listOfShelfs);
                flagToShowSearchInMenu = true;
                transaction.replace(R.id.fragment_container,listOfShelf,"listOfShelf");
                break;
            case R.id.nav_settings:
                transaction.replace(R.id.fragment_container,fragmentSettings,"settings");
                break;
            case R.id.nav_logout:
                SharedPreferences preferences = getSharedPreferences(MY_SETTINGS,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("hash");
                editor.commit();
                Intent intent = new Intent(this,LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        invalidateOptionsMenu();
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void arrayOfItemsSelected(ArrayList<Integer> statuses, ArrayList<Integer> sorting, ArrayList<Boolean> forSale) {
        System.out.println(statuses.toString());
        System.out.println(sorting.toString());

        System.out.println(furTracking.toString());
        ArrayList<FurSkin> selected = new ArrayList<>();
           for(boolean sale:forSale) {
               for (int sortingId : sorting) {
                   for (int statusId : statuses) {
                       for (FurSkin skin : furTracking.getFurSkins()) {
                           if (skin.getSorting() == sortingId && skin.getStatus() == statusId && skin.getIsForTanningAndSale()==sale) {
                               selected.add(skin);
                           }
                       }
                   }
               }
           }
            furTracking.createList(selected);
        }


    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }
}
