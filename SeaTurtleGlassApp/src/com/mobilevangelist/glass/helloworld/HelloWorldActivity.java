/*
 * Copyright (C) 2013 Mobilevangelist.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mobilevangelist.glass.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Main activity.
 */
public class HelloWorldActivity extends Activity {

  private TextView _statusTextView;
  private List<Card> mCards;
  private CardScrollView mCardScrollView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    
    
    // Even though the text-to-speech engine is only used in response to a menu action, we
    // initialize it when the application starts so that we avoid delays that could occur
    // if we waited until it was needed to start it up.


    //_card = new Card(_context);
    //_card.setText(R.string.app_name);
    //_cardView = _card.toView();
    //setContentView(_cardView);

    // An alternative way to layout the UX
    //setContentView(R.layout.layout_helloworld);
    //_statusTextView = (TextView)findViewById(R.id.status);
    
    /*Card myCard = new Card(this);
    myCard.setText("Hello, World!"); 
    myCard.setFootnote("First Glassware for Glass");
    myCard.setImageLayout(Card.ImageLayout.FULL);
    myCard.addImage(R.drawable.glass);

    View cardView = myCard.getView();       
    // Display the card we just created
    setContentView(cardView);*/

    createCards();

    mCardScrollView = new CardScrollView(this);
    ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
    mCardScrollView.setAdapter(adapter);
    mCardScrollView.activate();
    setContentView(mCardScrollView);

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


  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }
}
