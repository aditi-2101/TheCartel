package e.hkamatgi.project;

import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class sellbuy extends AppCompatActivity {
Button logout,sell,buy;
    private FirebaseAuth.AuthStateListener mAuthstateListener;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellbuy);
        logout = (Button) findViewById( R.id.logout);
        sell=findViewById(R.id.sellbutton);
       // buy=(Button)findViewById(R.id.buybutton);
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(
                        sellbuy.this,MainActivity.class);
                startActivity( i );
            }
        } );
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(sellbuy.this);
                builder.setCancelable(true);
                builder.setTitle("Sell item");
                builder.setMessage("write email to cartel with the details of product and a picture of the game");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Compose Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Intent sendmail=new Intent(Intent.ACTION_SENDTO);
                       sendmail.setData(Uri.parse("mailto:aditihk@gmail.com"));
                       startActivity(Intent.createChooser(sendmail,"send email via:"));
                       // startActivity(sendmail);
                    }
                });
                builder.show();
            }
        });
    }
}
