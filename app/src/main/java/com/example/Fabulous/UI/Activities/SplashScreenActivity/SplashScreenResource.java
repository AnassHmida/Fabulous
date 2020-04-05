package com.example.Fabulous.UI.Activities.SplashScreenActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class SplashScreenResource<T>{



        @NonNull
        public final SplashScreenResource.Splash status;

        @Nullable
        public final T data;

        @Nullable
        public final String message;


        public SplashScreenResource(@NonNull SplashScreenResource.Splash status, @Nullable T data, @Nullable String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }

        public static <T> SplashScreenResource<T> done (@Nullable T data) {
            return new SplashScreenResource<>(Splash.DONE, data, null);
        }



        public enum Splash {DONE}


}
