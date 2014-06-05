/*
 * Copyright (C) 2014 Makar Krasnoperov, Alexander Myltsev
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

import twitter4j._

object TwitterStreamer {
  val thankWords = List("thanks", "thank", "ty")

  def main(args: Array[String]): Unit = {
    val twitterStream = new TwitterStreamFactory().getInstance
    twitterStream.addListener(new StatusAdapter {
      override def onStatus(status: Status): Unit = {
        val containsTargetWord = status.getText.split("(\\.)|(\\s)").exists(thankWords.contains(_))
        if (containsTargetWord)
          println(s"${status.getUser.getName} : ${status.getText}")
      }
    })
    twitterStream.sample
  }
}
