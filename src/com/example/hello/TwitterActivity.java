package com.example.hello;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

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
      Intent i = new Intent(TwitterActivity.this, SuccessActivity.class);
      startActivity(i);
    }
  }
}
