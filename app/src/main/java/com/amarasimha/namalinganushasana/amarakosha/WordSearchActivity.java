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

import com.amarasimha.namalinganushasana.amarakosha.httpclient.MongoDbRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class WordSearchActivity extends Activity {

    public static final String EXTRA_MESSAGE = "com.amarasimha.namalinganushasana.amarakosha.MESSAGE";
    private static final String[] WORDS = {"स्वर", "अव्ययं", "स्वर्ग", "नाक", "त्रिदिव", "त्रिदशालया:", "सुरलोक", "दिव"};

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
        getSynonyms(inputText);
    }

    /**
     * Method to retrieve synonyms from Amarakosha Datasource
     * @param inputText
     * @return
     */
    private void getSynonyms(String inputText)
    {
        Random randomNumber = new Random();
        inputText = WORDS[randomNumber.nextInt(8)];
        StringBuilder reqParamJsonString = new StringBuilder("{'word':'").append(inputText).append("'}");
        RequestParams params = new RequestParams("q", reqParamJsonString);
        MongoDbRestClient.get("/", params, new JsonHttpResponseHandler() {

            String result = null;
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                try {
                    StringBuilder resultText = new StringBuilder();
                    if(responseArray != null && responseArray.length() > 0) {
                        JSONObject responseJson = responseArray.getJSONObject(0);
                        if(responseJson != null) {
                            resultText.append(getString(R.string.word_search_result_label_word))
                                    .append(responseJson.getString("word")).append("\n");
                            JSONArray synonymArray = responseJson.getJSONObject("metadata").getJSONArray("synonyms");
                            String synonyms = synonymArray.join(",");
                            resultText.append(getString(R.string.word_search_result_label_synonyms))
                                    .append(synonyms).append("\n");
                            resultText.append(getString(R.string.word_search_result_label_varga))
                                    .append(responseJson.getJSONObject("metadata").getString("varga")).append("\n");
                            resultText.append(getString(R.string.word_search_result_label_shloka))
                                    .append(responseJson.getJSONObject("metadata").getString("shloka"));
                        }
                    }
                    else {
                        resultText.append(getString(R.string.word_search_result_word_not_found));
                    }
                    updateSearchResultView(resultText.toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                System.out.println(errorResponse);
                System.out.println("REST call failed!");
            }
        });

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
