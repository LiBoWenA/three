package com.example.tiamo.three.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.tiamo.three.R;
import com.example.tiamo.three.bean.Chuandi;

import org.greenrobot.eventbus.EventBus;

public class HeadView extends LinearLayout {

    Context context;
    EditText editText;
    Button button;
    private String name;

    public HeadView(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public HeadView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.headview,null);
        editText = view.findViewById(R.id.ed_text);
        button = view.findViewById(R.id.b_qh);
        addView(view);


        name = editText.getText().toString();
        EventBus.getDefault().postSticky(new Chuandi(name,"shop_name"));


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null){
                    onClick.Click();
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = String.valueOf(s);
                if(!s1.equals("")){
                    Chuandi chuandi = new Chuandi(s1,"shop_name");
                    EventBus.getDefault().postSticky(chuandi);
                    if(onitemClick!=null){
                        onitemClick.Click(s1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    getEdText getEdText;

    public void setGetEdText(getEdText edText){
        getEdText = edText;
    }

    public interface getEdText{
        void getName(String name);
    }

    OnClick onClick;

    public void setOnClick(OnClick click){
        onClick = click;
    }

    public interface OnClick{
        void Click();
    }
    OnItemsClick onitemClick;

    public void setOnItemClick(OnItemsClick click){
        onitemClick = click;
    }

    public interface OnItemsClick{
        void Click(String s);
    }
}
