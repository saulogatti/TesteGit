package com.example.apigithub;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apigithub.objetos.Items;
import com.example.apigithub.rede.HttpService;
import com.example.apigithub.rede.ListenerResposta;

import org.springframework.http.ResponseEntity;

public class MainActivity extends AppCompatActivity implements ListenerResposta {

    private RecyclerView recyclerView;
    private ListaDados dados;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            recyclerView.setAdapter(null);
            HttpService httpService = new HttpService(this);
            httpService.execute("https://api.github.com/search/repositories?q=language:kotlin&sort=stars");
        });

        HttpService httpService = new HttpService(this);
        httpService.execute("https://api.github.com/search/repositories?q=language:kotlin&sort=stars");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void respostaDados(ResponseEntity<Items> responseEntity) {
        Log.d("Debug", "Dados " + responseEntity);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dados = new ListaDados(responseEntity.getBody());
        recyclerView.setAdapter(dados);
        dados.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);


    }
}