package com.example.mariocalculator

import android.content.Context
import android.hardware.display.DisplayManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly
import com.example.mariocalculator.databinding.ActivityMainBinding
import androidx.window.layout.WindowMetricsCalculator
import androidx.window.layout.WindowMetrics


class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding;
    var border = 10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var SIZE = getScreenWidth()/4;
        var buttonStrings = arrayOf(
            "^", "ln", "(", ")",
            "sin", "cos", "tan", "AC",
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            ".", "0", "=", "/"
        );

        var index = 0;
        for (i in 0..5) {
            for (j in 0..3) {
                var btn = createButton(j*SIZE,i*SIZE,SIZE,SIZE,buttonStrings[index]);
                if (btn.getText() == "^") btn.setBackgroundImage(R.drawable.exponentspritenew)
                else if (btn.getText() == "ln") btn.setBackgroundImage(R.drawable.lnspritenew);
                else if (btn.getText() == "(") btn.setBackgroundImage(R.drawable.openspritenew);
                else if (btn.getText() == ")") btn.setBackgroundImage(R.drawable.closedspritenew)
                else if (btn.getText() == "0") btn.setBackgroundImage(R.drawable.zerospritenew);
                else if (btn.getText() == "1") btn.setBackgroundImage(R.drawable.onespritenew);
                else if (btn.getText() == "2") btn.setBackgroundImage(R.drawable.twospritenew);
                else if (btn.getText() == "3") btn.setBackgroundImage(R.drawable.threespritenew);
                else if (btn.getText() == "4") btn.setBackgroundImage(R.drawable.fourspritenew);
                else if (btn.getText() == "5") btn.setBackgroundImage(R.drawable.fivespritenew);
                else if (btn.getText() == "6") btn.setBackgroundImage(R.drawable.sixspritenew);
                else if (btn.getText() == "7") btn.setBackgroundImage(R.drawable.sevenspritenew);
                else if (btn.getText() == "8") btn.setBackgroundImage(R.drawable.eightspritenew);
                else if (btn.getText() == "9") btn.setBackgroundImage(R.drawable.ninespritenew);
                else if (btn.getText() == "+") btn.setBackgroundImage(R.drawable.plus_sprite);
                else if (btn.getText() == "-") btn.setBackgroundImage(R.drawable.minus_sprite);
                else if (btn.getText() == "*") btn.setBackgroundImage(R.drawable.multiplication_sprite);
                else if (btn.getText() == "/") btn.setBackgroundImage(R.drawable.division_sprite);
                else if (btn.getText() == ".") btn.setBackgroundImage(R.drawable.point_sprite);
                else if (btn.getText() == "=") btn.setBackgroundImage(R.drawable.equals_sprite)
                else if (btn.getText() == "sin") btn.setBackgroundImage(R.drawable.sinspritenew);
                else if (btn.getText() == "cos") btn.setBackgroundImage(R.drawable.cosspritenew);
                else if (btn.getText() == "tan") btn.setBackgroundImage(R.drawable.tanspritenew);
                else if (btn.getText() == "AC") btn.setBackgroundImage(R.drawable.acspritenew);
                index++
            }
        }

        var sdkView = findViewById<TextView>(R.id.sdk);
        sdkView.text = getScreenWidth().toString();

    }

    fun createButton(x: Int, y: Int, width: Int, height: Int, textVal: String): com.example.mariocalculator.Button {
        var btn = Button(this);
        
        btn.addButtonTo(binding.root.findViewById(R.id.buttonPanel));
        btn.setWidth(width-border);
        btn.setHeight(height-border);
        btn.setX(x+border/2);
        btn.setY(y+border/2);
        btn.setText(textVal);
        btn.initDisplay(findViewById(R.id.display))

        return btn;
    }

    private fun getScreenWidth(): Int {
        val sdk = Build.VERSION.SDK_INT;
        val outMetrics = DisplayMetrics();
        var width = 0;

        @RequiresApi(Build.VERSION_CODES.R)
        if (sdk >= 30) {
            val display = this.display;
            display?.getRealMetrics(outMetrics);
        }
        else {
            @Suppress("DEPRECATION")
            val display = windowManager.defaultDisplay;
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics);
        }

        width = outMetrics.widthPixels;
        return width;
    }

    private fun getScreenHeight(): Int {
        val sdk = Build.VERSION.SDK_INT;
        val outMetrics = DisplayMetrics();
        var height = 0;

        @RequiresApi(Build.VERSION_CODES.R)
        if (sdk >= 30) {
            val display = this.display;
            display?.getRealMetrics(outMetrics);
        }
        else {
            @Suppress("DEPRECATION")
            val display = windowManager.defaultDisplay;
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics);
        }

        height = outMetrics.heightPixels;
        return height;
    }
}