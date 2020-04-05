package com.example.Fabulous.UI.Activities.ActualiteActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import com.bumptech.glide.Glide;
import com.example.Fabulous.Models.ActualiteDetails;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;

import com.example.Fabulous.UI.Fragments.Main.Actualites.List.ActualiteViewModel;
import com.example.Fabulous.UI.Fragments.Main.Actualites.NewPost.SpinnerDialogFragment;
import com.example.Fabulous.UI.Fragments.Resource;
import com.example.Fabulous.Utils.Parser.ProfileDetailsParser;
import com.example.Fabulous.Utils.util;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonElement;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import io.paperdb.Paper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observer;

import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


public class NewActualitePostActivity extends DaggerAppCompatActivity implements SpinnerDialogFragment.SpinnerDialogListener {
@BindView(R.id.mainimg)
    ImageView mainimg;

    @BindView(R.id.spinner_text)
    TextView spinner_text;
    @BindView(R.id.post_description)
    TextView post_description;
    @BindView(R.id.succ_sheet)
    LinearLayout succlayoutBottomSheet;
    @BindView(R.id.publish)
    Button publish;
    @BindView(R.id.new_post_progress)
    ProgressBar progress;
    @BindView(R.id.remove_img)
    TextView remove_img;
    @BindView(R.id.fail_sheet)
    LinearLayout faillayoutBottomSheet;
    BottomSheetBehavior sucessbottomSheetBehavior,failbottomSheetBehavior;
    String selected_image_path="";
    rx.Subscription subscription;
    String spinner_id="0";
    private ImageView imageview;
    private Button btnSelectImage;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 201;
    private NewActualitePostViewModel viewModel;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_actualite_post_layout);
        ButterKnife.bind(this);

        Paper.init(this);
        viewModel = new ViewModelProvider(this,providerFactory).get(NewActualitePostViewModel.class);
        sucessbottomSheetBehavior = BottomSheetBehavior.from(succlayoutBottomSheet);
        failbottomSheetBehavior = BottomSheetBehavior.from(faillayoutBottomSheet);
        SubscribeOberservers();




}

@OnClick(R.id.remove_img)
public void closeimage(){
    remove_img.setVisibility(View.GONE);
    mainimg.setImageDrawable(null);
    selected_image_path="";



    }
    @OnClick(R.id.choose_picture)
    public void ChoosePicture(){

        selectImage();
    }


@OnClick(R.id.spinner)
public void Spinnerclick(){
    SpinnerDialogFragment spinnerDialogFragment = SpinnerDialogFragment.newInstance();
    spinnerDialogFragment.show(getSupportFragmentManager(), "add_spinner_dialog_fragment");
}
    private void successfulLogin() {
        publish.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        util.showSheet(sucessbottomSheetBehavior);

    }
    private void showError(String call) {

        if(call.equals("network error")) {
            publish.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            util.showSheet(failbottomSheetBehavior);
        }
    }
    /**
     * Posts the image with the Json.
     * */

    /*private void PublishPost(String authorisation,MultipartBody.Part body, RequestBody data){
        publish.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        viewModel.AddNewPost(authorisation,body,data).removeObservers(this);
        viewModel.AddNewPost(authorisation,body,data).observe(this, new androidx.lifecycle.Observer<Resource<DefaultResponse>>() {
            @Override
            public void onChanged(Resource<DefaultResponse> postPostsResource) {
                if(postPostsResource != null){
                    switch(postPostsResource.status){
                        case DATARECEIVED:{
                            Log.d(TAG, "onChanged: " +postPostsResource.data.getSuccess());
                            successfulLogin();
                            break;
                        }
                        case ERROR:{

                            Log.d(TAG, "onChanged:  Error ! " + postPostsResource.message);
                            showError("network error");
                            break;

                        }case LOADING:{
                            Log.d(TAG, "onChanged:  Loading data ...");
                            break;
                        }
                        case NOT_AUTHENTICATED:
                        {
                            break;
                        }
                    }
                }
            }
        });
    }
*/
    private void SubscribeOberservers(){


        viewModel.observePublishTrigger().removeObservers(this);
        viewModel.observePublishTrigger().observe(this, new androidx.lifecycle.Observer<NewActualitePostResource<Response<Void>>>() {
            @Override
            public void onChanged(NewActualitePostResource< Response<Void>> postPostsResource) {
                if(postPostsResource != null){
                    switch(postPostsResource.status){
                        case DATARECEIVED:{
                            Log.d(TAG, "onChanged: " +postPostsResource.data);
                            successfulLogin();
                            break;
                        }
                        case ERROR:{

                            Log.d(TAG, "onChanged:  Error ! " + postPostsResource.message);
                            showError("network error");
                            break;

                        }case LOADING:{
                            Log.d(TAG, "onChanged mta3SubObserve:  Loading data ...");
                            break;
                        }

                    }
                }
            }
        });
    }



    /*public void LoadActualites(String authorisation,MultipartBody.Part body, RequestBody data) {
        publish.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
      ActualiteNewPostClient.getInstance().NewActualitesPost(authorisation,body,data)
             .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody actualiteResponseModel) {
                        Log.d(TAG, "onNext: "+String.valueOf(actualiteResponseModel));

//                                successfulLogin(actualiteResponseModel);


                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                   //     showError("network error");
                        Log.e("Login", e.getMessage(), e);
                        //       hideProgress();
                    }


                });

    }*/
@OnClick(R.id.close)
public void Close(){
        finish();
}

    @OnClick(R.id.publish)
    public void Publish(){
if(!spinner_id.equals("0")&&!selected_image_path.equals("")){


        String jsonData = transformToJson(spinner_id,post_description.getText().toString());
        ProfileDetails element= Paper.book().read("loggedmodel");

        File file = new File(selected_image_path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("postattachment", file.getName(), requestFile);
        RequestBody data =
                RequestBody.create(MediaType.parse("multipart/form-data"), jsonData);
   //LoadActualites(profileDetails.token.id,body,data);
viewModel.PassingData(element.token.id,body,data);
System.out.println("Image Path : "+selected_image_path+" ,  Data : " +jsonData+" , Authorization : "+element.token.id );

}
     //
    }




    /**
     * Transforms our training id and our description into a json format compatible with the fabulous API.
     * */
public String transformToJson(String id,String desription){
        return "{\"trainingId\":"+id+",\"description\":\""+desription+"\"}";
}
/**
 * OnActivity Results handles the image selection with our request code.
 * */
    /*@Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            Log.d("Test ", "onActivityResult: "+image.getPath());
            remove_img.setVisibility(View.VISIBLE);
            selected_image_path = image.getPath();
            Glide
                    .with(this)
                    .load(image.getPath())
                    .into(mainimg);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    /**
     * ApplyText is used to apply the spinner text selected from our BottomSheetDialogFragment.
     * */
    @Override
    public void ApplyText(String chosenitem,String chosenid) {
spinner_text.setText(chosenitem);
        spinner_id = chosenid;
    }

    // Select image from camera and gallery
    private void selectImage() {
        try {
     //       PackageManager pm = getPackageManager();
          //  int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED)  {

                if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                final CharSequence[] options = {"Prendre une photo", "Choisir de la bibliothéque","Annuler"};
                AlertDialog.Builder builder = new AlertDialog.Builder(NewActualitePostActivity.this);
                builder.setTitle("");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Prendre une photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choisir de la bibliothéque")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Annuler")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                } else
                    requestStoragePermission();
                //   Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();

        } else{
                requestPhonePermission();
                //   Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
             Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);



                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());

                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                         getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                destination.getParentFile().mkdirs();
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide
                        .with(this)
                        .load(destination)
                        .into(mainimg);
                imgPath = destination.getAbsolutePath();
                remove_img.setVisibility(View.VISIBLE);
                selected_image_path =imgPath;
                Glide
                        .with(this)
                        .load(bitmap)
                        .into(mainimg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                remove_img.setVisibility(View.VISIBLE);
                selected_image_path =imgPath;

                destination = new File(imgPath.toString());
                Glide
                        .with(this)
                        .load(destination)
                        .into(mainimg);
            //    imageview.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void requestPhonePermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);

    }
    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this,new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                STORAGE_REQUEST_CODE);
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(NewActualitePostActivity.this)
                .setMessage(message)
                .setPositiveButton("Ok", okListener)
                .setNegativeButton("Annuler", null)
                .create()
                .show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  //  Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    selectImage();
                } else {
                //    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("Vous devez accepter les autorisations d'accès",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPhonePermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //  Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    selectImage();
                } else {
              //      Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("Vous devez accepter les autorisations d'accès",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestStoragePermission();
                                            }
                                        }
                                    });
                        }
                    }
                }


        }
    }

}