package com.example.equiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RemarqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarque);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int idSeance = this.getIntent().getIntExtra(SeancesAdapter.ID_SEANCE,0);
        String start_date = this.getIntent().getStringExtra(SeancesAdapter.EXTRA_START_DATE);
        Log.w(RemarqueActivity.class.getSimpleName(), "id dans reamrque activity : "+idSeance);
        EditText t = findViewById(R.id.remarquetxt);
        TextView title = findViewById(R.id.seanceIdtxt);
        MyDB db = new MyDB(this);
        t.setText(db.readRemarque(idSeance));
        title.setText("Seance du "+start_date);

    }

    public void saveRemarque(View view) {
        EditText remarquetxt = findViewById(R.id.remarquetxt);
        String remarque = remarquetxt.getText().toString();
        int idSeance = this.getIntent().getIntExtra(SeancesAdapter.ID_SEANCE,0);
        MyDB db = new MyDB(this);
        db.insertRemarque(idSeance,remarque);
    }
}