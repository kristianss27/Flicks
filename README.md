# Project 1 - *Flicks*

Submitted by: **Cristian Sanchez** 
Email: Kristianss27@gmail.com
LinkedIn: https://www.linkedin.com/in/kristianss27

**Flicks** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **30** hours spent in total

## User Stories

The following **required** functionality is completed:

* [Implemented] User can **scroll through current movies** from the Movie Database API
* [Implemented] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [Implemented] For each movie displayed, user can see the following details:
  * [Implemented] Title, Poster Image, Overview (Portrait mode)
  * [Implemented] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [Implemented] User can **pull-to-refresh** popular stream to get the latest movies.
* [Implemented] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* [Implemented] Improved the user interface through styling and coloring.

The following **bonus** features are implemented:

* [ Implemented] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [Implemented] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [Implemented] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [Implemented] Overlay a play icon for videos that can be played.
    * [Implemented] More popular movies should start a separate activity that plays the video immediately.
    * [Implemented] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [Implemented] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [ ] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [ ] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) or [Volley](http://guides.codepath.com/android/Networking-with-the-Volley-Library) networking libraries.


## Video Walkthrough

Here's a walkthrough of implemented user stories (poltrait screen):[Video Walkthrough](http://i.imgur.com/CVXeZCT.gif)

Here's a walkthrough of implemented user stories (Landscape screen):[Video Walkthrough](http://i.imgur.com/JSH6DaS)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

The following **additional** features are implemented:

* [Implemented] Parceler

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
