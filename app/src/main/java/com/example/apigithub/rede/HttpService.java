package com.example.apigithub.rede;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.apigithub.objetos.Items;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class HttpService extends AsyncTask {
    private ListenerResposta mResposta;

    public HttpService(@NonNull ListenerResposta resposta) {
        mResposta = resposta;
    }

    @Override
    protected ResponseEntity<Items> doInBackground(Object[] objects) {
        if (objects[0] instanceof String) {

            String url = (String) objects[0];
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<Items> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Items.class);

                return responseEntity;
            } catch (HttpClientErrorException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mResposta.respostaDados((ResponseEntity<Items>) o);
    }
}
