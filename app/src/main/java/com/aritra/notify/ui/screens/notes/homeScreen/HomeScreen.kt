@file:OptIn(ExperimentalMaterial3Api::class)

package com.aritra.notify.ui.screens.notes.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection.*
import androidx.compose.material3.DismissValue.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aritra.notify.R
import com.aritra.notify.components.actions.LayoutToggleButton
import com.aritra.notify.components.actions.NoList
import com.aritra.notify.components.actions.SwipeDelete
import com.aritra.notify.components.dialog.TextDialog
import com.aritra.notify.components.note.GridNoteCard
import com.aritra.notify.components.note.NotesCard
import com.aritra.notify.components.topbar.TopBar
import com.aritra.notify.data.models.Note
import com.aritra.notify.utils.Const
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun HomeScreen(
    onFabClicked: () -> Unit,
    navigateToUpdateNoteScreen: (noteId: Int) -> Unit
) {

    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val listOfAllNotes by viewModel.listOfNotes.observeAsState(listOf())
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isGridView by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFabClicked() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        },
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                SearchBar(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(10.dp),
                    query = searchQuery,
                    onQueryChange = { search ->
                        searchQuery = search
                    },
                    onSearch = {
                        isGridView = !isGridView
                    },
                    active = false,
                    onActiveChange = {},
                    placeholder = { Text(stringResource(R.string.search_your_notes)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                modifier = Modifier.clickable { searchQuery = "" },
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        } else {
                            LayoutToggleButton(
                                isGridView = isGridView,
                                onToggleClick = { isGridView = !isGridView }
                            )
                        }
                    }
                ) {
                }
                if (listOfAllNotes.isNotEmpty()) {

                    if (isGridView) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, 5.dp, 0.dp, 0.dp),
                        ) {
                            itemsIndexed(listOfAllNotes.filter { note ->
                                note.title.contains(searchQuery, true)
                            }) { _, notesModel ->
                                GridNoteCard(notesModel, viewModel, navigateToUpdateNoteScreen, isGridView)
                            }
                        }
                    } else {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, 5.dp, 0.dp, 0.dp),
                        ) {

                            items(listOfAllNotes.filter { note ->
                                note.title.contains(searchQuery, true)
                            }) { notesModel ->
                                SwipeDelete(notesModel, viewModel, navigateToUpdateNoteScreen)
                            }
                        }
                    }
                } else {
                    NoList(
                        contentDescription = stringResource(R.string.no_notes_added),
                        message = stringResource(R.string.click_on_the_compose_button_to_add)
                    )
                }
            }
        }
    }
}