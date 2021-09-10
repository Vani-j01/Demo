package com.example.demo.helperclass;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Count {
    int type;
    int count;
    FirebaseAuth mAuth;

    public void add(){

    }

    public  void sub(){

    }

    public  void called(int t){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("Users").document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                        }
                    }
                });

        switch (t){
            case 1:
                add();
                break;
            case 2:
                sub();
                break;
        }

    }
}
