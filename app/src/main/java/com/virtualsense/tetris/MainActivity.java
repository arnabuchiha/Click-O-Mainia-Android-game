package com.virtualsense.tetris;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private GridView list;
    int possible=0;
    int a[][]={{0,1,1,0,0,0},
            {0,1,2,2,0,0},
            {0,1,1,2,3,0},
            {17,0,2,2,4,0},
            {17,5,5,5,6,0},
            {17,0,0,8,7,0},
            {17,9,8,8,10,10},
            {17,11,12,12,12,13},
            {17,15,12,14,0,16},
            {17,12,12,12,18,19}
    };
    boolean bool[][]=new boolean[10][6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        list = findViewById(R.id.grid_view);
        setGridView();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button button=view.findViewById(R.id.button);
                String str=button.getText().toString();
                LinkedList<Blocks> linkedList=new LinkedList<>();
                if(!str.equals("")) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (a[i][j] == Integer.valueOf(str)) {
                                a[i][j] = 0;
                                Blocks blocks = new Blocks();
                                blocks.setColumn(j);
                                blocks.setRow(i);
                                linkedList.add(blocks);
                            }
                        }
                    }
                    while(possible<10) {
                        for (int f = 0; f < 10; f++) {
                            for (int g = 0; g < 6; g++) {
                                bool[f][g] = false;
                            }
                        }
                        Blocks start=linkedList.getFirst();
                        Blocks end = linkedList.getLast();
                        for (int p = 9; p >= 0; p--) {
                            for (int q = 0; q < 6; q++) {
                                if (a[p][q] != 0)
                                    checkFall(a[p][q],start,end);
                            }
                        }
                        fall();
                        resetBool();
                        possible++;
                    }
                    possible=0;
                }
                setGridView();
            }
        });
    }

    /**
     * Reset boolean 2D arrays for next operation
     */
    private void resetBool() {
        for(int i=0;i<10;i++){
            for(int j=0;j<6;j++){
                bool[i][j]=false;
            }
        }
    }

    /**
     * Function to move block of numbers down
     */
    private void fall() {
        for(int i=9;i>=0;i--){
            for(int j=0;j<6;j++){
                if(bool[i][j]){
                    a[i+1][j]=a[i][j];
                    a[i][j]=0;
                }
            }
        }
    }

    /**
     * Function to check if blocks can move down
     * @param b
     * @param start
     * @param end
     */
    private void checkFall(int b, Blocks start, Blocks end) {
        ArrayList<Blocks> list=new ArrayList<>();
        for(int i=9;i>=0;i--){
            for(int j=5;j>=0;j--){
                if(a[i][j]==b){
                    Blocks blocks=new Blocks();
                    blocks.setRow(i);
                    blocks.setColumn(j);
                    list.add(blocks);
                }
            }
        }
        for(int i=0;i<list.size();i++){
            int row=list.get(i).getRow();
            int col=list.get(i).getColumn();
            if(row<9) {
                if (a[row + 1][col] == a[row][col] || a[row + 1][col] == 0 || bool[row + 1][col]) {
                    bool[row][col] = true;
                }
            }
        }
        for(int i=0;i<list.size();i++) {
            int row = list.get(i).getRow();
            int col = list.get(i).getColumn();
            if (!bool[row][col]) {
                for(int j=0;j<list.size();j++){
                    row=list.get(j).getRow();
                    col=list.get(j).getColumn();
                    bool[row][col]=false;
                }
                break;
            }
        }
    }

    /**
     * For forming the grid board
     */
    void setGridView(){
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++)
                data.add(String.valueOf(a[i][j]));
        }
        GridViewCustomAdapter adapter = new GridViewCustomAdapter(MainActivity.this, data);

        adapter.notifyDataSetChanged();
        list.invalidateViews();
        list.setAdapter(adapter);
        list.setVerticalScrollBarEnabled(false);
    }
}
