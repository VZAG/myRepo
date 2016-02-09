package agharibi.com.rab;

import android.app.Application;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;

public class RabApplication extends Application {
    private static final String YOUR_APP_ID = "6689232B-E7A2-A188-FF42-97B4047DE700";
    private static final String YOUR_SECRET_KEY = "7786DB06-B92E-68B0-FF2A-AE621A74AF00";
    private BackendlessUser user;

    @Override
    public void onCreate() {
        super.onCreate();
        String appVersion = "v1";
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);

        user = new BackendlessUser();
        user.setEmail("armen352@yahoo.com");
        user.setPassword("jack");

        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
            }
        });

    }
}
