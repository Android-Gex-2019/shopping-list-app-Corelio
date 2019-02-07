package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private ShoppingCart sc = new ShoppingCart();
    private static final String TAG = "Corelio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "at create");

        if(savedInstanceState != null){
            Log.d(TAG, "saved Instance");
        }else{
            Log.d(TAG, "no saved Instance");
        }

        //load saved state
        if(savedInstanceState != null){
            sc = (ShoppingCart)savedInstanceState.getSerializable("sc");
        }

        displayList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayList();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            sc = (ShoppingCart)savedInstanceState.getSerializable("sc");
        }

        displayList();
    }

    private void displayList() {
        //Clear fields
        for(int i=1; i<=10;i++){
            String fieldName = "txtItem" + i;
            TextView tmp = (TextView)findViewById((int)getResources().getIdentifier(fieldName, "id", getPackageName()));
            if(tmp!=null) {
                tmp.setVisibility(View.INVISIBLE);
                tmp.setText("");
            }
        }
        Iterator<Map.Entry<String, Integer>> it = sc.getShoppingList().entrySet().iterator();
        int i = 1;
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            String fieldName = "txtItem" + i;
            TextView tmp = (TextView)findViewById((int)getResources().getIdentifier(fieldName, "id", getPackageName()));
            if(tmp!=null) {
                tmp.setVisibility(View.VISIBLE);
                tmp.setText(pair.getValue()+ ": " + pair.getKey());
            }
            i++;
        }
    }

    public void LaunchItemList(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String item = data.getStringExtra(ItemsActivity.EXTRA_REPLY);
                //Toast.makeText(this, item, Toast.LENGTH_LONG).show();
                if(item != null) {
                    sc.addItem(item);
                }
            }
        }
        displayList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("sc", sc);
    }

}
