package com.amarasimha.namalinganushasana.amarakosha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class DisplaySynonymActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String inputText = intent.getStringExtra(WordSearchActivity.EXTRA_MESSAGE);
        try {
            InputStream is = getAssets().open("dictionary.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");
            try {
                JSONObject json = new JSONObject(jsonString);

                JSONArray words = json.getJSONArray("words");
                JSONObject wordSet = (JSONObject) words.get(0);
                String synonyms = wordSet.getString("स्वर");
                String varga = wordSet.getString("varga");
                String shloka = wordSet.getString("shloka");

                StringBuffer resultText = new StringBuffer("Searched for: ").append("स्वर");
                resultText.append("\n").append("Synonyms: ").append(synonyms);
                resultText.append("\n").append("Varga: ").append(varga);
                resultText.append("\n").append("Shloka: ").append(shloka);

                TextView textView = new TextView(this);
                textView.setText(resultText);
                textView.setTextSize(10);
                setContentView(textView);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
