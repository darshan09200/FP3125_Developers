package com.darshan09200.employeemanagement;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class TextChangedListener implements TextWatcher {
    private EditText target;

    public TextChangedListener(EditText target) {
        this.target = target;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        this.onTextChanged(target, s);
    }

    public abstract void onTextChanged(EditText target, Editable s);
}
