# arctouch-cc
ArcTouch Code challenge - Vinicius Camargos Candido

No major changes were made to the project, so it should run normally by importing it to Android Studio.
If I had more time I would get on with proper unit tests, and implement the search feature that was in the optional section.

Details to my approach to each task are listed below, although I would be glad to discuss it later on. 
 

Tasks:

Blockers:

 - Pagination: Added pagination when scrolling to the bottom of the list.
    Removed region parameter because BR only returned one page.
 
 - Removing logic from HomeActivity: Removing logic using a ViewModel.
    Also added a provider class to handle requests and keep ViewModel cleaner and only responsible for actual logic.
 
 - Details Screen: Implemented following architecture proposed on previous item. Sorry for the basic layout.
 
 - Getting rid of Splash screen: Changed genres request to be done before the movies request and removed Splash screen
 
 
Optional:

 - Improving network layer maintainability: Removed properties from TmdbApi class
    Stored api key in gradle build config. Getting language from locale. Kept base url with Api singleton class.
    Currently not using region as it had few results and did not allow pagination.
 
 - Getting rid of BaseActivity: Added a singleton for the API.
    Enables removing BaseActivity and guarantees that there is only one instance of the api
 
 - Implementing Search: Ran out of time. Sorry
 
 - Orientation issue: Fixed with property in Manifest


