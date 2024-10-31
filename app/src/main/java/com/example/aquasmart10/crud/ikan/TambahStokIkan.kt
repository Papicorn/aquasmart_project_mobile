package com.example.aquasmart10.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aquasmart10.R
import com.example.aquasmart10.Routes
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahStokIkan(navController: NavController) {
    val customFontFamily = FontFamily(
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.regular, FontWeight.Normal)
    )

    val list = listOf("Kolam A-A1", "Kolam B-B1")
    var selectedText by remember { mutableStateOf("-- Pilih Kolam --") }
    var isExpanded by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("") }
    var submittedValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var intValue by remember { mutableStateOf("") }
    var intValueLampiran by remember { mutableStateOf("") }
    var intValueNilaHitam by remember { mutableStateOf("") }

    // Data Pickers
    var showDatePickerTebar by remember { mutableStateOf(false) }
    var showDatePickerPanen by remember { mutableStateOf(false) }
    var selectedDateTebar by remember { mutableStateOf("") }
    var selectedDatePanen by remember { mutableStateOf("") }
    var selectedDateTebarTambah by remember { mutableStateOf<Long?>(null) }
    var selectedDatePanenTambah by remember { mutableStateOf<Long?>(null) }

    // Card Berat g/kg/ton
    val listSatuan = listOf("G", "KG", "TON")
    var selectedTextBerat by remember { mutableStateOf(listSatuan[1]) }
    var isExpandedSatuan by remember { mutableStateOf(false) }
    var intValueBerat by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFEFF6FC),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Stok Ikan Kolam A-A1",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp, start = 25.dp)
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                item {
                    // Dropdown Kolam
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            expanded = isExpanded,
                            onExpandedChange = { isExpanded = !isExpanded }
                        ) {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                value = selectedText,
                                onValueChange = {},
                                readOnly = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White,
                                ),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                }
                            )
                            ExposedDropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                list.forEachIndexed { index, text ->
                                    DropdownMenuItem(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = {
                                            Text(
                                                text = text,
                                                fontSize = 14.sp,
                                                fontFamily = customFontFamily
                                            )
                                        },
                                        onClick = {
                                            selectedText = list[index]
                                            isExpanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tanggal Tebar
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable { showDatePickerTebar = true },
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (selectedDateTebar.isEmpty()) "Tanggal Tebar" else selectedDateTebar,
                                fontFamily = customFontFamily,
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = { showDatePickerTebar = true },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CalendarMonth,
                                    contentDescription = "Edit",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    // Jadi disini tanggal tebar gabisa duluan dibanding tanggal panen
                    if (showDatePickerTebar) {
                        DatePickerTambah(
                            onDateSelected = { dateInMillis ->
                                dateInMillis?.let {
                                    if (selectedDatePanenTambah != null && it > selectedDatePanenTambah!!) {
                                        // Beri pesan kesalahan atau peringatan kepada pengguna
                                    } else {
                                        selectedDateTebarTambah = it
                                        selectedDateTebar = SimpleDateFormat(
                                            "dd/MM/yyyy",
                                            Locale.getDefault()
                                        ).format(it)
                                    }
                                }
                                showDatePickerTebar = false
                            },
                            onDismiss = { showDatePickerTebar = false }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tanggal Panen
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable { showDatePickerPanen = true },
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (selectedDatePanen.isEmpty()) "Tanggal Panen" else selectedDatePanen,
                                fontFamily = customFontFamily,
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = { showDatePickerPanen = true },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CalendarMonth,
                                    contentDescription = "Edit",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    //Tanggal tebar harus duluan, gaboleh tanggal panen duluan
                    if (showDatePickerPanen) {
                        DatePickerTambah(
                            onDateSelected = { dateInMillis ->
                                dateInMillis?.let {
                                    if (selectedDateTebarTambah != null && it < selectedDateTebarTambah!!) {
                                        // Beri pesan kesalahan atau peringatan kepada pengguna
                                    } else {
                                        selectedDatePanenTambah = it
                                        selectedDatePanen = SimpleDateFormat(
                                            "dd/MM/yyyy",
                                            Locale.getDefault()
                                        ).format(it)
                                    }
                                }
                                showDatePickerPanen = false
                            },
                            onDismiss = { showDatePickerPanen = false }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    //Hanya bisa input angka
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        TextField(
                            value = intValue,
                            onValueChange = { newText ->
                                // hanya angka yang bisa
                                if (newText.all { it.isDigit() }) {
                                    intValue = newText
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(
                                    "Nila Merah",
                                    fontFamily = customFontFamily,
                                    fontSize = 14.sp,
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    submittedValue = textValue  // Simpan nilai saat enter ditekan
                                    focusManager.clearFocus() //ini tu kalo misal udah selesai ngetik trus tekan enter keyboardnya ilang gitu
                                    // Di sini nambahin logika lain yang dibutuhkan
                                    // seperti menyimpan ke database atau memproses nilai
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    //Hanya bisa input angka
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        TextField(
                            value = intValueNilaHitam,
                            onValueChange = { newText ->
                                // hanya angka yang bisa
                                if (newText.all { it.isDigit() }) {
                                    intValueNilaHitam = newText
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(
                                    "Nila Hitam",
                                    fontFamily = customFontFamily,
                                    fontSize = 14.sp,
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    submittedValue = textValue  // Simpan nilai saat enter ditekan
                                    focusManager.clearFocus() //ini tu kalo misal udah selesai ngetik trus tekan enter keyboardnya ilang gitu
                                    // Di sini nambahin logika lain yang dibutuhkan
                                    // seperti menyimpan ke database atau memproses nilai
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(7f)
                                .height(50.dp)
                        ) {
                            // Konten Card pertama
                            TextField(
                                value = intValueBerat,
                                onValueChange = { newText ->
                                    // hanya angka yang bisa
                                    if (newText.all { it.isDigit() }) {
                                        intValueBerat = newText
                                    }
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                placeholder = {
                                    Text(
                                        "Berat",
                                        fontFamily = customFontFamily,
                                        fontSize = 14.sp,
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        submittedValue = textValue  // Simpan nilai saat enter ditekan
                                        focusManager.clearFocus() //ini tu kalo misal udah selesai ngetik trus tekan enter keyboardnya ilang gitu
                                        // Di sini nambahin logika lain yang dibutuhkan
                                        // seperti menyimpan ke database atau memproses nilai
                                    }
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(3f)
                                .height(50.dp)
                        ) {
                            ExposedDropdownMenuBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                expanded = isExpandedSatuan,
                                onExpandedChange = { isExpandedSatuan = !isExpandedSatuan }
                            ) {
                                TextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth(),
                                    value = selectedTextBerat,
                                    onValueChange = {},
                                    readOnly = true,
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.White,
                                        focusedContainerColor = Color.White,
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSatuan)
                                    }
                                )
                                ExposedDropdownMenu(
                                    expanded = isExpandedSatuan,
                                    onDismissRequest = { isExpandedSatuan = false },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    listSatuan.forEachIndexed { index, text ->
                                        DropdownMenuItem(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = {
                                                Text(
                                                    text = text,
                                                    fontSize = 14.sp,
                                                    fontFamily = customFontFamily
                                                )
                                            },
                                            onClick = {
                                                selectedTextBerat = listSatuan[index]
                                                isExpandedSatuan = false
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ){
                        TextField(
                            value = intValueLampiran,
                            onValueChange = { newText ->
                                intValueLampiran = newText
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(
                                    "Lampiran",
                                    fontFamily = customFontFamily,
                                    fontSize = 14.sp,
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    submittedValue = textValue  // Simpan nilai saat enter ditekan
                                    focusManager.clearFocus() //ini tu kalo misal udah selesai ngetik trus tekan enter keyboardnya ilang gitu
                                    // Di sini nambahin logika lain yang dibutuhkan
                                    // seperti menyimpan ke database atau memproses nilai
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = {
                            navController.navigate(Routes.StokIkanActivity)
                        },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E7BF9))
                        ) {
                            Text("Tambah")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTambah(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}