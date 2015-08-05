package com.amarasimha.namalinganushasana.amarakosha;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amarasimha.namalinganushasana.amarakosha.dao.WordSearchDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class WordSearchActivity extends Activity {

    public static final String EXTRA_MESSAGE = "com.amarasimha.namalinganushasana.amarakosha.MESSAGE";
    private static final String[] words = {"स्वर", "अव्ययं", "स्वर्ग", "नाक", "त्रिदिव", "त्रिदशालया:", "सुरलोक", "दिव"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word_search, menu);
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

    public void displaySearchResults(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String inputText = editText.getText().toString();
        String resultText = getSynonyms(inputText);
        updateSearchResultView(resultText);
    }

    /**
     * Method to retrieve synonyms from dictionary
     * @param inputText
     * @return
     */
    private String getSynonyms(String inputText)
    {
        StringBuffer resultText = new StringBuffer("No such word");

        Random randomNumber = new Random();
        inputText = words[randomNumber.nextInt(9)];
        /*RequestParams params = new RequestParams("word", inputText);
        MongoDbRestClient.get("words", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                System.out.println(errorResponse);
            }
        });*/
        WordSearchDAO wordSearchDao = new WordSearchDAO();
        JSONObject wordJson = wordSearchDao.getWordSynonyms(inputText);
        try {
            if(wordJson != null) {
                resultText = new StringBuffer();
                resultText.append("Word: ").append(wordJson.getString("word")).append("\n");
                resultText.append("Synonyms: ").append(wordJson.getJSONObject("metadata").getString("synonyms")).append("\n");
                resultText.append("Varga: ").append(wordJson.getJSONObject("metadata").getString("varga")).append("\n");
                resultText.append("Shloka: ").append(wordJson.getJSONObject("metadata").getString("shloka"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return resultText.toString();
    }

    /**
     * Method that updates the search result view
     * @param resultText
     */
    private void updateSearchResultView(String resultText){
        LinearLayout layout = (LinearLayout) findViewById(R.id.search_result_layout);
        TextView searchResultView = (TextView) layout.findViewById(R.id.search_result_view);
        searchResultView.setText(resultText);
        searchResultView.setTextSize(15);
        LayoutTransition transition = layout.getLayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
    }
}
