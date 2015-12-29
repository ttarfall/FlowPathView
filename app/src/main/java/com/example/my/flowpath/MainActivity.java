package com.example.my.flowpath;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<FlowPathEntity> entityList;
    private FlowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        init();
    }

    private void init() {
        mListView = (ListView)findViewById(R.id.list_flow_path);
        entityList = new ArrayList<FlowPathEntity>();
        for(int i=0; i<4; i++){
            FlowPathEntity entity = new FlowPathEntity();
            entity.setNumber(i+1);
            if(i==0) {
                entity.setIconVisiable(true);
                entity.setTopLineVisiable(false);
            }
            if(i==3)
                entity.setBottomLineVisiable(false);
            entityList.add(entity);
        }
        entityList.get(0).setCurrentNumber(true);
        entityList.get(0).setText("成功提交");
        entityList.get(1).setText("正在为你申购份额，请25分钟后查看投资状体");
        entityList.get(2).setText("2015-12-25 开始计息");
        entityList.get(3).setText("2016-12-25 还本付息，预计共 550.00 元");
        mAdapter = new FlowAdapter(this, entityList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
