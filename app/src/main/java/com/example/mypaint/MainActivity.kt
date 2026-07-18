package com.example.mypaint

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Clear
//import androidx.compose.material.icons.filled.Refresh
//import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
/*import androidx.compose.runtime.getValue    // for use by with state for topbar drop-down menu
import androidx.compose.runtime.setValue    // for use by with state for topbar drop-down menu
import androidx.compose.runtime.mutableStateOf  // for use by with state for topbar drop-down menu
import androidx.compose.runtime.remember    // for use by with state for topbar drop-down menu
*/

class MainActivity : ComponentActivity() {

    private lateinit var paintView: PaintView

    // Modern Activity Result API for permission requests
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Access granted.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize PaintView (existing Java view)
        paintView = PaintView(this).apply {
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(metrics)
            init(metrics)
        }

        setContent {
            MaterialTheme {
                // remember drop-down menu state
                //var menuExpanded by remember { mutableStateOf(false) }
                var filterMenuExpanded by remember { mutableStateOf(false) }
                var actionMenuExpanded by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("MyPaint (M3 + Compose)") },
                            actions = {
                                IconButton(onClick = { paintView.undo() }) {
                                    Icon(Icons.Default.Refresh, contentDescription = "Undo")
                                }
                                /*IconButton(onClick = { paintView.clear() }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                                }*/
                                // Filters Menu
                                Box {
                                    //IconButton(onClick = { menuExpanded = true }) {
                                    IconButton(onClick = { filterMenuExpanded = true }) {
                                        Icon(Icons.Default.Settings, contentDescription = "Filters")
                                    }
                                    DropdownMenu(
                                        //expanded = menuExpanded,
                                        //onDismissRequest = { menuExpanded = false }
                                        expanded = filterMenuExpanded,
                                        onDismissRequest = { filterMenuExpanded = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Normal") },
                                            onClick = {
                                                paintView.normal()
                                                //menuExpanded = false
                                                filterMenuExpanded = false
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Blur") },
                                            onClick = {
                                                paintView.blur()
                                                //menuExpanded = false
                                                filterMenuExpanded = false

                                            }
                                        )
                                    }
                                }
                                // Actions Menu (Clear and Save)
                                Box {
                                    IconButton(onClick = { actionMenuExpanded = true }) {
                                        Icon(Icons.Default.MoreVert, contentDescription = "Actions")
                                    }
                                    DropdownMenu(
                                        expanded = actionMenuExpanded,
                                        onDismissRequest = { actionMenuExpanded = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Save") },
                                            leadingIcon = { Icon(Icons.Default.Check, contentDescription = null) },
                                            onClick = {
                                                DrawingStorageManager.saveDrawing(this@MainActivity, paintView.getPaths())
                                                Toast.makeText(this@MainActivity, "Drawing saved!", Toast.LENGTH_SHORT).show()
                                                actionMenuExpanded = false
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Load") },
                                            leadingIcon = { Icon(Icons.Default.Check, contentDescription = null) },
                                            onClick = {
                                                //DrawingStorageManager.loadDrawing(this@MainActivity)
                                                paintView.setPaths(DrawingStorageManager.loadDrawing(this@MainActivity))
                                                actionMenuExpanded = false
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Clear") },
                                            leadingIcon = { Icon(Icons.Default.Clear, contentDescription = "Clear") },
                                            onClick = {
                                                paintView.clear()
                                                actionMenuExpanded = false
                                            }
                                        )
                                    }
                                }
                            }


                        )
                    }
                ) { innerPadding ->
                    // Bridging the Java View into Compose
                    AndroidView(
                        factory = { paintView },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        checkReadExternalStoragePermission()
    }

    private fun checkReadExternalStoragePermission() {
        /*when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permission already granted
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Toast.makeText(this, "App needs to view thumbnails.", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}
