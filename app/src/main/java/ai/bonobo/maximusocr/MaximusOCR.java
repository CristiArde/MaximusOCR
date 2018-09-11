package ai.bonobo.maximusocr;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    RecyclerView recyclerView;
    ItemsViewAdapter adapter;
    ImageButton recordBtn;
    Integer id = 0;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    List<ShoppingItem> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maximus_ocr);
        itemsList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recordBtn = (ImageButton)findViewById(R.id.recordBtn);

        //vertical recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
    //function to get the first assumption from the speech recognizer results.get(0) is usual the most accurate
    private String getFirst(Intent data){
        ArrayList<String> result = data
                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        if(result != null && result.size() > 0 )
            return result.get(0);

        return  null;
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

                    String recording = getFirst(data);
                    if(recording != null) {
                        String name="";
                        String price="";
                        for (int i =0; i<recording.length(); i++){
                            if(Character.isDigit(recording.charAt(i)))
                                price+=Character.toString(recording.charAt(i));
                            else if (Character.isLetter(recording.charAt(i)))
                                name+=Character.toString(recording.charAt(i));
                        }
                        itemsList.add(new ShoppingItem(id,name, Double.parseDouble(price)));
                        adapter = new ItemsViewAdapter(this,itemsList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
