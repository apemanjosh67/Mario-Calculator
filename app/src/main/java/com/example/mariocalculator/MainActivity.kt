package com.example.mariocalculator

import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.text.isDigitsOnly
import com.example.mariocalculator.databinding.ActivityMainBinding
import androidx.window.layout.WindowMetricsCalculator
import androidx.window.layout.WindowMetrics


class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding;
    var screenW = getScreenWidth();
    //var screenH = getScreenHeight();
    var border = 10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        var SIZE = 180;
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
                if (btn.getText() == "^") btn.setBackgroundImage(R.drawable.exponent_sprite)
                else if (btn.getText() == "ln") btn.setBackgroundImage(R.drawable.ln_sprite);
                else if (btn.getText() == "(") btn.setBackgroundImage(R.drawable.open_sprite);
                else if (btn.getText() == ")") btn.setBackgroundImage(R.drawable.closed_sprite)
                else if (btn.getText() == "0") btn.setBackgroundImage(R.drawable.zero_sprite);
                else if (btn.getText() == "1") btn.setBackgroundImage(R.drawable.one_sprite);
                else if (btn.getText() == "2") btn.setBackgroundImage(R.drawable.two_sprite);
                else if (btn.getText() == "3") btn.setBackgroundImage(R.drawable.three_sprite);
                else if (btn.getText() == "4") btn.setBackgroundImage(R.drawable.four_sprite);
                else if (btn.getText() == "5") btn.setBackgroundImage(R.drawable.five_sprite);
                else if (btn.getText() == "6") btn.setBackgroundImage(R.drawable.six_sprite);
                else if (btn.getText() == "7") btn.setBackgroundImage(R.drawable.seven_sprite);
                else if (btn.getText() == "8") btn.setBackgroundImage(R.drawable.eight_sprite);
                else if (btn.getText() == "9") btn.setBackgroundImage(R.drawable.nine_sprite);
                else if (btn.getText() == "+") btn.setBackgroundImage(R.drawable.plus_sprite);
                else if (btn.getText() == "-") btn.setBackgroundImage(R.drawable.minus_sprite);
                else if (btn.getText() == "*") btn.setBackgroundImage(R.drawable.multiplication_sprite);
                else if (btn.getText() == "/") btn.setBackgroundImage(R.drawable.division_sprite);
                else if (btn.getText() == ".") btn.setBackgroundImage(R.drawable.point_sprite);
                else if (btn.getText() == "=") btn.setBackgroundImage(R.drawable.equals_sprite)
                else if (btn.getText() == "sin") btn.setBackgroundImage(R.drawable.sin_sprite);
                else if (btn.getText() == "cos") btn.setBackgroundImage(R.drawable.cos_sprite);
                else if (btn.getText() == "tan") btn.setBackgroundImage(R.drawable.tan_sprite);
                else if (btn.getText() == "AC") btn.setBackgroundImage(R.drawable.ac_sprite);
                index++
            }
        }
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

    private fun print(message: String) {
        var textView = TextView(this);
        textView.text=message;
        binding.root.addView(textView);
    }

    private fun getScreenWidth(): Int {
        //val display = Context.
        return 0;
    }

    private fun getScreenHeight(): Int {
        return 0;
    }


}