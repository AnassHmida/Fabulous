// Generated code from Butter Knife. Do not modify!
package com.example.Fabulous;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f08005f;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.usernameEditText = Utils.findRequiredViewAsType(source, R.id.username_textInput, "field 'usernameEditText'", TextInputEditText.class);
    target.passwordEditText = Utils.findRequiredViewAsType(source, R.id.password_textInput, "field 'passwordEditText'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.login_button, "method 'login'");
    view7f08005f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.usernameEditText = null;
    target.passwordEditText = null;

    view7f08005f.setOnClickListener(null);
    view7f08005f = null;
  }
}
