package com.example.aula07_08_09_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    //constantes usadas no slider
    final int VALOR_MINIMO_SLIDER = 0;
    final int VALOR_MAXIMO_SLIDER = 100;

    //objetos/componentes do java
    ImageView imgLogo;
    SeekBar sldTransparencia;
    Switch btnOnOff;
    Button btnAbrirTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciando os objetos da tela (XML) com o do Java
        imgLogo = findViewById(R.id.imgLogo);
        sldTransparencia = findViewById(R.id.sldTransparencia);
        btnOnOff = findViewById(R.id.btnOnOff);
        btnAbrirTela = findViewById(R.id.btnAbrirTela);

        //adiciona um valor maximo para o slider (100)
        sldTransparencia.setMax(VALOR_MAXIMO_SLIDER);

        btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ao clicar no botao de on_off, verifica se esta ativo
                if (btnOnOff.isChecked()) {
                    //se estiver ativo, deixa a imagem 100% visivel e atualiza o slider
                    imgLogo.setAlpha(1f);
                    sldTransparencia.setProgress(sldTransparencia.getMax());
                }
                else {
                    //se estiver inativo, deixa a imagem 0% visivel e atualiza o slider
                    imgLogo.setAlpha(0f);
                    sldTransparencia.setProgress(VALOR_MINIMO_SLIDER);
                }
            }
        });

        sldTransparencia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //evento executado a cada alteracao do valor do slider
                imgLogo.setAlpha((float) progress / VALOR_MAXIMO_SLIDER);

                /*
                if (progress > VALOR_MINIMO_SLIDER)
                    btnOnOff.setChecked(true);
                else
                    btnOnOff.setChecked(false);
                */

                //ativa o botao de on_off se o slider tiver algum valor
                btnOnOff.setChecked(progress > VALOR_MINIMO_SLIDER);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //evento executado ao iniciar a acao do slider
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //evento executado ao finalizar a acao do slider
            }
        });

        btnAbrirTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criando o caminho para a segunda tela e ja abrindo ele
                startActivity(new Intent(MainActivity.this,
                        NotificacaoActivity.class));
            }
        });
    }
}