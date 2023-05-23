package com.example.mariocalculator

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.mariocalculator.ui.theme.Display

class Button {

    /*** INSTANCE VARIABLES ***/

    //Object variables
    private lateinit var layout: LinearLayout;
    private lateinit var textbox: TextView;
    private lateinit var display: Display;

    //Button aesthetic variables
    private var color = "#aaaaff"
    private var downColor = "#6666ff"
    private var border = 4;

    //Button click variables
    private var btnDown = false;


    /*** INITIALIZATION ***/

    //Constructor, given all properties
    constructor(activity: ComponentActivity, x: Int, y: Int, width: Int, height: Int, text: String) {
        //Set up LinearLayout
        layout = LinearLayout(activity);
        layout.setOnClickListener{buttonClick()};

        //Set up TextView inside LinearLayout
        textbox = TextView(activity);
        textbox.textSize=24F;
        layout.addView(textbox);

        //Set position
        setX(x);
        setY(y);

        //TODO: set size

        //Set text
        setText(text);
    }

    //Constructor, given just activity instance
    constructor(activity: ComponentActivity) {
        //Set up LinearLayout
        layout = LinearLayout(activity);
        layout.setOnClickListener{buttonClick()};

        //Set up TextView inside LinearLayout
        textbox = TextView(activity);
        textbox.textSize=24F;
        layout.addView(textbox);
    }

    public fun initDisplay(newTextView: TextView) {
        display = Display();
        display.init(newTextView);
    }

    public fun addButtonTo(root: RelativeLayout) {
        layout.setBackgroundColor(Color.parseColor(color));
        root.addView(layout);
    }


    /*** SETTERS ***/

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

    public fun setText(value: String) {
        textbox.text = value;
    }


    /*** GETTERS ***/

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
        return textbox.text.toString();
    }


    /*** EVENTS ***/

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
        display.update(textbox.text.toString())
    }

}