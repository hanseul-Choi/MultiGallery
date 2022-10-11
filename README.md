# MultiLibrary

This library navigates your gallery and imports the image url or file instead. </br></br>
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
</br>

If your gradle version is greater than 7.1.0, add this code in your setting.gradle
```gradle
dependencyResolutionManagement {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
</br>

Add Multigallery library and glide in your app module gradle
```gradle
dependencies {
    // implement latest version
    implementation 'com.github.hanseul-Choi:MultiGallery:1.0.2'
    
    // Glide
    def glideVer = "4.13.2"
    implementation "com.github.bumptech.glide:glide:$glideVer"
    annotationProcessor "com.github.bumptech.glide:compiler:$glideVer"
}
```
</br>

Because this library uses other activities, you need to add code in Manifest.
```xml
<manifest>
    <application>
        tools:replace="android:theme"
        ...
    </application>
</manifest>
```
</br>

Now, you can use this Multigallery library!

### Use

you can use this library code in activity. 
for example, we use this library in mainActivity.

```kotlin
// in MainActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this listener callback images url or file 
        val listener = object: ImageSelectListener {
            override fun getImageUrls(urls: List<String>) {
                for(i in urls) {
                    // get image url
                }

                Log.d("url", "urls 0 is ${urls[0]}")
            }

            override fun getImageFiles(files: List<File>) {
                for(i in files) {
                    // get image file
                }
            }
        }

        MultiGallery()
            .setImageNum(3) // set number of images
            .setContext(this) // set context
            .setListener(listener) // set listener for callback
            .build() // You have to call the method in order to use this library.
    }
}
```
</br>

and enjoy your coding ~ ^-^

## Subsequent Corrections
- No more needs tools:replace"android:theme"
- No more needs Databinding
- No more needs Glide Library
- Dynamically resize images

## Thanks to
- jitpack.io teams
- Glide teams
