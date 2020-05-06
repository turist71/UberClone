/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.apps.uberclone;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ldavila
 */
public class FacebookOrGoogleLoginForm extends Form{
    
    public FacebookOrGoogleLoginForm () {
        super (BoxLayout. y ());
        Form previous = getCurrentForm();
        getToolbar().setBackCommand("",Toolbar.BackCommandPolicy.AS_ARROW, e -> previous.showBack());
        add(new Label("Choose an account","FlagButton"));
        Resources r = Resources.getGlobalResources ();
        Button facebook = new Button( "Facebook" , r.getImage( "facebook.png"),"FlagButton");
        Button google = new Button( "Google" ,r.getImage( "google.png" ), "FlagButton" );
            add(facebook).add(google);
        }
    }
    
    

