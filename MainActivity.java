package com.dongyang.gg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS="15.164.98.47";
    private static String TAG="main";
        LinearLayout ctcategory;
        LinearLayout elecategory;
        LinearLayout sportscategory;
        LinearLayout culturecategory;
        LinearLayout petcategory;
        LinearLayout beautycategory;
        LinearLayout ftcategory;
        LinearLayout etccategory;
        LinearLayout bbcategory;
        SearchView searchKey;
        Button searchButton;
        String ctname;
        String c_id=null;




        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ctcategory = findViewById(R.id.ctcategory);
            elecategory = findViewById(R.id.electcategory);
            sportscategory = findViewById(R.id.sportscategory);
            culturecategory = findViewById(R.id.culturecategory);
            petcategory = findViewById(R.id.petcategory);
            beautycategory = findViewById(R.id.beautycategory);
            ftcategory = findViewById(R.id.ftcategory);
            etccategory = findViewById(R.id.etccategory);
            searchKey = findViewById(R.id.searchKey);
            searchButton = findViewById(R.id.searchButton);
            bbcategory = findViewById(R.id.babycategory);

            Intent intent=getIntent();
            c_id=intent.getStringExtra("c_id");

            BottomNavigationView BNV = findViewById(R.id.bottom_navigation);
            BNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.Category:
                            Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                            intent.putExtra("ctname", "all");
                            startActivity(intent);
                            return true;
                        case R.id.Chatting:
                            Intent intent1 = new Intent(getApplicationContext(), Chat_ListviewActivity.class);
                            startActivity(intent1);
                            return true;
                        case R.id.WishList:
                            Intent intent2 = new Intent(getApplicationContext(), WishListActivity.class);
                            startActivity(intent2);
                            return true;
                        case R.id.MyPage:
                            if(c_id!=null){
                                Intent intent3 = new Intent(getApplicationContext(), My_profileActivity.class);
                                startActivity(intent3);
                            }else{
                                Intent intent3 = new Intent(getApplicationContext(), Login_Activity.class);
                                startActivity(intent3);
                            }//조건문 완료
                            //Intent intent3 = new Intent(getApplicationContext(), My_profileActivity.class);


                            return true;
                        case R.id.Main:
                            Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent4);
                            return true;
                    }
                    return false;
                }
            });
            //문화 의류 가전 가구 게임 애완 스포츠 취미 육아
           // "가전", "스포츠", "애완", "육아", "게임", "의류", "취미", "기타"
            ctcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "의류");//수정해야함
                    startActivity(intent);
                }
            });
            elecategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                    intent.putExtra("ctname", "가전");//수정해야함
                    startActivity(intent);
                }
            });
            sportscategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "스포츠");//수정해야함
                    startActivity(intent);
                }
            });
            culturecategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "문화");//수정해야함
                    startActivity(intent);
                }
            });
            petcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "애완");//수정해야함
                    startActivity(intent);
                }
            });
            beautycategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "뷰티");//수정해야함
                    startActivity(intent);
                }
            });
            ftcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "가구");//수정해야함
                    startActivity(intent);
                }
            });
            bbcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "육아");//수정해야함
                    startActivity(intent);
                }
            });
            etccategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    intent.putExtra("ctname", "기타");//수정해야함
                    startActivity(intent);
                }
            });

//            searchButton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    String keyword = searchKey.getText().toString();
//
//                   InsertData task= new InsertData();
//                    task.execute("http://"+IP_ADDRESS+"/search.php", keyword);
//                    Intent intents = new Intent(getApplicationContext(), SearchListActivity.class);
//                    intents.putExtra("keyword", keyword);
//                    startActivity(intents);
//
//                }
//
//            });

            searchButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), MySItemListActivity.class);
                    startActivity(intent);
                }
            });
            searchKey.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/search.php", query);
                    Intent intents = new Intent(getApplicationContext(), SearchListActivity.class);
                    intents.putExtra("keyword", query);
                    startActivity(intents);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    class InsertData extends AsyncTask<String, Void, String> {//or intent로 keyword 넘겨서 출력
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog=ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response - "+result);
        }

        @Override
        protected String doInBackground(String... params){
            String keyword = (String)params[1];


            String serverURL=(String)params[0];
            String postparameters="&keyword="+keyword;

            try{
                URL url=new URL(serverURL);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream=httpURLConnection.getOutputStream();
                outputStream.write(postparameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode=httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code-"+responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode==HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }
                else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                StringBuilder sb=new StringBuilder();
                String line=null;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();
            }catch(Exception e ){
                Log.d(TAG, "InsertData: Error", e);

                return new String("Error:"+e.getMessage());
            }

        }
    }

    }




