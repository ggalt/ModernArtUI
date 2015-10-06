package george.lab.coursera.modernartui;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int curSeekPos = 0;

    private SeekBar setColor;
    private SeekBar setAlpha;

    private TextView tvL1;
    private TextView tvL2;
    private TextView tvL3;
    private TextView tvL4;
    private TextView tvR1;
    private TextView tvR2;
    private TextView tvR3;

    int L1Color = Color.rgb(213,178,178);
    int L2Color= Color.rgb(135,138,114);
    int L3Color= Color.rgb(247,6,26);
    int L4Color= Color.rgb(58,216,158);
    int R1Color= Color.rgb(251,202,3);
    int R2Color= Color.rgb(225,225,225);
    int R3Color= Color.rgb(112,187,179);

    int L1temp = L1Color;
    int L2temp = L2Color;
    int L3temp = L3Color;
    int L4temp = L4Color;
    int R1temp = R1Color;
    int R2temp = R2Color;
    int R3temp = R3Color;

    private android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvL1 = (TextView)findViewById(R.id.L1);
        tvL2 = (TextView)findViewById(R.id.L2);
        tvL3 = (TextView)findViewById(R.id.L3);
        tvL4 = (TextView)findViewById(R.id.L4);
        tvR1 = (TextView)findViewById(R.id.R1);
        tvR2 = (TextView)findViewById(R.id.R2);
        tvR3 = (TextView)findViewById(R.id.R3);

        setColor = (SeekBar)findViewById(R.id.colorSeekBar);
        setAlpha = (SeekBar)findViewById(R.id.opacitySeekBar);

        setColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateColors(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateAlpha(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MoreInfoOptionMenu:
                // TODO open dialog box
                MoreInfoFragDialog dialog = new MoreInfoFragDialog();
                dialog.show(fragmentManager, "more_info_dialog" );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int colorRotate( int color, int step) {
        // assume we've received a specific color (0-255) and a step
        return (color + step) % 256;
    }

    private int circularShift( int bits, int k ){
        return (bits >>> k) | (bits << (Integer.SIZE - k));
    }

    public void updateColors(int step) {
        //need to preserve temporary color in case we want an alpha shift
        L1temp =circularShift(L1Color, step);
        L2temp =circularShift(L2Color, step);
        L3temp =circularShift(L3Color, step);
        L4temp =circularShift(L4Color, step);
        R1temp =circularShift(R1Color, step);
        R2temp =circularShift(R2Color, step);
        R3temp =circularShift(R3Color, step);

        tvL1.setBackgroundColor(L1temp);
        tvL2.setBackgroundColor(L2temp);
        tvL3.setBackgroundColor(L3temp);
        tvL4.setBackgroundColor(L4temp);
        tvR1.setBackgroundColor(R1temp);
        tvR2.setBackgroundColor(R2temp);
        tvR3.setBackgroundColor(R3temp);
    }

    public void updateAlpha(int step) {
        int newAlpha = 255-step;
        tvL1.setBackgroundColor(setOpacity(L1temp, newAlpha));
        tvL2.setBackgroundColor(setOpacity(L2temp, newAlpha));
        tvL3.setBackgroundColor(setOpacity(L3temp, newAlpha));
        tvL4.setBackgroundColor(setOpacity(L4temp, newAlpha));
        tvR1.setBackgroundColor(setOpacity(R1temp, newAlpha));
        tvR2.setBackgroundColor(setOpacity(R2temp, newAlpha));
        tvR3.setBackgroundColor(setOpacity(R3temp, newAlpha));
    }

    public int setOpacity(int curColor, int alpha) {
        int red = (curColor>>16)&0xFF;
        int green = (curColor>>8)&0xFF;
        int blue = curColor&0xFF;
        return Color.argb(alpha,red, green, blue);
    }
}
