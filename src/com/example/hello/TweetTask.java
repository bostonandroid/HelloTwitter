package com.example.hello;

import android.os.AsyncTask;
import twitter4j.http.AccessToken;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Twitter;

import android.util.Log;

class TweetTask extends AsyncTask<Void, Void, Integer> {
  private AccessToken token;

  TweetTask(AccessToken a) {
    this.token = a;
  }

  protected Integer doInBackground(Void... ignored) {
    try {
      twitter().updateStatus("Hello, Twitter!");
    } catch (TwitterException e) {
      Log.i("TweetTask", e.toString());
    }

    return 0;
  }

  private Twitter twitter() {
    return new TwitterFactory().getOAuthAuthorizedInstance(
        "Rhq0rKl7sGHSkpAxDGOhSg",
        "DlUPKHFFzX3jFMIqkzm7eYe4GkkLfS716rWCeLj6Uk",
        this.token);
  }
}
