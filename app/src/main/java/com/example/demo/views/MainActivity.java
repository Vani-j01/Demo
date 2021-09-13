package com.example.demo.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.adapters.ExclusiveAdapter;
import com.example.demo.adapters.GroceriesAdapter;
import com.example.demo.adapters.SliderAdapter;
import com.example.demo.model.ProductsDataModel;
import com.example.demo.model.SlideshowDataModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SliderAdapter adapter;
    private ArrayList<SlideshowDataModel> slideshowDataModelArrayList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private SliderView sliderView;


    private RecyclerView recyclerView;
    private  ArrayList<ProductsDataModel> productsDataModelArrayList;
    private ExclusiveAdapter exclusiveAdapter;

    private  RecyclerView groceriesRecyclerView;
    private  ArrayList<ProductsDataModel> groceriesList;
    private GroceriesAdapter groceriesAdapter;

    private RecyclerView bestSellingRecyclerView;
    private  ArrayList<ProductsDataModel> bestSellingArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth= FirebaseAuth.getInstance();
        SignIn();

        //Slideshow
        slideshowDataModelArrayList= new ArrayList<>();
        sliderView = findViewById(R.id.slideshow);
        firebaseFirestore= FirebaseFirestore.getInstance();
        loadImages();

        //Exclusive
        productsDataModelArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.exclusiveOffer);
        recyclerView.setHasFixedSize(true);
        loadRecyclerViewData();

        //Groceries
        groceriesList = new ArrayList<>();
        groceriesRecyclerView= findViewById(R.id.groceries);
        groceriesRecyclerView.setHasFixedSize(true);
        loadGroceries();

        //Best Selling
        bestSellingRecyclerView= findViewById(R.id.bestSell);
        bestSellingArrayList= new ArrayList<>();
        loadBestSelling();





    }

    private void SignIn() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Signed In.",Toast.LENGTH_LONG).show();

                        assert user != null;
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void loadBestSelling() {
        firebaseFirestore.collection("Products").whereEqualTo("tag","best selling").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.e("TAG", "onSuccess: " );
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list){
                                ProductsDataModel dataModel = d.toObject(ProductsDataModel.class);
                                dataModel.setId(d.getId());
                                Log.e("TAG,", "onSuccess: "+ d.getId() );
                                bestSellingArrayList.add(dataModel);
                                Log.e("TAG", "onSuccess: "+dataModel );

                            }
                            exclusiveAdapter = new ExclusiveAdapter(bestSellingArrayList, MainActivity.this);
                            bestSellingRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                            bestSellingRecyclerView.setAdapter(exclusiveAdapter);
                            exclusiveAdapter.notifyDataSetChanged();
                        }
                        else {
                            Log.e("TAG", "onSuccess: Data not found" );
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: " );
            }
        });
    }




    private void loadRecyclerViewData() {
        firebaseFirestore.collection("Products").whereEqualTo("tag","exclusive offer").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.e("TAG", "onSuccess: " );
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list){
                                ProductsDataModel dataModel = d.toObject(ProductsDataModel.class);
                                dataModel.setId(d.getId());
                                Log.e("TAG,", "onSuccess: "+ d.getId() );
                                productsDataModelArrayList.add(dataModel);
                                Log.e("TAG", "onSuccess: "+dataModel );

                            }
                            exclusiveAdapter = new ExclusiveAdapter(productsDataModelArrayList, MainActivity.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                            recyclerView.setAdapter(exclusiveAdapter);
                            exclusiveAdapter.notifyDataSetChanged();
                        }
                        else {
                            Log.e("TAG", "onSuccess: Data not found" );
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: " );
            }
        });
    }



    private void loadGroceries() {
        firebaseFirestore.collection("Products").whereEqualTo("category","groceries").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.e("TAG", "onSuccess: " );
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list){
                                ProductsDataModel dataModel = d.toObject(ProductsDataModel.class);
                                Log.e("TAG,", "onSuccess: "+ d.getId() );
                                groceriesList.add(dataModel);
                                Log.e("TAG", "onSuccess: "+dataModel );

                            }
                            groceriesAdapter = new GroceriesAdapter(groceriesList, MainActivity.this);
                            groceriesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                            groceriesRecyclerView.setAdapter(groceriesAdapter);
                            groceriesAdapter.notifyDataSetChanged();
                        }
                        else {
                            Log.e("TAG", "onSuccess: Data not found" );
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: " );
            }
        });
    }




    private void loadImages() {

        firebaseFirestore.collection("Slideshow").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    SlideshowDataModel slideshowDataModel = documentSnapshot.toObject(SlideshowDataModel.class);
                    SlideshowDataModel dataModel= new SlideshowDataModel();

                    dataModel.setImage(slideshowDataModel.getImage());
                    slideshowDataModelArrayList.add(dataModel);

                    adapter= new SliderAdapter(MainActivity.this, slideshowDataModelArrayList);

                    sliderView.setSliderAdapter(adapter);
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: "+e.toString() );
            }
        });
    }



}