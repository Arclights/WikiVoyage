/*
 * Copyright 2014, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tommykvant.wikivoyage.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.tommykvant.wikivoyage.MainPage;
import com.tommykvant.wikivoyage.R;
import com.tommykvant.wikivoyage.utils.Utils;

/**
 * A Floating Action Button is a {@link android.widget.Checkable} view distinguished by a circled
 * icon floating above the UI, with special motion behaviors.
 */
public class FloatingActionButton extends FrameLayout {


    /**
     * An array of states.
     */
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    private static final String TAG = "FloatingActionButton";

    // A boolean that tells if the FAB has ben clicked once and is showing the search box
    private boolean showing;

    public FloatingActionButton(Context context) {
        this(context, null, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr);

        setClickable(true);

        // Set the outline provider for this view. The provider is given the outline which it can
        // then modify as needed. In this case we set the outline to be an oval fitting the height
        // and width.
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, getWidth(), getHeight());
            }
        });

        // Finally, enable clipping to the outline, using the provider we set above
        setClipToOutline(true);
    }

    /**
     * Override performClick() so that we can toggle the checked state when the view is clicked
     */
    @Override
    public boolean performClick() {

        if (!showing) {
            show();
        } else {
            ((MainPage) getContext()).search((ViewGroup) getParent());
        }
        return super.performClick();
    }

    public void show() {
        ViewGroup parent = (ViewGroup) getParent();

        EditText searchBox = (EditText) parent.findViewById(R.id.searchBox);
        ObjectAnimator boxOa = ObjectAnimator.ofFloat(searchBox, "x", searchBox.getX() - Utils.dpsToPixels(getResources(), 150));
        boxOa.setDuration(500);

        View masker = parent.findViewById(R.id.masker);
        ObjectAnimator maskOa = ObjectAnimator.ofFloat(masker, "x", masker.getX() + Utils.dpsToPixels(getResources(), 125));
        maskOa.setDuration(500);

        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX() + Utils.dpsToPixels(getResources(), 125));
        oa.setDuration(500);
        oa.start();
        maskOa.start();
        boxOa.start();
        searchBox.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchBox, 0);
        showing = true;
    }

    public void hide(){
        ViewGroup parent = (ViewGroup) getParent();

        EditText searchBox = (EditText) parent.findViewById(R.id.searchBox);
        ObjectAnimator boxOa = ObjectAnimator.ofFloat(searchBox, "x", searchBox.getX() + Utils.dpsToPixels(getResources(), 150));
        boxOa.setDuration(500);

        View masker = parent.findViewById(R.id.masker);
        ObjectAnimator maskOa = ObjectAnimator.ofFloat(masker, "x", masker.getX() - Utils.dpsToPixels(getResources(), 125));
        maskOa.setDuration(500);

        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX() - Utils.dpsToPixels(getResources(), 125));
        oa.setDuration(500);
        oa.start();
        maskOa.start();
        boxOa.start();
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBox.getWindowToken(),0);
        showing = false;
    }

    @Override
    public boolean callOnClick() {
        return super.callOnClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // As we have changed size, we should invalidate the outline so that is the the
        // correct size
        invalidateOutline();
    }


}