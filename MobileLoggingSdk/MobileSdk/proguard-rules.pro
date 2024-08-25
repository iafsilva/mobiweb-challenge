# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Allow GSON SerializedName annotion
-keepclassmembers,allowobfuscation class * {
 @com.google.gson.annotations.SerializedName <fields>;
}

# Keep the MobileSdk class and all its methods
-keep class com.ivoafsilva.mobiweb.mobilesdk.MobileSdk { *; }

# Keep classes used by your library but allow obfuscation
-keep class com.ivoafsilva.mobiweb.mobilesdk.** { *; }

# Keep Koin-related classes and methods
-keep class org.koin.core.** { *; }

# Preserve attributes needed for reflection
-keepattributes Signature, *Annotation*

# Keep all classes related to networking
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }