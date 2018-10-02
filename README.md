# <a href="https://github.com/rhildred/BitBucketLogCat" target="_blank">BitBucketLogCat</a>

Ships logcat from device to a bitbucket issue. Works with jitpack.io. 

See this [Tutorial](https://medium.com/@ome450901/publish-an-android-library-by-jitpack-a0342684cbd0) on how to publish an Android Library with JitPack.


Add it to your app/build.gradle with:

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
allprojects {
    ...
    dependencies {
        implementation 'com.github.rhildred:BitBucketLogCat:master'
    }
}
```

To use this log whatever you need to the logcat. For instance:

```java
        catch(Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
```

When you want to send the logcat to a bitbucket issue:

```java
BitBucketLogCat.e(this, "rhildred", "faculty", "Logged using my package", "rhildred");
```

or if in a fragment

```java
BitBucketLogCat.e(getActivity(), "rhildred", "faculty", "Logged using my package", "rhildred");
```

To make this more plug and play I created a Bitbucket user ysaasissues. You will need to add this user with read access to your repository.

![Add ysaasissues user with read privileges](https://rhildred.github.io/BitBucketLogCat/READMEImages/ysaasissuesreadonly.png "Add ysaasissues user with read privileges")
