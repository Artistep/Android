package com.artistep.artistep.page2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.artistep.artistep.R
import com.artistep.artistep.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.page2SearchEt.addTextChangedListener {
            if (it!!.equals(getString(R.string.page2_edittext1)) || it!!.isNullOrEmpty()) {
                binding.page2SearchTvTemp.visibility = View.VISIBLE
            }else{
                binding.page2SearchTvTemp.visibility = View.GONE
            }
        }

        binding.page2SearchBtnBack.setOnClickListener {
            finish()
        }

    }
}


