package com.example.Fabulous.UI.Fragments;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final PostStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public Resource(@NonNull PostStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> authenticated (@Nullable T data) {
        return new Resource<>(PostStatus.DATARECEIVED, data, null);
    }

    public static <T> Resource<T> error(@NonNull String msg, @Nullable T data) {
        return new Resource<>(PostStatus.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(PostStatus.LOADING, data, null);
    }

    public static <T> Resource<T> logout () {
        return new Resource<>(PostStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum PostStatus { DATARECEIVED, ERROR, LOADING, NOT_AUTHENTICATED}

}