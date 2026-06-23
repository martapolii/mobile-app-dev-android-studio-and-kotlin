package com.example.martapolishchuk_comp304lab2_ex1.screens.create

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab2_ex1.data.careerCategories
import com.example.martapolishchuk_comp304lab2_ex1.data.careerStatuses
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CareerItemForm
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.validateCareerItemInput

// Marta Polishchuk - 301432299

// access the CreateCareerItemViewModel from this screen-level composable:
@Composable
fun CreateCareerItemScreen(
    onBackClick: () -> Unit,
    viewModel: CreateCareerItemViewModel
){
    // screen-level state stays in the composable, save logic stays in the viewmodel
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf(careerCategories.first()) }
    var startDate by rememberSaveable { mutableStateOf("") }
    var targetCompletionDate by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf(careerStatuses.first()) }
    var progressPercentage by rememberSaveable { mutableStateOf("0") }
    var validationMessage by rememberSaveable { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Create Career Item",
                onBackClick = onBackClick
            )
        } // topBar

    ) { paddingValues ->
        CareerItemForm(
            title = title,
            onTitleChange = { title = it },
            description = description,
            onDescriptionChange = { description = it },
            category = category,
            categoryOptions = careerCategories,
            onCategoryChange = { category = it },
            startDate = startDate,
            onStartDateChange = { startDate = it },
            targetCompletionDate = targetCompletionDate,
            onTargetCompletionDateChange = { targetCompletionDate = it },
            status = status,
            statusOptions = careerStatuses,
            onStatusChange = { status = it },
            progressPercentage = progressPercentage,
            onProgressPercentageChange = { progressPercentage = it },
            validationMessage = validationMessage,
            saveButtonText = "save career item",
            onSaveClick = {
                validationMessage = validateCareerItemInput(
                    title = title,
                    description = description,
                    startDate = startDate,
                    targetCompletionDate = targetCompletionDate,
                    progressPercentage = progressPercentage
                )

                if (validationMessage == null) {
                    viewModel.addCareerItem(
                        title = title,
                        description = description,
                        category = category,
                        startDate = startDate,
                        targetCompletionDate = targetCompletionDate,
                        status = status,
                        progressPercentage = progressPercentage.toInt()
                    )
                    onBackClick()
                }
            },
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}
