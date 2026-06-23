# Midterm Candy Template Study Guide

This guide summarizes which files to study first in `week07/MidtermCandyTemplate` and what each part is for.

## What To Memorize First

1. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/model/Candy.kt`

This is your data model, form state, option lists, starter data, and validation. If you can recreate this file, you can rebuild the app's data layer quickly.

2. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/model/CandyRepository.kt`
   `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/model/CandyRepositoryImpl.kt`

These are your fake database files. Memorize the pattern:
- `StateFlow<List<Item>>`
- `getById`
- `add`
- `update`

3. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/HomeActivity.kt`

This is the home screen skeleton: `Scaffold`, reusable top bar, `Box`, `Column`, `Row`, `LazyColumn`, and click-to-open-detail using an `Intent`.

4. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/DetailActivity.kt`

This is the most important exam file. It shows real two-activity navigation, reading `Intent` extras, loading one item, and saving edits.

5. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CandyForm.kt`

This is your control library. It already contains the reusable `Text`, text field, `Button`, image button, `RadioButton`, `Checkbox`, segmented button, and `Image` blocks with comments above them.

6. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/viewmodel/DetailViewModel.kt`

This is the MVVM glue. Memorize the pattern:
- load item into form state
- expose `StateFlow`
- update fields with small functions
- validate
- save

7. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CommonTopBar.kt`

This is a quick reusable app bar you can drop into any screen.

## What Each Part Is For

`week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/HomeActivity.kt`

This is your list screen template. If the midterm asks for displaying items, start here.

`week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/DetailActivity.kt`

This is your open-second-activity-and-pass-data template. The main thing you usually change is the extra name and the item type.

`week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CandyForm.kt`

This is your form field bank. If you freeze during the exam, copy one block at a time from here.

`week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CandyListItem.kt`

This is your one-card-in-a-`LazyColumn` template.

`week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/CreateCandyActivity.kt`

This is lower priority. Study it only after the first five files. It is there so you can quickly add a third screen if needed.

`week07/MidtermCandyTemplate/app/src/main/AndroidManifest.xml`

This is what makes the activities real. If you forget to register an activity, navigation will fail.

## Fast Rebuild Order

If you had to rebuild this from scratch in the exam, do it in this order:

1. Create the data class and option lists in `Candy.kt`.
2. Build the repository interface and implementation.
3. Make `HomeActivity` with a `LazyColumn`.
4. Make `DetailActivity` and pass the item id with an `Intent`.
5. Add the reusable form component.
6. Add the ViewModels and factories.
7. Add the reusable top bar.
8. Register activities in the manifest.
9. Style it with the theme last.

## If You Only Memorize Three Things

Memorize these three files first:

1. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/components/CandyForm.kt`
2. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/HomeActivity.kt`
3. `week07/MidtermCandyTemplate/app/src/main/java/com/example/midtermcandytemplate/view/DetailActivity.kt`

Those three files cover almost everything in the review sheet:
- Compose UI
- list layout
- alignment
- reusable components
- two activities
- passing data
- editing data
- dynamic updates

## Notes

- The template project lives in `week07/MidtermCandyTemplate`.
- The app uses real Android activities and `Intent` extras.
- The optional create activity exists, but it is not linked from the home screen yet.
- The project was not fully build-verified here because this machine still needs an Android SDK path configured for the project.
