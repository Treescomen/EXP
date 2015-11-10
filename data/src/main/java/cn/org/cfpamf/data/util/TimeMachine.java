/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.org.cfpamf.data.util;

import android.os.Handler;


public class TimeMachine {

  private static Handler handler = new Handler();

  private TimeMachine() {
    //Empty
  }


  public static void sendMessageToTheFuture(final Runnable runnable, final int delay) {
    handler.postDelayed(runnable, delay);
  }


  public static void cancelMessage(Runnable runnable) {
    handler.removeCallbacks(runnable);
  }
}
