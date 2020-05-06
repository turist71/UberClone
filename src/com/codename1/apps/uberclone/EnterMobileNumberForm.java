/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.apps.uberclone;


import com.codename1.components.FloatingActionButton;
import static com.codename1.ui.CN.*;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;


/**
 *
 * @author ldavila
 */
public class EnterMobileNumberForm extends Form{
  
public   EnterMobileNumberForm() {  
   super (BoxLayout.y());
Form previous = getCurrentForm();
getToolbar().setBackCommand("" ,Toolbar.BackCommandPolicy.AS_ARROW, e -> previous. showBack ()); add( new Label( "Enter your mobile number" , "FlagButton" ));
CountryCodePicker countryCodeButton = new CountryCodePicker();
TextField phoneNumber = new TextField( "", "050-123-4567", 40, TextField. PHONENUMBER ); 
add(BorderLayout. centerEastWest (phoneNumber, null ,countryCodeButton)); 
Style ps = phoneNumber. getUnselectedStyle();
Style cs = countryCodeButton. getUnselectedStyle();
int pl = cs.getPaddingLeft(isRTL());
int pr = cs.getPaddingRight(isRTL());
countryCodeButton.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
countryCodeButton.getAllStyles().setPadding(ps.getPaddingTop(),ps.getPaddingBottom(),pl,pr);
setEditOnShow(phoneNumber); 
FloatingActionButton fab = FloatingActionButton.createFAB(FontImage. MATERIAL_ARROW_FORWARD);
fab.bindFabToContainer(this);


fab. addActionListener (e -> {String number = phoneNumber.getText();
if (number.startsWith( "0" )) 
{
	number = number. substring ( 1 ); 
    
    
}


new EnterSMSVerificationDigitsForm(countryCodeButton.getText() + "-" + number).show();
});

}
        
     }


