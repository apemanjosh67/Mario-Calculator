package com.example.mariocalculator

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.res.ResourcesCompat
import com.example.mariocalculator.ui.theme.Display
import java.lang.Math.abs
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.tan
import kotlin.math.ln
import kotlin.math.round;

class Button {

    /*** INSTANCE VARIABLES ***/

    //Object variables
    private lateinit var layout: LinearLayout;
    private lateinit var textbox: TextView;
    private lateinit var display: Display;
    private lateinit var activity: Activity;

    //Button aesthetic variables
    private var color = "#eeeeff"
    private var downColor = "#00aaaaff"
    private var border = 4;
    private var imageId = 0;


    /*** INITIALIZATION ***/

    //Constructor, given all properties
    constructor(activity: ComponentActivity, x: Int, y: Int, width: Int, height: Int, text: String) {
        this.activity = activity;
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
        textbox.textSize=32F;
        textbox.setTextColor(Color.parseColor("#ffffff"))
        var typeface = ResourcesCompat.getFont(activity.applicationContext, R.font.mario_font);
        textbox.setTypeface(typeface);
        layout.addView(textbox);
    }

    public fun initDisplay(newTextView: TextView) {
        display = Display();
        display.init(newTextView);
    }

    public fun addButtonTo(root: RelativeLayout) {
        //layout.setBackgroundColor(Color.parseColor(color));
        //layout.setBackgroundResource(R.drawable.qblocksprite);
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

        //textbox.x = width/2F-15;
        textbox.x = -200F;
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
        val OPERATIONS = arrayOf("+", "-", "*", "/", "^");
        val FUNCTIONS = arrayOf("ln", "sin", "cos", "tan");
        val EQUALS = "=";
        val ALL_CLEAR = "AC";

        if (item in NUMS) display.update(item);
        else if (item in OPERATIONS) display.update(item);
        else if (item in FUNCTIONS) display.update(item+"(")
        else if (item == ALL_CLEAR) display.clear();
        else if (item == EQUALS) {
            display.set( evaluateEquation( display.pop() ) )
        }; //TODO: compute answer
    }

    //Change button color when down or up
    private fun buttonDownColor(event: MotionEvent) {
        var action = event.action;

        when (action) {

            MotionEvent.ACTION_DOWN -> {
                buttonClick();
                setColorDown();
                //textbox.setTextColor(Color.parseColor("#dddddd"))
            }

            MotionEvent.ACTION_UP -> {
                setColorUp();
                //layout.setBackgroundResource(R.drawable.qblocksprite);
                setBackgroundImage(imageId);
                //textbox.setTextColor(Color.parseColor("#ffffff"))
            }

            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_CANCEL -> {}
            else -> {}

        }
    }


    /*** CALCULATE ***/
    fun evaluateEquation(equationString: String): String {
        //assuming equation is valid
        if (equationString == null) return "";

        var equation = "($equationString)";
        var openIndex = 0;
        var closedIndex = 0;
        var i = 0;
        while ("(" in equation && ")" in equation) {

            if (equation[i] == '(') openIndex = i;
            else if (equation[i] == ')') {
                closedIndex = i;
                var substring = getSubstring(equation, openIndex, closedIndex);
                var num = evaluateSubstring(substring);
                equation = equation.replace(substring, num); //"(3*-1)"
                equation = checkFunctions(equation,num,openIndex);

                i = 0;
                openIndex = 0;
                closedIndex = 0;
            }

            i++;
        }

        return equation;
    }

    fun checkFunctions(eqn:String, num:String, i: Int): String {

        if (i < 3) return eqn;


        var equation = eqn;
        var function = equation[i-3].toString() + equation[i-2].toString() + equation[i-1].toString();

        var computedValue = ""

        if (function == "sin") {
            computedValue = sin(num.toDouble()).toString();
            if (abs(0-computedValue.toDouble()) < 0.001) computedValue =  "0";
            else if (1-computedValue.toDouble() < 0.001 && 1-computedValue.toDouble() > -0.001) computedValue =  "1";
            else if (-1-computedValue.toDouble() < 0.001 && -1-computedValue.toDouble() > -0.001) computedValue =  "-1";

            equation = equation.replace(function + num, computedValue);
        }
        else if (function == "cos") {
            computedValue = cos(num.toDouble()).toString();
            if (abs(0-computedValue.toDouble()) < 0.001) computedValue = "0";
            else if (1-computedValue.toDouble() < 0.001 && 1-computedValue.toDouble() > -0.001) computedValue =  "1";
            else if (-1-computedValue.toDouble() < 0.001 && -1-computedValue.toDouble() > -0.001) computedValue =  "-1";

            equation = equation.replace(function + num, computedValue);
        }
        else if (function == "tan") {
            computedValue = tan(num.toDouble()).toString();
            if (abs(0-computedValue.toDouble()) < 0.001) computedValue =  "0";
            else if (1-computedValue.toDouble() < 0.001 && 1-computedValue.toDouble() > -0.001) computedValue =  "1";
            else if (-1-computedValue.toDouble() < 0.001 && -1-computedValue.toDouble() > -0.001) computedValue =  "-1";

            equation = equation.replace(function + num, computedValue);
        }
        else if (function[1].toString()+function[2].toString() == "ln") {
            computedValue = ln(num.toDouble()).toString();
            if (abs(0-computedValue.toDouble()) < 0.001) computedValue =  "0";
            else if (1-computedValue.toDouble() < 0.001 && 1-computedValue.toDouble() > -0.001) computedValue =  "1";
            else if (-1-computedValue.toDouble() < 0.001 && -1-computedValue.toDouble() > -0.001) computedValue =  "-1";

            equation = equation.replace(function[1].toString()+function[2].toString()+num, computedValue);
        }


        return equation;
    }

    //SUBSTRING
    fun getSubstring(str: String, i: Int, j: Int): String {
        if (i < 0 || j < 0 || j < i || i >= str.length || j >= str.length) {
            return str;
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

        var s = getSubstring(substring, 1, substring.length-2);
        //s = s.replace("-", "+-");
        s = calcReplace(s);
        if (s[0] == '+' || s[0] == '-') s = "0"+s

        //Get list of operators
        var opString = "";
        for (i in s.indices) {
            if (s[i] == '+' || s[i] == '*' || s[i] == '/' || s[i] == '^')
                opString += s[i].toString() + " ";
        }
        opString = getSubstring(opString, 0, opString.length-2)
        var opList = opString.split(" ").toMutableList();

        if (opString == "") return s;

        //Get list of numbers
        var nums = s.split("+", "*", "/", "^").toMutableList();

        //evaluate exponents
        var i = 0;
        while (i < opList.size) {
            if (opList[i] == "^") {
                var base = nums[i].toDouble();
                var exponent = nums[i+1].toDouble();
                var power = Math.pow(base, exponent);

                nums[i] = power.toString();
                nums.removeAt(i+1);
                opList.removeAt(i);
                i--;

            }
            i++;
        }

        //evaluate multiplication or division
        i = 0;
        while (i < opList.size) {
            if (opList[i] == "*") {

                var factor1 = nums[i].toDouble();
                var factor2 = nums[i+1].toDouble();
                var product = factor1*factor2;

                nums[i] = product.toString();
                nums.removeAt(i+1);
                opList.removeAt(i);
                i--;

            }
            else if (opList[i] == "/") {
                var dividend = nums[i].toDouble();
                var divisor = nums[i+1].toDouble();
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

                var addend1 = nums[i].toDouble();
                var addend2 = nums[i+1].toDouble();
                var sum = addend1 + addend2;

                nums[i] = sum.toString();
                nums.removeAt(i+1);
                opList.removeAt(i);
                i--;

            }
//        else if (opList[i] == "-") {
//
//            var minuend = nums[i].toDouble();
//            var subtrahend = nums[i+1].toDouble();
//            var difference = minuend - subtrahend;
//
//            nums[i] = difference.toString();
//            nums.removeAt(i+1);
//            opList.removeAt(i);
//            i--;
//
//        }

            i++;
        }

        var answer = nums[0];

        //round if able
        //answer = getSubstring(answer, 0, answer.indexOf('.')+7)
        if (answer.length < 2) return answer;
        if (answer[answer.length-2] == '.' && answer[answer.length-1] == '0')
            return answer.toDouble().toInt().toString();
        return answer;
    }

    private fun calcReplace(s: String): String {
        var str = s;
        for (i in str.indices) {
            if (i == 0) {
                if (str[i] == '-') str = "0+" + str;
            }
            else {

                if (s[i] == '-') {

                    if (s[i-1].toString().toIntOrNull() != null) {
                        str = getSubstring(s, 0, i-1) + "+-" + getSubstring(s, i+1, s.length-1);
                    }
                }
            }

        }
        return str;
    }

    /*** CHANGE COLORS ***/

    private fun setColorUp() {
        layout.setBackgroundColor(Color.parseColor(color));
    }

    private fun setColorDown() {
        layout.setBackgroundColor(Color.parseColor(downColor));
    }

    public fun setBackgroundImage(imageId: Int) {
        this.imageId = imageId;
        layout.setBackgroundResource(imageId);
    }
}

