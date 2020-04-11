 package e.hkamatgi.project;

 import android.content.Intent;
 import android.os.Bundle;
 //import android.support.v7.app.AppCompatActivity;
 import androidx.appcompat.app.AppCompatActivity;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.AuthResult;
 import com.google.firebase.auth.FirebaseAuth;

 import androidx.annotation.NonNull;

 public class MainActivity extends AppCompatActivity {
    EditText email1,pwd1;
    Button reg;
    TextView clktolog;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth=FirebaseAuth.getInstance();
        email1=findViewById(R.id.emailreg);
        pwd1=findViewById(R.id.pwdreg);
        reg=findViewById(R.id.register);
        clktolog=findViewById(R.id.login);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString();
                String pwd = pwd1.getText().toString();

                if (email.isEmpty()) {
                    email1.setError("enter email id");
                    email1.requestFocus();

                }else if(pwd.isEmpty())
                {
                    pwd1.setError("enter password");
                    pwd1.requestFocus();
                }
                else if((email.isEmpty()&&pwd.isEmpty()))
                {
                    Toast.makeText(MainActivity.this, "fill in the fields", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&&pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "signup unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(MainActivity.this,sellbuy.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "An error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clktolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,activity_login.class);
                startActivity(i);
            }
        });
    }
}
