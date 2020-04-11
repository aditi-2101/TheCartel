package e.hkamatgi.project;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.NonNull;

public class activity_login extends AppCompatActivity {
    EditText email2,password2;
    Button login;
    TextView register,forgotpwd;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();

        email2=findViewById(R.id.email);
        password2=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.clktoreg);
        forgotpwd=findViewById(R.id.forgotpwd);
        mAuthStateListner=new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(activity_login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent go=new Intent(activity_login.this,sellbuy.class);
                    startActivity(go);
                }
                else
                {
                    Toast.makeText(activity_login.this, "please log in", Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= email2.getText().toString();
                String pwd=password2.getText().toString();
                if (email.isEmpty()) {
                    email2.setError("enter email id");
                    email2.requestFocus();
                }
                if(pwd.isEmpty())
                {
                    password2.setError("enter password");
                    password2.requestFocus();
                }
                else if((email.isEmpty()&&pwd.isEmpty()))
                {
                    Toast.makeText(activity_login.this, "enter details", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&&pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText( activity_login.this,
                                        "SignUp Unsuccessful, Please try again!!!", Toast.LENGTH_LONG
                                ).show();
                            } else {
                                startActivity( new Intent(
                                        activity_login.this, sellbuy.class ) );
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText( activity_login.this, "Error Occurred!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( activity_login.this,
                        sellbuy.class );
                startActivity( i );
            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(activity_login.this,forgotpwd.class);
                startActivity(i2);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
}
