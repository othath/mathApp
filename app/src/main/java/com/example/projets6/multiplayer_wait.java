package com.example.projets6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class multiplayer_wait extends AppCompatActivity {
    LottieAnimationView lottie;
    DatabaseReference gamesRef;
    String adversaire;

    int idGame;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_wait);
        lottie = findViewById(R.id.lottie);

        lottie.playAnimation();
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        idGame = prefs.getInt("idGame", 0);

        gamesRef = FirebaseDatabase.getInstance().getReference("games");


        gamesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    adversaire = prefs.getString("adversaire", "UNKNOWN");
                    String player1 = snapshot.child(idGame + "/player1").getValue(String.class);


                    if (player1.equals(adversaire)){
                        gamesRef.child(idGame + "/playerend2").setValue(true);
                        boolean playerend1 = snapshot.child(idGame + "/playerend1").getValue(Boolean.class);
                        if(playerend1 == true){
                            goscore();
                            gamesRef.child(String.valueOf(idGame)).removeValue();
                            gamesRef.removeEventListener(this);

                        }
                    }else {
                        gamesRef.child(idGame + "/playerend1").setValue(true);
                        boolean playerend2 = snapshot.child(idGame + "/playerend2").getValue(Boolean.class);
                        if(playerend2 == true) {
                            goscore();
                            gamesRef.child(String.valueOf(idGame)).removeValue();
                            gamesRef.removeEventListener(this);

                        }
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        }
    public void goscore(){
        Intent intent = new Intent(this, activity_winFox.class);
        startActivity(intent);

    }
}