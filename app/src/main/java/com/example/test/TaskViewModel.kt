package com.example.test

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel(){
    private val _tasks = getData().toMutableStateList()
    val tasks:  List<DataModel>  get()= _tasks

    fun removeList(item: DataModel){
        _tasks.remove(item)
    }
}

private  fun getData() = List (30){ i-> DataModel(i, "List data # $i") }