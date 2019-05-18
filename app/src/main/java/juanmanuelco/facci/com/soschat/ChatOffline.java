package juanmanuelco.facci.com.soschat;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import juanmanuelco.facci.com.soschat.Adaptadores.AdaptadorChat;
import juanmanuelco.facci.com.soschat.DB.DB_SOSCHAT;
import juanmanuelco.facci.com.soschat.Entidades.Mensaje;
import juanmanuelco.facci.com.soschat.NEGOCIO.Mensajes;

public class ChatOffline extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private static final int TAKE_PHOTO = 2;
    private static final int RECORD_AUDIO = 3;
    private static final int RECORD_VIDEO = 4;
    private static final int CHOOSE_FILE = 5;
    private static final int DRAWING = 6;
    private static final int DOWNLOAD_IMAGE = 100;
    private static final int DELETE_MESSAGE = 101;
    private static final int DOWNLOAD_FILE = 102;
    private static final int COPY_TEXT = 103;
    private static final int SHARE_TEXT = 104;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_CONTENT_RESOLVER = 101;

    private EditText edit;
    private static ListView listView;
    private static List<Mensaje> listMensaje;
    private static AdaptadorChat adaptadorChat;
    private Uri fileUri;
    private String fileURL;
    private ArrayList<Uri> tmpFilesUri;
    SharedPreferences sharedPref;

    static DB_SOSCHAT db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_offline);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        db= new DB_SOSCHAT(this);
        final TextView netInfo = findViewById(R.id.velocidadInternet);
        netInfo.setText(Mensajes.datosSenal(0, 0, 0));

        //Initialize the adapter for the chat
        listView = (ListView) findViewById(R.id.messageList);
        listMensaje = new ArrayList<Mensaje>();


        adaptadorChat = new AdaptadorChat(this, listMensaje);
        listView.setAdapter(adaptadorChat);

        //Initialize the list of temporary files URI
        tmpFilesUri = new ArrayList<Uri>();
        Button button = (Button) findViewById(R.id.sendMessage);
        edit = (EditText) findViewById(R.id.editMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!edit.getText().toString().equals(""))
                    sendMessage(Mensaje.TEXT_MESSAGE);
                else
                    Toast.makeText(ChatOffline.this, R.string.mensaje_vacio, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void sendMessage(int tipo){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }
}
