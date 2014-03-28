/*
 * Copyright 2012 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.android.apps.mytracks.endtoendtest;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Debug;

/**
 * End-to-end test test runner.
 * 
 * @author Youtao Liu
 */
public class Instrumenter extends Instrumentation {

    private Thread vtThread = null;
  
  @Override
  public void onDestroy(){
    Debug.stopMethodTracing();
    super.onDestroy();
    
  }

  @Override
  public void onCreate(Bundle bundle) {

    super.onCreate(bundle);
    Debug.startMethodTracing("MyTrackTrace",100*1024*1024);
    
  }
}
