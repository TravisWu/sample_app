package com.traviswu.clutch;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {

    EditText editName, editNumber, editEmail;
    List<Contact>Contacts = new ArrayList<Contact>();
    ListView contactListView;
    GridView myGrid;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGrid= (GridView) findViewById(R.id.gridView);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editName);
        editNumber= (EditText) findViewById(R.id.editNumber);
        editEmail=(EditText) findViewById(R.id.editEmail);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.creatorTab);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.listTab);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);


        final Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContacts(editName.getText().toString(), editNumber.getText().toString(), editEmail.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(), editName.getText().toString() + " has been added!",Toast.LENGTH_SHORT).show();

            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                buttonAdd.setEnabled(!editName.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void populateList() {
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
    }
    private void addContacts(String Name, String Number, String Email) {
        Contacts.add(new Contact(Name, Number, Email));

    }

    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(Main.this, R.layout.listview_item, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            Contact currentContact = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentContact.get_Name());
            TextView number = (TextView) view.findViewById(R.id.contactPhone);
            number.setText(currentContact.get_Number());
            TextView email = (TextView) view.findViewById(R.id.contactEmail);
            email.setText(currentContact.get_Email());

            return view;

        }
    }
    class SingleName{
        int imageid;
        String personName;
        SingleName(int imageid,String personName){
            this.imageid=imageid;
            this.personName= personName;
        }

    }
    class theAdapter extends BaseAdapter
    {
        ArrayList<SingleName> list;
        theAdapter(Context context){
            list = new ArrayList<SingleName>();
            Resources res=context.getResources();
            String[] tempContactName=res.getStringArray(R.array.Contact_Names);
            int[] contactImage= {R.drawable.ahsen, R.drawable.andrei,R.drawable.edson, R.drawable.jake,R.drawable.jen, R.drawable.justin,R.drawable.niles, R.drawable.sylvester, R.drawable.travis};
            for(int i=0; i<10; i++){
                SingleName tempName = new SingleName(contactImage[i], tempContactName[i]);
                list.add(tempName);
            }
        }
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
