package com.example.penjadwalan.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.penjadwalan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {
    private static final int RESQUESCODE = 1;
    ImageView imageViewUser;
    static int PreqCode = 1;
    Uri pickedimgUri;

    private EditText userEmail, userpw, userpw2, username;
    private ProgressBar progressBar;
    private Button regbuttn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.RegMail);
        username = findViewById(R.id.RegNama);
        userpw = findViewById(R.id.RegPw);
        userpw2 = findViewById(R.id.RegConfirmpw);
        progressBar = findViewById(R.id.progressBar);
        regbuttn = findViewById(R.id.buttonReg);

        progressBar.setVisibility(View.INVISIBLE);
        regbuttn.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();


        imageViewUser = findViewById(R.id.RegUserimg);

        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=22){
                    checkAndRequestForPermission();
                }
                else {
                    openGallery();
                }
            }
        });


        regbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regbuttn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String nama = username.getText().toString();
                final String pw = userpw.getText().toString();
                final String pw2 = userpw2.getText().toString();

                if( email.isEmpty() || nama.isEmpty() || pw.isEmpty() || !pw.equals(pw2)){
                    // shwo error msg
                    showMessage("Please Verify all fields!");
                    regbuttn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }else {
                    CreateUserAcc(email,nama,pw);
                }

            }
        });

    }

    private void CreateUserAcc(String email, final String nama, String pw) {
        firebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //sukses create acc
                    showMessage("Regristrasi Berhasil");
                    //upgrade profile pict
                    updateUserInfo(nama ,pickedimgUri,firebaseAuth.getCurrentUser());
                }else{
                    showMessage("Regristrasi Gagal");
                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Toast.makeText(RegisterActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
//                    regbuttn.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    private void updateUserInfo(final String nama, Uri pickedimgUri, final FirebaseUser currentUser) {
        //update user photo and name
        //upload user foto to firebase and get url

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("user_photos");
        final StorageReference imagefilePath = storageReference.child(pickedimgUri.getLastPathSegment());
        imagefilePath.putFile(pickedimgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //img upload succes
                //can get photo url
                imagefilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //contain user image url

                        UserProfileChangeRequest profileupdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nama)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileupdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            //user info update sukses
                                            showMessage("Register Complete");
                                            updeteUI();
                                        }
                                    }
                                });
                    }
                });


            }
        });

    }

    private void updeteUI() {
        Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    //show toas msg
    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
    private void openGallery() {
        //TODO: open gallery intent for user to pick an image

        Intent galleryintent= new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,RESQUESCODE);

    }

    private void checkAndRequestForPermission() {
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(RegisterActivity.this, "Please Accept for required permission", Toast.LENGTH_SHORT).show();
            } else{
                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PreqCode);
            }
        }
        else {
            openGallery();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == RESQUESCODE && data != null){
            // users has succesfuly picked an image
            // save reerence to variable
            pickedimgUri = data.getData();
            imageViewUser.setImageURI(pickedimgUri);

        }
    }
    }

