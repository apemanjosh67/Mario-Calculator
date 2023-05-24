package com.example.mariocalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.mariocalculator.databinding.ActivityMainBinding
import android.widget.TextView

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
        return 0;
    }

    private fun getScreenHeight(): Int {
        return 0;
    }


}