package com.example.aula07_08_09_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificacaoActivity extends AppCompatActivity {

    Button btnNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        btnNotificacao = findViewById(R.id.btnNotificacao);

        btnNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarNotificacao();
            }
        });
    }

    private void criarNotificacao() {
        final String NOME_CANAL = "meu_canal_01";

        //pegando a instancia da central de notificacoes do android
        NotificationManager centralNotificacao =
            (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //verifica se a versao do android do celular é maior ou igual a 26 (O)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //criando o canal da notificacao
            NotificationChannel canalNotificacao =
                new NotificationChannel(NOME_CANAL, "Notificação",
                NotificationManager.IMPORTANCE_HIGH);

            canalNotificacao.setDescription("Descrição do canal");
            canalNotificacao.enableLights(true); //habilitando o led do celular
            canalNotificacao.setLightColor(Color.BLUE); //usando a cor AZUL para o LED
            canalNotificacao.enableVibration(true); //habilitando o vibra do celular
            canalNotificacao.setVibrationPattern(new long[]{0, 1000, 500, 1000}); //padrao de vibrar da notif

            //adicionando o canal criado na central de notificacao
            centralNotificacao.createNotificationChannel(canalNotificacao);
        }

        //criando a notificacao
        NotificationCompat.Builder notificacao =
            new NotificationCompat.Builder(this, NOME_CANAL);

        //autocancel -> ao clicar na notificacao,
        //              exclui a notificacao da central de notif.
        notificacao.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis()) //seta a hora que vai aparecer (agora)
            .setSmallIcon(R.mipmap.ic_launcher) //adiciona o icone do App na notif
            .setTicker("Notificação") //texto do ticker da notificacao
            .setContentTitle("Titulo da  Notificação") //adiciona o titulo da notif
            .setContentText("Texto que vai apararecer na notificação.") //texto da notif
            .setContentInfo("Info da notificação"); //info da notif

        //exibindo a notificacao criada na central de notificacoes do android
        centralNotificacao.notify(1, notificacao.build());
    }
}