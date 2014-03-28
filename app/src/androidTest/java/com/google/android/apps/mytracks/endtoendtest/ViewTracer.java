package com.google.android.apps.mytracks.endtoendtest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.robotium.solo.Solo;

import android.app.Instrumentation;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewTracer implements Runnable{

  private Solo SOLO = null;
  private Instrumentation instr;
  private ViewCatcher viewCatcher = new ViewCatcher();
  
  
  private class ViewCatcher implements Runnable {

    public void run() {
      Debug.stopMethodTracing();
      
      ArrayList<View> cv = SOLO.getCurrentViews();
      Iterator<View> it = cv.iterator();
      
      Gson gson = new Gson();
      
      JsonObject jsroot = new JsonObject();
      JsonArray jsarr = new JsonArray();
      while(it.hasNext()){
        jsarr.add(visit(it.next()));
      }
      jsroot.add("Views", jsarr);
      
      Log.v("MyTrace", gson.toJson(jsroot));
      
      Debug.startMethodTracing();
    }
  }
  
  public ViewTracer(Instrumentation instr){
    this.instr = instr;
    SOLO = new Solo(instr);
  }
  @Override
  public void run() {
    
//    UiAutomation uiaut = instr.getUiAutomation();
//    uiaut.setOnAccessibilityEventListener(new OnAccessibilityEventListener() {
//      
//      @Override
//      public void onAccessibilityEvent(AccessibilityEvent event) {
//        Log.v("MyTrackTraceEvents", event.toString());
//      }
//    });
 
    while(true){
      instr.waitForIdleSync();
      instr.runOnMainSync(viewCatcher);
    }
    
  }
  

  private JsonObject visit(View v){
    JsonObject jobj = new JsonObject(); 
    jobj.addProperty("class", v.getClass().toString() );
    
    JsonArray jsarr = new JsonArray();
    if(v instanceof ViewGroup){
      ViewGroup vg = (ViewGroup)v;
      for(int i = 0; i < vg.getChildCount(); i++){
        jsarr.add(visit(vg.getChildAt(i)));
      }
    }
    jobj.add("childs", jsarr);

    return jobj;
  }
}
