package com.wikitude.samples;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.wikitude.sdksamples.R;

public class MainSamplesListActivityGoogleGlass extends Activity{
	public static final String EXTRAS_KEY_ACTIVITY_TITLE_STRING = "activityTitle";
	public static final String EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL = "activityArchitectWorldUrl";

	public static final String EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY = "activitiesArchitectWorldUrls";
	public static final String EXTRAS_KEY_ACTIVITIES_TILES_ARRAY = "activitiesTitles";
	public static final String EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY = "activitiesClassnames";
	
	

    private List<Card> mCards;
    private CardScrollView mCardScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        createCards(this.getListLabels());

        mCardScrollView = new CardScrollView(this);
        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.activate();
        mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				/* get className of activity to call when clicking item at position x */
				final String className = getListActivities()[position];

				try {

					final Intent intent = new Intent(MainSamplesListActivityGoogleGlass.this, Class.forName(className));
					intent.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING,
							MainSamplesListActivityGoogleGlass.this.getListLabels()[position]);
					intent.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples"
							+ File.separator + MainSamplesListActivityGoogleGlass.this.getArchitectWorldUrls()[position]
							+ File.separator + "index.html");

					/* launch activity */
					MainSamplesListActivityGoogleGlass.this.startActivity(intent);

				} catch (Exception e) {
					/*
					 * may never occur, as long as all SampleActivities exist and are
					 * listed in manifest
					 */
					Toast.makeText(MainSamplesListActivityGoogleGlass.this, className + "\nnot defined/accessible",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
        setContentView(mCardScrollView);
    }
    
    protected final String[] getListLabels() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_TILES_ARRAY);
	}

	protected String getActivityTitle() {
		return getIntent().getExtras().getString(
				EXTRAS_KEY_ACTIVITY_TITLE_STRING);
	}

	protected String[] getListActivities() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY);
	}

	protected String[] getArchitectWorldUrls() {
		return getIntent().getExtras().getStringArray(
				EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY);
	}

	protected int getContentViewId() {
		return R.layout.list_sample;
	}

    private void createCards(String[] values) {
        mCards = new ArrayList<Card>();

        Card card;
        
        for (final String text: values) {
        	card = new Card(this);
            card.setText(text);
            mCards.add(card);
        }
    }

    private class ExampleCardScrollAdapter extends CardScrollAdapter {

        @Override
        public int getPosition(Object item) {
            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {
            return mCards.size();
        }

        @Override
        public Object getItem(int position) {
            return mCards.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return Card.getViewTypeCount();
        }

        @Override
        public int getItemViewType(int position){
            return mCards.get(position).getItemViewType();
        }

        @Override
        public View getView(int position, View convertView,
                ViewGroup parent) {
            return  mCards.get(position).getView(convertView, parent);
        }
    }
}
