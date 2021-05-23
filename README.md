## Implementation of GIT API at "repositories" endpoint. 

## Tech stack
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)

## Functionalities demonstration
<img src="https://github.com/leogmsantos/GIT-Repositories-API-MVVM/blob/master/GIT-Repositories-App.gif" width="280" height="400"/>
