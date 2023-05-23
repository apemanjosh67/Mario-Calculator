package com.example.mariocalculator.ui.theme

import android.widget.TextView

class CalcDisplay {
    lateinit var textView: TextView;

    public fun init(newTextView: TextView) {
        textView = newTextView;
        textView.textSize = 56F;
    }

    public fun update(newText: String) {
        var currText = textView.text.toString();
        textView.text = currText + newText;
    }
}