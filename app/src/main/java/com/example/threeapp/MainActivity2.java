package com.example.threeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    Button btnDelete, btnEdit;
    EditText textNombre, textApellido, textFecha, textCorreo;
    String id;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            id = extras.getString("id");
        }

        readUser();

        textNombre = findViewById(R.id.textNameUpt);
        textApellido = findViewById(R.id.textLastName);
        textFecha = findViewById(R.id.textDate2);
        textCorreo = findViewById(R.id.textEmail2);
    }

    private void readUser(){
        String URL = "http://52.240.58.203/android/fecth.php?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String nombre, apellido, fecha, correo;
                        try {
                            nombre = response.getString("nombre");
                            apellido = response.getString("apellido");
                            fecha = response.getString("fecha");
                            correo = response.getString("correo");

                            textNombre.setText(nombre);
                            textApellido.setText(apellido);
                            textFecha.setText(fecha);
                            textCorreo.setText(correo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public void onClickR(View v) {
        int id = v.getId();
        if(id == R.id.btnEdit){

            String nombre = textNombre.getText().toString().trim();
            String apellido = textApellido.getText().toString().trim();
            String fecha = textFecha.getText().toString().trim();
            String correo = textCorreo.getText().toString().trim();

            updateUser(nombre, apellido, fecha, correo);

        } else if (id == R.id.btnDelete) {

        }

    }

    private void updateUser(String nombre, String apellido, String fecha, String correo) {
        String URL = "http://52.240.58.203/android/edit.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity2.this, "Actualización exitosa", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("fecha", fecha);
                params.put("correo", correo);
                return super.getParams();
            }
        };

        requestQueue.add(stringRequest);
    }

    private void removeUser(String idUser){
        String URL = "http://52.240.58.203/android/delete.php?id=" + id;
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("id", idUser);
                return super.getParams();
            }

        };

        requestQueue.add(stringRequest);

    };
}