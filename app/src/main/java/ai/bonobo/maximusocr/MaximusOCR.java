package ai.bonobo.maximusocr;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.speech.RecognizerIntent;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MaximusOCR extends AppCompatActivity {

    ArrayList<String> description = new ArrayList<String>();
    ArrayList<Integer> price = new ArrayList<Integer>();
    EditText totalTxt;
    ListView listView;
    ImageButton recordBtn;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maximus_ocr);

        totalTxt = (EditText)findViewById(R.id.totalTxt);
        listView = (ListView)findViewById(R.id.listView);
        recordBtn = (ImageButton)findViewById(R.id.recordBtn);

        sharedPreferences = getApplicationContext().getSharedPreferences("ai.bonobo.maximusocr.MaximusOCR", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("items", null);

        recordBtn.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                        startSpeechListener();

            }
        });
    }

    private void startSpeechListener(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String[] results = result.get(0).split(" ");
                    if(results[0] != null  && results[1] != null) {
                        description.add(results[0]);
                        price.add(Integer.parseInt(results[1]));
                        final ListViewItemsAdapter listViewAdapter = new ListViewItemsAdapter(this, description, price);
                        listView.setAdapter(listViewAdapter);
                        listViewAdapter.notifyDataSetChanged();
                    }
                    else{
                        startSpeechListener();
                    }
                }
                break;
            }

        }
    }
}
