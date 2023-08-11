package sg.edu.rp.c346.id22021136.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Display extends AppCompatActivity {

    ListView lv;
    Button back;
    Button pg;
    Spinner spn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lv = findViewById(R.id.listV);
        back = findViewById(R.id.back);
        pg = findViewById(R.id.btnPG13);
        spn2 = findViewById(R.id.spinner2);


        DBHelper db = new DBHelper(Display.this);

        ArrayList<Movie> data = db.getMovies();

        CustomAdapter ca = new CustomAdapter(this, R.layout.row, data);
        lv.setAdapter(ca);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = data.get(position);
                Intent i = new Intent(Display.this, Edit.class);
                i.putExtra("movie", movie);
                startActivity(i);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Display.this, MainActivity.class);
                startActivity(i);
            }
        });

        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(Display.this);
                data.clear();
                ArrayList<Movie> pgmovies = db.getRatingMovie("PG13");
                data.addAll(pgmovies);
                ca.notifyDataSetChanged();
            }
        });

        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            DBHelper db = new DBHelper(Display.this);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        data.clear();
                        ArrayList<Movie> allMovies = db.getMovies();
                        data.addAll(allMovies);
                        ca.notifyDataSetChanged();
                        break;
                    case 1:
                        data.clear();
                        ArrayList<Movie> Gmovies = db.getRatingMovie("G");
                        data.addAll(Gmovies);
                        ca.notifyDataSetChanged();

                        break;
                    case 2:
                        data.clear();
                        ArrayList<Movie> PGmovies = db.getRatingMovie("PG");
                        data.addAll(PGmovies);
                        ca.notifyDataSetChanged();
                        break;
                    case 3:
                        data.clear();
                        ArrayList<Movie> PG13movies = db.getRatingMovie("PG13");
                        data.addAll(PG13movies);
                        ca.notifyDataSetChanged();
                        break;
                    case 4:
                        data.clear();
                        ArrayList<Movie> NC16movies = db.getRatingMovie("NC16");
                        data.addAll(NC16movies);
                        ca.notifyDataSetChanged();
                        break;
                    case 5:
                        data.clear();
                        ArrayList<Movie> M18movies = db.getRatingMovie("M18");
                        data.addAll(M18movies);
                        ca.notifyDataSetChanged();
                        break;
                    case 6:
                        data.clear();
                        ArrayList<Movie> R21movies = db.getRatingMovie("R21");
                        data.addAll(R21movies);
                        ca.notifyDataSetChanged();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}