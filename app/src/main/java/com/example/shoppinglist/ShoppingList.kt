package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)



@Composable
fun Test(){
    var itemList by remember{ mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember{ mutableStateOf(false) }
    var itemName by remember{ mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }

  Column{
      Text(
          text = "List",
          modifier = Modifier.padding(16.dp),
          style = MaterialTheme.typography.headlineLarge,
          fontFamily = FontFamily.Default,
          textAlign = TextAlign.Center
      )
      LazyColumn(
          modifier = Modifier
              .fillMaxSize()
              .padding(16.dp)
      ){
          items(itemList){
              ShoppingListItem(
                  item = it,
                  onEditClick = { /*TODO*/ },
                  onDeleteClick = {/*TODO*/}
              )
          }
      }
  }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        FloatingActionButton(
            onClick = { showDialog = true },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }


    //POPUP
    if(showDialog){
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = {
                        if(itemName.isNotBlank()){
                            val newItem = ShoppingItem(
                                id = itemList.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            itemList = itemList + newItem
                            showDialog = false
                            itemName = ""
                        }
                    }) {
                        Text(text = "Add")
                    }
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = { Text(text = "Add Item")},
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
            )
    }

}

@Composable
fun ShoppingListItem(
    item : ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit" )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete" )
            }
        }

    }

}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit){
    var editedName by remember{ mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember{ mutableStateOf(item.isEditing) }
}