# Week 7 Midterm Candy Template README

This file is the root study guide for `week07/MidtermCandyTemplate`.

The goal of this template is to give you a simple Android app skeleton that stays close to the material covered in class through weeks 1 to 6, while still being reusable.

## Project Purpose

This template is meant to help me practice:

- Jetpack Compose UI structure
- real Android activity navigation
- passing data with `Intent` extras
- reading and updating text field input
- reacting to button clicks
- using `RadioButton`, `Checkbox`, segmented buttons, and images
- showing a list with `LazyColumn`
- basic MVVM with a repository, `ViewModel`, and `ViewModelFactory`

The sample app theme is candy.

## Project Location

The Android Studio project is here:

- `week07/MidtermCandyTemplate`

This study guide file is here:

- `week07/MidtermCandyTemplateStudyGuide.md`

## What Is Included

- `HomeActivity` as the launcher activity
- `DetailActivity` as the second real activity - edit/view screen
- `CreateCandyActivity` as an optional third activity scaffold - add new screen
- reusable top app bar
- reusable candy form
- reusable list item card
- starter theme files
- a fake in-memory repository that acts like a small database
- comments above the main UI elements so you can quickly find and reuse them

## Architecture Snapshot

The template uses a simple MVVM structure:

- `model`
  Contains data classes, form state, option lists, validation, repository interface, repository implementation, and the shared repository provider.

- `view`
  Contains the activities and screen-level composables.

- `viewmodel`
  Contains the `ViewModel` classes and `ViewModelFactory` classes.

- `view/components`
  Contains reusable UI pieces such as the form, list item, top app bar, and optional extra Material 3 widgets.

- `ui/theme`
  Contains colors, typography, and theme setup.

## What Maps To Class Material

This template stays inside the weeks 1 through 6 scope.

### Week 5 Material

These patterns match the MVVM and dependency injection examples from week 5:

- repository interface
- repository implementation
- `ViewModel`
- `ViewModelFactory`
- constructor injection, where the repository is passed into the factory and then into the `ViewModel`

###  Week 6 Material

`CandyRepositoryProvider` uses a singleton `object` pattern.

That is the same shared-object idea used by `RetrofitClient` in the week 6 Retrofit example. In this project, the provider simply holds one shared repository instance so both activities read and update the same in-memory data.

### Compose and Activity Basics

The rest of the app uses concepts/topics covered in week 1-6:

- `Scaffold`
- `TopAppBar`
- `Column`
- `Row`
- `Box`
- `LazyColumn`
- text fields
- buttons
- click handling
- simple state updates
- activity registration in the manifest
- `Intent` extras


## What Each Key File Does

### `Candy.kt`

This file is the data layer foundation.

It contains:

- the candy data class
- the form state class
- option lists for radio buttons and segmented buttons
- starter sample data
- helper functions such as validation and conversion between model data and form data


### `CandyRepository.kt` and `CandyRepositoryImpl.kt`

These files act like a fake database.

They show the main repository pattern:

- store a list
- expose the list
- get one item by id
- add a new item
- update an existing item

### `CandyRepositoryProvider.kt`

This file gives the app one shared repository instance.

That matters because if `HomeActivity` and `DetailActivity` each created their own repository, they would not be working with the same data. The provider keeps the data shared while the app is running.

### `HomeActivity.kt`

This is the home screen template.

It shows:

- launcher activity setup
- `ViewModelProvider`
- `Scaffold`
- reusable top bar
- `Box`, `Column`, and `Row`
- `LazyColumn`
- clicking on a list item
- opening the second activity with an `Intent`


### `DetailActivity.kt`

This is the most important file for navigation.

It shows:

- reading `Intent` extras
- loading one item by id
- showing a form
- editing fields
- saving changes
- going back to the first activity

If a question asks for “two activities” or “pass data between screens,” this is needed.

### `CandyForm.kt`

This is the reusable UI bank.

It includes examples of:

- `Text`
- text fields
- `Button`
- image button using `IconButton`
- `RadioButton`
- `Checkbox`
- segmented buttons
- `Image`
- summary text that updates dynamically


### `DetailViewModel.kt`

This file shows the MVVM editing flow:

- load one selected item
- expose screen state
- update form fields with small functions
- validate input
- save back to the repository


### `CommonTopBar.kt`

This is a reusable top app bar.


### `CreateCandyActivity.kt`

This is optional and not linked from the home screen yet.

It exists if you want to allow the user to create a new object.

### `OptionalStudyComponents.kt`

This file is not wired into the app by default.

Contains a few extra Material 3 examples:

- `OutlinedButton`
- `ElevatedButton`
- `AssistChip`
- `HorizontalDivider`
- `ElevatedCard`

## How The App Flows

The current app flow is:

1. `HomeActivity` starts first.
2. The home screen shows a `LazyColumn` of candy items.
3. Tapping a candy opens `DetailActivity`.
4. The app passes the selected candy id and screen mode through `Intent` extras.
5. `DetailActivity` asks the `DetailViewModel` to load that candy from the shared repository.
6. The user edits fields in the form.
7. Tapping save updates the repository.
8. The app returns to the home screen.

The optional create activity follows the same form idea, but adds a new candy instead of editing an existing one.

## Fast Rebuild Order For The Midterm

If you had to rebuild this app from scratch:

1. Create the data class and form state in `Candy.kt`.
2. Create the repository interface.
3. Create the repository implementation with starter data.
4. Build `HomeActivity` with a `LazyColumn`.
5. Build `DetailActivity` and pass the item id through an `Intent`.
6. Build the reusable `CandyForm`.
7. Add the `ViewModel` and `ViewModelFactory` files.
8. Add the reusable top bar.
9. Register activities in the manifest.
10. Polish the theme last.

## If You Only Memorize Three Things

If you are short on time, memorize these three files first:

1. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CandyForm.kt`
2. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/HomeActivity.kt`
3. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/DetailActivity.kt`

Those three files cover most of the requirements:

- Compose UI
- screen structure
- list layout
- reusable components
- two activities
- passing data
- editable form controls
- dynamic UI updates

## Easy Customization Ideas

If the midterm uses a different topic instead of candy, you can keep the same structure and just rename the domain.

Examples:

- candy to movie
- candy to book
- candy to pet
- candy to course
- candy to product

Usually you would only need to change:

- the data class name
- the field names
- the option lists
- the strings shown on screen
- the sample data

## Lower Priority Files

These files matter, but they are not the first ones to memorize:

- `ui/theme/Color.kt`
- `ui/theme/Theme.kt`
- `ui/theme/Type.kt`
- resource files such as drawables and strings


## Important Notes

- The app uses real Android activities, not Navigation Compose.
- The home screen and detail screen are fully wired together.
- The create screen exists as a scaffold, but it is intentionally not linked from the home screen yet.
- The repository is in-memory, so it behaves like a simple fake database for practice.
- The provider file is week 6 material.


