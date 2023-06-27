# Super Mario Bros. Calculator <img src="https://seeklogo.com/images/S/super-mario-bros-8-bit-logo-3AFFC4525F-seeklogo.com.png" height="35px"/>
A Super Mario-style Android app calculator.<br><br>
Mario games were a large part of my childhood, so I thought nothing would be cooler than to have a Mario-themed calculator on my phone. Each button on the calculator is based on a sprite from the iconic 1985 release, Super Mario Bros. It uses the Mario-style font, and the user can toggle the Mario theme song in the background by long pressing the bottom of the screen.<br><br>
The code for the app was written in Kotlin, and was made using JetBrain's Android Studio IDE. In terms of functionality, the calculator can perform floating-point addition, subtraction, multiplication, division, with support for parentheses and order-of-operations. It can also calculate exponents, primary trigonometric functions, and the natural logarithm. As such, the calculator can be used to compute any elementary mathematical operation or function.<br><br>

Here is a video showcasing the app in action:<br>
<a href="https://youtu.be/BatlEBHACFI" target="blank">https://youtu.be/BatlEBHACFI</a><br>

Some sample screenshots of the app running on a Samsung S20 phone:
<div align="left">
<!--<img src="readres/1.jpg" alt="screenshot of app" width="150px" style="display:inline-block;"/>-->
<img src="readres/3.jpg" alt="screenshot of app" width="150px" style="display:inline-block"/>
<img src="readres/2.jpg" alt="screenshot of app" width="150px" style="display:inline-block"/>
<img src="readres/4.jpg" alt="screenshot of app" width="150px" style="display:inline-block"/>
</div>

# Installation <img src="https://cdn.pixabay.com/photo/2016/12/18/13/45/download-1915753_960_720.png" height="35px"/>
There are multiple ways you can install the app:
## 1. Google Play Store  <img src="https://img.freepik.com/free-icon/google-play_318-566073.jpg" height="25px"/>
&nbsp;&nbsp;&nbsp;&nbsp;App will soon be published on the Google Play Store, where you can download from there<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://play.google.com/store/apps/details?id=jmuszka.apps.mariocalculator&hl=en&gl=US" target="blank">https://play.google.com/store/apps/details?id=jmuszka.apps.mariocalculator&hl=en&gl=US</a><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;*screenshot of Google Play Store*<br><br>
## 2. My website
&nbsp;&nbsp;&nbsp;&nbsp;App is hosted on my website, from which you can directly download the APK<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;Here is a link to the download page:<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://jmuszka.com/calculator" target="blank">jmuszka.com/calculator</a><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/apemanjosh67/Mario-Calculator/blob/master/readres/Screenshot%20from%202023-06-06%2001-32-57.png?raw=true" width="400px"/><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;Click <a href="https://www.androidauthority.com/how-to-install-apks-31494/" target="blank">here</a> for a tutorial for manually installing APK files, if you are unfamiliar with how to do so.<br><br>
## 3. APKMirror <img src="https://downloadr2.apkmirror.com/wp-content/uploads/2020/08/55/5f42eaaa4945c.png" height="25px"/>
&nbsp;&nbsp;&nbsp;&nbsp;App will soon be uploaded on APKMirror, from which you can directly download the APK<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;*link to website*<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;*screenshot of website*<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;Click <a href="https://www.androidauthority.com/how-to-install-apks-31494/" target="blank">here</a> for a tutorial for manually installing APK files, if you are unfamiliar with how to do so.<br><br>
## 4. Build it yourself
&nbsp;&nbsp;&nbsp;&nbsp;Clone this Git repository onto your local machine, and build the APK in Android Studio or your preferred IDE<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;1)<br>
&nbsp;&nbsp;&nbsp;&nbsp;How to clone this GitHub repository into Android Studio:<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/" target="blank">https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/</a><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;2)<br>
&nbsp;&nbsp;&nbsp;&nbsp;How to build an APK in Android Studio:<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.geeksforgeeks.org/methods-of-generating-apk-of-android-application/" target="blank">https://www.geeksforgeeks.org/methods-of-generating-apk-of-android-application/</a><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;3)<br>
&nbsp;&nbsp;&nbsp;&nbsp;Click <a href="https://www.androidauthority.com/how-to-install-apks-31494/" target="blank">here</a> for a tutorial for manually installing APK files, if you are unfamiliar with how to do so.<br><br>
# In the future
Plans for the app after release:
## Add different themes
Currently, the app is styled after the sprites from the Overworld level theme (World 1-1) from Super Mario Bros. Eventually the user will have the option to choose which theme the app displays. This will include, but is not limited to, the Underground level theme (World 1-2), the Castle level theme (World 1-4), and the Underwater level theme (World 2-2). The themes will have the corresponding button sprites and backgrounds of their respective levels.<br><br>
Perhaps this will be implemented by adding a Swipe Touch Listener to the root Layout, which when called will stop the current Activity and open a new one, where each Activity is a different theme. The user can cycle through the themes by swiping across the screen.<br>
## Add horizontal support
Currently, the app is vertical-only, with a 4x6 grid of calculator buttons. Allowing the user to rotate the phone horizontally will allow for a larger button grid, meaning more potential functionality for the calculator. Perhaps a pi button, e button, factorial support, etc.<br>
## Bugfixing
Although the app has been rigorously tested, and all known bugs have been eliminated, it is always possible that there are unintentional features or functionality within the code. As such, I will continuously update this repository to fix any and all bugs that come to light.
