# Movies Database
An informative movies database that contains everything movies related!

![best-posters-of-the-decade](https://user-images.githubusercontent.com/80595523/178150552-fa564250-5a60-4112-8b39-5ec64978afdd.jpg)
---
## Features

- [x] Filter movies according to genre.
- [x] Explore the dataset with over 40,000 movies.
- [x] View movie posters.
- [x] Collect information such as:
      1. **Budget**
      2. **Release date**
      3. **Runtime**
      4. **Associated Genres**
      5. **Movie language**

---
## Designs
| Home Screen | Details Screen | Search Screen | Splash Screen
| ------ | ---- | ---- | ---- |
| ![Screenshot_1657470754](https://user-images.githubusercontent.com/80595523/178153535-a6979e35-5e5b-49d1-85dd-5cfd3ea172b5.png) | ![Screenshot_1657465355](https://user-images.githubusercontent.com/80595523/178151118-46af5958-8888-4b15-bccb-97b9ac608faf.png) | ![Screenshot_1657470762](https://user-images.githubusercontent.com/80595523/178153546-de19b9ae-a6bb-4334-a469-b4f833ba1567.png) | ![Screenshot_1657470385](https://user-images.githubusercontent.com/80595523/178153407-c91c2bba-3fc0-4047-94b2-0f3f2cd7041a.png)

---
## Architucture
The architecture used in the project is *Model-View-ViewModel* (MVVM)

![image](https://user-images.githubusercontent.com/80595523/178152193-0c215db6-99f8-4d26-a841-8e1806593130.png)
> Project Archtitecture
---
## Networking
The client used in the project is **Retrofit**
```kotlin
object ApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
```
