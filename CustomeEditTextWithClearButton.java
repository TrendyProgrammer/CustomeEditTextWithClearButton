package com.example.trendyprogrammer.customeedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
android.support.v7.widget.AppCompatEditText;

/**
 * View which is extends EditText.
 * This view provides a Clear "X" button within the text field
 * which,when press, clears the text from the EditText field.
 */

public class CustomeEditTextWithClearButton
        extends AppCompatEditText {

    private Drawable clearButtonImage;

    public CustomeEditTextWithClearButton(Context context) {
        super(context)
        initialize();
    }

    public CustomeEditTextWithClearButton(Context context, AttributeSet attrs) {
        super(context, attrs)
        initialize();
    }

    public CustomeEditTextWithClearButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        //lets initialize our Drawable variable here
        clearButtonImage =
                ResourcesComact.getDrawable(getResources(),
                        R.drawble.ic_clear_button_red_20dp, null);


        // if the clear button is tapped , clear the edittext text.

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Lets user the getCompoundDrawables()[2] expression to check
                // The drawable is on the "End" of the thext

                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float textClearButtonStart; //Use for LTR languages
                    float textClearButtonEnd;  //Use for RTL language
                    boolean isTextClearButtonClicked = false;
                    // let's Detect the touch in RTL or LTR layout direction.
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        //If layout direction is RTL ,get the end of the button on the left side
                        textClearButtonEnd = clearButtonImage
                                .getIntrinsicWidth() + getPaddingStart();

                        //If the touch occurred before the end of the button,
                        // then set isTextClearButtonIsClicked = true
                        if (event.getX() < clearButtonEnd) {
                            isTextClearButtonClicked = true;
                        } else {
                            //Layout Direction is LTR.
                            // Get the start of the button from the right side.
                            textClearButtonStart = (getWidth() - getPaddingEnd()
                                    - clearButtonImage.getIntrinsicWidth());
                            //If the touch event occured after the the start of the clearButtonImage,
                            // set isTextClearButtonClicked to true.
                            if (event.getX() > textClearButtonStart) {
                                isTextClearButtonClicked = true;
                            }
                        }
                        //Now lets check for actions if the button is tapped.
                        if (isTextClearButtonClicked) {
                            //let's check for ACTION_DOWN "always occurs befor ACTION_UP".
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                // switch to the black image of clear button.
                                clearButtonImage =
                                        ResourcesCompat.getDrawable(getResources(), R, drawable.ic_clear_black_20pd, null);
                                showClearButton();
                            }
                            //Now Check for the ACTION_UP.
                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                //Switch to the green image of clear button.
                                clearButtonImage =
                                        ResourcesCompact.getDrawable(getResources(),
                                                R.drawable.ic_clear_green_20dp, null);
                                //Clear the EditText and hide the clear Button.
                                getText().clear();
                                hideClearButton();
                                return true;
                            }

                        } else {
                            return false;
                        }
                        return false;
                    }
                });

                // If text changes, show or hide text "X" clear button
                addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s,
                                                  int start, int count, int after) {

                        //Do nothing.
                    }

                    @Override
                    public void onTextChanged(CharSequence s,
                                              int start, int before, int count) {

                        showClearButton()
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //Do nothing.
                    }
                });
            }

            /*
             * Show the clear "X" button.
             *
             */
            private void showClearButton() {
                //Set the drawable image (if any) to show to the left of,
                above, to the right of and below text.
                SetCompoundDrawablesRelativeWithInstrinsicBounds
                        (null,                        //start of text.
                                null,                      //Top of text.
                                clearButtonImage,            //End of text.
                                null);                        //Below the text.

            }

            /**
             * Hides the clear button.
             */
            private void hideClearButton() {
                SetCompoundDrawablesRelativeWithInstrinsicBounds
                        (null,                        //start of text.
                                null,                      //Top of text.
                                clearButtonImage,            //End of text.
                                null);                        //Below the text.
            }

        }
