package ph.edu.ust.jatallas.project;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

public class Frappe extends AppCompatActivity {

    ListView listView;
    AdapterFrappeListView adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<ModelFrappe> arrayList = new ArrayList<ModelFrappe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frappe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("FRAPPE ");

        title = new String[]{"Gong Cha UST The One","D'Cream P.Noval", "Moonleaf Espana", "I love Milktea Sampaloc", "Bon Appetea Espana" };
        description = new String[]{"Location: Food Court, Second Floor, The One Grand Centre, España", "Location: P. Noval Street, Near Espana Boulevard, Sampaloc, Manila",
                "Location: G/F, St Thomas Square, España Blvd, 397, Manila, 1015 Metro Manila", "Location: Padre Noval Street, Near UST Campus, Sampaloc, Manila",
                "Location: 838 P. Campa Street, Sampaloc, Manila"};
        icon = new int[]{R.drawable.milkgongcha , R.drawable.milkdcream, R.drawable.milkmoonleaf, R.drawable.milkilmilktea, R.drawable.milkbonappetea};

        listView = findViewById(R.id.listView);

        for (int i = 0; i<title.length; i++){
            ModelFrappe modelFrappe = new ModelFrappe(title[i], description[i],icon[i] );
            //bind strings in array
            arrayList.add(modelFrappe);
        }

        //pass results to AdapterFrappeListView
        adapter = new AdapterFrappeListView(this, arrayList);

        //bind adapter in list view
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufrappe, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
