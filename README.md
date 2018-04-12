# miskatino-bluetooth-terminal

**Bluetooth Terminal for Android to use with Miskatino microcontrollers**

Miskatino microcontrollers expose basic-like interface via UART, so they could be programmed via cable or bluetooth from
variety of host devices, including mobile phones and tablets. On android several bluetooth terminals from "google play" could
be used. Meanwhile this project represents dedicated bluetooth terminal, mainly intended to allow sending single keypresses
instead of collecting whole lines before sending (as most of popular applications do due to specifics of android keyboard).

### Build instructions

1. Download android sdk: https://developer.android.com/sdk/download.html

2. Set environment variable ANDROID_HOME to the folder where android-sdk is unpacked, e.g. `/home/user/utils/android-sdk`.

3. Use `$ANDROID_HOME/tools/bin/sdkmanager` tool to install SDK for android 2.3.3, i.e. `platforms;android-10`.

4. Change to directory with project and run `mvn package`.

5. Find `miskatino-btterm.apk` file in the folder `target`. Upload it to smartphone and install (either via adb or manually).

