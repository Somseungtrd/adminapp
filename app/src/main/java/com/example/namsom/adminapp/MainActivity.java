package com.example.namsom.adminapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private Button signInButton;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private MyConstant myConstant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myConstant = new MyConstant();

        //Bind Widget
        signInButton = (Button) findViewById(R.id.btnplaceadd);
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);


        //Sign In Controller
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value from Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();


                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(MainActivity.this, R.drawable.bird48,
                            getResources().getString(R.string.title_HaveSpace),
                            getResources().getString(R.string.message_HaveSpace));
                    myAlert.myDialog();
                } else {
                    //No Space
                    SynUser synUser = new SynUser(MainActivity.this);
                    synUser.execute(myConstant.getUrlGetJSON());

                }


                    }   // onClick
                });



    }  //Main Method





    private class SynUser extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;
        private String[] nameStrings;
        private String truePassword;
        private boolean aBoolean = true;

        public SynUser(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("13novV2", "e doIn ==> " + e.toString());
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("13novV2", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];


                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString("AdName");

                    Log.d("13novV3", "name(" + i + ") ==> " + nameStrings[i]);


                    //Check User
                    if (userString.equals(jsonObject.getString("AdName"))) {
                        aBoolean = false;
                        truePassword = jsonObject.getString("AdPass");
                    }

                }   // for

                if (aBoolean) {
                    //User False
                    MyAlert myAlert = new MyAlert(context, R.drawable.kon48,
                            getResources().getString(R.string.title_UserFalse),
                            getResources().getString(R.string.message_UserFalse));
                    myAlert.myDialog();

                } else if (passwordString.equals(truePassword)) {
                    //Password True
                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();




                    //Intent to Service
                    Intent intent = new Intent(MainActivity.this, Center_Page.class);
                    startActivity(intent);
                    finish();


                } else {
                    //Password False
                    MyAlert myAlert = new MyAlert(context, R.drawable.rat48,
                            getResources().getString(R.string.title_PasswordFalse),
                            getResources().getString(R.string.message_PasswordFalse));
                    myAlert.myDialog();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // SynUser







} //Main class
