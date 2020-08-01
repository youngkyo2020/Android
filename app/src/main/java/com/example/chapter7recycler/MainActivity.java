package com.example.chapter7recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateRight;
    Animation translateLeft;
    Button button,scaleButton;
    LinearLayout page;
    private boolean isPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        scaleButton = findViewById(R.id.scaleButton);
        page = findViewById(R.id.page);
        translateLeft = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        SlidingAnimationListener animListener = new SlidingAnimationListener();
        translateRight.setAnimationListener(animListener);
        translateLeft.setAnimationListener(animListener);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isPage){
                    page.startAnimation(translateRight);
                }else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeft);
                }
            }
        });
        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
                view.startAnimation(anim);
            }
        });
    }
    class SlidingAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPage){
                page.setVisibility(View.INVISIBLE);
                button.setText("열기");
                isPage = false;
            }else{
                button.setText("닫기");
                isPage = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}