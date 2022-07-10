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
| Home Screen | Details Screen | Search Screen | Splash Screen |
| ------ | ---- | ---- | ---- |
| ![Screenshot_1657472025](https://user-images.githubusercontent.com/80595523/178154368-a26da6bf-a33e-4b4d-b485-f5ff81ca6dec.png) | ![Screenshot_1657472032](https://user-images.githubusercontent.com/80595523/178154374-46b0a8dd-3337-490a-84e9-6110711aefb9.png) | ![Screenshot_1657472048](https://user-images.githubusercontent.com/80595523/178154383-b6a1d18d-536b-4871-9f4c-6696b2f40611.png) | ![Screenshot_1657472021](https://user-images.githubusercontent.com/80595523/178154385-a228f17a-f133-4872-8add-0f9ddae50b89.png) |

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
