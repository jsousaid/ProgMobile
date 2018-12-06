package br.fatec.projeto.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import br.fatec.projeto.R;

public class SucessoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucesso);

        getSupportActionBar().setTitle("Sucesso");

        Intent sucessoIntent = getIntent();

        String servicos = sucessoIntent.getStringExtra("servicos");
        String data = sucessoIntent.getStringExtra("data");
        String total = sucessoIntent.getStringExtra("total");
        String contentText = "Seu agendamento foi finalizado com sucesso no dia " + data + ".\n";
        contentText += "Os seguintes serviços foram adicionados: " + servicos + ".\n";
        contentText += "O valor total (sujeito a alterações) foi de R$" + total;

        TextView content = (TextView) findViewById(R.id.txt_content);
        content.setText(contentText);

        Button confirmaButton = (Button) findViewById(R.id.btn_confirma);
        confirmaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch notificacaoSwitch = (Switch) findViewById(R.id.switch_notifica);
                if (notificacaoSwitch.isChecked()) {
                    criarNotificacao("Agendamento Finalizado", "Em breve entraremos em contato em seu e-mail cadastrado para maiores informações.");
                }

                Intent listaIntent = new Intent(getApplicationContext(), ListaAgendamentosActivity.class);
                startActivity(listaIntent);
                finish();
            }
        });

    }

    private void criarNotificacao(String titulo, String texto) {

        // 01. Definir as propriedades da Notificação
        final int NOTIFICATION_ID = 123;
        final String CHANNEL_ID = "Notificação";

        // 02. Instanciar o gerenciador de notificações
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        // 03. Definir um Canal de Notificação para API >= 28
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, "canal", importance);
            canal.setDescription("Canal de Notificação");
            canal.enableLights(true);
            canal.setLightColor(Color.RED);
            canal.enableVibration(true);
            canal.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            canal.setShowBadge(true);
            notificationManager.createNotificationChannel(canal);
        }

        // 04. Especificar o ícone, o título e a mensagem da notificação
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(titulo)
                .setStyle(
                        new NotificationCompat.BigTextStyle().bigText(texto)
                )
                .setContentText(texto);

        // 05. Definir qual Atividade será chamada quando o usuário clicar na notificação
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MenuActivity.class);
        stackBuilder.addNextIntent(new Intent(this, MenuActivity.class));
        PendingIntent it = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(it);

        // 06. Exibir a notificação
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
