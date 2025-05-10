package com.example.test


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun GenerateListView(
    modifier: Modifier,

) {
    val myViewModel: TaskViewModel = viewModel()
    val myList = myViewModel.tasks
    LazyColumn {
        items(myList, key = { tasks -> tasks.id }) { tasks ->
            TaskView(modifier = modifier, onClosed = { myViewModel.removeList(tasks) }, tasks.label)
        }
    }
}


@Composable
fun TaskView(modifier: Modifier = Modifier, onClosed: () -> Unit, text: String) {
    var onCheckt by remember { mutableStateOf(false) }
    StateLessTaskView(
        modifier = modifier,
        text = text,
        onChecked = onCheckt,
        onCheckChanged = { onCheckt = !onCheckt },
        onClosed = onClosed
    )
}

@Composable
fun StateLessTaskView(
    modifier: Modifier = Modifier,
    text: String,
    onChecked: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    onClosed: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text, modifier = modifier.weight(1f))
        Checkbox(checked = onChecked, onCheckedChange = onCheckChanged)
        IconButton(onClick = onClosed) { Icon(Icons.Filled.Close, contentDescription = "Close") }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF, // white
    widthDp = 350,
    heightDp = 600
)
@Composable
fun PreviewTaskView() {
    GenerateListView(modifier = Modifier)
}

