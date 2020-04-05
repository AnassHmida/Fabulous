package com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Fabulous.UI.Activities.LoginActivity.LoginResource;


public class MesFormationsResource<T>{



    @NonNull
    public final MesFormationsResource.Splash status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public MesFormationsResource(@NonNull MesFormationsResource.Splash status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> MesFormationsResource<T> done (@Nullable T data) {
        return new MesFormationsResource<>(MesFormationsResource.Splash.DONE, data, null);
    }
    public static <T> MesFormationsResource<T> error(@NonNull String msg, @Nullable T data) {
        return new MesFormationsResource<>(MesFormationsResource.Splash.ERROR, data, msg);
    }



    public enum Splash {DONE,ERROR}


}
