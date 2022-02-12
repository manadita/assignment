package edu.neu.madcourse.numad21fa_yuzou;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class CreateLinkDialog extends Dialog {
    Activity context;
    private Button create;
    public EditText text_name;
    public EditText text_url;
    private View.OnClickListener myListener;



    public CreateLinkDialog(Activity context){
        super(context);
        this.context = context;
    }

    public CreateLinkDialog(Activity context, int theme, View.OnClickListener listener){
        super(context, theme);
        this.context = context;
        this.myListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.create_link_dialog);
        text_name = (EditText) findViewById(R.id.editText_name);
        text_url = (EditText) findViewById(R.id.editText_url);

        Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (d.getWidth() * 0.8);
        p.height = (int) (d.getHeight() * 0.6);
        dialogWindow.setAttributes(p);

    }

}
