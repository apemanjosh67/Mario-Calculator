package com.example.mariocalculator

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
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


    /*** INITIALIZATION ***/

    //Constructor, given all properties
    constructor(activity: ComponentActivity, x: Int, y: Int, width: Int, height: Int, text: String) {
        initLayout(activity);

        //Set position
        setX(x);
        setY(y);

        //TODO: set size

        //Set text
        setText(text);
    }

    //Constructor, given just activity instance
    constructor(activity: ComponentActivity) {
        initLayout(activity);
    }

    //Initialize layout
    private fun initLayout(activity: ComponentActivity) {
        //Set up LinearLayout
        layout = LinearLayout(activity);
        //layout.setOnClickListener{buttonClick()};
        layout.setOnTouchListener{ v, event ->
            buttonDownColor(event);
            true
        }

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

    //Process a button press
    private fun buttonClick() {
        var item = textbox.text.toString();

        val NUMS = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "(", ")");
        val OPERATIONS = arrayOf("+", "-", "*", "/", "^", "ln", "sin", "cos", "tan");
        val EQUALS = "=";
        val ALL_CLEAR = "AC";

        if (item in NUMS) display.update(item);
        else if (item in OPERATIONS) display.update(item);
        else if (item == ALL_CLEAR) display.clear();
        else if (item == EQUALS) display.set( evaluateEquation( display.pop() ) ); //TODO: compute answer
    }

    //Change button color when down or up
    private fun buttonDownColor(event: MotionEvent) {
        var action = event.action;

        when (action) {

            MotionEvent.ACTION_DOWN -> {
                buttonClick();
                setColorDown();
            }

            MotionEvent.ACTION_UP -> {
                setColorUp();
            }

            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_CANCEL -> {}
            else -> {}

        }
    }


    /*** CALCULATE ***/
    fun evaluateEquation(equationString: String): String {
        //assuming equation is valid

        var equation = "("+equationString+")";
        var openIndex = 0;
        var closedIndex = 0;
        var i = 0;
        while ("(" in equation && ")" in equation) {
            if (equation[i] == '(') openIndex = i;
            else if (equation[i] == ')') {
                closedIndex = i;
                var substring = getSubstring(equation, openIndex, closedIndex);
                var num = evaluateSubstring(substring);
                equation = equation.replace(substring, num);

                i = -1;
                openIndex = 0;
                closedIndex = 0;
            }

            i++;
        }

        return equation;
    }

    //SUBSTRING
    fun getSubstring(str: String, i: Int, j: Int): String {
        if (i < 0 || j < 0 || j < i || i >= str.length || j >= str.length) {
            return "";
        }

        var substring = "";
        for ( k in str.indices) {
            if (k >= i && k <= j) {
                substring += str[k].toString();
            }
        }

        return substring;
    }

    //only supports addition
//TODO: add support for more operations
    fun evaluateSubstring(substring: String): String {
//    var substring = getSubstring(substring, 1, substring.length-2);
//    substring = substring.replace("-", "+-")
//    var nums = substring.split("+")
//
//    var sum = 0;
//    for (num in nums) {
//        sum += num.toInt();
//    }
//
//    return sum.toString();
        var s = getSubstring(substring, 1, substring.length-2);
        s = s.replace("-", "+-");
        if (s[0] == '+') s = "0"+s

        //Get list of operators
        var opString = "";
        for (i in s.indices) {
            //if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/')
            if (s[i] == '+' || s[i] == '*' || s[i] == '/')
                opString += s[i].toString() + " ";
        }
        opString = getSubstring(opString, 0, opString.length-2)
        var opList = opString.split(" ").toMutableList();

        //Get list of numbers
//    var nums = s.split("+", "-", "*", "/").toMutableList();
        var nums = s.split("+", "*", "/").toMutableList();

        //evaluate multiplication or division
        var i = 0;
        while (i < opList.size) {
            if (opList[i] == "*") {
                var factor1 = nums[i].toFloat();
                var factor2 = nums[i+1].toFloat();
                var product = factor1*factor2;

                nums[i] = product.toString();
                nums.removeAt(i+1);
                opList.removeAt(i);
                i--;

            }
            else if (opList[i] == "/") {
                var dividend = nums[i].toFloat();
                var divisor = nums[i+1].toFloat();
                var quotient = dividend/divisor;

                nums[i] = quotient.toString();
                nums.removeAt(i+1);
                opList.removeAt(i)
                i--;

            }
            i++;
        }

        //evaluate addition or subtraction
        i = 0;
        while (i < opList.size) {
            if (opList[i] == "+") {
                var addend1 = nums[i].toFloat();
                var addend2 = nums[i+1].toFloat();
                var sum = addend1 + addend2;

                nums[i] = sum.toString();
                nums.removeAt(i+1);
                opList.removeAt(i);
                i--;

            }

            i++;
        }

        var answer = nums[0];

        //round if able
        if (answer[answer.length-2] == '.' && answer[answer.length-1] == '0')
            return answer.toFloat().toInt().toString();
        return nums[0];
    }


    /*** CHANGE COLORS ***/

    private fun setColorUp() {
        layout.setBackgroundColor(Color.parseColor(color));
    }

    private fun setColorDown() {
        layout.setBackgroundColor(Color.parseColor(downColor));
    }
}

