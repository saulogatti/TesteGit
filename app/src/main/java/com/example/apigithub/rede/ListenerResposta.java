package com.example.apigithub.rede;

import com.example.apigithub.objetos.Items;

import org.springframework.http.ResponseEntity;

public interface ListenerResposta {
    void respostaDados(ResponseEntity<Items> responseEntity);
}
