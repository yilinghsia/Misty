package com.seaturtle.misty;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.mobilevangelist.glass.helloworld.R;
import com.wikitude.tools.location.LocationService;


/**
 * Main activity
 */
@SuppressWarnings("deprecation")
public class MistyActivity extends Activity {

  private TextView _statusTextView;
  private List<Card> mCards;
  private CardScrollView mCardScrollView;
  MapView mMapView;
  GraphicsLayer mGraphicsLayer;
  LocationService mLocationService;
  LocationManager mLocationManager;
@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_helloworld);
    
    mMapView = (MapView) findViewById(R.id.map);
    //Adding base map
    mMapView.addLayer(new ArcGISTiledMapServiceLayer(this.getResources().getString(R.string.WORLD_TOPO)));
  
    //Add graphics layer
    mGraphicsLayer = new GraphicsLayer();
    mMapView.addLayer(mGraphicsLayer);


    
    // mMapView.setOnStatusChangedListener(new OnStatusChangedListener(){
    	
//        public void onStatusChanged(Object source, STATUS status) {
//        	if(source == mMapView && status == STATUS.INITIALIZED){
//        		//Get current location of the device with the LocationDisplayManager
//        		LocationDisplayManager ldm = mMapView.getLocationDisplayManager();
//        		ldm.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
//        		ldm.start();
//        	}
//        }
//    });

//    createCards();
//
//    mCardScrollView = new CardScrollView(this);
//    ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
//    mCardScrollView.setAdapter(adapter);
//    mCardScrollView.activate();
//    setContentView(mCardScrollView);
  }


private void createCards() {
      mCards = new ArrayList<Card>();

      Card card;

      card = new Card(this);
      card.setImageLayout(Card.ImageLayout.FULL);
      card.addImage(R.drawable.screen1);
      //card.setFootnote("say start");
      mCards.add(card);
      

      card = new Card(this);
      card.setImageLayout(Card.ImageLayout.FULL);
      card.addImage(R.drawable.screen3);
      mCards.add(card);

      card = new Card(this);
      card.setImageLayout(Card.ImageLayout.FULL);
      card.addImage(R.drawable.screen2);
      mCards.add(card);
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

      @SuppressWarnings("deprecation")
	@Override
      public int getViewTypeCount() {
          return CardBuilder.getViewTypeCount();
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


  @Override
  public void onResume() {
    super.onResume();
    mMapView.unpause();
	mGPSLocationManager.start();
  }

  @Override
  public void onPause() {
    super.onPause();
    mMapView.pause();
	mGPSLocationManager.stop();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }
}
