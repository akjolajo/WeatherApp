package kg.geektech.weatherapp.common;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull
    public final Status status;
    public final T data;
    public final String msg;

    public Resource(T data, String msg, @NonNull Status status) {
        this.data = data;
        this.msg = msg;
        this.status = status;
    }

    public static <T> Resource<T> success(@NonNull T data){
        return new Resource<>(data, null, Status.SUCCESS);
    }
    public static <T> Resource<T> error(String msg, @Nullable T data){
        return new Resource<>(data, msg, Status.ERROR);
    }
    public static <T> Resource<T> loading(){
        return new Resource<>(null, null, Status.LOADING);
    }

}