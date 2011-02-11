package com.example.hello;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

import twitter4j.http.RequestToken;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.Uri;

import android.util.Log;

public class TwitterActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Button next = (Button)findViewById(R.id.next);
    next.setOnClickListener(new NextListener());
  }

  class NextListener implements OnClickListener {
    public void onClick(View v) {
      RequestToken token;
      try {
        token = twitter().getOAuthRequestToken("hello-twitter:///");
      } catch (TwitterException e) {
        Log.i("TwitterActivity", e.toString());
        return;
      }
      saveRequestToken(token);
      Intent i = new Intent(Intent.ACTION_VIEW,
          Uri.parse(token.getAuthenticationURL()));
      startActivity(i);
    }
  }

  private void saveRequestToken(RequestToken token) {
    Editor prefEdit = preferences().edit();
    prefEdit.putString("requestToken", token.getToken());
    prefEdit.putString("requestSecret", token.getTokenSecret());
    prefEdit.commit();
  }

  private Twitter twitter() {
    Twitter t = new TwitterFactory().getInstance();
    t.setOAuthConsumer("Rhq0rKl7sGHSkpAxDGOhSg",
        "DlUPKHFFzX3jFMIqkzm7eYe4GkkLfS716rWCeLj6Uk");
    return t;
  }

  private SharedPreferences preferences() {
    return PreferenceManager.getDefaultSharedPreferences(this);
  }
}
