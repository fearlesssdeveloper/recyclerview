package br.com.alura.ceep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Nota(val titulo: String, val descricao: String) : Parcelable