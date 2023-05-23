package com.example.mariocalculator

import android.graphics.Color
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.mariocalculator.ui.theme.CalcDisplay

class CalcBtn {

    //instance variables
    lateinit var layout: LinearLayout;
    var color = "#aaaaff"
    var downColor = "#6666ff"
    var btnDown = false;
    var textVal = "";
    lateinit var textbox: TextView;
    lateinit var display: CalcDisplay;

    public fun initLayout(activity: ComponentActivity) {
        layout = LinearLayout(activity)
        layout.setOnClickListener{buttonClick()};

        textbox = TextView(activity);
        //textbox.setBackgroundColor(Color.parseColor("#ffbbbb"));
        textbox.textSize=24F;
        layout.addView(textbox);
    }

    public fun setColorTo(color:String) {
        this.color = color;
        layout.setBackgroundColor(Color.parseColor(color));
    }

    public fun addButtonTo(root: RelativeLayout) {
        layout.setBackgroundColor(Color.parseColor(color));
        root.addView(layout);
    }

    public fun setX(x: Int) {
        layout.x = x.toFloat();
    }

    public fun setY(y: Int) {
        layout.y = y.toFloat();
    }

    public fun setWidth(width: Int) {
        var params = layout.getLayoutParams();
        params.width = width;
        layout.setLayoutParams(params);

        textbox.x = width/2F-15;
    }

    public fun setHeight(height: Int) {
        var params = layout.getLayoutParams();
        params.height = height;
        layout.setLayoutParams(params);

        textbox.y = height/2F-35;
    }

    public fun getX(): Int {
        return layout.x.toInt();
    }

    public fun getY(): Int {
        return layout.y.toInt();
    }

    public fun getWidth(): Int {
        return layout.width;
    }

    public fun getHeight(): Int {
        return layout.height;
    }

    public fun getText(): String {
        return textVal;
    }

    public fun setText(value: String) {
        this.textVal = value;
        textbox.text = textVal;
    }

    private fun buttonDownColor() {
        if (btnDown) {
            layout.setBackgroundColor(Color.parseColor(color));
            btnDown = false;
        }
        else {
            layout.setBackgroundColor(Color.parseColor(downColor));
            btnDown = true;
        }

    }

    private fun buttonClick() {
        display.update(textVal)
    }

    private fun updateTextPos() {
        textbox.x = getWidth()/2F;
        textbox.y = getHeight()/2F;
    }

    public fun initDisplay(newTextView: TextView) {
        display = CalcDisplay();
        display.init(newTextView);
    }

}