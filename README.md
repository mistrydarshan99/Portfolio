# Bio <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/kotlin.png?alt=media&token=7e4e5ac0-d101-4f97-9f20-c5e1cb36eafe" height="20"> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/jetpack.png?alt=media&token=da5e9d49-7495-4fe1-8e0d-43894e99f5d2" width="25">
[![](https://jitpack.io/v/danimahardhika/candybar-library.svg)](https://jitpack.io/#danimahardhika/candybar-library) [![Build Status](https://travis-ci.org/danimahardhika/candybar-library.svg?branch=master)](https://travis-ci.org/danimahardhika/candybar-library) [![CircleCI](https://circleci.com/gh/danimahardhika/candybar-library.svg?style=svg)](https://circleci.com/gh/danimahardhika/candybar-library) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/108f01d34ab2456b866c5700f03591a5)](https://www.codacy.com/app/danimahardhika/candybar-library?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=danimahardhika/candybar-library&amp;utm_campaign=Badge_Grade) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


## A personal portfolio app

<img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_bio.png?alt=media&token=0ee960dc-1697-43e7-ad79-d30bd6897631" align="left" width="200" hspace="10" vspace="10">
93% of paper comes from trees, and they are not recycled paper. We’re losing over 15 billion trees annually. In the U.S. alone, 10,000,000,000 business cards are printed every year, and 88% of them are thrown away.<br/>
<br/>
<em>Stop printing business cards.</em>
<br/>
<div style="display:flex;" >
<a target="_blank" href="#">
    <img alt="Get it on Google Play"
        height="80"
        src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" />
</a>
<a target="_blank" href="https://8io.co/bio">
    <img alt="Visit Web"
        height="80"
        src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/visit_web.png?alt=media&token=c2c56c2e-f891-4761-b430-731840eda9a0" />
</a>
</div>
</br>


## Previews

<img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> 
<img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/>
<img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/github_placeholder.jpg?alt=media&token=0f502370-ca66-410d-89dc-1c0992774a43" width="215"/>


## Features:

| Features                                        | Description                                                            |
|:------------------------------------------------|:-----------------------------------------------------------------------|
| **Kotlin**                                      | This app is completely written in Kotlin.                              |
| **MVVM architecture**                           | Using the lifecycle aware viewmodels, the view observes changes in the model / repository.|
| **Android Architecture Components**             | Lifecycle awareness has been achieved using a combination of LiveData, ViewModels and Room.|
| **Firebase**                                    | Firebase Storage, Analytics, Crashlytics, Cloud Firestore, In App-Messaging, Remote Config|
| **Dependency Injection**                        | Common elements like context, networking interface are injected using Koin.|
| **Offline first architecture**                  | All the data is first tried to be loaded from the db and then updated from the server. This ensures that the app is usable even in an offline mode.|
| **Effective Networking**                        | Using a combination of Retrofit, Room and LiveData, we are able to handle networking in the most effective way.|
| **Intelligent sync**                            | Intelligent hybrid syncing logic makes sure your Android app does not make repeated calls to the same back-end API for the same data in a particular time period.|
| **Feature based packaging**                     | This screen-wise / feature-wise packaging makes code really easy to read and debug.|


## Download APK

You can download the apk from: 

1. [Google Playstore](https://play.google.com/store/apps/details?id=info.tumur.resume.app)
2. [Github Release](https://github.com/tumurb/Personal-Resume-Android/blob/master/app/release/Resume%201.2.apk)


## Requirements

* JDK Version 1.7 & above
* [Android SDK.](http://developer.android.com/sdk/index.html)
* Android SDK Tools
* Android SDK Build tools 28
* Android Support Repository
* Android Support library


## Firebase Setup

This project uses Firebases Analytics and Crashlytics, Cloud Firestore, Remote Config, etc. You will need to generate the configuration file (`google-services.json`) and copy it to your `/app` dir. See links below

1. [Setup Firebase setup](https://firebase.google.com/docs/android/setup)
2. [Setup Firebase Analytics](https://firebase.google.com/docs/analytics/android/start/)
3. [Setup Firebase Crashlytics](https://firebase.google.com/docs/crashlytics/get-started/)
4. [Setup Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging/android/client)
5. [Setup Firebase In-App Messaging](https://firebase.google.com/docs/in-app-messaging/get-started)
6. [Setup Firebase Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart/)
7. [Setup Firebase Storage](https://firebase.google.com/docs/storage/android/start)
8. [Setup Firebase Remote Config](https://firebase.google.com/docs/remote-config/use-config-android/)


## Project Setup

This project is built with Gradle, the [Android Gradle plugin](http://tools.android.com/tech-docs/new-build-system/user-guide) Clone this repository inside your working folder. Import the `settings.gradle` file in the root folder into e.g. Android Studio. (You can also have a look at the `build.gradle` files on how the projects depend on another.)

* Start Android Studio
* Select "Open Project" and select the generated root Project folder
* You may be prompted with "Unlinked gradle project" -> Select "Import gradle project" and select
the option to use the gradle wrapper
* You may also be prompted to change to the appropriate SDK folder for your local machine
* Once the project has compiled -> run the project!


## Contributing

### Would you like to contribute code?
1. [Fork Hydras](https://github.com/tumurb/Bio/).
2. Create a new branch ([using GitHub](https://help.github.com/articles/creating-and-deleting-branches-within-your-repository/)) or the command `git checkout -b branch-name dev`).
3. [Start a pull request](https://github.com/tumurb/Bio/compare). Reference [existing issues](https://github.com/tumurb/Bio/issues) when possible.

### No code!
* You can [discuss a bug](https://github.com/tumurb/Bio/issues) or if it was not reported yet [submit a bug](https://github.com/tumurb/Bio/issues/new).


## Libraries used

| Android Foundation | Architecture Components | Behavior | UI | Third party
|:-------------------|:------------------------|:---------|:---------|:---------|
|[Appcompat](https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat)|[Data Binding](https://developer.android.com/topic/libraries/data-binding/)|[Sharing](https://developer.android.com/training/sharing/shareaction)|[Constraint Layout](https://developer.android.com/training/constraint-layout/)|[Anko](https://github.com/Kotlin/anko)|
|[Android KTX](https://developer.android.com/kotlin/ktx)|[LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)|[Preferences](https://developer.android.com/guide/topics/ui/settings)|[Fragment](https://developer.android.com/guide/components/fragments)|[Koin](https://github.com/InsertKoinIO/koin)|
||[Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle)|[Media & Playback](https://developer.android.com/guide/topics/media-apps/media-apps-overview)|[Android Acessibility Guideline](https://developer.android.com/guide/topics/ui/accessibility/)|[Retrofit](http://square.github.io/retrofit/)|
||[Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)||[Material Design Guideline](https://material.io/design/guidelines-overview/)|[OkHttp](http://square.github.io/okhttp/)|
||[Paging](https://developer.android.com/topic/libraries/architecture/paging/)||[Material Theming](https://material.io/develop/android/theming/color/)|[Glide](https://github.com/bumptech/glide)|
||[Room](https://developer.android.com/topic/libraries/architecture/room.html)|||[Timber](https://github.com/JakeWharton/timber)|
||[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)|||[ThreenTenABP](https://github.com/JakeWharton/ThreeTenABP)|
||[Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager/)|||[Stetho](http://facebook.github.io/stetho/)|


## Project Maintained By

<img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/tumur.png?alt=media&token=75fb45df-fd35-488c-85f1-4ea321aae6d2" align="left" width="60" hspace="10" vspace="10">
<b>Tumur.B (Alex)</b><br/>
Android Developer
<br/>
<br/>
<a href="https://play.google.com/store/apps/dev?id=4872099625526337244"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_google.png?alt=media&token=df311441-34fe-44dd-bf24-c5cbf8f4a6c9" width="60"></a> <a href="https://tumur.me"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_web.png?alt=media&token=3854f445-8465-4ad3-8cd8-7aca2999964f" width="60"></a>
<a href="https://twitter.com/tumur_alex"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_twitter.png?alt=media&token=0ada2552-9a35-4231-a502-3feab11d67c6" width="60"></a>
<a href="https://www.linkedin.com/in/tumur-alex/"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_linkedin.png?alt=media&token=3a0baa2c-8a47-4301-8bf6-11603f3bfb0a" width="60"></a>


## License

    Copyright 2018 Tumur.B (Alex)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





