/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.apps.uberclone;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.util.SuccessCallback;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author ldavila
 */
public class CommonCode {
    
    public static void initBlackTitleForm(Form f, String title, SuccessCallback<String> searchResults)
    {
        Form backTo = getCurrentForm();
        f.getContentPane().setScrollVisible(false);
        Button back = new Button("","TitleCommand");
        back.addActionListener(e -> backTo.showBack());
        back.getAllStyles().setFgColor(0xffffff);
        FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
        f.setBackCommand(new Command("") {
            
            public void actionPerformed(ActionEvent evt)
            {
                backTo.showBack();
            }
            
        });
        
        Container searchBack = null;
        if(searchResults != null)
        {
            Button search = new Button("","TitleCommand");
            search.getAllStyles().setFgColor(0xffffff);
            FontImage.setMaterialIcon(search,FontImage.MATERIAL_SEARCH);
            search.addActionListener(e -> {});
            searchBack = BorderLayout.north(
            BorderLayout.centerEastWest(null,search,back)

            );
        } else {
            
         /*   searchBack = BorderLayout.north(
            
      //              BorderLayout.centerEastWest(null,null,back)
            
            );*/
           
        }
        
        Label titleLabel = new Label(title,"WhiteOnBlackTitle");
        titleLabel.getAllStyles().setMarginTop(back.getPreferredH());
        titleLabel.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS,Style.UNIT_TYPE_DIPS,Style.UNIT_TYPE_DIPS,Style.UNIT_TYPE_DIPS);
        f.getToolbar().setTitleComponent(LayeredLayout.encloseIn(searchBack,titleLabel));
        f.getAnimationManager().onTitleScrollAnimation(titleLabel.createStyleAnimation("WhiteOnBlackTitleLeftMargin",200));
        f.setTransitionInAnimator(CommonTransitions.createCover(
        
                CommonTransitions.SLIDE_VERTICAL,false,300
                
        ));
        
        f.setTransitionOutAnimator(CommonTransitions.createUncover(
        
        
        CommonTransitions.SLIDE_VERTICAL,true,300
        
        ));
        
        
        
    }
    
    
}
