package com.example.android.testing.espresso.fragmentscenario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * A simple [DialogFragment] subclass.
 */
class SampleDialogFragment : DialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_sample, container, false)

}
