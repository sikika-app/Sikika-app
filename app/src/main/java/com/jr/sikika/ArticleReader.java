package com.jr.sikika;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ArticleReader extends AppCompatActivity {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.imgPic)
    ImageView imgPic;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.articleLoadingSplash)
    LinearLayout loadingSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_reader);
        ButterKnife.bind(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent read = getIntent();
        String url = read.getStringExtra("url");
        String imgUrl = read.getStringExtra("imgUrl");

        loadData(url, imgUrl);
    }

    public void loadData(final String url, final String imgUrl){

        AsyncHttpClient httpClient = new AsyncHttpClient();

        httpClient.get(ArticleReader.this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(ArticleReader.this, "reload page", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                List<String> info = new ArrayList<>();
                Document document = Jsoup.parse(responseString);
                Element element = document.selectFirst("article");

                Element body = element.selectFirst("div[class=et_pb_module  et_pb_text et_pb_text_2 et_pb_bg_layout_light  et_pb_text_align_left]");
                Elements details = body.select("div[class=et_pb_text_inner] strong");
                for(int x = 0; x<=1; x++){
                    String t;
                    try{
                        t = details.eq(x).html().split(":")[1].trim();
                    }catch (Exception e){
                        t = "unknown";
                    }
                    info.add(t);
                }

                String datePublished = element.selectFirst("span[class=published]").html();

                String title = "";

                try{

                    title = document.selectFirst("h1").text();

                }catch (Exception e){

                    title = document.title();
                }



                txtTitle.setText(title);
                Glide.with(ArticleReader.this).load(imgUrl).into(imgPic);

                String content = "";

                Elements contents = document.select("div[class=et_pb_text_inner] p");

                if(contents.eq(0).text().trim().indexOf("Author") != -1 || contents.eq(1).text().trim().indexOf("Newsletter") != -1){

                    for(int x = 2; x<contents.size(); x++){
                        if(content == ""){
                            content = contents.eq(x).text();
                        }else{
                            content = content +"\n\n"+ contents.eq(x).text();
                        }
                    }
                }else{

                    for (Element x: contents) {
                        if(content == ""){
                            content = x.text();
                        }else{
                            content = content +"\n\n"+x.text();
                        }
                    }
                }


                if(content.isEmpty()){
                    content = "The article you wish to read cannot be properly parsed. select any of the browsers below to read article:";
                    txtContent.setTextSize(20f);
                    Uri uri = Uri.parse(url);
                    Intent x = Intent.createChooser(new Intent(Intent.ACTION_VIEW, uri), "open link externally");
                    startActivity(x);
                    finish();
                }

                txtContent.setText(content);
                loadingSplash.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);


            }
        });

    }

}
