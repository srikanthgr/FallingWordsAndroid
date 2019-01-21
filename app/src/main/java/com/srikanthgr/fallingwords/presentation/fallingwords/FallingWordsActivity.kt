package com.srikanthgr.fallingwords.presentation.fallingwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.srikanthgr.fallingwords.R

/**
 * FallingWords activity which inflates the {@link FallingWordsFragment}.
 */
class FallingWordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_content, FallingWordsFragment())
                .commit()
        }
    }
}
