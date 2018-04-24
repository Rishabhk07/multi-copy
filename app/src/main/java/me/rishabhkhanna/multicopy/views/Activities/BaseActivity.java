package me.rishabhkhanna.multicopy.views.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import me.rishabhkhanna.multicopy.R;
import me.rishabhkhanna.multicopy.Utils.Constants;
import me.rishabhkhanna.multicopy.views.Fragments.ClipboardFragment;
import me.rishabhkhanna.multicopy.views.Fragments.NotesFragment;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final String TAG = "BaseActivity";
    NotesFragment notesFragment = new NotesFragment();
    private ViewPager mViewPager;
    public static final boolean RELEASE_LOG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_base);
        Realm.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MultiCopy");


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_intro) {
            Intent i = new Intent(this, WelcomeActivity.class);
            i.putExtra(Constants.BASE_TO_WELCOME, 777);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_base, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return notesFragment;
                case 1:
                    ClipboardFragment clipboardFragment = new ClipboardFragment();
                    return clipboardFragment;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Notes";
                case 1:
                    return "Clipboard";
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RELEASE_LOG) {
            Log.d(TAG, "onActivityResult: ");
        }
        if (requestCode == Constants.NOTES_RESULT) {

            if (data != null) {
                String text = data.getStringExtra(Constants.ACTIVITY_RESULT_TEXT);
                int position = data.getIntExtra(Constants.ACTIVITY_RESULT_POSITION, 0);
                notesFragment.onNotesEdit(text, position);
            }
        }
        if (resultCode == Constants.NEW_NOTE_ACTIVTY_KEY) {
            if (RELEASE_LOG)
                Log.d(TAG, "onActivityResult: without null check");
            if (data != null) {
                String text = data.getStringExtra(Constants.ACTIVITY_NEW_NOTE_TEXT);
                String createdAt = data.getStringExtra(Constants.ACTIVITY_NEW_NOTE_CREATED);
                if (RELEASE_LOG)
                    Log.d(TAG, "onActivityResult: with null check" + text + " " + createdAt);
                notesFragment.onNewNote(text, createdAt);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NotesFragment getRecyclerViewFragment() {
        return notesFragment;
    }

}
