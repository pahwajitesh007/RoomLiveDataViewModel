# RoomLiveDataViewModel

Android Architecture Components — Room, LiveData and ViewModel

This application is based on Android Architecture Components.Here we just understand the basic implemenation of ROOM Persistence with LiveData classed and also understand the View Model class to save views data.

Room Persistence Library

The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
The library helps you create a cache of your app's data on a device that's running your app. This cache, which serves as your app's single source of truth, allows users to view a consistent copy of key information within your app, regardless of whether users have an internet connection.


LiveData overview

LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.



ViewModel Overview

The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.









