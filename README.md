# FilmBazzar Application

[Presentation Video link](https://www.youtube.com/watch?v=ZDMXr-xCS04)  
You can see posters and names of upcoming films worldwide in this application.

This application is designed with :
*   User Interface built with **[Jetpack Compose](https://developer.android.com/jetpack/compose)** and material3 with smooth animations
*   A presentation layer that contains a Compose screen (View) and a **ViewModel**.
*   Reactive UIs using **[Flow](https://developer.android.com/kotlin/flow)** and **[coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** for asynchronous operations.
*   A **data layer** with a repository and two data sources (local using Room and remote using retrofit and okhttp).
*   Dependency injection using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
*   Powered by **Paging3** for efficient data pagination.
*   To display images efficiently, utilize the **[Coil](https://coil-kt.github.io/coil/)** image loading library.

You can download the app from the links:
* from github : https://github.com/amiratashani/FilmBazzar/blob/master/app/release/FilmBazzar.apk
* from drive : https://drive.google.com/file/d/1OKZHlJ-4-6yURA9ougTdoARDdy1PmDhx/view?usp=sharing

Note:
 * The app also supports landscape mode and the status list of movies saved when the configuration change
 * To download photos, you must use VPN

Demo:

https://github.com/amiratashani/FilmBazzar/assets/50077165/c5166694-f944-4045-92b4-78ef46ee0e4d
