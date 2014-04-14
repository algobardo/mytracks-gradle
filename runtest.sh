#!/bin/bash

adb shell pm list instrumentation
adb shell am instrument -w com.google.android.maps.mytracks.test/com.google.android.apps.mytracks.endtoendtest.EndToEndTestRunner