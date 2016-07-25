package es.alvaroweb.mpvexample;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ChronoView {
    private ColorStateList triggeredBackgroundColor;
    private ColorStateList defaultBackgroundColor;
    private int defaultTextColor;
    @BindView(R.id.chronometer_view)
    TextView chronometerText;
    @BindView(R.id.go_button)
    Button goButton;
    @BindView(R.id.reset_button)
            Button resetButton;

    ChronoPresenter chronoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        triggeredBackgroundColor = ContextCompat.getColorStateList(this, R.color.colorPrimary);
        defaultBackgroundColor = ContextCompat.getColorStateList(this, R.color.colorWhite);
        defaultTextColor = getResources().getColor(R.color.colorPrimary);
        setPresenter();
        setRestartedState();
    }


    @Override
    public void setPresenter() {
        chronoPresenter = new ChronoPresenter(this);
    }

    @OnClick(R.id.go_button)
    @Override
    public void start() {
        chronoPresenter.toggle();

    }

    @OnClick(R.id.reset_button)
    @Override
    public void reset() {
        chronoPresenter.reset();
    }

    @Override
    public void updateTime(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chronometerText.setText(text);
            }
        });
    }

    @Override
    public void setPausedState() {
        chronometerText.setTextColor(Color.RED);
        goButton.setTextColor(Color.WHITE);
        goButton.setBackgroundTintList(triggeredBackgroundColor);
    }

    @Override
    public void setUnpausedState() {
        chronometerText.setTextColor(Color.BLUE);
        goButton.setTextColor(defaultTextColor);
        goButton.setBackgroundTintList(defaultBackgroundColor);
    }

    @Override
    public void setRestartedState() {
        chronometerText.setTextColor(Color.GRAY);
        goButton.setTextColor(defaultTextColor);
        goButton.setBackgroundTintList(defaultBackgroundColor);
        resetButton.setTextColor(defaultTextColor);
        resetButton.setBackgroundTintList(defaultBackgroundColor);
    }


}
