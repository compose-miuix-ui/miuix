# JNA (Java Native Access)
# JNA uses JNI callbacks and reflection that ProGuard cannot detect.
-keep class com.sun.jna.** { *; }
-keepclassmembers class * extends com.sun.jna.** { *; }
-keep interface * extends com.sun.jna.** { *; }
-keep class * implements com.sun.jna.Callback { *; }
-keep class * implements com.sun.jna.Library { *; }
-keep class * implements com.sun.jna.Structure { *; }
-keep class * extends com.sun.jna.Structure { *; }
-keepclassmembers class * extends com.sun.jna.Structure {
    <fields>;
}
-keep class * extends com.sun.jna.NativeLong { *; }
-dontwarn com.sun.jna.**
-dontnote com.sun.jna.**

# miuix-window
-keep class top.yukonga.miuix.kmp.window.** { *; }
-dontnote top.yukonga.miuix.**
