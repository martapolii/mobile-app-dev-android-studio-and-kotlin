package com.example.martapolishchuk_comp304lab2_ex1.screens.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.careerCategories
import com.example.martapolishchuk_comp304lab2_ex1.data.careerStatuses
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CareerItemForm
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.validateCareerItemInput

// Marta Polishchuk - 301432299

/*
VIEW/EDIT CAREER ITEM ---------------------------------------------------------------------------
- opens when career item is clicked on Home Screen
- pre-populated text fields
- button: Save - saves changes & returns to Home Screen

 */

@Composable
fun EditCareerItemScreen(
    careerItem: CareerItem,
    onBackClick: () -> Unit,
    viewModel: EditCareerItemViewModel
){
    var title by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.title) }
    var description by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.description) }
    var category by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.category) }
    var startDate by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.startDate) }
    var targetCompletionDate by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.targetCompletionDate) }
    var status by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.status) }
    var progressPercentage by rememberSaveable(careerItem.id) { mutableStateOf(careerItem.progressPercentage.toString()) }
    var validationMessage by rememberSaveable(careerItem.id) { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "View / Edit Career Item",
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
            saveButtonText = "save updates",
            onSaveClick = {
                validationMessage = validateCareerItemInput(
                    title = title,
                    description = description,
                    startDate = startDate,
                    targetCompletionDate = targetCompletionDate,
                    progressPercentage = progressPercentage
                )

                if (validationMessage == null) {
                    viewModel.updateCareerItem(
                        id = careerItem.id,
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
