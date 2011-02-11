package com.example.hello;

import android.util.Log;

class TweetTask {
  TweetTask(Object a) {}
  public void execute() {
    Log.i("TweetTask", "Ready to post to Twitter!");
  }
}
