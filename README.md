
# WhatAmIEating
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/363fafc613eb4bcbae445dada450b4c2)](https://www.codacy.com/gh/kralonur/WhatAmIEating/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kralonur/WhatAmIEating&amp;utm_campaign=Badge_Grade)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=kralonur_WhatAmIEating&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=kralonur_WhatAmIEating)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

#### WhatAmIEating is a food recipe application. You can find recipes from camera feed using TensorFlow and fetch the recipe from Spoonacular API.

## Interface
<img width="235" height="500" alt="camera_fragment" src="https://user-images.githubusercontent.com/18505576/104854915-744a9a00-591a-11eb-95b2-a21d98131808.png"> <img width="235" height="500" alt="recipe_list_fragment" src="https://user-images.githubusercontent.com/18505576/104854917-76145d80-591a-11eb-88df-fc9f5b146886.jpg"> <img width="235" height="500" alt="recipe_detail_fragment" src="https://user-images.githubusercontent.com/18505576/104854900-5b41e900-591a-11eb-85fd-39eaee4b60de.gif">

## Project setup

Clone the repo, open the project in Android Studio. 

Open local.properties file and enter this line; `apiKey="YOUR_SPOONACULAR_API_TOKEN"`.

Then hit "Run". Done!

## Architecture
Based on mvvm architecture and repository pattern.

![Architecture diagram](https://user-images.githubusercontent.com/18505576/96736485-54601480-13c5-11eb-8bd1-2308f224d58b.png)

## Tech Stack
- Minimum SDK 21
- MVVM Architecture
- DataBinding-ViewBinding
- Written 100% on kotlin language
- Architecture Components (Lifecycle, LiveData, ViewModel, Navigation)
- [**Spoonacular API**](https://spoonacular.com/food-api/docs) for gettin detailed information about food and recipes
- [**Material Design**](https://material.io/develop/android/docs/getting-started) for implementing material design
- [**Kotlin Coroutines**](https://github.com/Kotlin/kotlinx.coroutines) for threading operations
- [**Hilt**](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [**CameraX**](https://developer.android.com/training/camerax) for camera app development
- [**TensorFlow Lite**](https://www.tensorflow.org/lite/models/image_classification/overview) for image classification
- [**Retrofit 2**](https://github.com/square/retrofit) for constructing the REST API
- [**Moshi**](https://github.com/square/moshi) for parsing JSON
- [**OkHttp**](https://github.com/square/okhttp) for implementing interceptor, logging
- [**Glide**](https://github.com/bumptech/glide) for loading images
- [**Lottie**](https://github.com/airbnb/lottie-android) for showing animations
- [**Dexter**](https://github.com/Karumi/Dexter) for simplifying the process of requesting permissions
- [**AAChart**](https://github.com/AAChartModel/AAChartCore) for creating charts
- [**Timber**](https://github.com/JakeWharton/timber) for logging

## Possible future changes
- <s>Add search bar</s>
- Update UI