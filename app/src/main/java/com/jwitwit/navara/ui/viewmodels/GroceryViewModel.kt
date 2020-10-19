package com.jwitwit.navara.ui.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jwitwit.navara.models.GroceryItem
import com.jwitwit.navara.util.Resource

class GroceryViewModel: ViewModel() {

    val groceryItem = MutableLiveData<String>("")
    val isUnique = MutableLiveData<Boolean>(false)
    val isCbEnabled = MutableLiveData<Boolean>(true)
    val singleLiveEvent: MutableLiveData<Resource<String>> = MutableLiveData()
    var groceryList = mutableListOf<GroceryItem>()

    fun insertItem() {
        val item = groceryItem.value
        if (item != null) {
            when(isUnique.value) {
                false -> {
                    addGroceryItemToList(item)
                }
                true -> {
                    if(groceryList.any { groceryItem -> groceryItem.name == item }) {
                        singleLiveEvent.postValue(Resource.Error("The submitted form is invalid"))
                    } else {
                        addGroceryItemToList(item)
                    }
                }
            }
        }
        groceryItem.value = ""

        if(!checkListForDuplicates()) {
            isCbEnabled.postValue(false)
        }
    }

    fun resetList() {
        groceryList.clear()
        singleLiveEvent.postValue(Resource.Clear())
        isCbEnabled.postValue(true)
    }

    private fun addGroceryItemToList(item: String) {
        groceryList.add(
            GroceryItem(item)
        )
        singleLiveEvent.postValue(Resource.Insert(item))
    }

    private fun checkListForDuplicates(): Boolean {
        return groceryList.size == groceryList.distinct().count()
    }

}