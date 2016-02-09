package agharibi.com.rab;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EditFriendsActivity extends ListActivity {

    protected List<BackendlessUser> mBackendlessUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_edit_friends);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Backendless.Data.of(BackendlessUser.class).find(new AsyncCallback<BackendlessCollection<BackendlessUser>>() {

            @Override
            public void handleResponse(BackendlessCollection<BackendlessUser> users) {

                mBackendlessUserList = users.getData();
                int i = 0;
                String[] emails = new String[mBackendlessUserList.size()];

                for(BackendlessUser user : mBackendlessUserList) {
                    emails[i] = user.getEmail();
                    i++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        EditFriendsActivity.this,
                        android.R.layout.simple_list_item_checked,
                        emails);
                setListAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditFriendsActivity.this);
                builder.setMessage(R.string.edit_friends_no_data)
                        .setTitle(R.string.error_title)
                        .setPositiveButton(android.R.string.ok, null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}