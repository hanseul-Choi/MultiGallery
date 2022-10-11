# MultiLibrary

This library navigates your gallery and imports the image url or file instead.
This library allows you to apply the following features:
- Explore the user's image and use the album category to distinguish it.
- **Limits the number of image selections** for the user.
- Obtain file permissions automatically.
- Provides a user UI screen.

Our library is available through jitpack.io. Thanks to jitpack.io team.

## Setting

If your gradle version is lower than 7.1.0, add this code in your project gradle.

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```



## Subsequent Corrections
- No more needs tools:replace"android:theme"
- No more needs Databinding
- No more needs Glide Library
- Dynamically resize images
