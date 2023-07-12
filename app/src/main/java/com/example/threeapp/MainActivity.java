package com.example.threeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;
    EditText textName, textLastName, textDate, textEmail, textId;
    Button btn01, btnCancel;
    RequestQueue requestQueue;

    private static final String API_URL = "http://52.240.58.203/android/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        itemList = new ArrayList<>();


        final Button buttonRegister = findViewById(R.id.btn01);
        buttonRegister.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) { onClickR(v);}
        });

        textName = findViewById(R.id.textName);
        textLastName = findViewById(R.id.textName2);
        textDate = findViewById(R.id.textDate);
        textEmail = findViewById(R.id.textEmail);
        textId = findViewById(R.id.textId);
    }

    public void onClickR(View x) {
        int id = x.getId();

        if(id == R.id.btn01){
            String nombre = textName.getText().toString().trim();
            String apellido = textLastName.getText().toString().trim();
            String fecha = textDate.getText().toString().trim();
            String correo = textEmail.getText().toString().trim();

            createUser(nombre, apellido, fecha, correo);

        }else if(id == R.id.btnCancel){
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("id", textId.getText().toString().trim());
            startActivity(intent);
        }
    }

    private void createUser(String nombre, String apellido, String fecha, String correo) {
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("nombre", nombre);
            requestData.put("apellido", apellido);
            requestData.put("fecha", fecha);
            requestData.put("correo", correo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textName.setText("lola");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(MainActivity.this, "Usuario Agregado", Toast.LENGTH_SHORT).show();
                        //refreshTasks();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }

   /*
    private void createUser(String nombre, String apellido, String fecha, String correo){

        textName.setText("lola");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        textName.setText("Lucer");
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
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("fecha", fecha);
                params.put("correo", correo);
                return params;
            }
        };
    }*/
}