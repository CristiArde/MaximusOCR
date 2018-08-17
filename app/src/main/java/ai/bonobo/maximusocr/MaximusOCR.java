package ai.bonobo.maximusocr;

import ai.bonobo.maximusocr.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MaximusOCR extends AppCompatActivity {

    EditText totalTxt;
    ListView listView;
    ImageButton recordBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maximus_ocr);

        totalTxt = (EditText)findViewById(R.id.totalTxt);
        listView = (ListView)findViewById(R.id.listView);
        recordBtn = (ImageButton)findViewById(R.id.recordBtn);
    }
}
