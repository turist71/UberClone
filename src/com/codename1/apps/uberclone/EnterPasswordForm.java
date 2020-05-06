/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.apps.uberclone;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ldavila
 */
public class EnterPasswordForm extends Form {
    
    public EnterPasswordForm () {
        
	super (new BorderLayout());
        Form previous = getCurrentForm();
        getToolbar().setBackCommand ("" ,Toolbar.BackCommandPolicy.AS_ARROW, e -> previous.showBack ());
        Container box = new Container(BoxLayout. y ());
        box.setScrollableY ( true );
    
        box.add (new SpanLabel( "Welcome back, signin to continue" ,"FlagButton"));
        TextField password = new TextField( "" , "Enter your password",80,TextField.PASSWORD);
        setEditOnShow(password);
        box.add (password);
        SpanLabel error = new SpanLabel( "Password error" , "ErrorLabel" );
        error.setVisible(false);
        box.add(error);
        add(CENTER,box);
        Button forgot = new Button("I forgot my password","ForgotPassword");
        Button account = new Button( "I don't have an account" ,"ForgotPassword" );
        add(SOUTH, BoxLayout.encloseY(forgot,account));
        FloatingActionButton fab = FloatingActionButton.createFAB (
        FontImage. MATERIAL_ARROW_FORWARD);
        fab.bindFabToContainer(this);
        fab.addActionListener (e -> { /*new MapForm().show();*/});
}

}
