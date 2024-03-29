// Generated by Dagger (https://google.github.io/dagger).
package com.example.Fabulous;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<Context> contextProvider;

  private final Provider<Context> activityContextProvider;

  private final Provider<LoginPresenterImpl> loginPresenterProvider;

  public LoginActivity_MembersInjector(
      Provider<Context> contextProvider,
      Provider<Context> activityContextProvider,
      Provider<LoginPresenterImpl> loginPresenterProvider) {
    this.contextProvider = contextProvider;
    this.activityContextProvider = activityContextProvider;
    this.loginPresenterProvider = loginPresenterProvider;
  }

  public static MembersInjector<LoginActivity> create(
      Provider<Context> contextProvider,
      Provider<Context> activityContextProvider,
      Provider<LoginPresenterImpl> loginPresenterProvider) {
    return new LoginActivity_MembersInjector(
        contextProvider, activityContextProvider, loginPresenterProvider);
  }

  @Override
  public void injectMembers(LoginActivity instance) {
    injectContext(instance, contextProvider.get());
    injectActivityContext(instance, activityContextProvider.get());
    injectLoginPresenter(instance, loginPresenterProvider.get());
  }

  public static void injectContext(LoginActivity instance, Context context) {
    instance.context = context;
  }

  public static void injectActivityContext(LoginActivity instance, Context activityContext) {
    instance.activityContext = activityContext;
  }

  public static void injectLoginPresenter(
      LoginActivity instance, LoginPresenterImpl loginPresenter) {
    instance.loginPresenter = loginPresenter;
  }
}
