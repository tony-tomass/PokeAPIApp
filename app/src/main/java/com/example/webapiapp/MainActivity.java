package com.example.webapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView image_iv;
    TextView nat_num_value_tv;
    TextView name_value_tv;
    TextView weight_value_tv;
    TextView height_value_tv;
    TextView base_xp_value_tv;
    TextView move_value_tv;
    TextView ability_value_tv;
    EditText enter_name_et;
    Button search_bt;
    RecyclerView list_rv;

    List<Pokemon> pkmn_list_for_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        image_iv = findViewById(R.id.image_IV);
        nat_num_value_tv = findViewById(R.id.nat_num_TV);
        name_value_tv = findViewById(R.id.name_TV);
        weight_value_tv = findViewById(R.id.weight_value_TV);
        height_value_tv = findViewById(R.id.height_value_TV);
        base_xp_value_tv = findViewById(R.id.base_xp_value_TV);
        move_value_tv = findViewById(R.id.move_value_TV);
        ability_value_tv = findViewById(R.id.ability_value_TV);
        enter_name_et = findViewById(R.id.enter_name_ET);
        search_bt = findViewById(R.id.search_BT);
        list_rv = findViewById(R.id.list_RV);

        pkmn_list_for_rv = new ArrayList<>();

        image_iv.setImageResource(R.drawable.pokemon_icon);

        search_bt.setOnClickListener(search_listener);

        list_rv.setLayoutManager(new LinearLayoutManager(this));
        list_rv.setAdapter(new MyAdapter(getApplicationContext(), pkmn_list_for_rv));

    }

    View.OnClickListener search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = enter_name_et.getText().toString().trim();
            makeRequest(name);
            Log.i("List_size", String.valueOf(pkmn_list_for_rv.size()));
        }
    };

    private void makeRequest(String poke_req) {
        ANRequest req = AndroidNetworking.get("https://pokeapi.co/api/v2/pokemon/{poke_req}")
                .addPathParameter("poke_req", poke_req)
                .setPriority(Priority.LOW)
                .build();
        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject pokemon_list) {
                try {
                    //https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
                    // [] = array
                    // {} = object
                    String id = "#" + pokemon_list.getString("id");
                    String name = pokemon_list.getString("name");
                    String weight = pokemon_list.getString("weight");
                    String height = pokemon_list.getString("height");
                    String base_xp = pokemon_list.getString("base_experience");
                    JSONArray move_list = pokemon_list.getJSONArray("moves");
                    JSONArray ability_list = pokemon_list.getJSONArray("abilities");
                    String move_0 = move_list.getJSONObject(0)
                            .getJSONObject("move")
                            .getString("name");
                    String ability_0 = ability_list.getJSONObject(0)
                            .getJSONObject("ability")
                            .getString("name");
                    String image_url = pokemon_list.getJSONObject("sprites")
                            .getJSONObject("other")
                            .getJSONObject("official-artwork")
                            .getString("front_default");
                    String sprite_url = pokemon_list.getJSONObject("sprites")
                            .getJSONObject("versions")
                            .getJSONObject("generation-viii")
                            .getJSONObject("icons")
                            .getString("front_default");

                    nat_num_value_tv.setText(id);
                    name_value_tv.setText(name);
                    weight_value_tv.setText(weight);
                    height_value_tv.setText(height);
                    base_xp_value_tv.setText(base_xp);
                    move_value_tv.setText(move_0);
                    ability_value_tv.setText(ability_0);
                    Picasso.get().load(image_url).into(image_iv);

                    pkmn_list_for_rv.add(new Pokemon(Integer.parseInt(pokemon_list.getString("id")), name, sprite_url));
                    list_rv.setAdapter(new MyAdapter(getApplicationContext(), pkmn_list_for_rv));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                /*
                for (Pokemon pokemon : pokemon_list) {
                    Log.i("POKEMON", "name: " + pokemon.getName());
                    Log.i("POKEMON", "number: " + pokemon.getNat_num());

                    nat_num_value_tv.setText(pokemon.getNat_num());
                    name_value_tv.setText(pokemon.getName());
                    weight_value_tv.setText(String.valueOf(pokemon.getWeight()));
                    height_value_tv.setText(String.valueOf(pokemon.getHeight()));
                    base_xp_value_tv.setText(String.valueOf(pokemon.getBase_experience()));
                    move_value_tv.setText(pokemon.getMove());
                    ability_value_tv.setText(pokemon.getAbility());
                }
                 */
            }

            @Override
            public void onError(ANError anError) {
                String TAG = "API_ERROR";
                // received error from server
                // error.getErrorCode() - the error code from server
                // error.getErrorBody() - the error body from server
                // error.getErrorDetail() - just an error detail
                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                Log.i(TAG, "onError errorCode : " + anError.getErrorCode());
                Log.i(TAG, "onError errorBody : " + anError.getErrorBody());
                Log.i(TAG, "onError errorDetail : " + anError.getErrorDetail());
                Toast.makeText(getApplicationContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
            }
        });
    }

}