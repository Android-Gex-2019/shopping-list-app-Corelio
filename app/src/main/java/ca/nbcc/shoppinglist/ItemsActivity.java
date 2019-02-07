package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class ItemsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "ca.nbcc.shoppinglist.itemsactivity.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    public void selectItem(View view) {
        String item = view.getTag().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NavUtils.navigateUpTo(this, intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
