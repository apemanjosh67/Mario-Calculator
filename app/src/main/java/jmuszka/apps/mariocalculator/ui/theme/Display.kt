package com.example.mariocalculator.ui.theme

import android.graphics.Color
import android.widget.TextView

class Display {

    //
    lateinit var textView: TextView;

    public fun init(newTextView: TextView) {
        textView = newTextView;
        textView.textSize = 42F;
        textView.setTextColor(Color.parseColor("#ffffff"));
    }

    public fun update(newText: String) {
        var currText = textView.text.toString();
        textView.text = currText + newText;
    }

    public fun clear() {
        textView.text = "";
    }

    public fun pop(): String {
        var expression = textView.text.toString();
        clear();
        return expression;
    }

    public fun set(newText: String) {
        textView.text = newText;
    }
}