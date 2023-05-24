package com.example.mariocalculator.ui.theme

import android.widget.TextView

class Display {

    //
    lateinit var textView: TextView;

    public fun init(newTextView: TextView) {
        textView = newTextView;
        textView.textSize = 56F;
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