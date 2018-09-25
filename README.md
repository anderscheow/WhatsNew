[![](https://jitpack.io/v/anderscheow/WhatsNew.svg)](https://jitpack.io/#anderscheow/WhatsNew)
[![Build Status](https://travis-ci.org/anderscheow/WhatsNew.svg?branch=master)](https://travis-ci.org/anderscheow/WhatsNew)

# What's New
Beautiful way to showcase new features of your app.

<img src="https://raw.githubusercontent.com/anderscheow/WhatsNew/master/images/demo.png"/>

## Download

Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    ... 
    
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency
```groovy
dependencies {
  compile 'io.github.anderscheow:whatsnew:1.0.0'
}
```

Usage
-----
Setup features
````kotlin
val features = ArrayList<Feature>().apply {

    // Recommended: Use builder (Support more configurations)
    this.add(Feature.Builder()
            .setIconRes(R.drawable.access_point)
            .setIconColor(Color.RED)
            .setTitleRes(R.string.title_one)
            .setTitleTextColor(Color.BLACK)
            .setDescriptionRes(R.string.description_one)
            .setDescriptionTextColor(Color.BLACK)
            .build())

    // or use constructor
    this.add(Feature(R.drawable.account, Color.RED, R.string.title_two, Color.BLACK, R.string.description_two, Color.BLACK))
}
````

Setup and show What's New
````kotlin
// Only show partial configurations, please refer WhatsNew.Builder to view more configurations
WhatsNew.Builder(this)
        .setTitleRes(R.string.app_name)
        .setTitleColor(Color.BLACK)
        .setBackgroundRes(android.R.color.white)
        .setPrimaryButtonBackgroundColor(Color.RED)
        .setPrimaryButtonTextColor(Color.WHITE)
        .setPrimaryButtonTextRes(R.string.lets_go)
        .enablePrimaryButtonAllCaps(false)
        .setSecondaryButtonTextColor(Color.RED)
        .setSecondaryButtonTextRes(R.string.learn_more)
        .enableSecondaryButtonAllCaps(false)
        .enableFadeAnimation(true)
        .setFadeAnimationDuration(500L)
        .setFeatureItemAnimator(FeatureItemAnimator.FADE_IN_UP)
        .setFeatureItemAnimatorDuration(500L)
        .setFeatures(features)
        .setListener(object : WhatsNewListener {
            override fun onWhatsNewShowed(whatsNew: WhatsNew) {
                Log.d(TAG, "onWhatsNewShowed")
            }

            override fun onWhatsNewDismissed() {
                Log.d(TAG, "onWhatsNewDismissed")
            }

            override fun onPrimaryButtonClicked(whatsNew: WhatsNew) {
                Log.d(TAG, "onPrimaryButtonClicked")
            }

            override fun onSecondaryButtonClicked(whatsNew: WhatsNew) {
                Log.d(TAG, "onSecondaryButtonClicked")
            }
        })
        .build()
````

### Support different animation when display features. 
Thanks to [RecyclerView Animators by wasabeef](https://github.com/wasabeef/recyclerview-animators)

* NONE
* SCALE_IN
* SCALE_IN_TOP
* SCALE_IN_BOTTOM
* SCALE_IN_LEFT
* SCALE_IN_RIGHT
* FADE_IN
* FADE_IN_UP
* FADE_IN_DOWN
* FADE_IN_LEFT
* FADE_IN_RIGHT
* SLIDE_IN_UP
* SLIDE_IN_DOWN
* SLIDE_IN_LEFT
* SLIDE_IN_RIGHT
* OVERSHOOT_IN_LEFT
* OVERSHOOT_IN_RIGHT

## Changelog

**1.0.0**

* Introduce What's New library

## Contributions
Any contribution is more than welcome! You can contribute through pull requests and issues on GitHub.

## License
Validator is released under the [MIT License](https://github.com/anderscheow/Validator/blob/master/LICENSE)