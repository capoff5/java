package com.dongyang.gg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemRegist extends AppCompatActivity {
    private static String IP_ADDRESS="15.164.98.47";
    private static String TAG="phptest";

    private EditText pic;
    private EditText name;
    private EditText stprice;
    private EditText price;
    private Spinner type;
    private RadioGroup state;
    private Button registBtn;
    private EditText intro;
    private String sstate;
    private  TextView mTextViewResult;
    String iitype;
    String[] items = {"가전", "스포츠", "애완", "육아", "게임", "의류","뷰티",
        "문화", "기타"};

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_regist);

        imageview = (ImageView)findViewById(R.id.image_insert);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });



        pic=(EditText)findViewById(R.id.pic);
        name=(EditText)findViewById(R.id.i_name);
        stprice=(EditText)findViewById(R.id.i_stprice);
        price=(EditText)findViewById(R.id.i_price);
        type=(Spinner)findViewById(R.id.i_type);
        state=(RadioGroup) findViewById(R.id.radioGroup);
        intro=(EditText)findViewById(R.id.i_intro);
        registBtn=(Button) findViewById(R.id.registButton);
        mTextViewResult=(TextView)findViewById(R.id.textView_main_result);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iitype=items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mTextViewResult=(TextView)findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.i_state1:
                        sstate="신품";
                        break;
                    case R.id.i_state2:
                        sstate="상";
                        break;
                    case R.id.i_state3:
                        sstate="중";
                        break;
                    case R.id.i_state4:
                        sstate="하";
                        break;
                }
            }
        });


        registBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //String ipic = imageview.getImageUri();
                String ipic = pic.getText().toString();
                String iname=name.getText().toString();
                String istprice=stprice.getText().toString();
                String iprice=price.getText().toString();
                String itype=type.getSelectedItem().toString();
                String istate=sstate;
                String iintro=intro.getText().toString();
                String iid="leso";

               InsertData task= new InsertData();
                task.execute("http://"+IP_ADDRESS+"/insert_i.php", ipic, iname, iid, istprice, iprice, itype, istate, iintro);

                pic.setText("");
                name.setText("");
                stprice.setText("");
                price.setText("");
                intro.setText("");

                Log.d(TAG, "testdata - "+ipic+iname+ iid+ istprice+ iprice+ itype+ istate+ iintro);

            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
            //ItemListActivity.IMAGE_URI = selectedImageUri;
        }
    }


    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog=ProgressDialog.show(ItemRegist.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response - rrr "+result);


                mTextViewResult.setText(errorString);
                Log.d(TAG, "error message - "+errorString);

        }

        @Override
        protected String doInBackground(String... params){
            String ipic = (String)params[1];
            String iname=(String)params[2];
            String iid=(String)params[3];
            String istprice=(String)params[4];
            String iprice=(String)params[5];
            String itype=(String)params[6];
            String istate=(String)params[7];
            String iintro=(String)params[8];

            String serverURL=(String)params[0];
            String postparameters="&ipic="+ipic+"&iname="+iname+"&iid"+iid+"&istprice="+istprice+"&iprice="+iprice
                    +"&itype="+itype+"&istate"+istate+"&iintro"+iintro;

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
                Log.d(TAG, "POST response code-bbb"+responseStatusCode);

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
                errorString = e.toString();

                return new String("Error:"+e.getMessage());
            }

        }
    }
}

