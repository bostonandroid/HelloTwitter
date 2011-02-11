package com.example.hello;

import android.app.Activity;
import android.os.Bundle;

import android.net.Uri;
import twitter4j.http.RequestToken;
import twitter4j.http.AccessToken;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import android.util.Log;

public class SuccessActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.success);
  }

  @Override
  public void onResume() {
    super.onResume();

    Uri uri = getIntent().getData();
    if (uri != null) {
      AccessToken token = getToAccessToken(uri);
      saveAccessToken(token);
      new TweetTask(token).execute();
    }
  }

  private AccessToken getToAccessToken(Uri uri) {
    try {
      return twitter().getOAuthAccessToken(requestToken(),
          uri.getQueryParameter("oauth_verifier"));
    } catch (TwitterException e) {
      Log.i("SuccessActivity", e.toString());
      return null;
    }
  }

  private void saveAccessToken(AccessToken token) {
    Editor prefEdit = preferences().edit();
    prefEdit.putString("accessToken", token.getToken());
    prefEdit.putString("accessSecret", token.getTokenSecret());
    prefEdit.commit();
  }

  private RequestToken requestToken() {
    return new RequestToken(
        preferences().getString("requestToken", null),
        preferences().getString("requestSecret", null));
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
