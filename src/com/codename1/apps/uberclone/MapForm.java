/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.apps.uberclone;

import static com.codename1.ui.FontImage.*; 

import com.codename1.maps.Coord;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.LayeredLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;

/**
 *
 * @author ldavila
 */
public class MapForm extends Form {
    

private static final String MAP_JS_KEY = "AIzaSyAeB8YTSQmClMSLlW-S-gArMMj0Gk8wB2M";
private Image square;
private int shadowHeight;
private Image dropShadow;
private static final Coord telAviv = new Coord( 32.072449 , 34.778613 );

public MapForm () {
super( new LayeredLayout());
setScrollableY( false ); 
//*

shadowHeight = convertToPixels(4);
callSeriallyOnIdle(() > { 
/*①*/  int t = convertToPixels( 3 ); dropShadow = Effects.squareShado(getDisplayWidth() + shadowHeight * 2 ,shadowHeight * 2 ,shadowHeight, 0.3f); });
setTransitionOutAnimator(CommonTransitions.createEmpty()); 
/*②*/ MapContainer mc = new MapContainer(MAP_JS_KEY);
mc.setShowMyLocation( true ); 
/*③*/ add(mc);
Container mapLayer = new Container();
mapLayer.setLayout(new MapLayout(mc, mapLayer)); 
/*④*/ add(mapLayer);
mc.zoom(telAviv, mc.getMaxZoom () + 1 );
Resources gr = Resources.getGlobalResources();
Label car = new Label(gr.getImage( "map-vehicle-icon-uberX.png" ));
car.getAllStyles().setOpacity( 140 ); 
/*⑤*/ mapLayer.add(telAviv, car); 
/*⑥*/ square = Image.createImage(convertToPixels( 0.7f ),convertToPixels( 0.7f ), 0xff000000 ); 
/*⑦*/ Button whereTo = new Button( "Where To?" , square, "WhereTo" );
whereTo.setGap(convertToPixels( 3 )); 
/*⑧*/ add(BoxLayout.encloseY (whereTo));
Container h1 = createHistoryButton( "Mikve Yisrael Str..." );
Container h2 = createHistoryButton( "Burgeranch" );
ScaleImageLabel gradient = new ScaleImageLabel(gr.getImage( "gradient-overlay.png" )); 
/*⑨*/ gradient.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
add(BorderLayout.south(gradient));
add(BorderLayout.south(FlowLayout. encloseCenter (h1, h2))); 

whereTo.addActionListener(e > showNavigationToolbar()); 

from.addFocusListener(new FocusListener() {

public void focusGained (Component cmp) {

fromSelected.setIco(square); //① 
if (layer.getComponentCount () > 1) { //② 

Component c = layer.getComponentAt ( 1 );
c.setY(getDisplayHeight()); //③ 
layer.animateUnlayout(200,150,() > { //④ 
c.remove (); //⑤ 
revalidate();

});

}

}


public void focusLost (Component cmp) {

fromSelected.setIcon(circle);

}

});

to.addFocusListener( new FocusListener() { 

public void focusGained (Component cmp) {

fromSelected.setIcon (circle);
toSelected.setIcon (square);
showToNavigationBar(layer);

}

public void focusLost (Component cmp) {
toSelected.setIcon (circle);

}

});

//*
}



@Override 
protected void initGlobalToolbar () {

setToolbar( new Toolbar( true ));
CommonCode.constructSideMenu (getToolbar());

}


private Container createHistoryButton (String title) 
{
FloatingActionButton history = FloatingActionButton.createFAB(FontImage.MATERIAL_HISTORY, "History" );
TextArea historyLabel = new TextArea(title, 3 , 4 );
historyLabel.setUIID ( "HistoryLabel" );
historyLabel.setEditable ( false );

historyLabel.setGrowByContent( false );
Container h = BoxLayout.encloseY(history,historyLabel);
h.setLeadComponent(history);
return h;
}


void showNavigationToolbar () {

final Container layer = getLayeredPane(MapForm.class, true); 
/*①*/ Button back = new Button( "" , "TitleCommand" );
FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK); 
/*②*/ TextField from = new TextField( "" , "From" , 40 , TextField.ANY);
TextField to = new TextField( "" , "Where to?" , 40 , TextField. ANY );
Image circle=Image.createImage(square.getWidth(),square.getHeight(),0);
Graphics g = circle.getGraphics();
g.setColor( 0xa4a4ac ); 
/*③*/ g.setAntiAliased( true );
g.fillArc( 0 , 0 , circle. getWidth (), circle. getHeight (), 0 , 360 );
final Label fromSelected = new Label(square);
final Label toSelected = new Label(circle);
from.getHintLabel().setUIID("FromToTextFieldHint");
from.setUIID("FromToTextField");
to.getHintLabel().setUIID("FromToTextFieldHint");
to.setUIID( "FromToTextField" );
Container navigationToolbar = BoxLayout.encloseY(back, /*④*/ BorderLayout.centerCenterEastWest(from,null,fromSelected),BorderLayout.centerCenterEastWest (to, null , toSelected));
navigationToolbar.setUIID ( "WhereToToolbar" );
navigationToolbar.getUnselectedStyle().setBgPainter((g1, rect) > paintWhereToToolbarBackground(g1, rect, fromSelected,circle, toSelected)); 

/*⑤*/  back.addActionListener (e > {
navigationToolbar.setY(-navigationToolbar.getHeight ());
navigationToolbar.getParent().animateUnlayout(200,120,() > {layer.removeAll (); revalidate();});
});

layer.setLayout(new BorderLayout()); 
/*⑥*/ layer.add(NORTH,navigationToolbar);
navigationToolbar.setWidth(getDisplayWidth()); 
/*⑦*/ navigationToolbar.setHeight(getPreferredH());
navigationToolbar.setY(-navigationToolbar.getHeight ());
layer.animateLayout(200);





} 



private void paintWhereToToolbarBackground(Graphics g1, Rectangle rect, final Label fromSelected, Image circle, final Label toSelected) {
g1.setAlpha(255);
/*①*/ g1.setColor(0xffffff); 
/*②*/ if (dropShadow != null ) { 
/*③*/ g1.drawImage(dropShadow, rect.getX() - shadowHeight, rect.getY () + rect.getHeight() - dropShadow.getHeight() / 4*3 );
/* ④*/ g1.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getY() + rect.getHeight() - shadowHeight);
} else g1.fillRect(rect.getX (),rect.getY(),rect.getWidth(), rect.getHeight());
g1.setColor(0xa4a4ac);

g1.setAntiAliased(true);
int x = fromSelected.getAbsoluteX() + fromSelected.getWidth()/2-1;
int y = fromSelected.getAbsoluteY() + fromSelected.getHeight()/2+circle.getHeight()/2; 
/*⑤*/ g1.fillRect(x, y, 2, toSelected.getAbsoluteY()-y+toSelected.getHeight()/2-circle.getHeight()/2);


}


private void showToNavigationBar (Container parentLayer) {

MultiButton addHome = new MultiButton( "Add Home" ); /*①*/ 
addHome.setUIID("Container");
addHome.setUIIDLine1("WhereToButtonLine1");
addHome.setIconUIID("WhereToButtonIcon");
setMaterialIcon(addHome,MATERIAL_HOME);
MultiButton addWork = new MultiButton("Add Work");
addWork.setUIID( "Container" );
addWork.setUIIDLine1( "WhereToButtonLine1" );
addWork.setIconUIID( "WhereToButtonIcon" );
setMaterialIcon(addWork, MATERIAL_WORK);
MultiButton savedPlaces = new MultiButton( "Saved Places" );
savedPlaces.setUIID( "Container" );
savedPlaces.setUIIDLine1( "WhereToButtonLineNoBorder" );
savedPlaces.setEmblemUIID( "WhereToButtonLineNoBorder" );
savedPlaces.setIconUIID( "WhereToButtonIcon" );
savedPlaces.setEmblem(createMaterial(MATERIAL_NAVIGATE_NEXT,


whereSeparator.setShowEvenIfBlank ( true ); //② 
MultiButton history1 = new MultiButton( "Mikve Yisrael Str..." );
history1.setUIID ( "Container" );
history1.setUIIDLine1 ( "WhereToButtonLine1" );
history1.setIconUIID ( "WhereToButtonIcon" );
setMaterialIcon(history1, MATERIAL_HISTORY);
Container result = BoxLayout. encloseY (addHome, addWork,
savedPlaces, whereSeparator, history1);
result.setUIID ( "Form" ); //③ 
result.setScrollableY ( true );
result.setScrollVisible ( false ); //④ 
result.setY (getDisplayHeight());
result.setWidth (getDisplayWidth());
result.setHeight (result. getPreferredH ());
parentLayer.add (CENTER, result);
parentLayer.animateLayout ( 200 ); //⑤ 

} 



}