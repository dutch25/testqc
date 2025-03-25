    package com.example.testqc;

    import android.os.Bundle;
    import android.view.MotionEvent;
    import android.widget.ImageView;
    import android.widget.ViewFlipper;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {

        private ViewFlipper viewFlipper;
        private float initialX;
        private ImageView[] dots;
        private int currentBanner = 0;
        private int totalBanners = 3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            viewFlipper = findViewById(R.id.viewFlipper);

            dots = new ImageView[]{
                    findViewById(R.id.dot1),
                    findViewById(R.id.dot2),
                    findViewById(R.id.dot3)
            };

            updateDots(0); // Set first dot as active
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = event.getX();
                    return true;

                case MotionEvent.ACTION_UP:
                    float finalX = event.getX();

                    if (initialX > finalX) {
                        // Swipe Left
                        if (currentBanner < totalBanners - 1) {
                            viewFlipper.showNext();
                            currentBanner++;
                        }
                    } else if (initialX < finalX) {
                        // Swipe Right
                        if (currentBanner > 0) {
                            viewFlipper.showPrevious();
                            currentBanner--;
                        }
                    }

                    updateDots(currentBanner);
                    return true;
            }
            return super.onTouchEvent(event);
        }

        private void updateDots(int position) {
            for (int i = 0; i < dots.length; i++) {
                dots[i].setImageResource(i == position ? R.drawable.dot_active : R.drawable.dot_inactive);
            }
        }
    }