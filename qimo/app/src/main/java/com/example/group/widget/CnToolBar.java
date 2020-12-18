package com.example.group.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;

import com.example.group.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CnToolBar extends Toolbar {

    private LayoutInflater mInflater;
    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private ImageButton mRightImageButton;


    public CnToolBar(Context context) {
        this(context,null);
    }

    public CnToolBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CnToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CnToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


    initView();

    }

    private void initView(){

        View mView = mInflater.inflate(R.layout.toolbar,null);

        mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
        mRightImageButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);


        addView(mView);
    }
     @Override
     public void setTitle(int resId)
     {
         setTitle(getContext().getText(resId));

     }
    @Override
    public void setTitle(CharSequence title) {
        if(mTextTitle!=null)
            mTextTitle.setText(title);

    }

}
