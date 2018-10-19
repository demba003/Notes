package pl.kamil.notes.utils;

import android.text.TextWatcher;

public interface SimpleTextWatcher extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    default void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
}
