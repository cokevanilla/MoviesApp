package sg.edu.rp.c346.id22021136.moviesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Edit extends AppCompatActivity {

    Button update;
    Button delete;
    Button cancel;

    EditText title;
    EditText year;
    EditText genre;
    Spinner spn;
    String rating = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        cancel= findViewById(R.id.Cancel);
        title = findViewById(R.id.edTitle);
        year = findViewById(R.id.etYear);
        genre = findViewById(R.id.etGenre);
        spn = findViewById(R.id.change);

        Intent i = getIntent();
        Movie song = (Movie) i.getSerializableExtra("movie");
        title.setText(song.getTitle());
        genre.setText(song.getGenre());
        year.setText(Integer.toString(song.getYear()));


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        rating = "G";
                        break;
                    case 1:
                        rating = "PG";
                        break;
                    case 2:
                        rating = "PG13";
                        break;
                    case 3:
                        rating = "NC16";
                        break;
                    case 4:
                        rating = "M18";
                        break;
                    case 5:
                        rating = "R21";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(Edit.this);
                String tt = title.getText().toString();
                song.setTitle(tt);
                String genreInp = genre.getText().toString();
                song.setGenre(genreInp);
                int yearInp = Integer.parseInt(year.getText().toString());
                song.setYear(yearInp);
                song.setRating(rating);
                db.updateMovie(song);
                db.close();
                Intent i = new Intent(Edit.this, Display.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(Edit.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + song.getTitle());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Cancel", null);
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(Edit.this);
                        db.deleteMovie(song.getId());
                        db.close();
                        Intent i = new Intent(Edit.this, Display.class);
                        startActivity(i);
                    }

                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(Edit.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do Not Discard", null);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Edit.this, Display.class);
                        startActivity(i);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}