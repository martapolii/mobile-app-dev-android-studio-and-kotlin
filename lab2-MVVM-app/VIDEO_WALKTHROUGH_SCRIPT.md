# lab 2 video walkthrough script

## intro

Hi, this is my Lab 2 app, called the Student Career Development Hub.

The goal of this app is to let a student manage career development items such as skill development, certifications, academic projects, internship applications, and career goals.

This app has 3 main screens:
- a home screen
- a create career item screen
- a view and edit career item screen

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` lines 55 to 107
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 44 to 139
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemScreen.kt` lines 19 to 89
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemScreen.kt` lines 27 to 98

## app flow

When the app opens, it starts on the home screen.

The home screen shows the list of career items using a `LazyColumn`.

When the user taps the floating action button, the app switches to the create screen.

When the user taps an existing card, the app switches to the view and edit screen for that specific item.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` lines 71 to 105
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 51 to 68
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 81 to 136

## rubric point 1: mvvm architecture

This app follows the MVVM architecture, which means I separated the project into the UI layer, the ViewModel layer, and the data layer.

The UI layer is made of composable screen files like `HomeScreen`, `CreateCareerItemScreen`, and `EditCareerItemScreen`.

The ViewModel layer contains `HomeViewModel`, `CreateCareerItemViewModel`, and `EditCareerItemViewModel`. These classes handle the app logic instead of putting that logic directly in the UI.

The data layer contains the `CareerItem` model, the repository interface, and the repository implementation.

This separation makes the code easier to organize, maintain, and explain.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItem.kt` lines 3 to 30
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItemRepository.kt` lines 7 to 20
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItemRepositoryImpl.kt` lines 8 to 67
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeViewModel.kt` lines 8 to 17
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemViewModel.kt` lines 7 to 32
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemViewModel.kt` lines 7 to 37

## rubric point 2: viewmodel classes and stateflow

The rubric asked us to use ViewModel classes and `StateFlow`.

In my app, the repository stores the list of career items in a `MutableStateFlow`, and then exposes it as a `StateFlow`.

The `HomeViewModel` reads that `StateFlow`, and then the UI collects it using `collectAsState()`.

That means when data changes, for example when a new career item is added or an existing item is updated, the home screen updates automatically.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItemRepositoryImpl.kt` lines 10 to 11
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItemRepositoryImpl.kt` lines 46 to 67
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeViewModel.kt` lines 12 to 16
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` line 73

## rubric point 3: dependency injection

For dependency injection, I used constructor injection with a repository and ViewModel factories.

In `MainActivity`, I create one shared `CareerItemRepositoryImpl`.

Then I pass that same repository into `HomeViewModelFactory`, `CreateCareerItemViewModelFactory`, and `EditCareerItemViewModelFactory`.

That means all 3 ViewModels receive the same data source, so all screens stay connected to the same list of career items.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` lines 32 to 41
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeViewModelFactory.kt` lines 7 to 16
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemViewModelFactory.kt` lines 7 to 17
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemViewModelFactory.kt` lines 7 to 16

## rubric point 4: home screen

The home screen displays all career items using `LazyColumn`, which was specifically required in the rubric.

Each card shows the title, category, status, progress percentage, and completion indicator.

I also used a floating action button as the main action to create a new career item.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 44 to 68
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 81 to 136

## rubric point 5: create career item screen

The create screen is responsible for entering a new career item.

The screen keeps its UI state inside the composable using `rememberSaveable`, and when the user taps save, the input is validated first.

If validation passes, the screen calls `CreateCareerItemViewModel`, and the ViewModel sends the new item to the repository.

I also used dropdown menus for category and status to make the form more structured and user-friendly.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemScreen.kt` lines 24 to 32
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemScreen.kt` lines 43 to 87
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemViewModel.kt` lines 11 to 31
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 31 to 191
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 193 to 218

## rubric point 6: view and edit career item screen

The view and edit screen opens when the user taps a card from the home screen.

It receives the selected career item, pre-populates all the form fields, and lets the user update the values.

When the save button is tapped, the screen validates the input and then calls `EditCareerItemViewModel`, which updates the repository.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` lines 91 to 104
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemScreen.kt` lines 33 to 96
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemViewModel.kt` lines 15 to 36

## rubric point 7: reusable composables and code organization

To keep the UI organized and reusable, I created shared composables.

`CommonTopBar` is reused across screens for a consistent app bar.

`CareerItemForm` is reused by both the create screen and the edit screen, so I did not duplicate the form logic.

This also makes the project easier to maintain.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CommonTopBar.kt` lines 18 to 45
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 29 to 191

## rubric point 8: material design 3

This app uses Material Design 3 components throughout the interface.

Examples include `Scaffold`, `CenterAlignedTopAppBar`, `FloatingActionButton`, `Card`, `Button`, `OutlinedTextField`, and `DropdownMenu`.

I also customized the look so the app does not feel like the default template.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 50 to 68
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 94 to 132
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CommonTopBar.kt` lines 22 to 45
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 57 to 191

## rubric point 9: theme customization

The professor emphasized making the app unique, so I created a custom light theme and a custom dark theme.

The light theme uses a navy, teal, and coral palette.

The dark theme uses a deeper navy background with brighter accent colors.

I also customized the typography to make it feel more modern.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/ui/theme/Color.kt`
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/ui/theme/Theme.kt` lines 10 to 63
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/ui/theme/Type.kt` lines 9 to 62

## rubric point 10: validation and user friendliness

For user friendliness, I added form validation so required fields cannot be left blank, and progress must be a number between 0 and 100.

I also used clear labels and supporting text for the date and progress fields.

This improves the app’s input flow and makes the interface easier to understand.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 81 to 113
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 116 to 218

## rubric point 11: accessibility and responsive design

For accessibility, I used Material 3 components, which already provide good default touch targets.

I also added content descriptions for key icons like the back button and the add button.

For readability, I used a custom light and dark color scheme with strong contrast and clear typography.

For responsive behavior, the screens use flexible Compose layouts like `Scaffold`, `fillMaxWidth`, `fillMaxSize`, and a vertically scrollable shared form, so the layout can expand and adapt instead of being fixed to one screen size.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeScreen.kt` lines 58 to 66
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CommonTopBar.kt` lines 35 to 41
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/components/CareerItemForm.kt` lines 53 to 55
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/ui/theme/Theme.kt` lines 10 to 63
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/ui/theme/Type.kt` lines 12 to 62

## how the files work together

If I explain the whole app in one flow, it works like this:

`MainActivity` creates the repository and injects it into the ViewModels.

The repository holds the career item data using `StateFlow`.

The home screen reads the list from `HomeViewModel`.

The create screen sends new data to `CreateCareerItemViewModel`.

The edit screen sends updated data to `EditCareerItemViewModel`.

Because all of those ViewModels share the same repository, when data changes, the home screen automatically reflects the updated list.

file refs:
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/MainActivity.kt` lines 32 to 49
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/data/CareerItemRepositoryImpl.kt` lines 46 to 67
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/home/HomeViewModel.kt` lines 12 to 16
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/create/CreateCareerItemViewModel.kt` lines 11 to 31
- `app/src/main/java/com/example/martapolishchuk_comp304lab2_ex1/screens/edit/EditCareerItemViewModel.kt` lines 15 to 36

## short closing

To summarize, this project demonstrates MVVM architecture, dependency injection, StateFlow, Compose UI with LazyColumn, Material Design 3, a custom light and dark theme, reusable composables, form validation, and connected create and edit flows across 3 main screens.

Thank you.
