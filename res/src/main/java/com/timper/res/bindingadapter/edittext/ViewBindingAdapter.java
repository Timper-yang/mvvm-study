package com.timper.res.bindingadapter.edittext;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.timper.res.bindingadapter.action.Command;

public final class ViewBindingAdapter {

  @BindingAdapter({ "requestFocus" }) public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
    if (needRequestFocus) {
      editText.setFocusableInTouchMode(true);
      editText.setSelection(editText.getText().length());
      editText.requestFocus();
      InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    } else {
      editText.setFocusable(false);
    }
  }

  @BindingAdapter({ "textWatcher" }) public static void textWatcher(EditText editText, final TextWatcher textWatcher) {
    if (textWatcher != null) {
      editText.addTextChangedListener(textWatcher);
    }
  }

  @BindingAdapter(value = { "beforeTextChangedCommand", "onTextChangedCommand", "afterTextChangedCommand" }, requireAll = false)
  public static void editTextCommand(EditText editText, final Command<TextChangeDataWrapper> beforeTextChangedCommand,
      final Command<TextChangeDataWrapper> onTextChangedCommand, final Command<String> afterTextChangedCommand) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (beforeTextChangedCommand != null) {
          beforeTextChangedCommand.execute(new TextChangeDataWrapper(s, start, count, count));
        }
      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (onTextChangedCommand != null) {
          onTextChangedCommand.execute(new TextChangeDataWrapper(s, start, before, count));
        }
      }

      @Override public void afterTextChanged(Editable s) {
        if (afterTextChangedCommand != null) {
          afterTextChangedCommand.execute(s.toString());
        }
      }
    });
  }

  public static class TextChangeDataWrapper {
    public CharSequence s;
    public int start;
    public int before;
    public int count;

    public TextChangeDataWrapper(CharSequence s, int start, int before, int count) {
      this.s = s;
      this.start = start;
      this.before = before;
      this.count = count;
    }
  }
}

