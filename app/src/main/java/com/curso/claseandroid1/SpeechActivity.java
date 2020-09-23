package com.curso.claseandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements RecognitionListener, View.OnClickListener {

    private SpeechRecognizer speechRecognizer;
    private Intent speechIntent;

    private Button bSpeech;
    private TextView textViewSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        bSpeech = findViewById(R.id.buttonSpeech);
        textViewSpeech = findViewById(R.id.txvSpeech);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);

        speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 60000);
        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,60000);

        bSpeech.setOnClickListener(this);

    }


    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        Log.d("Final", "Finalizo Speech");
        speechRecognizer.stopListening();
    }

    @Override
    public void onError(int error) {
        Log.d("Error", "Error");

        speechRecognizer.startListening(speechIntent);
        textViewSpeech.setText("Stop");

    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> listaResultados = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String resultado = listaResultados.get(0);
        Log.d("Resultado", resultado);
        textViewSpeech.setText(resultado);

        speechRecognizer.startListening(speechIntent);



    }

    @Override
    public void onPartialResults(Bundle partialResults) {

        ArrayList<String> listaResultados = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String resultadoParcial = listaResultados.get(0);
        Log.d("Resultado Parcial", resultadoParcial);

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.buttonSpeech:
                speechRecognizer.startListening(speechIntent);
                break;
        }




    }
}