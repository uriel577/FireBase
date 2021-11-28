package mx.edu.utng.firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView tvMensaje;
    private EditText etTexto;

    //Referencia a la base de datos
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef = ref.child("mimensaje");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        etTexto = (EditText) findViewById(R.id.etTexto);
    }

    public void modificar(View view) {
        //Accion del boton de modificar
        String mensaje = etTexto.getText().toString();
        mensajeRef.setValue(mensaje); //Actuliza el mensaje en la base de datos
        etTexto.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                tvMensaje.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}